package com.example.app_qr;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import android.widget.Button;

public class StartVideo extends AppCompatActivity {
    private final int MY_PERMISSIONS_REQUEST_CAMERA = 1;
    Button hiddeMe;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video);
        hiddeMe = (Button) findViewById(R.id.end);

        hiddeMe = findViewById(R.id.end);
        hiddeMe.setVisibility(View.INVISIBLE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                hiddeMe.setVisibility(View.VISIBLE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    // verificamos la version de ANdroid que sea al menos la M para mostrar
                    // el dialog de la solicitud de la camara
                    if (shouldShowRequestPermissionRationale(
                            Manifest.permission.CAMERA)) ;
                    requestPermissions(new String[]{Manifest.permission.CAMERA},
                            MY_PERMISSIONS_REQUEST_CAMERA);
                }
                return;
            }
        }, 5000);

        hiddeMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplication(), App.class);
                startActivity(i);
            }
        });


    }


}