package com.e2i1.linkeepserver.domain.account;

import com.e2i1.linkeepserver.config.oauth.Constant.SocialLoginType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;

import static com.e2i1.linkeepserver.config.oauth.Constant.SocialLoginType.valueOf;

@Controller
@RequiredArgsConstructor
public class AccountController {
    private final OauthService oAuthService;

    /**
     * 유저 소셜 로그인으로 리다이렉트 해주는 url
     */
//    @NoAuth
    @GetMapping("/login/oauth2/{socialLoginType}")
    public void socialLoginRedirect(
            @PathVariable(name = "socialLoginType")
            String socialLoginPath
    ) throws IOException {
        SocialLoginType socialLoginType= valueOf(socialLoginPath.toUpperCase());
        oAuthService.request(socialLoginType);
    }

}
