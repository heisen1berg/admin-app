package core.logic;

import core.data.Comment;
import core.data.Subscription;
import core.service.ServerStatusService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.ExecutionException;

@RestController
public class RestEventController {
    @Resource
    private CommentFlow commentFlow;
    @Resource
    private Control control;
    @Resource
    private ServerStatusService serverStatusService;

    @PostMapping("/state")
    public HttpEntity<String> checkState() {
        HttpEntity<String> send=new HttpEntity("OK");
        return send;
    }
    @PostMapping("/admin/subscribe")
    public ResponseEntity<HttpStatus> processSubscribe(@RequestBody Subscription sub){
        control.subscribe(sub.getPostId(),false);
        serverStatusService.updateLastRequestTime();
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/admin/addComment")
    public ResponseEntity<HttpStatus> processComment(@RequestBody Comment comment) throws ExecutionException {
        commentFlow.addComment(comment);
        serverStatusService.updateLastRequestTime();
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/admin/dict/add-word")
    public ResponseEntity<HttpStatus> processAdd(@RequestBody String word) {
        control.addWordtoAutoModeration(word);
        serverStatusService.updateLastRequestTime();
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/admin/dict/remove-word")
    public ResponseEntity<HttpStatus> processDelete(@RequestBody String word) {
        control.deleteWordFromAutomoderation(word);
        serverStatusService.updateLastRequestTime();
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
