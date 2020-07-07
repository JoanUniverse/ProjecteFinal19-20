package com.example.myperfectteam;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
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
            if(s!=null){
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
            }
        }
    }

    public void onRegistrarseRegisterButtonClick(View v) {
        getETtext();
        new RequestAsync().execute();
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
