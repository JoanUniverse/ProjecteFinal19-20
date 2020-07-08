package com.example.myperfectteam;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.example.myperfectteam.mptutilities.Preferences;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
//Joan Quetglas Alomar
public class SteamOpenIDSignInActivity extends AppCompatActivity {

    // The string will appear to the user in the login screen
    // you can put your app's name
    final String REALM_PARAM = "MyPerfectTeam";
    int gameID;
    int userID;
    String playerName;
    String platformID;
    Preferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        gameID = intent.getIntExtra("gameID", 0);
        playerName = intent.getStringExtra("playerName");
        preferences = new Preferences(this);
        userID = preferences.getUserID();
        setContentView(R.layout.activity_steam_open_idsign_in);


        final WebView webView = new WebView(this);
        webView.getSettings().setJavaScriptEnabled(true);

        final Activity activity = this;

        setContentView(webView);

        // Constructing openid url request
        String url = "https://steamcommunity.com/openid/login?" +
                "openid.claimed_id=http://specs.openid.net/auth/2.0/identifier_select&" +
                "openid.identity=http://specs.openid.net/auth/2.0/identifier_select&" +
                "openid.mode=checkid_setup&" +
                "openid.ns=http://specs.openid.net/auth/2.0&" +
                "openid.realm=https://" + REALM_PARAM + "&" +
                "openid.return_to=https://" + REALM_PARAM + "/signin/";

        webView.loadUrl(url);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url,
                                      Bitmap favicon) {

                //checks the url being loaded
                setTitle(url);
                Uri Url = Uri.parse(url);

                if (Url.getAuthority().equals(REALM_PARAM.toLowerCase())) {
                    // That means that authentication is finished and the url contains user's id.
                    webView.stopLoading();

                    // Extracts user id.
                    Uri userAccountUrl = Uri.parse(Url.getQueryParameter("openid.identity"));
                    platformID = userAccountUrl.getLastPathSegment();
                    preferences.setLastPlatfornID(platformID);
                    new RequestAsync().execute();

                }
            }
        });
    }

    public class RequestAsync extends AsyncTask<String,String,String> {
        @Override
        protected String doInBackground(String... strings) {
            try {
                // POST Request
                HashMap<String, String> postDataParams = new HashMap<>();
                postDataParams.put("playerNameA", playerName);
                postDataParams.put("gameIDA", String.valueOf(gameID));
                postDataParams.put("userIDA", String.valueOf(userID));
                postDataParams.put("playerPlatformIDA", platformID);

                return RequestHandler.sendPost(RequestHandler.INSERT_PLAYER,postDataParams);
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
                    JSONObject playerData;
                    playerData = convertedObject.getJSONObject("dades");
                    preferences = new Preferences(getApplicationContext());
                    preferences.setLastPlayerID(playerData.getInt("playerID"));
                    preferences.setPlayerName(playerData.getString("playerName"));
                    preferences.setLastPlatfornID(playerData.getString("playerPlatformID"));
                    //Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
                    Toast.makeText(getApplicationContext(), "Welcome " + playerData.getString("playerName"), Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), ForumListActivity.class);
                    startActivity(intent);
                    finish();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
