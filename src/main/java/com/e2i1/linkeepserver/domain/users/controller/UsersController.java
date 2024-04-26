package com.e2i1.linkeepserver.domain.users.controller;

import com.e2i1.linkeepserver.common.annotation.UserSession;
import com.e2i1.linkeepserver.domain.token.dto.TokenResDTO;
import com.e2i1.linkeepserver.domain.users.business.UsersBusiness;
import com.e2i1.linkeepserver.domain.users.dto.LoginReqDTO;
import com.e2i1.linkeepserver.domain.users.dto.NicknameResDTO;
import com.e2i1.linkeepserver.domain.users.dto.ProfileDTO;
import com.e2i1.linkeepserver.domain.users.dto.UserHomeResDTO;
import com.e2i1.linkeepserver.domain.users.entity.UsersEntity;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UsersController {
    private final UsersBusiness usersBusiness;

    @GetMapping("/home")
    public ResponseEntity<UserHomeResDTO> getUserHome(@UserSession UsersEntity user) {
        UserHomeResDTO home = usersBusiness.getUserHome(user);

        return ResponseEntity.ok(home);
    }

    @GetMapping("/nicknames")
    public ResponseEntity<List<NicknameResDTO>> getNicknameList(@RequestParam String search) {
        List<NicknameResDTO> nicknameList = usersBusiness.searchNicknames(search);
        return ResponseEntity.ok(nicknameList);
    }

    @GetMapping("/profile")
    public ResponseEntity<ProfileDTO> getProfile(@UserSession UsersEntity user) {
        ProfileDTO profile = usersBusiness.getProfile(user);

        return ResponseEntity.ok(profile);
    }

    @PatchMapping("/profile")
    public ResponseEntity<String> editProfile(@Valid @RequestBody ProfileDTO profile, @UserSession UsersEntity user) {
        log.info("========= 수정할 프로필 정보 : {}", profile);
        usersBusiness.editProfile(profile, user);
        return ResponseEntity.ok("success");
    }

    @GetMapping("/check-duplicated-nickname")
    public ResponseEntity<Boolean> isDuplicatedNickName(@RequestParam String nickname) {
        Boolean result = usersBusiness.validateDuplicatedNickname(nickname);

        return ResponseEntity.ok(result);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResDTO> login(@RequestBody LoginReqDTO loginReqDTO) {
        TokenResDTO response = usersBusiness.login(loginReqDTO);

        return ResponseEntity.ok(response);
    }


}
