package core.service;

import core.data.PostEntity;
import core.db.PostRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

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
    public void disableSub(Long id){
        PostEntity postEntity=getPostById(id);
        postEntity.setActiveFlag(false);
        repo.save(postEntity);
    }
    public void updateLastCommentTime(Long id){
        PostEntity p=getPostById(id);
        p.setLastCommentTime(new Date());
        p.setActiveFlag(true);
        addPost(p);
    }
    public PostEntity getPostById(Long id){return repo.findById(id).get();}
}