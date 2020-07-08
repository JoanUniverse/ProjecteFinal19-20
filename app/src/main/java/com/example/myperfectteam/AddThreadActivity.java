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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myperfectteam.mptutilities.Preferences;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class AddThreadActivity extends AppCompatActivity {
    Preferences preferences;
    EditText threadNameET;
    EditText threadDescriptionET;
    String threadTitle;
    String threadDescription;
    TextView textViewBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_thread);
        threadNameET = findViewById(R.id.addThreadNameET);
        threadDescriptionET = findViewById(R.id.addThreadDescriptionET);
        preferences = new Preferences(this);
        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_action_bar);
        textViewBar = findViewById(R.id.name);
        textViewBar.setText("Create thread");
    }

    public void onAddThreadClicked(View v) {
        threadTitle = threadNameET.getText().toString();
        threadDescription = threadDescriptionET.getText().toString();
        if (threadTitle.trim().equals("")) {
            Toast.makeText(this, "Thread must have a name!", Toast.LENGTH_LONG).show();
        } else if (threadDescription.trim().equals("")) {
            Toast.makeText(this, "Thread must have a description!", Toast.LENGTH_LONG).show();
        } else {
//            LlistaForo.fils.add(new Fil(0,threadName, threadDescription));
            new RequestAsync().execute();

        }
    }

    public class RequestAsync extends AsyncTask<String,String,String> {
        @Override
        protected String doInBackground(String... strings) {
            try {
                // POST Request
                HashMap<String, String> postDataParams = new HashMap<>();
                postDataParams.put("threadTitleA", threadTitle);
                postDataParams.put("threadDescriptionA", threadDescription);
                postDataParams.put("forumIDA", String.valueOf(preferences.getLastForumID()));
                postDataParams.put("playerIDA", String.valueOf(preferences.getLastPlayerID()));

                return RequestHandler.sendPost(RequestHandler.INSERT_THREAD,postDataParams);
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
                    Toast.makeText(getApplicationContext(),"Thread added!", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), ThreadListActivity.class);
                    startActivity(intent);
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