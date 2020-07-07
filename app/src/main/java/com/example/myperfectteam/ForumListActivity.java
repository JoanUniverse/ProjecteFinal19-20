package com.example.myperfectteam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum_list);
        preferences = new Preferences(this);
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
        Intent intent = new Intent(this, MainActivity.class);
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
}
