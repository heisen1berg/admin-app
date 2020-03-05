package Core.MainLogic;

import Core.DataStructures.Comment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import Core.Services.DBService;

import java.util.concurrent.ExecutionException;

@RestController
public class RestEventController {
    @Resource
    private CommentFlow controlFlow;

    @PostMapping("/admin/addComment")
    public ResponseEntity<HttpStatus> processEvent(@RequestBody Comment comment) throws ExecutionException {
        controlFlow.addComment(comment);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    /*@PostMapping("/admin/dict/add-word")
    public ResponseEntity<HttpStatus> processAdd(@RequestBody String word) {
        amService.addWord(word);
        return ResponseEntity.ok(HttpStatus.OK);
    }*/

    /*@PostMapping("/admin/dict/remove-word")
    public ResponseEntity<HttpStatus> processDelete(@RequestBody String word) {
        amService.deleteWord(word);
        return ResponseEntity.ok(HttpStatus.OK);
    }*/
}
