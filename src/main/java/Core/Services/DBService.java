package Core.Services;
import java.util.Date;
import java.util.List;

import Core.DBPackage.PostRepo;
import Core.DataStructures.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class DBService {

    @Autowired
    private PostRepo repo;

    public List<Post> list() {
        return repo.findAll();
    }

    public boolean addPost(Post p){
        try {
            repo.save(p);
            return true;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }
    public List<Post> getAllSubs(){
        return repo.findAll();
    }
    public void updateLastCommentTime(Long id){
        Post p=getPostById(id);
        p.setLastCommentTime(new Date());
        addPost(p);
    }
    public Post getPostById(Long id){return repo.findById(id).get();}
}