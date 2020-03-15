package Core.Services;

import Core.DataStructures.Subscription;
import Core.MainLogic.ControlPanel;
import org.springframework.http.HttpEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import javax.annotation.Resource;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class ServerStatusService {
    private static final String BOT_API_STATE_URL = "http://localhost:8080/bot-api/state";
    private static final String BOT_API_SUBSCRIPTION_STATE_URL = "http://localhost:8080/bot-api/subscriptionstate";

    private boolean serverConnection=true;
    private RestTemplate restTemplate = new RestTemplate();
    private Date lastRequestTime;

    @Resource
    private CacheService cacheService;

    @Resource
    private ControlPanel controlPanel;

    public void updateLastRequestTime(){
        lastRequestTime=new Date();
    }

    @Scheduled(fixedDelay = 10000)
    private void scheduledCheck() throws ExecutionException {
        if (Instant.now().getEpochSecond()-lastRequestTime.getTime()/1000 > 120) {
            if (!checkState(BOT_API_STATE_URL, "")) {
                serverConnection=false;
            }
        }
        else{serverConnection=true;}
        if(serverConnection) {
            List<Subscription> subList = cacheService.getAllActiveSubs();
            for (Subscription s : subList) {
                if (Instant.now().getEpochSecond() - s.getLastCommentTime().getTime()/1000 > 60) {
                    if (!checkState(BOT_API_SUBSCRIPTION_STATE_URL, s)) {
                        controlPanel.sendSubToApiBot(s);
                    }
                }
            }
        }
    }

    // Проверяем, все ли в порядке с бот апи
    private <T> boolean checkState(String url, T t) {
        final HttpEntity<T> request = new HttpEntity<>(t);
        try {
            final Object state = restTemplate.postForObject(url, request, t.getClass());
            return state.toString().equals("OK");
        }
        catch(Exception e){
            return false;
        }
    }


}
