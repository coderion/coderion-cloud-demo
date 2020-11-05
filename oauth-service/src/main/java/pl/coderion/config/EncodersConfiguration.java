package pl.coderion.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class EncodersConfiguration {

    @Bean
    public PasswordEncoder sha256HexPasswordEncoder() {
        return new Sha256HexPasswordEncoder();
    }
}
