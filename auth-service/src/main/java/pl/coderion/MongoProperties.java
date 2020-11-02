package pl.coderion;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("spring.data.mongodb")
public class MongoProperties {

    private String host;

    private int port;

    private String username;

    private String password;

    private String database;
}
