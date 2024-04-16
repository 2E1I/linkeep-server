package com.e2i1.linkeepserver.domain.users.service;

import com.e2i1.linkeepserver.common.error.ErrorCode;
import com.e2i1.linkeepserver.common.exception.ApiException;
import com.e2i1.linkeepserver.domain.users.entity.UsersEntity;
import com.e2i1.linkeepserver.domain.users.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsersService {
    private final UsersRepository usersRepository;

    public UsersEntity createUser(String name, String email, String imgUrl) {
        UsersEntity newUser = UsersEntity.builder()
                .nickname(name)
                .email(email)
                .imgUrl(imgUrl)
                .build();
        return usersRepository.save(newUser);
    }

    public UsersEntity findById(Long userId) {
        return usersRepository.findById(userId)
                .orElseThrow(() -> new ApiException(ErrorCode.USER_NOT_FOUND));
    }

    public UsersEntity findByEmail(String email) {
        return usersRepository.findByEmail(email);
    }
}
