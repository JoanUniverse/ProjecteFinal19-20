package com.example.myperfectteam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class FilActivity extends AppCompatActivity {
    Preferences preferences;
    ThreadObject threadObject;
    public static ArrayList<Message> messages = new ArrayList<>();
    private ListView listView;
    private ArrayAdapter<Message> adapter;
    FloatingActionButton floatingActionButton;
    LinearLayout linearLayout;
    Boolean layoutWeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fil);
        floatingActionButton = findViewById(R.id.fabScroll);
        preferences = new Preferences(this);
        threadObject = MainActivity.threadObjectSelected;

        layoutWeight = true;
        linearLayout = findViewById(R.id.linearLayoutMessages);
        new RequestAsync().execute();
        showMessages();

        TextView playerTV = findViewById(R.id.threadPlayerName);
        TextView threadTitleTV = findViewById(R.id.threadTitle);
        TextView threadDescriptionTV = findViewById(R.id.threadDescription);
        TextView threadDateTV = findViewById(R.id.threadDate);

        playerTV.setText(threadObject.getPlayerName());
        threadTitleTV.setText(threadObject.getThreadTitle());
        threadDescriptionTV.setText(threadObject.getThreadDescription());
        threadDateTV.setText(threadObject.getThreadDate());
    }

    public class RequestAsync extends AsyncTask<String,String,String> {
        @Override
        protected String doInBackground(String... strings) {
            try {
                HashMap<String, String> p = new HashMap<String, String>();
                p.put("messageToIDA", "");
                p.put("threadIDA", String.valueOf(threadObject.getThreadID()));
                p.put("teamIDA", "");

                return RequestHandler.sendPost(RequestHandler.GET_ALL_MESSAGES, p);
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
                    messages = new ArrayList<>();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject explrObject = jsonArray.getJSONObject(i);
                        Message message = new Gson().fromJson(explrObject.toString(), Message.class);
                        messages.add(message);
                    }
                    showMessages();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private void showMessages() {
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);


                // edited here
                if (linearLayout != null) {
                    if (layoutWeight) {
                        param.weight = 0;
                        layoutWeight = false;
                    } else {
                        param.weight = 4;
                        layoutWeight = true;
                    }
                    linearLayout.setLayoutParams(param);
                }
            }
        });
        listView = findViewById(R.id.messagesListView);

        adapter = new ArrayMessage(this, R.layout.message_list_item, messages);
        listView.setAdapter(adapter);

    }

//    public interface FabMessageListener {
//        void onFabMessageSelected();
//    }
//    public void setFabMessageListener(FabMessageListener fabMessageListener) {
//        this.fabMessageListener = fabMessageListener;
//    }
}
