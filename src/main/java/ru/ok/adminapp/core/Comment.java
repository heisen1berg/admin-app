package ru.ok.adminapp.core;

public class Comment {
    private long postId;
    private long commentId;
    private String text;

    public Comment(long postId, long commentId, String text) {
        this.postId = postId;
        this.commentId = commentId;
        this.text = text;
    }

    public long getPostId() {
        return postId;
    }

    public long getCommentId() {
        return commentId;
    }

    public String getText() {
        return text;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

    public void setCommentId(long commentId) {
        this.commentId = commentId;
    }

    public void setText(String text) {
        this.text = text;
    }
}
