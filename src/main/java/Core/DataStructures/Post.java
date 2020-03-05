package Core.DataStructures;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name="Posts")
public class Post {

    @Id
    Long PostID;
    Date createdAt;
    Date lastCommentTime;

    public Long getPostID(){return PostID;}
    public void setPostID(Long PostID){this.PostID=PostID;}
    public Date getCreatedAt(){return createdAt;}
    public void setCreatedAt(Date createdAt){this.createdAt=createdAt;}
    public Date getLastCommentTime(){return lastCommentTime;}
    public void setLastCommentTime(Date lastCommentTime){this.lastCommentTime = lastCommentTime;}
    public Post(){}
    public Post(Long PostID, Date createdAt, Date lastCommentTime){
        this.PostID=PostID;
        this.createdAt=createdAt;
        this.lastCommentTime =lastCommentTime;
    }
}
