package com.e2i1.linkeepserver.domain.users.business;

import com.e2i1.linkeepserver.common.annotation.Business;
import com.e2i1.linkeepserver.domain.users.converter.UsersConverter;
import com.e2i1.linkeepserver.domain.users.service.UsersService;
import lombok.RequiredArgsConstructor;

@Business
@RequiredArgsConstructor
public class UsersBusiness {
    private final UsersService usersService;
    private final UsersConverter usersConverter;
}
