package com.example.myperfectteam.mptactivities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myperfectteam.R;
import com.example.myperfectteam.mptutilities.Preferences;

public class PlayerNameActivity extends AppCompatActivity {

    EditText playerNameET;
    String playerName;
    int gameID;
    TextView textViewBar;
    Preferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_name);
        preferences = new Preferences(this);
        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_action_bar);
        textViewBar = findViewById(R.id.name);
        textViewBar.setText("Player name");
        playerNameET = findViewById(R.id.playerNameET);
        Intent intent = getIntent();
        gameID = intent.getIntExtra("gameID", 0);
        Toast.makeText(this, "ID: " + gameID, Toast.LENGTH_LONG).show();
    }

    public void onPlayerNameButtonClick(View v) {
        playerName = playerNameET.getText().toString();
        if (playerName.trim().equals("")) {
            Toast.makeText(this, "This field can't be empty", Toast.LENGTH_LONG).show();
        } else {
            Intent intent = new Intent(this, SteamOpenIDSignInActivity.class);
            intent.putExtra("gameID", gameID);
            intent.putExtra("playerName", playerName);
            startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.logout:
                preferences.clearPreferences();
                Toast.makeText(getApplicationContext(), "Bye", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
