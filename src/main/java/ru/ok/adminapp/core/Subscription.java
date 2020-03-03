package ru.ok.adminapp.core;


public class Subscription {

    private long adminID;
    private long postID;
    private long lastEditedTime;

    public Subscription(long adminID, long postID, long lastEditedTime) {
        this.adminID = adminID;
        this.postID = postID;
        this.lastEditedTime = lastEditedTime;
    }

    public long getLastEditedTime() {
        return lastEditedTime;
    }

    public void setLastEditedTime(long lastEditedTime) {
        this.lastEditedTime = lastEditedTime;
    }

    public long getAdminID() {
        return adminID;
    }

    public void setAdminID(int adminID) {
        this.adminID = adminID;
    }

    public long getPostID() {
        return postID;
    }

    public void setPostID(int postID) {
        this.postID = postID;
    }
}


