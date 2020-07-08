package com.example.myperfectteam;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myperfectteam.mptutilities.Preferences;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {
    EditText userNameET;
    EditText userEmailET;
    EditText passwordET;
    EditText confirmPasswordET;
    String userName;
    String userEmail;
    String password;
    String confirmPassword;
    TextView textViewBar;
    Preferences preferences;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        preferences = new Preferences(this);
        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_action_bar);
        textViewBar = findViewById(R.id.name);
        textViewBar.setText("Register");
        inicializeETs();
    }

    public class RequestAsync extends AsyncTask<String,String,String> {
        @Override
        protected String doInBackground(String... strings) {
            try {
                // POST Request
                HashMap<String, String> postDataParams = new HashMap<>();
                postDataParams.put("userEmailA", userEmail);
                postDataParams.put("userPasswordA", password);
                postDataParams.put("userNameA", userName);

                return RequestHandler.sendPost(RequestHandler.USER_REGISTER,postDataParams);
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
                    Toast.makeText(getApplicationContext(), "User registered successfully!", Toast.LENGTH_LONG).show();
                    preferences.clearPreferences();
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "This user already exists", Toast.LENGTH_LONG).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public void onRegistrarseRegisterButtonClick(View v) {
        getETtext();
        if(userName.trim().equals("") || userEmail.trim().equals("") || password.trim().equals("") || confirmPassword.trim().equals("")) {
            Toast.makeText(getApplicationContext(), "No field can be empty", Toast.LENGTH_LONG).show();
        } else if(password.equals(confirmPassword)) {
            new RequestAsync().execute();
        } else {
            Toast.makeText(getApplicationContext(), "Passwords don't match!", Toast.LENGTH_LONG).show();
        }
    }

    private void getETtext() {
        userName = userNameET.getText().toString();
        userEmail = userEmailET.getText().toString();
        password = passwordET.getText().toString();
        confirmPassword = confirmPasswordET.getText().toString();
    }

    private void inicializeETs() {
        userNameET = findViewById(R.id.userNameRegisterEditText);
        userEmailET = findViewById(R.id.userEmailRegisterEditText);
        passwordET = findViewById(R.id.passwordRegisterEditText);
        confirmPasswordET = findViewById(R.id.confirmPasswordRegisterEditText);
    }
}
