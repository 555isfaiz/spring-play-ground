package morgan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Hello world!
 *
 */
@RestController
@SpringBootApplication
@EnableConfigurationProperties(Config.class)
public class App {
    @Autowired
    Config config;

    @Autowired
    MyService service;

    @RequestMapping("/")
    String home() {
        return "hello world";
    }

    @RequestMapping("/status")
    String status() {
        return "config.hello: " + config.hello() + ", config.world: " + config.world()
                + ", service.key: " + service.serviceKey + ", serviceVal: " + service.serviceVal
                + ".";
    }

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
