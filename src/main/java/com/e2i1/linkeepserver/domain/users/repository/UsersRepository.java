package com.e2i1.linkeepserver.domain.users.repository;

import com.e2i1.linkeepserver.domain.users.entity.UserStatus;
import com.e2i1.linkeepserver.domain.users.entity.UsersEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<UsersEntity,Long> {

    Optional<UsersEntity> findFirstByIdAndStatusOrderByIdDesc(Long userId, UserStatus status);
    Optional<UsersEntity> findFirstByEmailAndStatusOrderByIdDesc (String email, UserStatus status);
}
