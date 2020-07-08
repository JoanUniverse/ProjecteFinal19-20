package com.example.myperfectteam;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myperfectteam.mptutilities.Preferences;

public class CsgoPlayerStatsActivity extends AppCompatActivity {
    String messagePlayerName;
    TextView playerNameTV;
    Preferences preferences;
    TextView textViewBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_csgo_player_stats);
        Intent intent = getIntent();
        messagePlayerName = intent.getStringExtra("playerName");
        playerNameTV = findViewById(R.id.csgoStatsPlayerName);
        playerNameTV.setText(messagePlayerName);
        preferences = new Preferences(this);
        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_action_bar);
        textViewBar = findViewById(R.id.name);
        textViewBar.setText("Player stats");
    }

    public void onCsgoCloseButtonClick(View v) {
        finish();
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