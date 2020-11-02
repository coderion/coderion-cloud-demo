package pl.coderion.config.mongodb;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "pl.coderion")
public class MongoConfig {
}
