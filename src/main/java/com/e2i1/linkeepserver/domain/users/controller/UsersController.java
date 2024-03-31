package com.e2i1.linkeepserver.domain.users.controller;

import com.e2i1.linkeepserver.domain.users.business.UsersBusiness;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UsersController {
    private final UsersBusiness usersBusiness;
}
