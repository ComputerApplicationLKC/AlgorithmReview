package com.leekimcho.memberservice.domain.member.dto;

import com.leekimcho.memberservice.domain.member.entity.AuthType;
import com.leekimcho.memberservice.domain.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OAuthAttributeDto {
    private Map<String, Object> attributes; // OAuth2 반환하는 유저정보 MAP
    private String nameAttributeKey;
    private String name;
    private String email;
    private AuthType authType;


    public OAuthAttributeDto(Map<String, Object> attributes, String nameAttributeKey, String name, String email) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.email = email;
    }

    public static OAuthAttributeDto of(String registrationId, String MemberNameAttributeName, Map<String, Object> attributes){
        // 여기서 네이버와 카카오 등 구분 (ofNaver, ofKakao)
        if("naver".equals(registrationId))
            return ofNaver(MemberNameAttributeName , attributes);

        return ofGoogle(MemberNameAttributeName, attributes);
    }

    private static OAuthAttributeDto ofNaver(String MemberNameAttributeName, Map<String, Object> attributes) {
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");
        return OAuthAttributeDto.builder()
                .name((String) response.get("name"))
                .email((String) response.get("email"))
                .nameAttributeKey("id")
                .attributes(response)
                .authType(AuthType.GOOGLE)
                .build();
    }


    private static OAuthAttributeDto ofGoogle(String MemberNameAttributeName, Map<String, Object> attributes) {
        return OAuthAttributeDto.builder()
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
