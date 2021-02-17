package com.example.app_qr;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.app_qr.Auxiliar.AskAuxiliar;

public class Login extends AppCompatActivity {
    EditText nombre, apellidos;
    Spinner grupo;
    Button continuar;
    ImageView imagen;
    TextView selecciona;

    //static public String[] lista = {"1º Bachiller","2º Bachiller","1º ESO", "2º ESO", "3º ESO", "4º ESO"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        nombre = (EditText) findViewById(R.id.nombre);
        apellidos = (EditText)findViewById(R.id.apellidos);
        grupo = (Spinner)findViewById(R.id.spinner);
        continuar = (Button)findViewById(R.id.continuar);
        imagen = (ImageView)findViewById(R.id.imagen);
        selecciona = (TextView)findViewById(R.id.selecciona);

        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.Spinner_items, R.layout.color_spinner_layout);

        TranslateAnimation an = new TranslateAnimation(0.0f,  0.0f,  -1600.0f,  0.0f);
        an.setDuration(1000);
        imagen.startAnimation(an);

        TranslateAnimation an1 = new TranslateAnimation(-1600.0f,  0.0f,  0.0f,  0.0f);
        an1.setDuration(1000);
        nombre.startAnimation(an1);

        TranslateAnimation an3 = new TranslateAnimation( 1600.0f,  0.0f,  0.0f,  0.0f);
        an3.setDuration(1000);
        apellidos.startAnimation(an3);

        TranslateAnimation an4 = new TranslateAnimation(00.0f, 0.0f, 1600.0f, 0.0f);
        an4.setDuration(2000);
        grupo.startAnimation(an4);
        continuar.startAnimation(an4);
        selecciona.startAnimation(an4);

        adapter.setDropDownViewResource(R.layout.spinner_dropdown_layout);
        grupo.setAdapter(adapter);

        continuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), StartVideo.class);
                startActivity(intent);
                finish();
            }
        });
    }


}