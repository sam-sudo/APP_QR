package com.example.app_qr;

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
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.app_qr.Fragments.Criptex;

public class Finish extends AppCompatActivity {
    ImageView imagen;
    TextView nombre, apellidos, curso, tiempo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.finish);

        imagen = (ImageView) findViewById(R.id.imagen);
        nombre = (TextView)findViewById(R.id.nombreRespuesta);
        apellidos = (TextView)findViewById(R.id.appellidosRespuesta);
        curso = (TextView)findViewById(R.id.cursoRespuesta);
        tiempo = (TextView)findViewById(R.id.tiempoRespuesta);

        nombre.setText(Login.nombreStr);
        apellidos.setText(Login.apellidosStr);
        curso.setText(Login.grupoStr);
        tiempo.setText(Criptex.textFormater);

    }

}