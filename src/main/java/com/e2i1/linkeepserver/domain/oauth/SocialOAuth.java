package com.e2i1.linkeepserver.domain.oauth;

public interface SocialOAuth {
    /**
     * 각 소셜 로그인 페이지로 redirect할 URL build
     * 사용자로부터 로그인 요청을 받아 소셜 로그인 서버 인증용 코드 요청
     */
    String getOAuthRedirectURL();
}
