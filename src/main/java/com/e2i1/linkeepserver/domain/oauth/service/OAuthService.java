package com.e2i1.linkeepserver.domain.oauth.service;

import com.e2i1.linkeepserver.common.error.ErrorCode;
import com.e2i1.linkeepserver.common.exception.ApiException;
import com.e2i1.linkeepserver.config.oauth.Constant.SocialLoginType;
import com.e2i1.linkeepserver.domain.oauth.GoogleOAuth;
import com.e2i1.linkeepserver.domain.oauth.model.GetSocialOAuthResDTO;
import com.e2i1.linkeepserver.domain.oauth.model.GoogleOAuthToken;
import com.e2i1.linkeepserver.domain.oauth.model.GoogleUser;
import com.e2i1.linkeepserver.domain.token.business.TokenBusiness;
import com.e2i1.linkeepserver.domain.token.dto.TokenResDTO;
import com.e2i1.linkeepserver.domain.users.entity.UsersEntity;
import com.e2i1.linkeepserver.domain.users.service.UsersService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Objects;

import static com.e2i1.linkeepserver.config.oauth.Constant.SocialLoginType.GOOGLE;

@Slf4j
@Service
@RequiredArgsConstructor
public class OAuthService {

    private final GoogleOAuth googleOAuth;
    private final HttpServletResponse response;
    private final UsersService usersService;
    private final TokenBusiness tokenBusiness;

    public void request(SocialLoginType socialLoginType) throws IOException {
        String redirectURL;
        if (Objects.requireNonNull(socialLoginType) == GOOGLE) {
            // 각 소셜 로그인을 요청하면 소셜로그인 페이지로 리다이렉트 해주는 프로세스
            redirectURL = googleOAuth.getOAuthRedirectURL();
        } else {
            throw new ApiException(ErrorCode.UNKNOWN_OAUTH_LOGIN);
        }

        response.sendRedirect(redirectURL);
    }

    public GetSocialOAuthResDTO OAuthLogin(SocialLoginType socialLoginType, String code) throws IOException {

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
                // 유저가 우리 서버에 없으면 새로 생성 = 자동 회원 가입
                if (user == null) {
                    user = usersService.createUser(googleUser.getName(), userEmail, googleUser.getEmail());
                }

                // 토큰 발급
                TokenResDTO tokenResDTO = tokenBusiness.issueToken(user);

                // access token, jwt token 등이 담긴 자바 객체를 다시 전송
                return new GetSocialOAuthResDTO(tokenResDTO, Math.toIntExact(user.getId()), oAuthToken.getAccess_token(), oAuthToken.getToken_type());

            }
            default:{
                throw new ApiException(ErrorCode.UNKNOWN_OAUTH_LOGIN);
            }

        }

    }
}
