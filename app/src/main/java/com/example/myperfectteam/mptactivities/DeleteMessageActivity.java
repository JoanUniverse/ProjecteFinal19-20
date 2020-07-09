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
import com.example.myperfectteam.mptutilities.Preferences;
import com.example.myperfectteam.mptutilities.RequestHandler;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class DeleteMessageActivity extends AppCompatActivity {
    FloatingActionButton floatingCancelFab;
    FloatingActionButton floatingDeleteFab;
    Preferences preferences;
    int playerMessageID = -1;
    int messageID = -1;
    TextView textViewBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_message);
        preferences = new Preferences(this);
        floatingCancelFab = findViewById(R.id.cancelDeleteFab);
        floatingDeleteFab = findViewById(R.id.deleteFab);
        Intent intent = getIntent();
        playerMessageID = intent.getIntExtra("playerMessageID",-1);
        messageID = intent.getIntExtra("messageID",-1);
        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_action_bar);
        textViewBar = findViewById(R.id.name);
        textViewBar.setText("Delete message");

        floatingCancelFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ThreadMessagesActivity.class);
                startActivity(intent);
                finish();
            }
        });floatingDeleteFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new RequestAsync().execute();
            }
        });

    }
    public class RequestAsync extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... strings) {
            try {
                HashMap<String, String> p = new HashMap<String, String>();
                p.put("playerIDA", String.valueOf(playerMessageID));
                p.put("messageIDA", String.valueOf(messageID));

                return RequestHandler.sendPostToken(RequestHandler.DELETE_CSGO_MESSAGE, p, preferences.getUserToken());
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
                    Intent intent = new Intent(getApplicationContext(),ThreadMessagesActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Intent intent = new Intent(getApplicationContext(),ThreadMessagesActivity.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), "Error deleting message!", Toast.LENGTH_LONG).show();
                    finish();
                }
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