package pl.coderion.configuration;

import org.joda.time.DateTimeZone;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.coderion.service.DateService;
import pl.coderion.service.DefaultDateService;

@Configuration
public class DateServiceConfiguration {

    @Bean
    DateService dateService() {
        return new DefaultDateService(defaultTimeZone());
    }

    @Bean
    DateTimeZone defaultTimeZone() {
        return DateTimeZone.forID(ConstantValues.DEFAULT_TIMEZONE);
    }
}
