package ru.ok.adminapp.core;


public class Subscription {

    private long adminID;
    private long postID;

    public Subscription(long adminID, long postID) {
        this.adminID = adminID;
        this.postID = postID;
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


