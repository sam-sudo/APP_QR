package com.example.app_qr;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    private final int MY_PERMISSIONS_REQUEST_CAMERA = 1;
    EditText player1, player2;
    Spinner grupo;
    Button continuar;
    ImageView imagen;
    TextView selecciona, insta1, insta2, version;
    public static String jugador1, jugador2, grupoStr;

    //static public String[] lista = {"1º Bachiller","2º Bachiller","1º ESO", "2º ESO", "3º ESO", "4º ESO"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        player1 = (EditText) findViewById(R.id.player1);
        player2 = (EditText) findViewById(R.id.player2);
        grupo = (Spinner) findViewById(R.id.spinner);
        continuar = (Button) findViewById(R.id.continuar);
        imagen = (ImageView) findViewById(R.id.imagen);
        selecciona = (TextView) findViewById(R.id.selecciona);
        insta1 = (TextView)findViewById(R.id.insta1);
        insta2 = (TextView)findViewById(R.id.insta2);
        version = (TextView)findViewById(R.id.version);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // verificamos la version de ANdroid que sea al menos la M para mostrar
            // el dialog de la solicitud de la camara
            if (shouldShowRequestPermissionRationale(
                    Manifest.permission.CAMERA)) ;
            requestPermissions(new String[]{Manifest.permission.CAMERA},
                    MY_PERMISSIONS_REQUEST_CAMERA);
        }

        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.Spinner_items, R.layout.color_spinner_layout);

        TranslateAnimation an = new TranslateAnimation(0.0f, 0.0f, -1600.0f, 0.0f);
        an.setDuration(1000);
        imagen.startAnimation(an);

        TranslateAnimation an1 = new TranslateAnimation(-1600.0f, 0.0f, 0.0f, 0.0f);
        an1.setDuration(1000);
        player1.startAnimation(an1);

        TranslateAnimation an3 = new TranslateAnimation(1600.0f, 0.0f, 0.0f, 0.0f);
        an3.setDuration(1000);
        player2.startAnimation(an3);

        TranslateAnimation an4 = new TranslateAnimation(00.0f, 0.0f, 1600.0f, 0.0f);
        an4.setDuration(2000);
        grupo.startAnimation(an4);
        continuar.startAnimation(an4);
        selecciona.startAnimation(an4);
        insta1.startAnimation(an4);
        insta2.startAnimation(an4);
        version.startAnimation(an4);

        adapter.setDropDownViewResource(R.layout.spinner_dropdown_layout);
        grupo.setAdapter(adapter);

        continuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                jugador1 = player1.getText().toString();
                jugador2 = player2.getText().toString();
                grupoStr = grupo.getSelectedItem().toString();
                if (!validate(jugador1, jugador2)) {
                    Intent intent = new Intent(getApplicationContext(), StartVideo.class);
                    startActivity(intent);
                    finish();

                } else {
                    Toast.makeText(getApplicationContext(), "Por favor introduce nombre y apellidos", Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    public boolean validate(String nombre, String apellidos) {
        if (nombre.length() == 0 && apellidos.length() == 0) {
            return true;
        } else {
            return false;
        }
    }


}