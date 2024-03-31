package com.e2i1.linkeepserver.domain.users.service;

import com.e2i1.linkeepserver.domain.users.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsersService {
    private final UsersRepository usersRepository;
}
