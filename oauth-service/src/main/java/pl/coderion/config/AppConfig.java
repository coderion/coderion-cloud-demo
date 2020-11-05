package pl.coderion.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;

@Configuration
public class AppConfig {

    @Value("${oauth.token.endpoint:http://localhost:9999/oauth/token}")
    String tokenEndpoint;

    @Value("${oauth.client.id.client-id:cid}")
    String clientId;

    @Value("${oauth.client.secret.client-secret:GHJw#@18K}")
    String clientSecret;

    @Bean
    OAuth2RestTemplate oAuth2RestTemplate() {
        DefaultOAuth2ClientContext clientContext = new DefaultOAuth2ClientContext();

        ClientCredentialsResourceDetails resource = new ClientCredentialsResourceDetails();
        resource.setAccessTokenUri(tokenEndpoint);
        resource.setClientId(clientId);
        resource.setClientSecret(clientSecret);
        resource.setGrantType("client_credentials");

        OAuth2RestTemplate restTemplate = new OAuth2RestTemplate(resource, clientContext);

        return restTemplate;
    }
}
