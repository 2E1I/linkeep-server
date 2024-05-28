package com.e2i1.linkeepserver.domain.users.service;


import com.e2i1.linkeepserver.common.error.ErrorCode;
import com.e2i1.linkeepserver.common.exception.ApiException;
import com.e2i1.linkeepserver.domain.users.dto.EditProfileReqDTO;
import com.e2i1.linkeepserver.domain.users.entity.UsersEntity;
import com.e2i1.linkeepserver.domain.users.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import static com.e2i1.linkeepserver.domain.users.entity.UserStatus.REGISTERED;

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

    public UsersEntity getUser(String email) {
        return usersRepository.findByEmailAndStatusOrderByIdDesc(email, REGISTERED);
    }


  public UsersEntity findById(Long userId) {
        return  usersRepository.findById(userId).orElseThrow(()-> new ApiException(ErrorCode.USER_NOT_FOUND));
  }

    public List<UsersEntity> searchNicknames(String search) {
        return usersRepository.findByNicknameContainingAndStatus(search, REGISTERED);
    }

    /**
     * @Param nickname과 중복되는 nickname이 DB에 존재하면 true nickname이 unique하면 false 리턴
     */
    public Boolean isDuplicatedNickname(String nickname) {
        Optional<UsersEntity> user = usersRepository.findByNicknameAndStatus(nickname, REGISTERED);
        return user.isPresent();
    }

    public void editProfile(String imgUrl, EditProfileReqDTO editProfile, UsersEntity user) {
        // 유저 정보 수정하기
        user.update(editProfile.getNickname(), imgUrl, editProfile.getDescription());

        // 메서드 끝나면 transactional 종료되어 수정된 유저 정보가 DB에 반영된다
        // user는 UserSessionResolver에서 getUserWithThrow로 가져온 user 객체임 -> 즉, 영속성 컨텍스트 안에 있음 -> dirty checking 가능
    }

    public UsersEntity findByNickName(String nickName) {
      return usersRepository.findByNickname(nickName);
  }

    @Transactional
    public void deleteUser(Long userId) {
        UsersEntity user = usersRepository.findById(userId).orElseThrow();
        user.delete();
    }

    public void deleteUnregisteredUsersForOneYear() {
        LocalDateTime cutoffDate = LocalDateTime.now().minusYears(1).with(LocalTime.MAX); // 1년 전의 하루 끝 시간으로 설정

        usersRepository.deleteUnregisteredUsersForOneYear(cutoffDate);
    }
}
