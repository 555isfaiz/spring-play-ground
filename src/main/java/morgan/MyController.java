package morgan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
        return "key and val: " + service.serviceKey + " " + service.serviceVal;
    }

    @PostMapping
    String set(@RequestBody ServiceSetRequest request) {
        service.setNewValue(request.newVal(), request.from());
        return "request from " + request.from() + " succeeded";
    }
}
