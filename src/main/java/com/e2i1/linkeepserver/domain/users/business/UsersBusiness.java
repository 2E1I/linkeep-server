package com.e2i1.linkeepserver.domain.users.business;

import com.e2i1.linkeepserver.common.annotation.Business;
import com.e2i1.linkeepserver.domain.token.business.TokenBusiness;
import com.e2i1.linkeepserver.domain.token.dto.TokenResDTO;
import com.e2i1.linkeepserver.domain.users.converter.UsersConverter;
import com.e2i1.linkeepserver.domain.users.dto.LoginReqDTO;
import com.e2i1.linkeepserver.domain.users.entity.UsersEntity;
import com.e2i1.linkeepserver.domain.users.service.UsersService;
import lombok.RequiredArgsConstructor;

@Business
@RequiredArgsConstructor
public class UsersBusiness {
    private final UsersService usersService;
    private final UsersConverter usersConverter;
    private final TokenBusiness tokenBusiness;

    public TokenResDTO login(LoginReqDTO loginReqDTO) {
        UsersEntity user = usersService.getUser(loginReqDTO.getEmail());

        // 로그인 시, 해당 유저 없으면 자동 회원가입 진행
        if (user == null) {
            UsersEntity newUser = usersConverter.toEntity(loginReqDTO);
            user = usersService.register(newUser);
        }

        // 토큰 발행
        return tokenBusiness.issueToken(user);
    }
}
