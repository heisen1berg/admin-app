package Core.Services;

import Core.CachePackage.CustomCacheLoader;
import Core.CachePackage.Key;
import Core.CachePackage.Serializer;
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
    private Serializer ser;
    @Autowired
    private CustomCacheLoader loader;

    private LoadingCache<Key, Serializable> comment_cache;
    private LoadingCache<Key, Serializable> ban_cache;
    private LoadingCache<Key, Serializable> sub_cache;

    public void init() {
        comment_cache = CacheBuilder.newBuilder().build(loader);
        ban_cache = CacheBuilder.newBuilder().build(loader);
        sub_cache = CacheBuilder.newBuilder().build(loader);
    }
    public void putComment(Comment comment){
        ser.serialize(new Key(comment.getCommentId(),Comment.class),comment);
        comment_cache.put(new Key(comment.getCommentId(),Comment.class),comment);
    }
    public Comment getCommentById(Long id) throws ExecutionException {
        return (Comment)comment_cache.get(new Key(id,Comment.class) );
    }
    public LoadingCache<Key, Serializable> getBanCache(){return ban_cache;}

    public void putBan(Ban ban){
        ser.serialize(new Key(ban.getCommentId(),Ban.class),ban);
        ban_cache.put(new Key(ban.getCommentId(),Ban.class),ban);
    }
    public Ban getBanById(Long id) throws ExecutionException {
        return (Ban) ban_cache.get(new Key(id,Ban.class));
    }
    public LoadingCache<Key, Serializable> getCommentCache(){return comment_cache;}

    public void putSub(Subscription sub){
        ser.serialize(new Key(sub.getPostID(),Subscription.class),sub);
        sub_cache.put(new Key(sub.getPostID(),Subscription.class),sub);
    }
    public Subscription getSubById(Long id) throws ExecutionException {
        return (Subscription) sub_cache.get(new Key(id,Subscription.class));
    }
    public void updateLastCommentTime(Long postId) throws ExecutionException {
        Subscription sub=(Subscription)sub_cache.get(new Key(postId,Subscription.class));
        sub.setLastCommentTime(new Date());
        ser.serialize(new Key(postId,Subscription.class),sub);
    }
    public LoadingCache<Key, Serializable> getSubCache(){return sub_cache;}
}

