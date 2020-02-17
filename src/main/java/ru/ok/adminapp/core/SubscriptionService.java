package ru.ok.adminapp.core;

import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

public class SubscriptionService {

    private static RestTemplate restTemplate;

    public static void subscribe(String subscribeURI, long adminID, long postID) {
        restTemplate = new RestTemplate();
        //sendSubToDB(adminID, postID);
        final HttpEntity<Subscription> request = new HttpEntity<>(new Subscription(adminID, postID));
        final URI sub = restTemplate.postForLocation(subscribeURI, request, Subscription.class);
    }
}
