package Core.Services;

import Core.CachePackage.*;
import Core.DataStructures.*;
import Core.MainLogic.ControlPanel;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.LoadingCache;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.Date;

@Service
public class CacheService {
    @Resource
    private CacheStorage ser;
    @Resource
    private CustomCacheLoader loader;
    @Resource
    private DBService dbService;

    private LoadingCache<Key, Serializable> comment_cache;
    private LoadingCache<Key, Serializable> ban_cache;
    private LoadingCache<Key, Serializable> sub_cache;

    public void init() {
        comment_cache = CacheBuilder.newBuilder().build(loader);
        ban_cache = CacheBuilder.newBuilder().build(loader);
        sub_cache = CacheBuilder.newBuilder().build(loader);
        loadSubsFromDB();
    }

    public void putComment(Comment comment){
        ser.put(new Key(comment.getCommentId(),Comment.class),comment);
        comment_cache.put(new Key(comment.getCommentId(),Comment.class),comment);
    }
    public Comment getCommentById(Long id) throws ExecutionException {
        return (Comment)comment_cache.get(new Key(id,Comment.class) );
    }
    public LoadingCache<Key, Serializable> getCommentCache(){return comment_cache;}

    public void putBan(Ban ban){
        ser.put(new Key(ban.getCommentId(),Ban.class),ban);
        ban_cache.put(new Key(ban.getCommentId(),Ban.class),ban);
    }
    public Ban getBanById(Long id) throws ExecutionException {
        return (Ban) ban_cache.get(new Key(id,Ban.class));
    }
    public LoadingCache<Key, Serializable> getBanCache(){return ban_cache;}

    public void putSub(Subscription sub){
        ser.put(new Key(sub.getPostId(),Subscription.class),sub);
        sub_cache.put(new Key(sub.getPostId(),Subscription.class),sub);
    }
    public Subscription getSubById(Long id) throws ExecutionException {
        return (Subscription) sub_cache.get(new Key(id,Subscription.class));
    }
    public void updateLastCommentTime(Long postId) throws ExecutionException {
        Subscription sub=(Subscription)sub_cache.get(new Key(postId,Subscription.class));
        sub.setLastCommentTime(new Date());
        ser.put(new Key(postId,Subscription.class),sub);
        dbService.updateLastCommentTime(postId);
    }
    public LoadingCache<Key, Serializable> getSubCache(){return sub_cache;}
    public void loadSubsFromDB(){
        List<PostEntity> posts=dbService.getAllSubs();
        for(PostEntity p:posts){
            if(p.getActiveFlag()){putSub(new Subscription(p.getPostID(),p.getLastCommentTime(), ControlPanel.adminId));};
        }
    }
    public List getAllActiveSubs(){
        ArrayList<Subscription> ar = new ArrayList(sub_cache.asMap().values());
        for (Subscription s:ar){
            if(!s.isActive()){ar.remove(s);}
        }
        return ar;
    }

    public void resetCache(){
        File dir=new File("cache");
        File[] files=dir.listFiles();
        for(File f:files){
            f.delete();
        }
        init();
    }
}

