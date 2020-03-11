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
        try {
            cacheService.updateLastCommentTime(comment.getPostId());
        }
        catch(CacheLoader.InvalidCacheLoadException e){
            //игнорить
            Date date=new Date();
            cacheService.putSub(new Subscription(comment.getPostId(), date,ControlPanel.adminId));
            dbService.addPost(new Post(comment.getPostId(),date,date));
        }

        if(auto_moderation_trigger){
            banFlow.createBan(comment);
        }
    }
}
