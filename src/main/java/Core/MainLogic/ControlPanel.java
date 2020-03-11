package Core.MainLogic;

import Core.DataStructures.Post;
import Core.DataStructures.Subscription;
import Core.Services.AutoModService;
import Core.Services.CacheService;
import Core.Services.DBService;
import Core.UI.CommandConsole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

@Service
public class ControlPanel {
    public static String botApiSubscribeUrl = "";
    public static long adminId =0;
    @Autowired
    private CommandConsole commandConsole;
    @Autowired
    private DBService dbService;
    @Autowired
    private CacheService cacheService;
    @Autowired
    private AutoModService autoModService;

    private RestTemplate restTemplate = new RestTemplate();

    public void subscribe(long postId, boolean sendToApiBot) {
        Date date=new Date();
        Subscription sub=new Subscription(postId, date,ControlPanel.adminId);
        dbService.addPost(new Post(postId, date,date));
        cacheService.putSub(sub);

        if(sendToApiBot) {
            sendSubToApiBot(sub);
        }
    }
    public void sendSubToApiBot(Subscription sub){
        final HttpEntity<Subscription> request = new HttpEntity<>(sub);
        restTemplate.postForLocation(botApiSubscribeUrl, request, Subscription.class);
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
