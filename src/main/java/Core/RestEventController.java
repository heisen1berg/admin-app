package Core;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//Контроллер, который будет использоваться для общения с бот-апи, прием постов и комментов к ним и т.д.

@RestController
public class RestEventController {
    final static private String BAN_URI = "/bot-api/ban";
    final static private String EVENTS_URI = "/admin/events";
    final static private String SUBSCRIBE_URI = "/admin/subscribe";

    @PostMapping(BAN_URI)
    public ResponseEntity processBan(@RequestBody Ban ban) {
        System.err.printf("Post %d, comment %d banned\n",
                ban.getPostId(), ban.getCommentId());
        return ResponseEntity.ok(HttpStatus.OK);
    }

    //Подписка админа по HTTP
    @PostMapping(SUBSCRIBE_URI)
    public ResponseEntity subscribe(@RequestBody Subscription subscription) {
        SubscriptionService.subscribe(subscription.getAdminID(), subscription.getPostID());
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping(EVENTS_URI)
    public ResponseEntity processEvent(@RequestBody Event event) {
        new ServiceHandler().process(event, "http://localhost:8080" +
                BAN_URI);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
