package com.e2i1.linkeepserver.domain.users.controller;

import static com.e2i1.linkeepserver.common.constant.PageConst.DEFAULT_PAGE_SIZE;

import com.e2i1.linkeepserver.common.annotation.UserSession;
import com.e2i1.linkeepserver.domain.token.dto.TokenResDTO;
import com.e2i1.linkeepserver.domain.users.business.UsersBusiness;
import com.e2i1.linkeepserver.domain.users.dto.*;
import com.e2i1.linkeepserver.domain.users.entity.UsersEntity;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UsersController {

    private final UsersBusiness usersBusiness;

    /**
     * 로그인한 유저의 home 화면 요청
     */
    @GetMapping("/home")
    public ResponseEntity<LoginHomeResDTO> getHome(
        @RequestParam(value = "lastId", required = false) Long lastId,
        @RequestParam(value = "size", defaultValue = DEFAULT_PAGE_SIZE) Integer size,
        @UserSession UsersEntity user) {
        LoginHomeResDTO home = usersBusiness.getHome(lastId, size, user);

        return ResponseEntity.ok(home);
    }

    /**
     * userId의 home 화면 요청
     */
    @GetMapping("/{userId}/home")
    public ResponseEntity<UserHomeResDTO> getUserHome(
        @PathVariable Long userId,
        @UserSession UsersEntity user) {
        UserHomeResDTO home = usersBusiness.getUserHome(userId, user);

        return ResponseEntity.ok(home);
    }

    @GetMapping("/nicknames")
    public ResponseEntity<List<NicknameResDTO>> getNicknameList(@RequestParam String search) {
        List<NicknameResDTO> nicknameList = usersBusiness.searchNicknames(search);
        return ResponseEntity.ok(nicknameList);
    }

    @GetMapping("/profile")
    public ResponseEntity<ProfileResDTO> getProfile(@UserSession UsersEntity user) {
        ProfileResDTO profile = usersBusiness.getProfile(user);

        return ResponseEntity.ok(profile);
    }

    @PatchMapping("/profile")
    public ResponseEntity<String> editProfile(
        @RequestPart(value = "image", required = false) MultipartFile imgFile,
        @Valid @RequestPart EditProfileReqDTO editProfile, @UserSession UsersEntity user) {
        log.info("========= 수정할 프로필 정보 : {}", editProfile);

        usersBusiness.editProfile(imgFile, editProfile, user);

        return ResponseEntity.ok("success");
    }

    @GetMapping("/check-duplicated-nickname")
    public ResponseEntity<Boolean> isDuplicatedNickName(@RequestParam String nickname) {
        Boolean result = usersBusiness.validateDuplicatedNickname(nickname);

        return ResponseEntity.ok(result);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResDTO> login(@RequestBody LoginReqDTO loginReqDTO) {
        LoginResDTO response = usersBusiness.login(loginReqDTO);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<TokenResDTO> signup(
        @RequestPart(value = "image", required = false) MultipartFile imgFile,
        @RequestPart SignupReqDTO signupInfo
    ) {
        TokenResDTO response = usersBusiness.signup(signupInfo, imgFile);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader("authorization-token") String token,
        @UserSession UsersEntity user) {
        usersBusiness.logout(token, user);

        return ResponseEntity.ok("로그아웃 되었습니다.");
    }

    @GetMapping("/recent-search")
    public ResponseEntity<RecentSearchResDTO> recentSearch(@UserSession UsersEntity user) {
        RecentSearchResDTO response = usersBusiness.getRecentSearch(user.getId());

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/recent-search")
    public ResponseEntity<String> deleteRecentSearch(
        @RequestParam int index,
        @UserSession UsersEntity user) {

        usersBusiness.deleteRecentKeyword(user.getId(), index);

        return ResponseEntity.ok("최근 검색어 삭제 완료했습니다.");
    }

    @DeleteMapping()
    public ResponseEntity<String> deleteUser(@UserSession UsersEntity user) {
        usersBusiness.deleteUser(user.getId());

        return ResponseEntity.ok("링킵 서비스 회원 탈퇴 처리가 되었습니다.");
    }


}
