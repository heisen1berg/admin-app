package Core;

import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

//Подписка на пост: формирование запроса для бот-апи и вызов метода создания картеля в бд

public class SubscriptionService {

    private static RestTemplate restTemplate;
    // Адрес приема подписок у бот-апи
    private static final String SUBSCRIBE_URI = "http://localhost:8081/subscribe";

    public static void subscribe(long adminID, long postID) {
        restTemplate = new RestTemplate();
        Main.addSub((int)postID);
        final HttpEntity<Subscription> request = new HttpEntity<>(new Subscription(adminID, postID));
        final URI sub = restTemplate.postForLocation(SUBSCRIBE_URI, request, Subscription.class);
    }
}
