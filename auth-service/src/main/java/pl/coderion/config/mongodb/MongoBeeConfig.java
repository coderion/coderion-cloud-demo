package pl.coderion.config.mongodb;

import com.github.mongobee.Mongobee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.mongodb.core.MongoTemplate;
import pl.coderion.MongoProperties;

@Configuration
@DependsOn("mongoTemplate")
public class MongoBeeConfig {

    @Value("${spring.data.mongodb.host}")
    private String mongoHost;

    @Value("${spring.data.mongodb.port}")
    private String mongoPort;

    @Value("${spring.data.mongodb.database}")
    private String mongoDatabase;

//    private static final String MONGODB_URL_FORMAT = "mongodb://%s:%s@%s:%d/%s";  // version with username & password
    private static final String MONGODB_URL_FORMAT = "mongodb://%s:%d/%s";
    private static final String MONGODB_CHANGELOGS_PACKAGE = "pl.coderion.config.mongodb.changelogs";

    @Autowired
    private MongoProperties mongoProperties;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Bean
    public Mongobee mongobee() {

        System.out.println("Host: " + mongoHost);
        System.out.println("Database: " + mongoDatabase);

        Mongobee runner = new Mongobee(String.format(MONGODB_URL_FORMAT,
//                mongoProperties.getUsername(),
//                mongoProperties.getPassword(),
                mongoProperties.getHost(),
                mongoProperties.getPort(),
                mongoProperties.getDatabase()));
        runner.setMongoTemplate(mongoTemplate);
        runner.setDbName(mongoProperties.getDatabase());
        runner.setChangeLogsScanPackage(MONGODB_CHANGELOGS_PACKAGE);
        return runner;
    }

}
