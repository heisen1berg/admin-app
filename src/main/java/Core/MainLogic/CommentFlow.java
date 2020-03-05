package Core.MainLogic;

import Core.DataStructures.Comment;
import Core.DataStructures.Post;
import Core.DataStructures.Subscription;
import Core.Services.AutoModService;
import Core.Services.CacheService;
import Core.Services.DBService;
import com.google.common.cache.CacheLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.ExecutionException;

@Service
public class CommentFlow {
    private static final int ADMIN_ID=0;
    @Autowired
    private CacheService cacheService;

    @Autowired
    private DBService dbService;

    @Autowired
    private AutoModService amService;

    @Autowired
    private BanFlow banFlow;

    public void addComment(Comment comment) throws ExecutionException {
        boolean auto_moderation_trigger=amService.process(comment);

        cacheService.putComment(comment);
        System.out.println("Comment cached");
        try {
            cacheService.updateLastCommentTime(comment.getPostId());
            System.out.println("Post last comment time updated and cached");
        }
        catch(CacheLoader.InvalidCacheLoadException e){
            Date date=new Date();
            cacheService.putSub(new Subscription(ADMIN_ID, comment.getPostId(), date));
            System.out.println("Subscription cached");
            dbService.addPost(new Post(comment.getPostId(),date,date));
            System.out.println("Post subscription added to DB");
        }

        if(auto_moderation_trigger){
            banFlow.createBan(comment);
        }
    }
}
