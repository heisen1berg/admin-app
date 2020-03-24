package Core.MainLogic;

import Core.DataStructures.PostEntity;
import Core.DataStructures.Subscription;
import Core.Interface.CustomLogger;
import Core.Interface.LogLine;
import Core.Services.AutoModService;
import Core.Services.CacheService;
import Core.Services.DBService;
import Core.Interface.CommandConsole;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class ControlPanel {
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
        Date date=new Date();
        Subscription sub=new Subscription(postId, date,ControlPanel.adminId);
        dbService.addPost(new PostEntity(postId, date,date));
        cacheService.putSub(sub);
        log.info("SUB: Subscribed to post " + sub.getPostId()+".", LogLine.TYPE_NEW_SUBSCRIPTION);
        if(sendToApiBot) {
            sendSubToApiBot(sub);
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
