package Core.MainLogic;

import Core.DataStructures.Comment;
import Core.Services.AutoModService;
import Core.Services.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class CommentFlow {
    @Autowired
    CacheService cacheService;

    @Autowired
    AutoModService amService;

    @Autowired
    BanFlow banFlow;

    public void addComment(Comment comment) throws ExecutionException {
        cacheService.putComment(comment);
        cacheService.updateLastCommentTime(comment.getPostId());
        boolean auto_moderation_trigger=amService.process(comment);
        if(auto_moderation_trigger){
            comment.setAutoModerated(true);
            banFlow.createBan(comment);
        }
    }
}
