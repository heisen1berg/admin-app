package Core;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class RestEventController {
    @Resource
    private ServiceHandler serviceHandler;

    @PostMapping("/admin/comments")
    public ResponseEntity<HttpStatus> processEvent(@RequestBody Comment comment) {
        serviceHandler.process(comment);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/admin/dict/add-word")
    public ResponseEntity<HttpStatus> processAdd(@RequestBody String word) {
        serviceHandler.addWord(word);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/admin/dict/delete-word")
    public ResponseEntity<HttpStatus> processDelete(@RequestBody String word) {
        serviceHandler.deleteWord(word);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
