package Core.Services;

import Core.CachePackage.CustomCacheLoader;
import Core.DataStructures.*;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.LoadingCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.Serializable;
import java.util.concurrent.ExecutionException;
import java.util.Date;

@Service
public class CacheService {

    @Autowired
    private CustomCacheLoader loader;
    private LoadingCache<Long, Serializable> comment_cache;
    private LoadingCache<Long, Serializable> ban_cache;
    private LoadingCache<Long, Serializable> sub_cache;

    public void init() {
        comment_cache = CacheBuilder.newBuilder().build(loader);
        ban_cache = CacheBuilder.newBuilder().build(loader);
        sub_cache = CacheBuilder.newBuilder().build(loader);
    }
    public void putComment(Comment comment){
        comment_cache.put(comment.getCommentId(),comment);
    }
    public Comment getCommentById(Long id) throws ExecutionException {
        return (Comment)comment_cache.get(id);
    }
    public LoadingCache<Long, Serializable> getBanCache(){return ban_cache;}

    public void putBan(Ban ban){
        ban_cache.put(ban.getCommentId(),ban);
    }
    public Ban getBanById(Long id) throws ExecutionException {
        return (Ban) ban_cache.get(id);
    }
    public LoadingCache<Long, Serializable> getCommentCache(){return comment_cache;}

    public void putSub(Subscription sub){
        sub_cache.put(sub.getPostID(),sub);
    }
    public Subscription getSubById(Long id) throws ExecutionException {
        return (Subscription) sub_cache.get(id);
    }
    public void updateLastCommentTime(Long postId) throws ExecutionException {
        ((Subscription)sub_cache.get(postId)).setLastCommentTime(new Date());
    }
    public LoadingCache<Long, Serializable> getSubCache(){return sub_cache;}
}

