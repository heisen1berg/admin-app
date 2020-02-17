package Core;

import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

//Подписка на пост: формирование запроса для бот-апи и вызов метода создания картеля в бд

public class SubscriptionService {

    private static RestTemplate restTemplate;

    public static void subscribe(String subscribeURI, long adminID, long postID) {
        restTemplate = new RestTemplate();
        Main.addSub((int)postID);
        final HttpEntity<Subscription> request = new HttpEntity<>(new Subscription(adminID, postID));
        final URI sub = restTemplate.postForLocation(subscribeURI, request, Subscription.class);
    }
}
