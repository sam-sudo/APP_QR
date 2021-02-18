package com.example.app_qr;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.app_qr.Fragments.Criptex;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Poem extends AppCompatActivity {
    EditText autor;
    Button enviar;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.poem);

        autor = (EditText)findViewById(R.id.autor);
        enviar = (Button)findViewById(R.id.enviar);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (autor.getText().toString().trim().toLowerCase().replace(" ", "").equals("antoniohernández") || autor.getText().toString().trim().toLowerCase().replace(" ", "").equals("antoniohernandez")){
                    Toast.makeText(getApplicationContext(), "Nombre correcto", Toast.LENGTH_SHORT).show();
                    // Create a new user with a first and last name
                    Map<String, Object> users = new HashMap<>();
                    users.put("username", Login.nombreStr);
                    users.put("lastname", Login.apellidosStr);
                    users.put("time", Criptex.textFormater);
                    users.put("group", Login.grupoStr);

                    // Add a new document with a generated ID
                    db.collection("users")
                            .add(users)
                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    Log.d("TAG", "DocumentSnapshot added with ID: " + documentReference.getId());
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.w("" +
                                            "", "Error adding document", e);
                                }
                            });

                    Intent intent = new Intent(getApplicationContext(), Finish.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(getApplicationContext(), "NOMBRE INCORRECTO", Toast.LENGTH_SHORT).show();
                }

            }
        });







    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        AlertDialog.Builder builder = new AlertDialog.Builder(Poem.this);
        builder.setTitle("¡¡¡CUIDADO!!!");
        builder.setMessage("Si aceptas se cerrará la aplicacción y el juego habrá acabado");

        builder.setPositiveButton("Salir", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
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
