package Core.DataStructures;


import java.io.Serializable;
import java.util.Date;
public class Subscription implements Serializable {

    private long adminID;
    private long postID;
    private Date lastCommentTime;

    public Subscription(long adminID, long postID, Date lastEditedTime) {
        this.adminID = adminID;
        this.postID = postID;
        this.lastCommentTime = lastEditedTime;
    }

    public Date getLastCommentTime() {
        return lastCommentTime;
    }

    public void setLastCommentTime(Date lastEditedTime) {
        this.lastCommentTime = lastEditedTime;
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


