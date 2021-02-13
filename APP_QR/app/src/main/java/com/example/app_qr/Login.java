package com.example.app_qr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class Login extends AppCompatActivity {
    EditText nombre, apellidos;
    Spinner grupo;
    Button continuar;

    //static public String[] lista = {"1º Bachiller","2º Bachiller","1º ESO", "2º ESO", "3º ESO", "4º ESO"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        nombre = (EditText) findViewById(R.id.nombre);
        apellidos = (EditText)findViewById(R.id.apellidos);
        grupo = (Spinner)findViewById(R.id.spinner);
        continuar = (Button)findViewById(R.id.continuar);

        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.Spinner_items, R.layout.color_spinner_layout);

        adapter.setDropDownViewResource(R.layout.spinner_dropdown_layout);
        grupo.setAdapter(adapter);

        continuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), StartVideo.class);
                startActivity(intent);
            }
        });
    }
}