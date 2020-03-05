package Core.MainLogic;

import Core.DataStructures.Post;
import Core.DataStructures.Subscription;
import Core.Services.CacheService;
import Core.Services.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Date;

@Resource
public class ControlPanel {
    private static final int ADMIN_ID=0;
    private static final String BOT_API_SUBSCRIBE_URL = "";

    @Autowired
    private DBService dbService;
    @Autowired
    private CacheService cacheService;

    private static RestTemplate restTemplate = new RestTemplate();

    public void subscribe(long postId) {
        Date date=new Date();
        Subscription sub=new Subscription(ADMIN_ID, postId, date);
        dbService.addPost(new Post(postId, date,date));
        cacheService.putSub(sub);
        final HttpEntity<Subscription> request = new HttpEntity<>(sub);
        //restTemplate.postForLocation(BOT_API_SUBSCRIBE_URL, request, Subscription.class);
    }

}
