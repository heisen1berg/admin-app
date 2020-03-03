package ru.ok.adminapp.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.validation.constraints.Null;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class SubscriptionService {

    private static RestTemplate restTemplate = new RestTemplate();
    private static long lastActionTime = Long.MAX_VALUE;
    private static String defaultBotApiSubscribeURL = "http://localhost:8080/bot-api/subscribe";
    private static String defaultBotApiStateURL = "http://localhost:8080/bot-api/state";
    private static String defaultBotApiSubscriptionStateURL = "http://localhost:8080/bot-api/subscriptionstate";

    @Autowired
    private static CacheConfig cache;


    // Запрос о подписке
    public static void subscribe(String subscribeURI, long adminID, long postID) {
        Instant instant = Instant.now();
        long time = instant.getEpochSecond();
        //sendSubToDataBase(adminID, postID, time);
        final HttpEntity<Subscription> request = new HttpEntity<>(new Subscription(adminID, postID, time));
        restTemplate.postForLocation(subscribeURI, request, Subscription.class);
    }


    // Сравниваем время взаимодействия с бот апи
    @Scheduled(fixedDelay = 10000)
    private void checkForBotApiActivity() throws ExecutionException {
        if (Instant.now().getEpochSecond() - lastActionTime > 120000) {
            if (!checkState(defaultBotApiStateURL, "")) {
                connectWithJournal();
            }
        }
        List<Long> subIdList = new ArrayList<>();
        //subIdList = DataBase.getSubIdList();
        for (long id: subIdList) {
            Subscription sub = cache.getSubscription(id);
            if (sub == null) {
                // sub = DataBase.getSubscriptionByID(id);
            }
            if (Instant.now().getEpochSecond() - sub.getLastEditedTime() > 60000) {
                if (!checkState(defaultBotApiSubscriptionStateURL, sub)) {
                    subscribe(defaultBotApiSubscribeURL, sub.getAdminID(), sub.getPostID());
                }
            }
        }
    }

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
