package com.example.app_qr;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

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

        nombre.setText(Login.jugador1);
        apellidos.setText(Login.jugador2);
        curso.setText(Login.grupoStr);
        tiempo.setText(Criptex.textFormater);

    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        AlertDialog.Builder builder = new AlertDialog.Builder(Finish.this);
        builder.setTitle("¡¡¡GRACIAS POR PARTICIPAR!!!");

        builder.setPositiveButton("Salir", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finishAffinity();
                System.exit(0);
            }
        });

        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

}