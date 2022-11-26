package com.leekimcho.memberservice.domain.member.dto;

import com.leekimcho.memberservice.domain.member.entity.AuthType;
import com.leekimcho.memberservice.domain.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GoogleTokenDto {

    @NotBlank
    private String accessToken;

    Map<String, Object> attributes; // OAuth2 반환하는 유저정보 MAP
    private String nameAttributeKey;
    private String name;
    private String email;
    private AuthType authType;


    public GoogleTokenDto(Map<String, Object> attributes, String nameAttributeKey, String name, String email) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.email = email;
    }

    public static GoogleTokenDto of(String registrationId, String MemberNameAttributeName, Map<String, Object> attributes){

        return ofGoogle(MemberNameAttributeName, attributes);
    }


    private static GoogleTokenDto ofGoogle(String MemberNameAttributeName, Map<String, Object> attributes) {
        return GoogleTokenDto.builder()
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .nameAttributeKey(MemberNameAttributeName)
                .attributes(attributes)
                .authType(AuthType.GOOGLE)
                .build();
    }

    public Member toEntity(){
        return new Member(email,"temp", name);
    }


}
