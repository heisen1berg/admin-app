package core.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name="posts")
public class PostEntity {
    @Id
    @Column(name="post_id")
    private long postID;
    @Column(name="created_at")
    private Date createdAt;
    @Column(name="last_comment_time")
    private Date lastCommentTime;
    @Column(name="active_flag")
    private boolean activeFlag;

    public long getPostID(){return postID;}
    public void setPostID(long PostID){this.postID =PostID;}
    public Date getCreatedAt(){return createdAt;}
    public void setCreatedAt(Date createdAt){this.createdAt=createdAt;}
    public Date getLastCommentTime(){return lastCommentTime;}
    public void setLastCommentTime(Date lastCommentTime){this.lastCommentTime = lastCommentTime;}
    public boolean getActiveFlag(){return activeFlag;}
    public void setActiveFlag(boolean activeFlag){this.activeFlag=activeFlag;}
    public PostEntity(){}
    public PostEntity(long PostID, Date createdAt, Date lastCommentTime){
        this.postID =PostID;
        this.createdAt=createdAt;
        this.lastCommentTime =lastCommentTime;
        this.activeFlag=true;
    }
    public PostEntity(long PostID, Date createdAt, Date lastCommentTime,boolean activeFlag){
        this.postID =PostID;
        this.createdAt=createdAt;
        this.lastCommentTime =lastCommentTime;
        this.activeFlag=activeFlag;
    }
}
