package pl.coderion;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("fraction-service")
public class Configuration {

    private int maximum;

    private int minimum;
}
