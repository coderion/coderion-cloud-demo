package pl.coderion.config.mongodb;

import com.github.mongobee.Mongobee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.mongodb.core.MongoTemplate;
import pl.coderion.MongoProperties;

@Configuration
@DependsOn("mongoTemplate")
public class MongoBeeConfig {

//    private static final String MONGODB_URL_FORMAT = "mongodb://%s:%s@%s:%d/%s";  // version with username & password
    private static final String MONGODB_URL_FORMAT = "mongodb://%s:%d/%s";
    private static final String MONGODB_CHANGELOGS_PACKAGE = "pl.coderion.config.mongodb.changelogs";

    @Autowired
    private MongoProperties mongoProperties;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Bean
    public Mongobee mongobee() {

        System.out.println("Host: " + mongoProperties.getHost());
        System.out.println("Database: " + mongoProperties.getDatabase());

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
