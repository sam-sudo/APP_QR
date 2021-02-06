package com.example.app_qr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private Button start;
    private TextView countDown;
    private static long startTimeInMilis = 7000;//1200000
    private CountDownTimer mcountDownTimer;
    private boolean butonStart = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
}