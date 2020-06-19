package com.example.projectefinal19_20;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {
    private EditText nomUsuariET;
    private EditText contrasenyaET;
    private String nomUsuari;
    private String contrasenya;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        nomUsuariET = findViewById(R.id.nomUsuariEditText);
        contrasenyaET = findViewById(R.id.contrasenyaEditText);
    }

    public void onEnviarButtonClick(View v) {
        nomUsuari = nomUsuariET.getText().toString();
        contrasenya = contrasenyaET.getText().toString();
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("CodiUsuari", nomUsuari);
        startActivity(intent);
    }

    public void onRegistrarseButtonClick(View v) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
}
