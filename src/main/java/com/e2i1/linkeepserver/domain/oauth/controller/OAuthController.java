package com.e2i1.linkeepserver.domain.oauth.controller;

import com.e2i1.linkeepserver.config.oauth.Constant;
import com.e2i1.linkeepserver.domain.oauth.model.GetSocialOAuthResDTO;
import com.e2i1.linkeepserver.domain.oauth.service.OAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

import static com.e2i1.linkeepserver.config.oauth.Constant.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/app/accounts")
public class OAuthController {

    private final OAuthService oAuthService;

    //@NoAuth
    @GetMapping("/auth/{socialLoginType}")
    public void socialLoginRedirect(
            @PathVariable(name = "socialLoginType")
            String SocialLoginType
    ) throws IOException {
        SocialLoginType socialLoginType= Constant.SocialLoginType.valueOf(SocialLoginType.toUpperCase());
        oAuthService.request(socialLoginType);
    }

    @ResponseBody
    @GetMapping("/auth/{socialLoginType}/callback")
    public ResponseEntity<GetSocialOAuthResDTO> callback (
            @PathVariable(name = "socialLoginType") String socialLoginPath,
            @RequestParam(name = "code") String code
    ) throws IOException {
        System.out.println(">> 소셜 로그인 API 서버로부터 받은 code :" + code);
        SocialLoginType socialLoginType = SocialLoginType.valueOf(socialLoginPath.toUpperCase());
        GetSocialOAuthResDTO getSocialOAuthRes = oAuthService.OAuthLogin(socialLoginType, code);

        return ResponseEntity.ok()
                .body(getSocialOAuthRes);
    }
}
