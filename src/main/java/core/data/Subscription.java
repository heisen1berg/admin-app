package core.data;


import core.cache.Cachable;
import core.cache.Key;

import java.io.Serializable;
import java.util.Date;
public class Subscription implements Serializable, Cachable {
    private long adminId;
    private long postId;
    private Date lastCommentTime;
    private boolean activeFlag;

    private Key key=new Key(postId,Subscription.class);
    @Override
    public Key getKey(){return key;}

    public Subscription(long postId, Date lastEditedTime, long adminId) {
        this.adminId=adminId;
        this.postId = postId;
        this.lastCommentTime = lastEditedTime;
        activeFlag=true;
    }

    public Date getLastCommentTime() {
        return lastCommentTime;
    }

    public void setLastCommentTime(Date lastEditedTime) {
        this.lastCommentTime = lastEditedTime;
    }

    public long getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public boolean isActive(){return activeFlag;}
    public void setActive(boolean activeFlag){this.activeFlag=activeFlag;}

    public long getAdminId(){return adminId;}
    public void setAdminId(Long adminId){this.adminId = adminId;}
}


