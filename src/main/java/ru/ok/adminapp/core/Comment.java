package ru.ok.adminapp.core;

public class Comment {
    long commentID;
    String text;

    public Comment(long commentID, String text) {
        this.commentID = commentID;
        this.text = text;
    }

    public void setCommentID(long commentID) {
        this.commentID = commentID;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getCommentID() {
        return commentID;
    }

    public String getText() {
        return text;
    }
}
