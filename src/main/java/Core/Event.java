package Core;

public class Event {
    private long postId;
    private long commentId;
    private String text;

    public Event(long postId, long commentId, String text) {
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
