package Core.Services;

import Core.DataStructures.Post;
import Core.DataStructures.Subscription;
import org.hibernate.engine.jdbc.spi.JdbcServices;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.time.Instant;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class ServerStatusService {
    private static final String BOT_API_STATE_URL = "http://localhost:8080/bot-api/state";
    private static final String BOT_API_SUBSCRIPTION_STATE_URL = "http://localhost:8080/bot-api/subscriptionstate";


    private static RestTemplate restTemplate = new RestTemplate();
    private static long lastActionTime = Long.MAX_VALUE;

    @Autowired
    private CacheService cacheService;

    // Сравниваем время взаимодействия с бот апи
    /*@Scheduled(fixedDelay = 10000)
    private void checkForBotApiActivity() throws ExecutionException {
        if (Instant.now().getEpochSecond() - lastActionTime > 120000) {
            if (!checkState(BOT_API_STATE_URL, "")) {
                connectWithJournal();
            }
        }
        List<Long> subIdList = new ArrayList<>();
        //subIdList = DataBase.getSubIdList();
        for (long id: subIdList) {
            Subscription sub = cacheService.getSubById(id);
            if (sub == null) {
                // sub = DataBase.getSubscriptionByID(id);
            }
            if (Instant.now().getEpochSecond() - sub.getLastCommentTime().getTime() > 60000) {
                if (!checkState(BOT_API_SUBSCRIPTION_STATE_URL, sub)) {
                    subscribe(sub.getPostID());
                }
            }
        }
    }*/

    // Проверяем, все ли в порядке с бот апи
    private <T> boolean checkState(String url, T t) {
        final HttpEntity<T> request = new HttpEntity<>(t);
        final Object state = restTemplate.postForObject(url, request, t.getClass());
        return state.toString().equals("OK");
    }

    // Соединяемся с журналом по RMI и запрашиваем новые посты
    private void connectWithJournal() {

    }
}
