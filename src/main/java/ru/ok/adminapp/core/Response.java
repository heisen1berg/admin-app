package ru.ok.adminapp.core;

public class Response {
    private long adminId;
    private long commentId;

    public Response(long adminId, long commentId) {
        this.adminId = adminId;
        this.commentId = commentId;
    }

    public long getCommentId() {
        return commentId;
    }

    public long getAdminId() {
        return adminId;
    }
}