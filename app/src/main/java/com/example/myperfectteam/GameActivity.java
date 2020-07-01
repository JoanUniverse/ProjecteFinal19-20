package com.example.myperfectteam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity {
    Preferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        preferences = new Preferences(this);
        Intent intent = getIntent();
        String nomUsuari = intent.getStringExtra("UserCode");
        Toast.makeText(this, "Welcome " + nomUsuari, Toast.LENGTH_LONG).show();
        preferences.getUserID();
        preferences.getUserToken();
    }

    public void onCSGOGameButtonClick(View v) {
        Intent intent = new Intent(this, SteamOpenIDSignInActivity.class);
        intent.putExtra("gameID", 1);
        startActivity(intent);
    }
}
