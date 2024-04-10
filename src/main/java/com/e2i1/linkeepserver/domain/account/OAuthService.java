package com.e2i1.linkeepserver.domain.account;

import com.e2i1.linkeepserver.config.oauth.Constant;
import com.e2i1.linkeepserver.config.oauth.Constant.SocialLoginType;
import com.e2i1.linkeepserver.domain.account.model.GetSocialOAuthResDTO;
import com.e2i1.linkeepserver.domain.account.model.GoogleOAuthToken;
import com.e2i1.linkeepserver.domain.account.model.GoogleUser;
import com.e2i1.linkeepserver.domain.users.entity.UsersEntity;
import com.e2i1.linkeepserver.domain.users.service.UsersService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Objects;

import static com.e2i1.linkeepserver.config.oauth.Constant.SocialLoginType.GOOGLE;

@Service
@RequiredArgsConstructor
public class OAuthService {

    private final GoogleOAuth googleOAuth;
    private final HttpServletResponse response;
    private final UsersService usersService;

    public void request(SocialLoginType socialLoginType) throws IOException {
        String redirectURL;
        if (Objects.requireNonNull(socialLoginType) == GOOGLE) {
            // 각 소셜 로그인을 요청하면 소셜로그인 페이지로 리다이렉트 해주는 프로세스
            redirectURL = googleOAuth.getOAuthRedirectURL();
        } else {
            throw new IllegalArgumentException("알 수 없는 소셜 로그인 형식입니다.");
        }

        response.sendRedirect(redirectURL);
    }

    public GetSocialOAuthResDTO OAuthLogin(Constant.SocialLoginType socialLoginType, String code) throws IOException {

        switch (socialLoginType){
            case GOOGLE:{
                //구글로 일회성 코드를 보내 액세스 토큰이 담긴 응답객체를 받아옴
                ResponseEntity<String> accessTokenResponse= googleOAuth.requestAccessToken(code);
                //응답 객체가 JSON형식으로 되어 있으므로, 이를 deserialization해서 자바 객체에 담을 것이다.
                GoogleOAuthToken oAuthToken=googleOAuth.getAccessToken(accessTokenResponse);

                //액세스 토큰을 다시 구글로 보내 구글에 저장된 사용자 정보가 담긴 응답 객체를 받아온다.
                ResponseEntity<String> userInfoResponse=googleOAuth.requestUserInfo(oAuthToken);
                //다시 JSON 형식의 응답 객체를 자바 객체로 역직렬화한다.
                GoogleUser googleUser= googleOAuth.getUserInfo(userInfoResponse);

                String userEmail = googleUser.getEmail();

                // 우리 서버의 DB에 해당 user email 존재하는지 확인
                UsersEntity user = usersService.findByEmail(userEmail);

                // 서버에 user가 존재하면 앞으로 회원 인가 처리를 위한 JWT token 발급
                if (user != null) {
                    // TODO: 토큰 발급
                    // TODO: access token, jwt token 등이 담긴 자바 객체를 다시 전송
                    //GetSocialOAuthResDTO getSocialOAuthResDTO = new GetSocialOAuthResDTO(jwttoken, user.getId(), oAuthToken.getAccess_token(), oAuthToken.getToken_type());
                    //return getSocialOAuthResDTO;
                } else {
                    throw new IllegalArgumentException("계정이 존재 하지 않습니다");
                }


            }
            default:{
                throw new IllegalArgumentException("알 수 없는 소셜 로그인 형식입니다.");
            }

        }

    }
}
