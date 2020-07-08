package com.example.myperfectteam.mptobjects;

public class Forum {
    private int forumID;
    private String forumName;
    private String forumDescription;

    public Forum(int forumID, String forumName, String forumDescription) {
        this.forumID = forumID;
        this.forumName = forumName;
        this.forumDescription = forumDescription;
    }

    public int getForumID() {
        return forumID;
    }

    public void setForumID(int forumID) {
        this.forumID = forumID;
    }

    public String getForumName() {
        return forumName;
    }

    public void setForumName(String forumName) {
        this.forumName = forumName;
    }

    public String getForumDescription() {
        return forumDescription;
    }

    public void setForumDescription(String forumDescription) {
        this.forumDescription = forumDescription;
    }
}
