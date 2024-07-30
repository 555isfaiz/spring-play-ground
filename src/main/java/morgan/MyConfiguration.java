package morgan;

import java.lang.reflect.Proxy;
import java.util.Random;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import morgan.processors.BadValueProcessor;
import morgan.processors.GoodValueProcessor;

/**
 * Configuration
 */
@Configuration
public class MyConfiguration {

    @Bean
    ValueProcessor valueProcessor() {
        GoodValueProcessor goodValueProcessor = new GoodValueProcessor();
        BadValueProcessor badValueProcessor = new BadValueProcessor();
        Random rand = new Random();
        ValueProcessor proxy =
                (ValueProcessor) Proxy.newProxyInstance(ValueProcessor.class.getClassLoader(),
                        new Class[] {ValueProcessor.class}, (p, method, args) -> {
                            if (rand.nextInt(100) > 50) {
                                return method.invoke(goodValueProcessor, args);
                            } else {
                                return method.invoke(badValueProcessor, args);
                            }
                        });
        return proxy;
    }
}
