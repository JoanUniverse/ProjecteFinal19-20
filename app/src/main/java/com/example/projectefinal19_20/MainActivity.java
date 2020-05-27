package com.example.projectefinal19_20;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LlistaForo.ForoListener{

    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        String nomUsuari = intent.getStringExtra("CodiUsuari");
        Toast.makeText(this, "Benvolgut " + nomUsuari, Toast.LENGTH_LONG).show();
        LlistaForo llistaForo = (LlistaForo)
                getSupportFragmentManager().findFragmentById(R.id.FrgLlista);
        llistaForo.setForoListener(this);
    }

    @Override
    public void onFilSeleccionat(String fil) {
        // Mirar l'orientació
        int orientation = this.getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            // estam en vertical
            Intent i = new Intent(this, FilActivity.class);
            i.putExtra("nomFil", fil);
            startActivity(i);
        } else {
            // estam en horitzontal
            ((FilForo) getSupportFragmentManager()
                    .findFragmentById(R.id.FrgFil)).mostraFil(fil);
        }
    }
}
