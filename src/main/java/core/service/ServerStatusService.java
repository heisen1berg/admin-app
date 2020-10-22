package core.service;

import core.gui.CustomLogger;
import core.logic.Control;
import org.springframework.http.HttpEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.time.Instant;
import java.util.Date;
import java.util.concurrent.ExecutionException;

@Service
@EnableScheduling
public class ServerStatusService {
    private static final String BOT_API_STATE_URL = "http://localhost:8081/state";
    private static final String BOT_API_SUBSCRIPTION_STATE_URL = "http://localhost:8081/subscriptionstate";

    private boolean serverConnection=true;
    private RestTemplate restTemplate = new RestTemplate();
    private Date lastRequestTime=new Date();

    @Resource
    private CacheService cacheService;
    @Resource
    private Control control;
    @Resource
    private CustomLogger log;

    public void updateLastRequestTime(){
        lastRequestTime=new Date();
    }

    @Scheduled(fixedDelay = 10000)
    private void scheduledCheck() throws ExecutionException {
        if (Instant.now().getEpochSecond()-lastRequestTime.getTime()/1000 > 120) {
            if (!checkState(BOT_API_STATE_URL, "")) {
                serverConnection=false;
                log.warning("WARNING: No connection to server");
            }
        }
        else{serverConnection=true;}
        /*if(serverConnection) {
            List<Subscription> subList = cacheService.getAllSubs();
            for (Subscription s : subList) {
                if (Instant.now().getEpochSecond() - s.getLastCommentTime().getTime()/1000 > 10) {
                    if (!checkState(BOT_API_SUBSCRIPTION_STATE_URL, s)) {

                        cacheService.disableSub(s);
                        control.sendSubToApiBot(s);
                        log.info("SUB: Subscription on post "+s.getPostId()+" turned inactive.",
                                LogLine.TYPE_SUBSCRIPTION_INACTIVE);
                    }
                }
            }
        }*/
    }

    private <T> boolean checkState(String url, T t) {
        final HttpEntity<T> request = new HttpEntity<>(t);
        try {
            restTemplate.postForObject(url, request, t.getClass());
            return true;
        }
        catch(Exception e){
            return false;
        }
    }


}
