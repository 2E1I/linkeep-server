package com.e2i1.linkeepserver.domain.token.business;

import com.e2i1.linkeepserver.common.annotation.Business;
import com.e2i1.linkeepserver.common.error.ErrorCode;
import com.e2i1.linkeepserver.common.exception.ApiException;
import com.e2i1.linkeepserver.domain.token.converter.TokenConverter;
import com.e2i1.linkeepserver.domain.token.dto.TokenDTO;
import com.e2i1.linkeepserver.domain.token.dto.TokenResDTO;
import com.e2i1.linkeepserver.domain.token.service.TokenService;
import com.e2i1.linkeepserver.domain.users.entity.UsersEntity;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
@Business
public class TokenBusiness {

    private final TokenConverter tokenConverter;
    private final TokenService tokenService;

    public TokenResDTO issueToken(UsersEntity userEntity) {
        return Optional.ofNullable(userEntity)
                .map(entity -> {
                    Long userId = userEntity.getId();

                    TokenDTO accessToken = tokenService.issueAccessToken(userId);
                    TokenDTO refreshToken = tokenService.issueRefreshToken(userId);

                    return tokenConverter.toResponse(accessToken, refreshToken);
                })
                .orElseThrow(()-> new ApiException(ErrorCode.NULL_POINT));
    }

    public Long validationAccessToken(String accessToken) {
        return tokenService.validationToken(accessToken);
    }
}
