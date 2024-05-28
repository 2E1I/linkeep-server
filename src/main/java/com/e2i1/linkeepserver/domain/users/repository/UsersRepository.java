package com.e2i1.linkeepserver.domain.users.repository;

import com.e2i1.linkeepserver.domain.users.entity.UserStatus;
import com.e2i1.linkeepserver.domain.users.entity.UsersEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface UsersRepository extends JpaRepository<UsersEntity, Long> {

    Optional<UsersEntity> findFirstByIdAndStatusOrderByIdDesc(Long userId, UserStatus status);

    Optional<UsersEntity> findFirstByEmailAndStatusOrderByIdDesc(String email, UserStatus status);

    UsersEntity findByEmailAndStatusOrderByIdDesc(String email, UserStatus status);

    Optional<UsersEntity> findByNicknameAndStatus(String nickname, UserStatus status);

    List<UsersEntity> findByNicknameContainingAndStatus(String search, UserStatus status);
    UsersEntity findByNickname(String nickName);

    @Transactional
    @Modifying
    @Query("DELETE FROM UsersEntity u WHERE u.status = 'UNREGISTERED' AND u.updateAt < :oneYearAgo")
    int deleteUnregisteredUsersForOneYear(LocalDateTime oneYearAgo);
}
