package com.e2i1.linkeepserver.domain.token.ifs;

import com.e2i1.linkeepserver.domain.token.dto.TokenDTO;

import java.util.Map;

public interface TokenHelperIfs {
    TokenDTO issueAccessToken(Map<String, Object> data);

    TokenDTO issueRefreshToken(Map<String, Object> data);

    Map<String, Object> validationTokenWithThrow(String token);
}

