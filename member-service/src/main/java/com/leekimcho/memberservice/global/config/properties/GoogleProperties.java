package com.leekimcho.memberservice.global.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "spring.security.oauth2.client.registration.google")
@Getter
@Setter
public class GoogleProperties {
    private String clientId;
    private String clientSecret;
    private String redirectUri;
    private String tokenRequestUrl;
    private String profileRequestUrl;
}
