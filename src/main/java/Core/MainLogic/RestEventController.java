package Core.MainLogic;

import Core.DataStructures.Comment;
import Core.Services.AutoModService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import Core.Services.DBService;

@RestController
public class RestEventController {
    @Resource
    private AutoModService amService;
    @Resource
    private DBService dbService;

    @PostMapping("/admin/addComment")
    public ResponseEntity<HttpStatus> processEvent(@RequestBody Comment comment) {
        amService.process(comment);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/admin/dict/add-word")
    public ResponseEntity<HttpStatus> processAdd(@RequestBody String word) {
        amService.addWord(word);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/admin/dict/remove-word")
    public ResponseEntity<HttpStatus> processDelete(@RequestBody String word) {
        amService.deleteWord(word);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
