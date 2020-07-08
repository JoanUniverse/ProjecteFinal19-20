package com.example.myperfectteam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
    FloatingActionButton floatingActionButtonMessage;
    LinearLayout linearLayout;
    LinearLayout sendMessageLinearLayout;
    Boolean layoutWeight;
    Boolean sendMessageLinearLayoutVisibility;
    Button sendMessageButton;
    EditText messageET;
    String messageText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fil);
        floatingActionButton = findViewById(R.id.fabScroll);
        floatingActionButtonMessage = findViewById(R.id.fabMessage);
        preferences = new Preferences(this);
        threadObject = MainActivity.threadObjectSelected;

        messageText = "";
        layoutWeight = true;
        linearLayout = findViewById(R.id.linearLayoutMessages);
        sendMessageButton = findViewById(R.id.messageButton);
        messageET = findViewById(R.id.messageET);
        sendMessageLinearLayout = findViewById(R.id.sendMessageLinearLayout);
        sendMessageLinearLayoutVisibility = false;
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


                // Change Button src and Layout weight
                if (linearLayout != null) {
                    if (layoutWeight) {
                        param.weight = 0;
                        floatingActionButton.setImageResource(R.drawable.ic_baseline_expand_more_24);
                        layoutWeight = false;
                    } else {
                        param.weight = 2;
                        floatingActionButton.setImageResource(R.drawable.ic_baseline_expand_less_24);
                        layoutWeight = true;
                    }
                    linearLayout.setLayoutParams(param);
                }
            }
        });

        floatingActionButtonMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sendMessageLinearLayoutVisibility) {
                    sendMessageLinearLayout.setVisibility(View.INVISIBLE);
                    sendMessageLinearLayoutVisibility = false;
                } else {
                    sendMessageLinearLayout.setVisibility(View.VISIBLE);
                    sendMessageLinearLayoutVisibility = true;
                }
            }
        });

        sendMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                messageText = messageET.getText().toString().trim();
                if (messageText.equals("")) {
                    Toast.makeText(getApplicationContext(), "Message can't be empty!", Toast.LENGTH_LONG).show();
                } else {
                    new RequestAsyncSendMessage().execute();
                }
            }
        });
        listView = findViewById(R.id.messagesListView);

        adapter = new ArrayMessage(this, R.layout.message_list_item, messages);
        listView.setAdapter(adapter);

    }

    public class RequestAsyncSendMessage extends AsyncTask<String,String,String> {
        @Override
        protected String doInBackground(String... strings) {
            try {
                HashMap<String, String> p = new HashMap<String, String>();
                p.put("messageToIDA", "");
                p.put("threadIDA", String.valueOf(threadObject.getThreadID()));
                p.put("teamIDA", "");
                p.put("messageTextA", messageText);
                p.put("playerIDA", String.valueOf(preferences.getLastPlayerID()));

                return RequestHandler.sendPost(RequestHandler.SEND_MESSAGE, p);
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
                    Toast.makeText(getApplicationContext(), "Message Sent", Toast.LENGTH_LONG).show();
                    messageET.setText("");
                    new RequestAsync().execute();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
