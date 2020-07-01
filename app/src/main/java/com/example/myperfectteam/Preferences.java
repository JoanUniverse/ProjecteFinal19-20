package com.example.myperfectteam;

import android.content.Context;
import android.content.SharedPreferences;

public class Preferences {
    private static final String PREFERENCES_NAME = "MyPerfectTeamPreferences";
    private static final String APPUSERID = "userID";
    private static final String APPUSERNAME = "userName";
    private static final String APPUSERPASSWORD = "userPassword";
    private static final String APPUSERTOKEN = "userToken";
    private static final String STEAMPLATFORMID = "steamPlatformID";
    private static final String BATTLENETPLATFORMID = "battleNetPlatformID";

    private static final String CSGOPLAYERID = "csgoPlayerID";
    private static final String OWPLAYERID = "owPlayerID";

    private int userID;
    private String userName;
    private String userPassword;
    private String userToken;
    private String steamPlatformID;
    private String battleNetPlatformID;
    private int csgoPlayerID;
    private int owPlayerID;
    private SharedPreferences pref;

    public Preferences(Context context) {
        this.pref = context.getSharedPreferences(PREFERENCES_NAME, context.MODE_PRIVATE);
        this.userID = this.pref.getInt(APPUSERID, -1);
        this.userName = this.pref.getString(APPUSERNAME, "");
        this.userPassword = this.pref.getString(APPUSERPASSWORD, "");
        this.userToken = this.pref.getString(APPUSERTOKEN, "");
        this.steamPlatformID = this.pref.getString(STEAMPLATFORMID, "");
        this.battleNetPlatformID = this.pref.getString(BATTLENETPLATFORMID, "");
        this.csgoPlayerID = this.pref.getInt(CSGOPLAYERID, -1);
        this.owPlayerID = this.pref.getInt(OWPLAYERID, -1);
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
        SharedPreferences.Editor editor = this.pref.edit();
        editor.putInt(APPUSERID, userID);
        editor.commit();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
        SharedPreferences.Editor editor = this.pref.edit();
        editor.putString(APPUSERNAME, userName);
        editor.commit();
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
        SharedPreferences.Editor editor = this.pref.edit();
        editor.putString(APPUSERPASSWORD, userPassword);
        editor.commit();
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
        SharedPreferences.Editor editor = this.pref.edit();
        editor.putString(APPUSERTOKEN, userToken);
        editor.commit();
    }

    public String getSteamPlatformID() {
        return steamPlatformID;
    }

    public void setSteamPlatformID(String steamPlatformID) {
        this.steamPlatformID = steamPlatformID;
        SharedPreferences.Editor editor = this.pref.edit();
        editor.putString(STEAMPLATFORMID, steamPlatformID);
        editor.commit();
    }

    public String getBattleNetPlatformID() {
        return battleNetPlatformID;
    }

    public void setBattleNetPlatformID(String battleNetPlatformID) {
        this.battleNetPlatformID = battleNetPlatformID;
        SharedPreferences.Editor editor = this.pref.edit();
        editor.putString(BATTLENETPLATFORMID, battleNetPlatformID);
        editor.commit();
    }

    public int getCsgoPlayerID() {
        return csgoPlayerID;
    }

    public void setCsgoPlayerID(int csgoPlayerID) {
        this.csgoPlayerID = csgoPlayerID;
        SharedPreferences.Editor editor = this.pref.edit();
        editor.putInt(CSGOPLAYERID, csgoPlayerID);
        editor.commit();
    }

    public int getOwPlayerID() {
        return owPlayerID;
    }

    public void setOwPlayerID(int owPlayerID) {
        this.owPlayerID = owPlayerID;
        SharedPreferences.Editor editor = this.pref.edit();
        editor.putInt(OWPLAYERID, owPlayerID);
        editor.commit();
    }
}
