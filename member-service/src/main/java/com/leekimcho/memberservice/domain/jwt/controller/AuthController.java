package com.leekimcho.memberservice.domain.jwt.controller;
import com.leekimcho.memberservice.domain.jwt.service.AccessTokenService;
import com.leekimcho.memberservice.domain.jwt.service.RefreshTokenService;
import com.leekimcho.memberservice.domain.member.dto.JwtPayload;
import com.leekimcho.memberservice.global.dto.Result;
import com.leekimcho.memberservice.global.utils.CookieProvider;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.HttpHeaders;
import java.text.SimpleDateFormat;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member-service/api/auth")
@Slf4j
public class AuthController {

    private final RefreshTokenService refreshTokenService;
    private final AccessTokenService accessTokenService;
    private final CookieProvider cookieProvider;

    @GetMapping("/reissue")
    public ResponseEntity<Result> refreshToken(@RequestHeader(value = "X-AUTH-TOKEN") String accessToken,
                                               @CookieValue(value = "refresh-token") String refreshToken) {
        JwtPayload jwtPayload = refreshTokenService.refreshJwtToken(accessToken, refreshToken);

        ResponseCookie responseCookie = cookieProvider.createRefreshTokenCookie(refreshToken);

        return ResponseEntity.status(HttpStatus.OK)
                .header(HttpHeaders.SET_COOKIE, responseCookie.toString())
                .body(Result.createSuccessResult(new RefreshTokenResponse(jwtPayload)));
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static class RefreshTokenResponse {
        private String accessToken;
        private String expiredTime;

        public RefreshTokenResponse(JwtPayload jwtPayload) {
            this.accessToken = jwtPayload.getAccessToken();
            this.expiredTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                    .format(jwtPayload.getAccessTokenExpiredDate());
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<Result> logout(@RequestHeader("X-AUTH-TOKEN") String accessToken) {

        refreshTokenService.logoutToken(accessToken);

        ResponseCookie refreshCookie = cookieProvider.removeRefreshTokenCookie();

        return ResponseEntity.status(HttpStatus.OK)
                .header(HttpHeaders.SET_COOKIE, refreshCookie.toString())
                .body(Result.createSuccessResult(""));
    }

    @GetMapping("/check/access-token")
    public ResponseEntity<Result> checkAccessToken(@RequestHeader(name = "Authorization") String authorization) {

        accessTokenService.checkAccessToken(authorization);

        return ResponseEntity.status(HttpStatus.OK)
                .body(Result.createSuccessResult(null));
    }

}
