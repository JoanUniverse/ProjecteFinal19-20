package com.example.myperfectteam;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.JsonParser;

public class Preferences {
    private static final String PREFERENCES_NAME = "MyPerfectTeamPreferences";
    private static final String APPUSERID = "userID";
    private static final String APPUSERNAME = "userName";
    private static final String APPUSERPASSWORD = "userPassword";
    private static final String APPUSERTOKEN = "userToken";
    private static final String LASTPLATFORMID = "lastPlatformID";
    private static final String LASTFORUMID = "lastForumID";
    private static final String LASTTHREADID = "lastThreadID";
    private static final String PLAYERNAME = "playerName";
    private static final String THREADOBJECT = "threadObject";

    private static final String LASTPLAYERID = "lastPlayerID";
    private static final String CSGOPLAYERID = "csgoPlayerID";
    private static final String OWPLAYERID = "owPlayerID";

    private int userID;
    private String userName;
    private String userPassword;
    private String userToken;
    private String lastPlatfornID;
    private String playerName;
    private String threadObject;

    private int lastPlayerID;
    private int csgoPlayerID;
    private int owPlayerID;
    private int lastForumID;
    private int lastThreadID;
    private SharedPreferences pref;

    public Preferences(Context context) {
        this.pref = context.getSharedPreferences(PREFERENCES_NAME, context.MODE_PRIVATE);
        this.userID = this.pref.getInt(APPUSERID, -1);
        this.userName = this.pref.getString(APPUSERNAME, "");
        this.userPassword = this.pref.getString(APPUSERPASSWORD, "");
        this.userToken = this.pref.getString(APPUSERTOKEN, "");
        this.lastPlatfornID = this.pref.getString(LASTPLATFORMID, "");
        this.csgoPlayerID = this.pref.getInt(CSGOPLAYERID, -1);
        this.owPlayerID = this.pref.getInt(OWPLAYERID, -1);
        this.lastForumID = this.pref.getInt(LASTFORUMID, -1);
        this.lastThreadID = this.pref.getInt(LASTTHREADID, -1);
        this.lastPlayerID = this.pref.getInt(LASTPLAYERID, -1);
        this.playerName = this.pref.getString(PLAYERNAME, "");
        this.threadObject = this.pref.getString(THREADOBJECT, "");
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

    public int getLastForumID() {
        return lastForumID;
    }

    public void setLastForumID(int lastForumID) {
        this.lastForumID = lastForumID;
        SharedPreferences.Editor editor = this.pref.edit();
        editor.putInt(LASTFORUMID, lastForumID);
        editor.commit();
    }

    public int getLastThreadID() {
        return lastThreadID;
    }

    public void setLastThreadID(int lastThreadID) {
        this.lastThreadID = lastThreadID;
        SharedPreferences.Editor editor = this.pref.edit();
        editor.putInt(LASTTHREADID, lastThreadID);
        editor.commit();
    }

    public int getLastPlayerID() {
        return lastPlayerID;
    }

    public void setLastPlayerID(int lastPlayerID) {
        this.lastPlayerID = lastPlayerID;
        SharedPreferences.Editor editor = this.pref.edit();
        editor.putInt(LASTPLAYERID, lastPlayerID);
        editor.commit();
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
        SharedPreferences.Editor editor = this.pref.edit();
        editor.putString(PLAYERNAME, playerName);
        editor.commit();
    }

    public String getLastPlatfornID() {
        return lastPlatfornID;
    }

    public void setLastPlatfornID(String lastPlatfornID) {
        this.lastPlatfornID = lastPlatfornID;
        SharedPreferences.Editor editor = this.pref.edit();
        editor.putString(LASTPLATFORMID, lastPlatfornID);
        editor.commit();
    }

    public String getThreadObject() {
        return threadObject;
    }

    public void setThreadObject(String threadObject) {
        this.threadObject = threadObject;
        SharedPreferences.Editor editor = this.pref.edit();
        editor.putString(THREADOBJECT, threadObject);
        editor.commit();
    }
}
