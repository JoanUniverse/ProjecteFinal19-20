package com.example.myperfectteam.mptutilities;

import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

public class RequestHandler {
    private static String ip = "192.168.18.3";
    public static final String USER_LOGIN =  "http://" + ip + "/MyPerfectTeamServer/public/appuser/login/";
    public static final String USER_REGISTER =  "http://" + ip + "/MyPerfectTeamServer/public/appuser/userregister/";
    public static final String INSERT_PLAYER = "http://" + ip + "/MyPerfectTeamServer/public/player/insert/";
    public static final String INSERT_THREAD = "http://" + ip + "/MyPerfectTeamServer/public/thread/insert/";
    public static final String GET_ALL_THREADS = "http://" + ip + "/MyPerfectTeamServer/public/thread/";
    public static final String GET_ALL_FORUMS = "http://" + ip + "/MyPerfectTeamServer/public/forum/";
    public static final String CHECK_PLAYER = "http://" + ip + "/MyPerfectTeamServer/public/player/check/";
    public static final String GET_ALL_MESSAGES = "http://" + ip + "/MyPerfectTeamServer/public/message/";
    public static final String SEND_MESSAGE = "http://" + ip + "/MyPerfectTeamServer/public/message/insert/";
    public static final String GET_CSGO_STATS = "http://api.steampowered.com/ISteamUserStats/GetUserStatsForGame/v0002/?appid=730&key=A58F69C967D4DD4E75EE60A68B6B3543&steamid=";
    public static final String UPDATE_CSGO_STATS = "http://" + ip + "/MyPerfectTeamServer/public/stats/updateORinsert/";
    public static final String GET_CSGO_PLAYER_STATS = "http://" + ip + "/MyPerfectTeamServer/public/stats/player/";
    public static final String DELETE_CSGO_MESSAGE = "http://" + ip + "/MyPerfectTeamServer/public/message/delete/";

    public static String sendPost(String r_url, HashMap<String, String> postDataParams) throws Exception {
        URL url = new URL(r_url);

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setReadTimeout(20000);
        conn.setConnectTimeout(20000);
        conn.setRequestMethod("POST");
        conn.setDoInput(true);
        conn.setDoOutput(true);

        OutputStream os = conn.getOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
        writer.write(encodeParams(postDataParams));
        writer.flush();
        writer.close();
        os.close();

        //Comprovam que la peticio ha donat un 200 OK
        int responseCode = conn.getResponseCode();
        if (responseCode == HttpsURLConnection.HTTP_OK) {

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuffer sb = new StringBuffer("");
            String line = "";
            while ((line = in.readLine()) != null) {
                sb.append(line);
                break;
            }
            in.close();
            return sb.toString();
        }
        return null;
    }

    public static String sendGet(String url) throws IOException {
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        //con.setRequestProperty("Authorization", token);
        int responseCode = con.getResponseCode();
        System.out.println("Response Code :: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            return response.toString();
        } else {
            return "";
        }
    }
    public static String sendPostToken(String r_url, HashMap<String, String> postDataParams,String token) throws Exception {
        URL url = new URL(r_url);

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setReadTimeout(20000);
        conn.setConnectTimeout(20000);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Authorization", token);
        conn.setDoInput(true);
        conn.setDoOutput(true);

        OutputStream os = conn.getOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
        writer.write(encodeParams(postDataParams));
        writer.flush();
        writer.close();
        os.close();

        //Comprovam que la peticio ha donat un 200 OK
        int responseCode = conn.getResponseCode();
        if (responseCode == HttpsURLConnection.HTTP_OK) {

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuffer sb = new StringBuffer("");
            String line = "";
            while ((line = in.readLine()) != null) {
                sb.append(line);
                break;
            }
            in.close();
            return sb.toString();
        }
        return null;
    }

    public static String sendGetToken(String url,String token) throws IOException {
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Authorization", token);
        int responseCode = con.getResponseCode();
        System.out.println("Response Code :: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            return response.toString();
        } else {
            return "";
        }
    }


    private static String encodeParams(HashMap<String, String> params) throws Exception {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }
        return result.toString();
    }
}