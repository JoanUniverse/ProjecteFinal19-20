package com.example.myperfectteam;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myperfectteam.arrayadapters.ArrayForum;
import com.example.myperfectteam.mptobjects.Forum;
import com.example.myperfectteam.mptutilities.Preferences;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class ForumListActivity extends AppCompatActivity {
    Preferences preferences;
    private ArrayList<Forum> forums = new ArrayList<>();
    private ListView listView;
    private ArrayAdapter<Forum> adapter;
    private Context context;
    int forumID;
    TextView textViewBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum_list);
        preferences = new Preferences(this);
        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_action_bar);
        textViewBar = findViewById(R.id.name);
        textViewBar.setText("Forums");
        new RequestAsync().execute();
        showForums();
    }

    private void showForums() {
        listView = findViewById(R.id.forumListView);

        adapter = new ArrayForum(getApplicationContext(), R.layout.forum_list_item, forums);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> list, View view, int pos, long id) {
                Forum forum = adapter.getItem(pos);
                forumID = forum.getForumID();
                preferences.setLastForumID(forumID);
                goToMainActivity();
            }
        });
    }

    private void goToMainActivity() {
        Intent intent = new Intent(this, ThreadListActivity.class);
        intent.putExtra("forumID", forumID);
        startActivity(intent);
    }

    public class RequestAsync extends AsyncTask<String,Void,String> {
        @Override
        protected String doInBackground(String... params) {
            try {
                //GET Request
                HashMap<String, String> p = new HashMap<String, String>();
                return RequestHandler.sendGet(RequestHandler.GET_ALL_FORUMS);
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
                    JSONArray jsonArray = convertedObject.getJSONArray("dades");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject explrObject = jsonArray.getJSONObject(i);
                        Forum forum = new Gson().fromJson(explrObject.toString(), Forum.class);
                        forums.add(forum);
                    }
                    showForums();
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
