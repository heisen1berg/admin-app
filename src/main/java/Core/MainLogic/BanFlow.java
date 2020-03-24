package Core.MainLogic;

import Core.DataStructures.Comment;
import Core.DataStructures.Ban;
import Core.Interface.CustomLogger;
import Core.Interface.LogLine;
import Core.Services.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@Service
public class BanFlow {
    private static final String JOURNAL_BAN_URL="";
    //заменить автовиреды
    @Resource
    CacheService cacheService;
    @Resource
    private CustomLogger log;

    private static RestTemplate restTemplate = new RestTemplate();

    public void createBan(Comment comment){
        Ban ban=new Ban(comment);
        cacheService.putBan(ban);
        log.info("BAN: Comment "+ban.getCommentId()+" has been banned.", LogLine.TYPE_BAN_CREATED);
        final HttpEntity<Ban> request = new HttpEntity<>(ban);
        //restTemplate.postForLocation(JOURNAL_BAN_URL,request,Ban.class);
    }
}
