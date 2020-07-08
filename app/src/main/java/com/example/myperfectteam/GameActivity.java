package com.example.myperfectteam;

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

import com.example.myperfectteam.mptutilities.Preferences;
import com.example.myperfectteam.mptutilities.RequestHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class GameActivity extends AppCompatActivity {
    Preferences preferences;
    private final int CSGOID = 1;
    private final int OWID = 2;
    String userID;
    TextView textViewBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_action_bar);
        textViewBar = findViewById(R.id.name);
        textViewBar.setText("Games");

        preferences = new Preferences(this);
        Intent intent = getIntent();
        userID = intent.getStringExtra("UserCode");
        Toast.makeText(this, "Welcome " + userID, Toast.LENGTH_LONG).show();
        preferences.getUserID();
        preferences.getUserToken();
    }

    public void onCSGOGameButtonClick(View v) {
        preferences.setLastGameID(CSGOID);
        if (preferences.getCsgoPlayerID() != -1) {
            Intent intent = new Intent(getApplicationContext(), ForumListActivity.class);
            startActivity(intent);
        } else {
            new RequestAsync().execute();
        }
    }

    public class RequestAsync extends AsyncTask<String,String,String> {
        @Override
        protected String doInBackground(String... strings) {
            try {
                // POST Request
                HashMap<String, String> postDataParams = new HashMap<>();
                postDataParams.put("gameIDA", String.valueOf(CSGOID));
                postDataParams.put("userIDA", String.valueOf(preferences.getUserID()));

                return RequestHandler.sendPost(RequestHandler.CHECK_PLAYER,postDataParams);
            }
            catch(Exception e){
                return new String("Exception: " + e.getMessage());
            }
        }

        @Override
        protected void onPostExecute(String s) {
            Boolean response;
            try {
                JSONObject convertedObject = new JSONObject(s);
                response = convertedObject.getBoolean("correcta");
                if(response){
                    JSONObject playerData;
                    playerData = convertedObject.getJSONObject("dades");
                    preferences = new Preferences(getApplicationContext());
                    preferences.setLastPlayerID(playerData.getInt("playerID"));
                    preferences.setPlayerName(playerData.getString("playerName"));
                    preferences.setLastPlatfornID(playerData.getString("playerPlatformID"));
                    Intent intent = new Intent(getApplicationContext(), ForumListActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(getApplicationContext(), PlayerNameActivity.class);
                    intent.putExtra("gameID", CSGOID);
                    startActivity(intent);
                }
                //Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
            } catch (JSONException e) {
                e.printStackTrace();
            }
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
