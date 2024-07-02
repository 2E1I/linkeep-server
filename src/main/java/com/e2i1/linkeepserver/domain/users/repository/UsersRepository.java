package com.e2i1.linkeepserver.domain.users.repository;

import com.e2i1.linkeepserver.domain.users.entity.UserStatus;
import com.e2i1.linkeepserver.domain.users.entity.UsersEntity;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UsersRepository extends JpaRepository<UsersEntity, Long> {

    Optional<UsersEntity> findFirstByIdAndStatusOrderByIdDesc(Long userId, UserStatus status);

    UsersEntity findByEmailAndStatusOrderByIdDesc(String email, UserStatus status);

    Optional<UsersEntity> findByNicknameAndStatus(String nickname, UserStatus status);


    @Query("SELECT u FROM UsersEntity u "
        + "WHERE u.id != :userId "
        + "AND u.status = :status "
        + "AND LOWER(u.nickname) LIKE CONCAT('%', lower(:search), '%') "
        + "ORDER BY u.nickname ")
    List<UsersEntity> findByNicknameContainingAndStatusOrderByNickname(
        @Param("search") String search, @Param("userId") Long userId,
        @Param("status") UserStatus status);

    UsersEntity findByNickname(String nickName);

    @Transactional
    @Modifying
    @Query("DELETE FROM UsersEntity u WHERE u.status = 'UNREGISTERED' AND u.updateAt < :oneYearAgo")
    void deleteUnregisteredUsersForOneYear(LocalDateTime oneYearAgo);
}
