package com.example.myperfectteam.mptactivities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myperfectteam.R;
import com.example.myperfectteam.mptobjects.Forum;
import com.example.myperfectteam.mptutilities.Preferences;
import com.example.myperfectteam.mptutilities.RequestHandler;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class CsgoPlayerStatsActivity extends AppCompatActivity {
    String messagePlayerName;
    int playerID;
    TextView playerNameTV;
    Preferences preferences;
    TextView textViewBar;
    TextView kdRatioTV;
    TextView mvpTV;
    TextView headshotRatioTV;
    TextView timePlayedTV;
    TextView totalWinsTV;

    private int totalWins;
    private double KDRatio;
    private int headshotRatio;
    private int MVP;
    private double timePlayed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_csgo_player_stats);
        Intent intent = getIntent();
        messagePlayerName = intent.getStringExtra("playerName");
        playerID = intent.getIntExtra("playerID", -1);
        playerNameTV = findViewById(R.id.csgoStatsPlayerName);
        playerNameTV.setText(messagePlayerName);
        preferences = new Preferences(this);
        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_action_bar);
        textViewBar = findViewById(R.id.name);
        textViewBar.setText("Player stats");
        kdRatioTV = findViewById(R.id.kdRatioTV);
        mvpTV = findViewById(R.id.mvpTV);
        headshotRatioTV = findViewById(R.id.headshotRatioTV);
        timePlayedTV = findViewById(R.id.timePlayedTV);
        totalWinsTV = findViewById(R.id.totalWinsTV);
        new RequestAsync().execute();
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

    public class RequestAsync extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... strings) {
            try {
                // POST Request
                HashMap<String, String> postDataParams = new HashMap<>();
                postDataParams.put("playerIDA", String.valueOf(playerID));

                return RequestHandler.sendPostToken(RequestHandler.GET_CSGO_PLAYER_STATS, postDataParams,preferences.getUserToken());
            } catch (Exception e) {
                return new String("Exception: " + e.getMessage());
            }
        }

        @Override
        protected void onPostExecute(String s) {
            Boolean response;
            try {
                JSONObject convertedObject = new JSONObject(s);
                response = convertedObject.getBoolean("correcta");
                if (response) {
                    JSONObject userData;
                    userData = convertedObject.getJSONObject("dades");
                    totalWins = userData.getInt("totalWins");
                    KDRatio = userData.getDouble("KDRatio");
                    headshotRatio = userData.getInt("headshotRatio");
                    MVP = userData.getInt("MVP");
                    timePlayed = userData.getDouble("timePlayed");
                    setPlayerStats();
                    Toast.makeText(getApplicationContext(), "Stats set", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "No stats", Toast.LENGTH_LONG).show();
                }
            } catch (JSONException e) {

                e.printStackTrace();
            }
        }
    }

    private void setPlayerStats() {
        timePlayedTV.setText(String.valueOf(timePlayed));
        kdRatioTV.setText(String.valueOf(KDRatio));
        headshotRatioTV.setText(String.valueOf(headshotRatio));
        mvpTV.setText(String.valueOf(MVP));
        totalWinsTV.setText(String.valueOf(totalWins));
    }
}