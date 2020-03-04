package ru.ok.adminapp.core;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class SubscriptionController {

    final static private String SUBSCRIBE_URI = "/admin/subscribe";

    //Подписка админа по HTTP
    @PostMapping(SUBSCRIBE_URI)
    public ResponseEntity subscribe(@RequestBody Subscription subscription) {
        SubscriptionService.subscribe("http://localhost:8081/subscribe", subscription.getAdminID(), subscription.getPostID());
        return ResponseEntity.ok(HttpStatus.OK);
    }

}
