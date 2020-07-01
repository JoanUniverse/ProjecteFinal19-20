package com.example.myperfectteam;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {
    Preferences preferences;
    private EditText userNameET;
    private EditText userPasswordET;
    private String userName;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        preferences = new Preferences(this);
        userNameET = findViewById(R.id.userNameEditText);
        userPasswordET = findViewById(R.id.passwordEditText);
    }

    public class RequestAsync extends AsyncTask<String,String,String> {
        @Override
        protected String doInBackground(String... strings) {
            try {
                // POST Request
                HashMap<String, String> postDataParams = new HashMap<>();
                postDataParams.put("userNameA", userName);
                postDataParams.put("userPasswordA", password);

                return RequestHandler.sendPost("http://192.168.18.3/MyPerfectTeamServer/public/appuser/login/",postDataParams);
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
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
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
