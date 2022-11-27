package com.leekimcho.memberservice.domain.jwt.service;

import com.leekimcho.memberservice.domain.member.dto.GoogleTokenDto;
import com.leekimcho.memberservice.domain.member.entity.Member;
import com.leekimcho.memberservice.domain.member.repository.MemberRepository;
import com.leekimcho.memberservice.domain.member.service.MemberServiceImpl;
import com.leekimcho.memberservice.global.utils.CookieProvider;
import com.leekimcho.memberservice.global.utils.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RequiredArgsConstructor
@Service
public class OAuthService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final MemberRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenService refreshTokenService;
    private final MemberServiceImpl memberServiceImpl;
    private final CookieProvider cookieProvider;

    @Override
    @Transactional
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest,OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);
        //OAuth 서비스 id
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        //OAuth 로그인 진행시 키가 되는 필드값
        String MemberNameAttributeName = userRequest.getClientRegistration()
                .getProviderDetails()
                .getUserInfoEndpoint()
                .getUserNameAttributeName();

        // OAuth2MemberService
        GoogleTokenDto attributeDto = GoogleTokenDto.of(registrationId, MemberNameAttributeName,oAuth2User.getAttributes());

        Member member = saveMember(attributeDto);

        String memberEmail = member.getEmail();

        Collection<? extends GrantedAuthority> authorities = memberServiceImpl.loadUserByUsername(memberEmail).getAuthorities();

        return new DefaultOAuth2User(
                authorities
                , attributeDto.getAttributes()
                , attributeDto.getNameAttributeKey());

    }

    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        String MemberEmail = String.valueOf(((DefaultOAuth2User) authentication.getPrincipal()).getAttributes().get("email"));


        String refreshToken = jwtTokenProvider.createJwtRefreshToken();
        Long memberId = memberRepository.findByEmail(MemberEmail).get().getId();
        refreshTokenService.updateRefreshToken(memberId, jwtTokenProvider.getRefreshTokenId(refreshToken));

        // 쿠키 설정
        ResponseCookie refreshTokenCookie = cookieProvider.createRefreshTokenCookie(refreshToken);

        Cookie cookie = cookieProvider.of(refreshTokenCookie);

        response.setContentType(APPLICATION_JSON_VALUE);
        response.addCookie(cookie);

        // body 설정
        String accessToken = jwtTokenProvider.createJwtAccessToken(String.valueOf(memberId), request.getRequestURI(), authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList()));
        Date expiredTime = jwtTokenProvider.getExpiredTime(accessToken);

        response.sendRedirect("http://localhost:8000/member-service/user/google/callback");

    }


    @Transactional
    public Member saveMember(GoogleTokenDto attributeDto){
        return memberRepository.save(
                memberRepository.findByEmail(attributeDto.getEmail())
                        .orElse(attributeDto.toEntity())
        );
    }

}
