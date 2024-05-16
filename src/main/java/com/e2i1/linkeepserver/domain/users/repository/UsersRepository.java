package com.e2i1.linkeepserver.domain.users.repository;

import com.e2i1.linkeepserver.domain.users.entity.UserStatus;
import com.e2i1.linkeepserver.domain.users.entity.UsersEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<UsersEntity, Long> {

    Optional<UsersEntity> findFirstByIdAndStatusOrderByIdDesc(Long userId, UserStatus status);

    Optional<UsersEntity> findFirstByEmailAndStatusOrderByIdDesc(String email, UserStatus status);

    UsersEntity findByEmailAndStatusOrderByIdDesc(String email, UserStatus status);

    Optional<UsersEntity> findByNicknameAndStatus(String nickname, UserStatus status);

    List<UsersEntity> findByNicknameContainingAndStatus(String search, UserStatus status);
    UsersEntity findByNickname(String nickName);
}
