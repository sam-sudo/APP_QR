package com.example.app_qr;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.webkit.URLUtil;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.app_qr.Auxiliar.AskAuxiliar;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class App extends AppCompatActivity implements NumberPicker.OnValueChangeListener {

    private TextView countDown;
    private ListView lista;
    private SurfaceView cameraView;
    NumberPicker numberPicker1, numberPicker2, numberPicker3, numberPicker4, numberPicker5, numberPicker6;


    private static long startTimeInMilis = 1200000;//1200000
    private CameraSource cameraSource;
    private final int MY_PERMISSIONS_REQUEST_CAMERA = 1;
    private String token = "";
    private String lastToken = "";
    int endToken = 0;
    private MediaPlayer mp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //startCountDownTime();

        cameraView = (SurfaceView) findViewById(R.id.visor);
        lista = findViewById(R.id.lista);
        countDown = (TextView)findViewById(R.id.cronometro);
        mp = MediaPlayer.create(this,R.raw.numberpicker);
     

        initQR();
        TimeList();
        numberPicker();
        AskAuxiliar.randomCode();




    }


    private void numberPicker() {
        numberPicker1 = (NumberPicker)findViewById(R.id.numberPicker1);
        numberPicker2 = (NumberPicker)findViewById(R.id.numberPicker2);
        numberPicker3 = (NumberPicker)findViewById(R.id.numberPicker3);
        numberPicker4 = (NumberPicker)findViewById(R.id.numberPicker4);
        numberPicker5 = (NumberPicker)findViewById(R.id.numberPicker5);
        numberPicker6 = (NumberPicker)findViewById(R.id.numberPicker6);

        numberPicker1.setMaxValue(9);
        numberPicker2.setMaxValue(9);
        numberPicker3.setMaxValue(9);
        numberPicker4.setMaxValue(9);
        numberPicker5.setMaxValue(9);
        numberPicker6.setMaxValue(9);

        numberPicker1.setOnValueChangedListener(this);
        numberPicker2.setOnValueChangedListener(this);
        numberPicker3.setOnValueChangedListener(this);
        numberPicker4.setOnValueChangedListener(this);
        numberPicker5.setOnValueChangedListener(this);
        numberPicker6.setOnValueChangedListener(this);

    }

    public void validateNumberPicker(){

        if (    numberPicker1.getValue() == AskAuxiliar.randomList.get(0)&&
                numberPicker2.getValue() == AskAuxiliar.randomList.get(1)&&
                numberPicker3.getValue() == AskAuxiliar.randomList.get(2)&&
                numberPicker4.getValue() == AskAuxiliar.randomList.get(3)&&
                numberPicker5.getValue() == AskAuxiliar.randomList.get(4)&&
                numberPicker6.getValue() == AskAuxiliar.randomList.get(5) ){

            Intent poema = new Intent(this, Poem.class);
            startActivity(poema);
        }
    }


    private void startCountDownTime() {//Este metodo inicia la cuenta atras
        CountDownTimer mcountDownTimer = new CountDownTimer(startTimeInMilis, 1000) {
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



    public void initQR() {

        // creo el detector qr
        BarcodeDetector barcodeDetector =
                new BarcodeDetector.Builder(this)
                        .setBarcodeFormats(Barcode.ALL_FORMATS)
                        .build();

        // creo la camara
        cameraSource = new CameraSource
                .Builder(this, barcodeDetector)
                .setRequestedPreviewSize(1600, 1024)
                .setAutoFocusEnabled(true) //you should add this feature
                .build();

        // listener de ciclo de vida de la camara
        cameraView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {

                // verifico si el usuario dio los permisos para la camara
                if (ActivityCompat.checkSelfPermission(App.this, Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        // verificamos la version de ANdroid que sea al menos la M para mostrar
                        // el dialog de la solicitud de la camara
                        if (shouldShowRequestPermissionRationale(
                                Manifest.permission.CAMERA)) ;
                        requestPermissions(new String[]{Manifest.permission.CAMERA},
                                MY_PERMISSIONS_REQUEST_CAMERA);
                    }
                    return;
                } else {

                    try {

                        cameraSource.start(cameraView.getHolder());
                    } catch (IOException ie) {
                        Log.e("CAMERA SOURCE", ie.getMessage());
                    }
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                cameraSource.stop();
            }
        });

        // preparo el detector de QR
        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {
            }


            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {
                final SparseArray<Barcode> barcodes = detections.getDetectedItems();

                if (barcodes.size() > 0) {

                    // obtenemos el token
                    token = barcodes.valueAt(0).displayValue.toString();

                    // verificamos que el token anterior no se igual al actual
                    // esto es util para evitar multiples llamadas empleando el mismo token
                    if (!token.equals(lastToken)) {

                        // guardamos el ultimo token proceado
                        lastToken = token;
                        Log.i("token", token);
                        Log.i("lat token", lastToken);

                        if(continueWhithTheOrder(token)){
                            Intent intent = new Intent(getApplicationContext(), Ask_activity.class);
                            startActivity(intent);
                        }else {
                            Log.d("prueba", "hemos entrado");
                            Toast.makeText(getApplicationContext(), "No es el codigo QR que te toca", Toast.LENGTH_SHORT).show();}

                        new Thread(new Runnable() {
                            public void run() {
                                try {
                                    synchronized (this) {
                                        wait(5000);
                                        // limpiamos el token
                                        lastToken = "";
                                    }
                                } catch (InterruptedException e) {
                                    // TODO Auto-generated catch block
                                    Log.e("Error", "Waiting didnt work!!");
                                    e.printStackTrace();
                                }
                            }
                        }).start();

                    }
                }
            }
        });

    }
    public void TimeList(){
        // ArrayList<Encapsulador> datos = new ArrayList<Encapsulador>();
        ArrayList<String> datos = new ArrayList<String>();
        //datos.add(new Encapsulador(R.mipmap.ic_launcher, "PREGUNTA", "Tiempo que has tardado"));
        datos.add("Código 1 --> 8");
        datos.add("Código 1 --> 3");
        datos.add("Código 1 --> 1");
        datos.add("Código 1 --> 8");
        datos.add("Código 1 --> 3");
        datos.add("Código 1 --> 1");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, datos);
        lista.setAdapter(adapter);
    }


    @Override
    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
        mp.start();
        validateNumberPicker();


    }

    boolean continueWhithTheOrder(String token){

        if(endToken + 1 == Integer.parseInt(token))
        {
            endToken = Integer.parseInt(token);
            return true;
        }
        return false;
    }
}