package com.example.myperfectteam;

import java.util.Date;

public class ThreadObject {
    private int threadID;
    private String threadDescription;
    private String threadTitle;
    private String threadDate;
    private int forumID;
    private int playerID;

    public ThreadObject(int threadID, String threadDescription, String threadTitle, String threadDate, int forumID, int playerID) {
        this.threadID = threadID;
        this.threadDescription = threadDescription;
        this.threadTitle = threadTitle;
        this.threadDate = threadDate;
        this.forumID = forumID;
        this.playerID = playerID;
    }

    public int getThreadID() {
        return threadID;
    }

    public void setThreadID(int threadID) {
        this.threadID = threadID;
    }

    public String getThreadDescription() {
        return threadDescription;
    }

    public void setThreadDescription(String threadDescription) {
        this.threadDescription = threadDescription;
    }

    public String getThreadTitle() {
        return threadTitle;
    }

    public void setThreadTitle(String threadTitle) {
        this.threadTitle = threadTitle;
    }

    public String getThreadDate() {
        return threadDate;
    }

    public void setThreadDate(String threadDate) {
        this.threadDate = threadDate;
    }

    public int getForumID() {
        return forumID;
    }

    public void setForumID(int forumID) {
        this.forumID = forumID;
    }

    public int getPlayerID() {
        return playerID;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }
}
