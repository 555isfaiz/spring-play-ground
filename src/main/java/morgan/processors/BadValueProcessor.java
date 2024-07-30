package morgan.processors;

import morgan.ValueProcessor;

/**
 * BadValueProcessor
 */
public class BadValueProcessor implements ValueProcessor {

    public String process(String value) {
        return value + " is bad!";
    }
}
