package com.example.app_qr;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RadioButton;

import com.example.app_qr.Adapters.MyPagerAdapter;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;
import com.google.android.material.tabs.TabLayout;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private Button start;
    private TextView countDown;
    private static long startTimeInMilis = 7000;//1200000
    private CountDownTimer mcountDownTimer;
    private boolean butonStart = false;

    ViewPager viewPager;

    //

    private CameraSource fuenteCamara;
    private SurfaceView visorCamara;
    private final int MY_PERMISSIONS_REQUEST_CAMERA = 1;
    private String token = "";
    private String tokenanterior = "";
    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        visorCamara = (SurfaceView) findViewById(R.id.visor);
        lista = findViewById(R.id.lista);
        iniciaQR();
        listadoDeTiempo();

        viewPager = findViewById(R.id.pager);

        MyPagerAdapter myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(myPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        start = (Button)findViewById(R.id.inicio);
        countDown = (TextView)findViewById(R.id.cronometro);

        viewButtonStart();

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start.setEnabled(false);
                startCountDownTime();
            }
        });


    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.req0:
                if (checked)
                    // Pirates are the best
                    break;
            case R.id.req1:
                if (checked)
                    // Ninjas rule
                    break;
            case R.id.req2:
                if (checked)
                    // Ninjas rule
                    break;
        }

    }

    private void viewButtonStart() {//Este metodo nos deshabilita el boton start cuando pulsamos en el para no poder modificar el tiempo del crono
        if (!butonStart){
            start.setEnabled(true);
        }else {
            start.setEnabled(false);
        }
    }

    private void startCountDownTime() {//Este metodo inicia la cuenta atras
        mcountDownTimer = new CountDownTimer(startTimeInMilis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                startTimeInMilis = millisUntilFinished;
                updateTime();
            }

            @Override
            public void onFinish() {
                Toast.makeText(getApplicationContext(), "TIEMPO TERMINADO", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(), GameOver.class);
                startActivity(intent);
            }
        }.start();
    }

    private void updateTime() {//Este metodo actualiza el texto del cronometro
        int minutos = (int) (startTimeInMilis / 1000) / 60;
        int segundos = (int) (startTimeInMilis / 1000) % 60;

        String textFormater = String.format(Locale.getDefault(), "%02d:%02d", minutos, segundos);

        countDown.setText(textFormater);
    }

    public void iniciaQR() {


        //PREPARACIÓN QR
        BarcodeDetector barcodeDetector =
                new BarcodeDetector.Builder(this)
                        .setBarcodeFormats(Barcode.ALL_FORMATS)
                        .build();

        //INICIALIZAIÓN DE CÁMARA
        fuenteCamara = new CameraSource
                .Builder(this, barcodeDetector)
                .setRequestedPreviewSize(1600, 1024)
                .setAutoFocusEnabled(true)
                .build();

        //REGISTRADOR DE IMAGEN
        visorCamara.getHolder().addCallback(new SurfaceHolder.Callback() {
            /*
             * El holder es como ponerle una pantalla a una pared para q el visor camara acepte lo proyectado q capta por la camara
             * */
            @Override
            public void surfaceCreated(SurfaceHolder holder) {

                //CHEQUEO DE PERMISO
                if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (shouldShowRequestPermissionRationale(
                                Manifest.permission.CAMERA)) ;
                        requestPermissions(new String[]{Manifest.permission.CAMERA},
                                MY_PERMISSIONS_REQUEST_CAMERA);
                    }
                    return;
                } else {
                    try {
                        fuenteCamara.start(visorCamara.getHolder());
                    } catch (IOException ie) {
                        Log.e("TOKEN", ie.getMessage());

                    }
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                fuenteCamara.stop();
            }
        });

        //ACTIVACIÓN LECTOR QR
        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {
            }


            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {
                final SparseArray<Barcode> barcodes = detections.getDetectedItems();

                if (barcodes.size() > 0) {

                    //OBTENIÓN DEL TOKEN
                    token = barcodes.valueAt(0).displayValue.toString();

                    //VERIFICA NUEVA LECTURA
                    if (!token.equals(tokenanterior)) {

                        //SE GUARDA LECTURA
                        tokenanterior = token;
                        Log.i("TOKEN", token);

                        if (URLUtil.isValidUrl(token)) {
                            //TOKEN URL
                            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(token));
                            startActivity(browserIntent);

                        } else {
                            //TOQUEN NO URL
                            Intent shareIntent = new Intent();
                            shareIntent.setAction(Intent.ACTION_SEND);
                            shareIntent.putExtra(Intent.EXTRA_TEXT, token);
                            shareIntent.setType("text/plain");
                            startActivity(shareIntent);
                        }

                        new Thread(new Runnable() {
                            public void run() {
                                try {
                                    synchronized (this) {
                                        wait(5000);
                                        //LIMPIEZA DE TOKEN
                                        tokenanterior = "";
                                    }
                                } catch (InterruptedException e) {
                                    // TODO Auto-generated catch block
                                    Log.e("Error", "ERROR DETECTADO!!");
                                    e.printStackTrace();
                                }
                            }
                        }).start();

                    }
                }
            }
        });

    }
    public void listadoDeTiempo(){
        // ArrayList<Encapsulador> datos = new ArrayList<Encapsulador>();
        ArrayList<String> datos = new ArrayList<String>();
        //datos.add(new Encapsulador(R.mipmap.ic_launcher, "PREGUNTA", "Tiempo que has tardado"));
        datos.add("En la pregunta 1 has tardado");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, datos);
        lista.setAdapter(adapter);
    }


}