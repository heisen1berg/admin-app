package Core.DataStructures;

import java.io.Serializable;

public class Ban implements Serializable {
    private long postId;
    private long commentId;

    public Ban(Comment comment) {
        postId = comment.getPostId();
        commentId = comment.getCommentId();
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
