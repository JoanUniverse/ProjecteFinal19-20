package com.example.myperfectteam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class PlayerNameActivity extends AppCompatActivity {

    EditText playerNameET;
    String playerName;
    int gameID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_name);
        playerNameET = findViewById(R.id.playerNameET);
        Intent intent = getIntent();
        gameID = intent.getIntExtra("gameID", 0);
        Toast.makeText(this, "ID: " + gameID, Toast.LENGTH_LONG).show();
    }

    public void onPlayerNameButtonClick(View v) {
        playerName = playerNameET.getText().toString();
        Intent intent = new Intent(this, SteamOpenIDSignInActivity.class);
        intent.putExtra("gameID", gameID);
        intent.putExtra("playerName", playerName);
        startActivity(intent);
    }
}
