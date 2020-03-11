package Core.DataStructures;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name="Posts")
//PostEntity
public class Post {
    @Id
    //camel-case, все с маленькой в бд
    private long PostID;
    private Date createdAt;
    private Date lastCommentTime;
    private boolean activeFlag;

    public long getPostID(){return PostID;}
    public void setPostID(long PostID){this.PostID=PostID;}
    public Date getCreatedAt(){return createdAt;}
    public void setCreatedAt(Date createdAt){this.createdAt=createdAt;}
    public Date getLastCommentTime(){return lastCommentTime;}
    public void setLastCommentTime(Date lastCommentTime){this.lastCommentTime = lastCommentTime;}
    public boolean getActiveFlag(){return activeFlag;}
    public void setActiveFlag(boolean activeFlag){this.activeFlag=activeFlag;}
    public Post(){}
    public Post(long PostID, Date createdAt, Date lastCommentTime){
        this.PostID=PostID;
        this.createdAt=createdAt;
        this.lastCommentTime =lastCommentTime;
        this.activeFlag=true;
    }
}
