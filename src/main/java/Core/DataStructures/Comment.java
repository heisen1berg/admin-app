package Core.DataStructures;

import java.io.Serializable;

public class Comment implements Serializable {
    private long postId;
    private long commentId;
    private String text;
    private boolean autoModerated=false;

    public Comment(long postId, long commentId, String text) {
        this.postId = postId;
        this.commentId = commentId;
        this.text = text;
    }
    public void setPostId(long postId) {
        this.postId = postId;
    }
    public long getPostId() {
        return postId;
    }
    public void setCommentId(long commentId) {
        this.commentId = commentId;
    }
    public long getCommentId() {
        return commentId;
    }
    public void setText(String text){this.text = text;}
    public String getText() { return text;}
    public boolean getAutoModerated(){return autoModerated;}
    public void setAutoModerated(boolean autoModerated){this.autoModerated=autoModerated;}
}
