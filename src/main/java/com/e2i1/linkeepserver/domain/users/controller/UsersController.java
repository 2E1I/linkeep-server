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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UsersController {
    private final UsersBusiness usersBusiness;

    @GetMapping("/home")
    public ResponseEntity<UserHomeResDTO> getUserHome() {
        return ResponseEntity.ok(null);
    }

    @GetMapping("/nicknames")
    public ResponseEntity<List<NicknameResDTO>> getNicknameList(@RequestParam String search) {
        return ResponseEntity.ok(null);
    }

    @GetMapping("/profile")
    public ResponseEntity<ProfileDTO> getProfile(@UserSession UsersEntity user) {
        ProfileDTO profile = ProfileDTO.builder()
                .nickname(user.getNickname())
                .description(user.getDescription())
                .imgUrl(user.getImgUrl())
                .build();
        return ResponseEntity.ok(profile);
    }

    @PatchMapping("/profile")
    public ResponseEntity<String> editProfile(@Valid @RequestBody ProfileDTO profile) {
        return ResponseEntity.ok("success");
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResDTO> login(@RequestBody LoginReqDTO loginReqDTO) {
        TokenResDTO response = usersBusiness.login(loginReqDTO);

        return ResponseEntity.ok(response);
    }


}
