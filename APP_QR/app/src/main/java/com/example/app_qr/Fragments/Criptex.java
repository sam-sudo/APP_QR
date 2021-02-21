package com.example.app_qr.Fragments;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.example.app_qr.Ask_activity;
import com.example.app_qr.Auxiliar.AskAuxiliar;
import com.example.app_qr.GameOver;
import com.example.app_qr.Poem;
import com.example.app_qr.R;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import static com.example.app_qr.Auxiliar.AskAuxiliar.randomList;

public class Criptex extends Fragment implements NumberPicker.OnValueChangeListener {
    private TextView countDown;
    public static ListView lista;
    private SurfaceView cameraView;
    NumberPicker numberPicker1, numberPicker2, numberPicker3, numberPicker4, numberPicker5, numberPicker6;
    public static String textFormater = "";


    public static long startTimeInMilis;//1200000
    static List<Integer> circuitList;
    public static Set<Integer> circuit = new HashSet<Integer>();
    private CameraSource cameraSource;
    private final int MY_PERMISSIONS_REQUEST_CAMERA = 1;
    private String token = "";
    private String lastToken = "";
    private MediaPlayer mp;
    ArrayAdapter<String> adapter;
    static int contadorTokens = 0;
    int oneTine = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.criptex , container ,false);
        startTimeInMilis = 1500000;
        startCountDownTime();


        cameraView = (SurfaceView) view.findViewById(R.id.visor);
        lista = view.findViewById(R.id.lista);
        countDown = (TextView)view.findViewById(R.id.cronometro);
        mp = MediaPlayer.create(getContext(),R.raw.numberpicker);
        numberPicker1 = (NumberPicker)view.findViewById(R.id.numberPicker1);
        numberPicker2 = (NumberPicker)view.findViewById(R.id.numberPicker2);
        numberPicker3 = (NumberPicker)view.findViewById(R.id.numberPicker3);
        numberPicker4 = (NumberPicker)view.findViewById(R.id.numberPicker4);
        numberPicker5 = (NumberPicker)view.findViewById(R.id.numberPicker5);
        numberPicker6 = (NumberPicker)view.findViewById(R.id.numberPicker6);


        initQR();
        TimeList();
        numberPicker();
        numberCircuit();
        AskAuxiliar.randomCode();
        return view;
    }



    @Override
    public void onResume() {
        super.onResume();
//        Toast.makeText(getContext(),"resume",Toast.LENGTH_SHORT).show();
        adapter.notifyDataSetChanged();
        oneTine = 0;
        Log.d("failed", "LIMPIANDO" + oneTine);

    }





    private void numberPicker() {


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

        if (    numberPicker1.getValue() == randomList.get(0)&&
                numberPicker2.getValue() == randomList.get(1)&&
                numberPicker3.getValue() == randomList.get(2)&&
                numberPicker4.getValue() == randomList.get(3)&&
                numberPicker5.getValue() == randomList.get(4)&&
                numberPicker6.getValue() == randomList.get(5) ){

            Log.d("validateNumberPicker", "validateNumberPicker: comprobar");
                Intent poema = new Intent(getContext(), Poem.class);
                startActivity(poema);
                onDestroy();



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
                Toast.makeText(getActivity().getApplicationContext(), "TIEMPO TERMINADO", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getContext(), GameOver.class);
                startActivity(intent);
                //onDestroy();
                getActivity().finish();
            }
        }.start();
    }



    private void updateTime() {//Este metodo actualiza el texto del cronometro
        int minutos = (int) (startTimeInMilis / 1000) / 60;
        int segundos = (int) (startTimeInMilis / 1000) % 60;
        textFormater = String.format(Locale.getDefault(), "%02d:%02d", minutos, segundos);

        countDown.setText(textFormater);
    }



    public void initQR() {

        // creo el detector qr
        BarcodeDetector barcodeDetector =
                new BarcodeDetector.Builder(getContext())
                        .setBarcodeFormats(Barcode.ALL_FORMATS)
                        .build();

        // creo la camara
        cameraSource = new CameraSource
                .Builder(getContext(), barcodeDetector)
                .setRequestedPreviewSize(1600, 1024)
                .setAutoFocusEnabled(true) //you should add this feature
                .build();

        // listener de ciclo de vida de la camara
        cameraView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {

                // verifico si el usuario dio los permisos para la camara
                if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA)
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
                        Log.i("last token", lastToken);

                        if(continueWhithTheOrder(token)){

                            Log.d("failed", "receiveDetections: PRE --- INTENT");
                            if(oneTine==0){
                                Log.d("failed", "receiveDetections: INTENT");
                                Intent intent = new Intent(getContext(), Ask_activity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                                oneTine ++;
                            }




                        }else {
                            Log.d("prueba", "hemos entrado");
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getContext(), "No es el codigo QR que te toca", Toast.LENGTH_SHORT).show();
                                }
                            });

                        }

                        new Thread(new Runnable() {
                            public void run() {
                                try {
                                    synchronized (this) {
                                        wait(3000);
                                        // limpiamos el token

                                        lastToken = "";
                                    }
                                } catch (InterruptedException e) {
                                    // TODO Auto-generated catch block
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
        AskAuxiliar.datos.add("CÓDIGOS");
        adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, AskAuxiliar.datos);


        lista.setAdapter(adapter);

    }


    @Override
    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
        mp.start();
        picker.setOnScrollListener(new NumberPicker.OnScrollListener() {
            @Override
            public void onScrollStateChange(NumberPicker view, int scrollState) {
                if(scrollState == SCROLL_STATE_IDLE){
                    Log.d("validateNumberPicker", "validateNumberPicker: comprobar2");

                    picker.postDelayed(() ->validateNumberPicker() ,1000);


                }
            }
        });



    }



    boolean continueWhithTheOrder(String token){


        if(Integer.parseInt(token) == circuitList.get(contadorTokens)){
            Log.d("circuitList", "" + contadorTokens);

            Log.d("circuitList", "" + contadorTokens);
            return true;
        }else{
            return false;
        }


    }

    public static void numberCircuit(){
       while(circuit.size() < 6){
           circuit.add((int) (Math.random() * 6) + 1);
           circuitList = new ArrayList<>(circuit);
           Collections.shuffle(circuitList);
           System.out.println("------------------------------------" + circuitList);

       }
        Log.d("circuitList", "" + circuitList);


    }
//  circuit.add((int) (Math.random()*6)+1);
}