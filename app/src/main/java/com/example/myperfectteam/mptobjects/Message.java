package com.example.myperfectteam.mptobjects;

public class Message {
    private int messageID;
    private int messageToID;
    private String messageToName;
    private String messageDate;
    private String messageText;
    private int playerID;
    private String playerName;
    private int threadID;
    private int teamID;
    private String teamName;

    public Message(int messageID, int messageToID, String messageToName, String messageDate, String messageText, int playerID, String playerName, int threadID, int teamID, String teamName) {
        this.messageID = messageID;
        this.messageToID = messageToID;
        this.messageToName = messageToName;
        this.messageDate = messageDate;
        this.messageText = messageText;
        this.playerID = playerID;
        this.playerName = playerName;
        this.threadID = threadID;
        this.teamID = teamID;
        this.teamName = teamName;
    }

    public int getMessageID() {
        return messageID;
    }

    public void setMessageID(int messageID) {
        this.messageID = messageID;
    }

    public int getMessageToID() {
        return messageToID;
    }

    public void setMessageToID(int messageToID) {
        this.messageToID = messageToID;
    }

    public String getMessageToName() {
        return messageToName;
    }

    public void setMessageToName(String messageToName) {
        this.messageToName = messageToName;
    }

    public String getMessageDate() {
        return messageDate;
    }

    public void setMessageDate(String messageDate) {
        this.messageDate = messageDate;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public int getPlayerID() {
        return playerID;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getThreadID() {
        return threadID;
    }

    public void setThreadID(int threadID) {
        this.threadID = threadID;
    }

    public int getTeamID() {
        return teamID;
    }

    public void setTeamID(int teamID) {
        this.teamID = teamID;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    @Override
    public String toString() {
        return "Message{" +
                "messageID=" + messageID +
                ", messageToID=" + messageToID +
                ", messageToName='" + messageToName + '\'' +
                ", messageDate='" + messageDate + '\'' +
                ", messageText='" + messageText + '\'' +
                ", playerID=" + playerID +
                ", playerName='" + playerName + '\'' +
                ", threadID=" + threadID +
                ", teamID=" + teamID +
                ", teamName='" + teamName + '\'' +
                '}';
    }
}
