package com.e2i1.linkeepserver.domain.users.controller;

import com.e2i1.linkeepserver.common.annotation.UserSession;
import com.e2i1.linkeepserver.domain.token.dto.TokenResDTO;
import com.e2i1.linkeepserver.domain.users.business.UsersBusiness;
import com.e2i1.linkeepserver.domain.users.dto.EditProfileReqDTO;
import com.e2i1.linkeepserver.domain.users.dto.LoginReqDTO;
import com.e2i1.linkeepserver.domain.users.dto.LoginResDTO;
import com.e2i1.linkeepserver.domain.users.dto.NicknameResDTO;
import com.e2i1.linkeepserver.domain.users.dto.ProfileResDTO;
import com.e2i1.linkeepserver.domain.users.dto.SignupReqDTO;
import com.e2i1.linkeepserver.domain.users.dto.UserHomeResDTO;
import com.e2i1.linkeepserver.domain.users.entity.UsersEntity;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;

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


}
