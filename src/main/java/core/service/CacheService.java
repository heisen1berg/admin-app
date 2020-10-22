package core.service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import core.cache.*;
import core.data.Ban;
import core.data.Comment;
import core.data.PostEntity;
import core.data.Subscription;
import core.logic.Control;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.ExecutionException;

@Service
public class CacheService {
    @Resource
    private CacheStorage storage;
    @Resource
    private CustomCacheLoader loader;
    @Resource
    private DBService dbService;

    private LoadingCache<Key, Cachable> comment_cache;
    private LoadingCache<Key, Cachable> ban_cache;
    private LoadingCache<Key, Cachable> sub_cache;

    public void init() {
        comment_cache = CacheBuilder.newBuilder().build(loader);
        ban_cache = CacheBuilder.newBuilder().build(loader);
        sub_cache = CacheBuilder.newBuilder().build(loader);
        loadSubsFromDB();
    }

    public void putComment(Comment comment){
        storage.put(comment);
        comment_cache.put(new Key(comment.getCommentId(),Comment.class),comment);
    }
    public Comment getCommentById(Long id) throws ExecutionException {
        return (Comment)comment_cache.get(new Key(id,Comment.class) );
    }
    public LoadingCache<Key, Cachable> getCommentCache(){return comment_cache;}

    public void putBan(Ban ban){
        storage.put(ban);
        ban_cache.put(new Key(ban.getCommentId(),Ban.class),ban);
    }
    public Ban getBanById(Long id) throws ExecutionException {
        return (Ban) ban_cache.get(new Key(id,Ban.class));
    }
    public LoadingCache<Key, Cachable> getBanCache(){return ban_cache;}

    public void putSub(Subscription sub){
        storage.put(sub);
        sub_cache.put(new Key(sub.getPostId(),Subscription.class),sub);
    }
    public void removeSub(Subscription sub){
        sub_cache.asMap().values().remove(sub);
        storage.removeSub(sub);
    }
    public Subscription getSubById(Long id) throws ExecutionException, CacheLoader.InvalidCacheLoadException, SubscriptionNotFoundException {
        Subscription sub;
        try{
            return(Subscription)sub_cache.get(new Key(id,Subscription.class));
        }
        catch(CacheLoader.InvalidCacheLoadException e){
            try {
                final PostEntity postEntity=dbService.getPostById(id);
                return new Subscription(postEntity.getPostID(),postEntity.getLastCommentTime(),Control.adminId);
            }
            catch(NoSuchElementException e1){
                throw new SubscriptionNotFoundException();
            }
        }
    }
    public void updateLastCommentTime(Long postId) throws SubscriptionNotFoundException,ExecutionException{
        Subscription sub;
        try{
            sub=(Subscription)sub_cache.get(new Key(postId,Subscription.class));
            sub.setLastCommentTime(new Date());
            putSub(sub);
        }
        catch(CacheLoader.InvalidCacheLoadException e){
            try {
                dbService.getPostById(postId);
                putSub(new Subscription(postId,new Date(), Control.adminId));
                dbService.updateLastCommentTime(postId);
            }
            catch(NoSuchElementException e1){
                throw new SubscriptionNotFoundException();
            }
        }
        dbService.updateLastCommentTime(postId);
    }
    public LoadingCache<Key, Cachable> getSubCache(){return sub_cache;}
    public void loadSubsFromDB(){
        List<PostEntity> posts=dbService.getAllSubs();
        for(PostEntity p:posts){
            if(p.getActiveFlag()){putSub(new Subscription(p.getPostID(),p.getLastCommentTime(), Control.adminId));};
        }
    }
    public List<Subscription> getAllSubs(){
        return new ArrayList(sub_cache.asMap().values());
    }
    public void disableSub(Subscription s){
        removeSub(s);
        dbService.disableSub(s.getPostId());
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

