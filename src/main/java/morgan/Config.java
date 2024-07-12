package morgan;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "morgan")
public record Config(String hello, int world) {
}
