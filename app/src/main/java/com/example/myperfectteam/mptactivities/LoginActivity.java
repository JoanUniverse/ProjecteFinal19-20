package com.example.myperfectteam.mptactivities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myperfectteam.R;
import com.example.myperfectteam.mptutilities.Preferences;
import com.example.myperfectteam.mptutilities.RequestHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {
    Preferences preferences;
    private EditText userNameET;
    private EditText userPasswordET;
    private String userName;
    private String password;
    TextView textViewBar;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_action_bar);
        textViewBar = findViewById(R.id.name);
        textViewBar.setText("Login");
        preferences = new Preferences(this);
        userNameET = findViewById(R.id.userNameEditText);
        userPasswordET = findViewById(R.id.passwordEditText);
        if(preferences.getUserID() != -1) {
            Intent intent = new Intent(getApplicationContext(), GameActivity.class);
            userName = preferences.getUserName();
            intent.putExtra("UserCode", userName);
            startActivity(intent);
            finish();
        }
    }

    public class RequestAsync extends AsyncTask<String,String,String> {
        @Override
        protected String doInBackground(String... strings) {
            try {
                // POST Request
                HashMap<String, String> postDataParams = new HashMap<>();
                postDataParams.put("userNameA", userName);
                postDataParams.put("userPasswordA", password);

                return RequestHandler.sendPost(RequestHandler.USER_LOGIN,postDataParams);
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
                    JSONObject userData;
                    userData = convertedObject.getJSONObject("dades");
                    preferences = new Preferences(getApplicationContext());
                    preferences.setUserID(userData.getInt("userID"));
                    preferences.setUserName(userData.getString("userName"));
                    preferences.setUserPassword(userData.getString("userPassword"));
                    preferences.setUserToken(userData.getString("userToken"));
                    Intent intent = new Intent(getApplicationContext(), GameActivity.class);
                    intent.putExtra("UserCode", userName);
                    startActivity(intent);
                }
                //Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public void onLoginButtonClick(View v) {
        userName = userNameET.getText().toString();
        password = userPasswordET.getText().toString();
        new RequestAsync().execute();
    }

    public void onRegisterButtonClick(View v) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

//    private String CryptPassword(String password) {
//        String generatedSecuredPasswordHash = BCrypt.hashpw(password, BCrypt.gensalt(12));
//        System.out.println(generatedSecuredPasswordHash);
//        return generatedSecuredPasswordHash;
//    }
}
