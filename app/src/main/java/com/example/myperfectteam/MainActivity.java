package com.example.myperfectteam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements LlistaForo.ForoListener, LlistaForo.FabListener{

    public static ThreadObject threadObjectSelected;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        int forumID = intent.getIntExtra("forumID", 0);
        Toast.makeText(this, "FORUM ID: " + forumID, Toast.LENGTH_LONG).show();
        LlistaForo llistaForo = (LlistaForo)
                getSupportFragmentManager().findFragmentById(R.id.FrgLlista);
        llistaForo.setForoListener(this);
        llistaForo.setFabListener(this);
    }

    @Override
    public void onFilSeleccionat(ThreadObject threadObject) {
        // Mirar l'orientació
        threadObjectSelected = threadObject;
        int orientation = this.getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            // estam en vertical
            Intent i = new Intent(this, FilActivity.class);
            startActivity(i);
        } else {
            // estam en horitzontal
            ((FilForo) getSupportFragmentManager()
                    .findFragmentById(R.id.FrgFil)).mostraFil(threadObject);
        }
    }

    public void onFabSelected() {
        Intent i = new Intent(this, AddThreadActivity.class);
        startActivity(i);
        finish();
    }
}
