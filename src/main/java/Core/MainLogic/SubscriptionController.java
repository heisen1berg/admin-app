package Core.MainLogic;

import Core.DataStructures.Subscription;
import Core.Services.SubscriptionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class SubscriptionController {
    private static final String SUBSCRIBE_URL = "http://localhost:8081/subscribe";

    @PostMapping(SUBSCRIBE_URL)
    public ResponseEntity subscribe(@RequestBody Subscription subscription) {
        SubscriptionService.subscribe("http://localhost:8081/subscribe", subscription.getAdminID(), subscription.getPostID());
        return ResponseEntity.ok(HttpStatus.OK);
    }

}
