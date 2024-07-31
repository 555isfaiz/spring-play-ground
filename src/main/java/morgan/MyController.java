package morgan;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

record ServiceSetRequest(String from, String newVal) {
}


@RestController
@RequestMapping("/service")
public class MyController {

    @Autowired
    MyService service;

    @GetMapping
    String get() {
        return "key and val: " + service.getServiceKey() + " " + service.getServiceVal();
    }

    @PostMapping
    String set(@RequestBody ServiceSetRequest request) {
        service.setNewValue(request.newVal(), request.from());
        return "request from " + request.from() + " succeeded";
    }

    @GetMapping("/history")
    List<MyData> getAllHistory() {
        return service.getAllHistory();
    }

    @DeleteMapping("/history/{id}")
    ResponseEntity<String> deleteHistory(@PathVariable("id") long id) {

        if (!service.getHistoryById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }

        service.deleteHistoryById(id);
        return ResponseEntity.ok().body("success");
    }
}
