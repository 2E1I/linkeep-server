package com.e2i1.linkeepserver.domain.token.service;

import com.e2i1.linkeepserver.common.error.ErrorCode;
import com.e2i1.linkeepserver.common.exception.ApiException;
import com.e2i1.linkeepserver.domain.token.dto.TokenDTO;
import com.e2i1.linkeepserver.domain.token.ifs.TokenHelperIfs;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class TokenService {

    private final TokenHelperIfs tokenHelperIfs;

    public TokenDTO issueAccessToken(Long userId) {
        HashMap<String, Object> data = new HashMap<>();
        data.put("userId", userId);

        return tokenHelperIfs.issueAccessToken(data);
    }

    public TokenDTO issueRefreshToken(Long userId) {
        HashMap<String, Object> data = new HashMap<>();
        data.put("userId", userId);

        return tokenHelperIfs.issueRefreshToken(data);
    }

    public Long validationToken(String token) {

        // validationTokenWithThrow의 결과에는 token을 파싱하고 그 결과를 map 형태로 넣은게 return
        Map<String, Object> map = tokenHelperIfs.validationTokenWithThrow(token);
        Object userId = map.get("userId");

        // userId 없을 수 있음 -> null check
        Objects.requireNonNull(userId, () -> {
            throw new ApiException(ErrorCode.NULL_POINT);
        });

        return Long.parseLong(userId.toString());
    }
}
