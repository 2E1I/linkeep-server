package com.e2i1.linkeepserver.domain.users.service;

import static com.e2i1.linkeepserver.domain.users.entity.UserStatus.REGISTERED;

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

    public UsersEntity register(UsersEntity user) {
        return usersRepository.save(user);
    }

    public UsersEntity getUserWithThrow(Long userId) {
        return usersRepository.findFirstByIdAndStatusOrderByIdDesc(userId, REGISTERED)
            .orElseThrow(() -> new ApiException(ErrorCode.USER_NOT_FOUND));
    }

    public UsersEntity getUserWithThrow(String email) {
        return usersRepository.findFirstByEmailAndStatusOrderByIdDesc(email, REGISTERED)
            .orElseThrow(() -> new ApiException(ErrorCode.USER_NOT_FOUND));
    }

}
