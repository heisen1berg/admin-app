package Core.MainLogic;

import Core.DataStructures.Comment;
import Core.DataStructures.Ban;
import Core.DataStructures.Subscription;
import Core.Services.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BanFlow {
    @Autowired
    CacheService cacheService;

    private static final String JOURNAL_BAN_URL="";
    private static RestTemplate restTemplate = new RestTemplate();

    public void createBan(Comment comment){
        Ban ban=new Ban(comment);
        cacheService.putBan(ban);
        final HttpEntity<Ban> request = new HttpEntity<>(ban);
        restTemplate.postForLocation(JOURNAL_BAN_URL,request,Ban.class);
    }
}
