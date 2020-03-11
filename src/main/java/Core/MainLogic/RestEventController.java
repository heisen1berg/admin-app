package Core.MainLogic;

import Core.DataStructures.Comment;
import Core.DataStructures.Subscription;
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
    private ControlPanel controlPanel;
    @PostMapping("/admin/subscribe")
    public ResponseEntity<HttpStatus> processSubscribe(@RequestBody Subscription sub){
        controlPanel.subscribe(sub.getPostId(),false);
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @PostMapping("/admin/addComment")
    public ResponseEntity<HttpStatus> processComment(@RequestBody Comment comment) throws ExecutionException {
        commentFlow.addComment(comment);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/admin/dict/add-word")
    public ResponseEntity<HttpStatus> processAdd(@RequestBody String word) {
        controlPanel.addWordtoAutoModeration(word);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/admin/dict/remove-word")
    public ResponseEntity<HttpStatus> processDelete(@RequestBody String word) {
        controlPanel.deleteWordFromAutomoderation(word);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
