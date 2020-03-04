package Core.DBPackage;

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
    Date lastRequestTime;

    public Long getPostID(){return PostID;}
    public void setPostID(Long PostID){this.PostID=PostID;}
    public Date getCreatedAt(){return createdAt;}
    public void setCreatedAt(Date createdAt){this.createdAt=createdAt;}
    public Date getLastRequestTime(){return lastRequestTime;}
    public void setLastRequestTime(Date lastRequestTime){this.lastRequestTime=lastRequestTime;}
    public Post(){}
    public Post(Long PostID, Date createdAt, Date lastRequestTime){
        this.PostID=PostID;
        this.createdAt=createdAt;
        this.lastRequestTime=lastRequestTime;
    }
}
