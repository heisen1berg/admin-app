package ru.ok.adminapp.core;

import java.io.*;

public class Ban implements Serializable {
    private long postId;
    private long commentId;

    public Ban(long postId, long commentId) {
        this.postId = postId;
        this.commentId = commentId;
    }

    public long getCommentId() {
        return commentId;
    }

    public long getPostId() {
        return postId;
    }

    public void setCommentId(long commentId) {
        this.commentId = commentId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }
}
