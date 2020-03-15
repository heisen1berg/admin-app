package Core.Services;
import java.util.Date;
import java.util.List;

import Core.DBPackage.PostRepository;
import Core.DataStructures.PostEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class DBService {

    @Resource
    private PostRepository repo;

    public List<PostEntity> list() {
        return repo.findAll();
    }

    public boolean addPost(PostEntity p){
        try {
            repo.save(p);
            return true;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }
    public List<PostEntity> getAllSubs(){
        return repo.findAll();
    }
    public void updateLastCommentTime(Long id){
        PostEntity p=getPostById(id);
        p.setLastCommentTime(new Date());
        addPost(p);
    }
    public PostEntity getPostById(Long id){return repo.findById(id).get();}
}