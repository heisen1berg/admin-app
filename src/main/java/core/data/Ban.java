package core.data;

import core.cache.Cachable;
import core.cache.Key;

import java.io.Serializable;

public class Ban implements Serializable, Cachable {
    private long postId;
    private long commentId;
    private Key key=new Key(commentId,Ban.class);
    @Override
    public Key getKey(){return key;}

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
