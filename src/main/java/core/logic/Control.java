package core.logic;

import core.cache.SubscriptionNotFoundException;
import core.data.PostEntity;
import core.data.Subscription;
import core.gui.CommandConsole;
import core.gui.CustomLogger;
import core.gui.LogLine;
import core.service.AutoModService;
import core.service.CacheService;
import core.service.DBService;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Date;
import java.util.concurrent.ExecutionException;

@Service
public class Control {
    public static String botApiSubscribeUrl = "";
    public static long adminId =0;
    @Resource
    private CommandConsole commandConsole;
    @Resource
    private DBService dbService;
    @Resource
    private CacheService cacheService;
    @Resource
    private AutoModService autoModService;
    @Resource
    private CustomLogger log;

    private RestTemplate restTemplate = new RestTemplate();

    public void subscribe(long postId, boolean sendToApiBot) {
        try {
            if(cacheService.getSubById(postId)==null)throw new SubscriptionNotFoundException();
        }
        catch (ExecutionException|SubscriptionNotFoundException e){
            final Date date=new Date();
            final Subscription sub=new Subscription(postId, date, Control.adminId);
            dbService.addPost(new PostEntity(postId, date,date));
            cacheService.putSub(sub);
            log.info("SUB: Subscribed to post " + sub.getPostId()+".", LogLine.TYPE_NEW_SUBSCRIPTION);
            if(sendToApiBot) {
                sendSubToApiBot(sub);
            }
        }

    }
    public void sendSubToApiBot(Subscription sub){
        final HttpEntity<Subscription> request = new HttpEntity<>(sub);
        //restTemplate.postForLocation(botApiSubscribeUrl, request, Subscription.class);
    }
    public void resetCache(){
        cacheService.resetCache();
    }
    public void addWordtoAutoModeration(String s){
        autoModService.addWord(s);
    }
    public void deleteWordFromAutomoderation(String s){
        autoModService.deleteWord(s);
    }
    public void init(){
        cacheService.init();
    }
}
