package ru.ok.adminapp.core;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class RestEventController {

    private final String botApiURI = "http://localhost:8080/asd";
    // как дополнение к коду Никиты
    @GetMapping("/receiveCase")
    public Comment sendCase(@RequestBody Admin admin) {
        return DemoApplication.queues.get(admin.getAdminID()).poll();
    }

    @PostMapping("/ban")
    public ResponseEntity banComment(@RequestBody Response msg) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Response> request = new HttpEntity<>(new Response(
                msg.getAdminId(), msg.getCommentId()
        ));
        restTemplate.postForLocation(botApiURI, request, Response.class);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}

