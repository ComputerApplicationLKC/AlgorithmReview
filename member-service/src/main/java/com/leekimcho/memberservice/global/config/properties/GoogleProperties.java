package com.leekimcho.memberservice.global.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@Setter
public class GoogleProperties {
    private String clientId = "557796254251-f2ktkuj37ha3fssgc080uro7ee4um5l4.apps.googleusercontent.com";
    private String clientSecret = "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";
    private String tokenRequestUrl = "https://oauth2.googleapis.com/token";
    private String profileRequestUrl = "https://www.googleapis.com/oauth2/v3/userinfo";
}
