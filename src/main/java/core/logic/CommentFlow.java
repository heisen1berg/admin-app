package core.logic;

import core.cache.SubscriptionNotFoundException;
import core.data.Comment;
import core.gui.CustomLogger;
import core.gui.LogLine;
import core.service.AutoModService;
import core.service.CacheService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.ExecutionException;

@Service
public class CommentFlow {
    @Resource
    private CacheService cacheService;
    @Resource
    private AutoModService amService;
    @Resource
    private BanFlow banFlow;
    @Resource
    private CustomLogger log;

    public void addComment(Comment comment) throws ExecutionException {
        try {
            cacheService.updateLastCommentTime(comment.getPostId());
        }
        catch(SubscriptionNotFoundException e){
            log.warning("WARNING: Received comment for unsubscribed post.");
            return;
        }
        boolean auto_moderation_trigger=amService.process(comment);

        cacheService.putComment(comment);
        log.info("COMMENT: Comment " + comment.getCommentId() + " on post "+ comment.getPostId()+" received.",
                LogLine.TYPE_COMMENT_RECEIVED);

        if(auto_moderation_trigger){
            banFlow.createBan(comment);
        }
    }
}
