package morgan.processors;

import morgan.ValueProcessor;

/**
 * GoodValueProcessor
 */
public class GoodValueProcessor implements ValueProcessor {

    public String process(String value) {
        return value + " is good!";
    }
}
