package com.e2i1.linkeepserver.domain.token.helper;

import com.e2i1.linkeepserver.common.error.ErrorCode;
import com.e2i1.linkeepserver.common.exception.ApiException;
import com.e2i1.linkeepserver.domain.token.dto.TokenDTO;
import com.e2i1.linkeepserver.domain.token.ifs.TokenHelperIfs;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Component
public class JwtTokenHelper implements TokenHelperIfs {

    @Value("${token.secret.key}")
    private String secretKey;

    @Value("${token.access-token.plus-hour}")
    private Long accessTokenPlusHour;

    @Value("${token.refresh-token.plus-hour}")
    private Long refreshTokenPlusHour;


    @Override
    public TokenDTO issueAccessToken(Map<String, Object> data) {
        return makeToken(data, accessTokenPlusHour);
    }

    @Override
    public TokenDTO issueRefreshToken(Map<String, Object> data) {
        return makeToken(data, refreshTokenPlusHour);
    }

    @Override
    public Map<String, Object> validationTokenWithThrow(String token) {
        // key 만들기
        SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes());

        // 매개변수로 넘어온 token 문자열을 파싱하고 검증 -> 서명 유효성, 토큰 만료 여부 등을 파악
        JwtParser parser = Jwts.parserBuilder()
                .setSigningKey(key)
                .build();

        try {
            Jws<Claims> result = parser.parseClaimsJws(token);
            return new HashMap<>(result.getBody());

        } catch (Exception e) {
            if (e instanceof SignatureException) {
                // 토큰이 유효하지 않을 때
                throw new ApiException(ErrorCode.INVALID_TOKEN);
            } else if (e instanceof ExpiredJwtException) {
                // 만료된 토큰
                throw new ApiException(ErrorCode.EXPIRED_TOKEN);
            }
            else {
                // 그외 에러
                throw new ApiException(ErrorCode.TOKEN_EXCEPTION);
            }
        }
    }

    private TokenDTO makeToken(Map<String, Object> data, Long plusHour) {
        // 토큰 만료 시간 = 현재 시간 + 토큰 유효 시간
        LocalDateTime expiredLocalDateTime = LocalDateTime.now().plusHours(plusHour);

        // LocalDateTime -> Date 객체로 변환 : JWT 라이브러리는 Date 객체 사용
        Date expiredAt = Date.from(expiredLocalDateTime.atZone(ZoneId.systemDefault()).toInstant());

        // yml 파일에 저장한 secretKey로 SecretKey 객체 생성 -> token 서명에 사용할 것
        SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes());

        // JWT token 만들기!
        String jwtToken = Jwts.builder()
                .signWith(key, SignatureAlgorithm.HS256)
                .setClaims(data)
                .setExpiration(expiredAt)
                .compact();

        return TokenDTO.builder()
                .token(jwtToken)
                .expiredAt(expiredLocalDateTime)
                .build();
    }
}
