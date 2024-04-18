package com.e2i1.linkeepserver.interceptor;

import com.e2i1.linkeepserver.common.error.ErrorCode;
import com.e2i1.linkeepserver.common.exception.ApiException;
import com.e2i1.linkeepserver.domain.token.business.TokenBusiness;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@Component
public class AuthorizationInterceptor implements HandlerInterceptor
{

    private final TokenBusiness tokenBusiness;

    // request의 사전 처리 담당(인증과 같은 중요한 검증 작업을 수행)
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // Object handler는 현재 요청을 처리하고 있는 핸들러(컨트롤러) 객체임
        log.info("Authorization Interceptor url : {}", request.getRequestURI());

        // CORS 방지 : OPTIONS 메서드로 사전 요청 오면 다 pass
        if (HttpMethod.OPTIONS.matches(request.getMethod())) {
            return true;
        }

        // .js .html .png 등 resource를 요청하는 경우 = pass(인증 절차 없이 통과)
        if (handler instanceof ResourceHttpRequestHandler) {
            return true;
        }

        // header 검증(JWT 유효한지)

        // 헤더에서 토큰 꺼내기
        String accessToken = request.getHeader("authorization-token");
        // 토큰 없으면 예외 터뜨리기
        if (accessToken == null) {
            throw new ApiException(ErrorCode.AUTHORIZATION_TOKEN_NOT_FOUND);
        }

        // 토큰 있으면 validation 진행 -> userId 꺼내기
        Long userId = tokenBusiness.validationAccessToken(accessToken);

        // userId 있는 경우
        if (userId != null) {
            // 한가지 요청에 대해서 글로벌하게 저장할 수 있는 저장소에 저장하기
            RequestAttributes requestContext = Objects.requireNonNull(RequestContextHolder.getRequestAttributes());
            requestContext.setAttribute("userId", userId, RequestAttributes.SCOPE_REQUEST); // 범위는 이번 request 동안만
            return true;
        }

        // userId 없는 경우
        throw new ApiException(ErrorCode.BAD_REQUEST, "인증 실패");
    }
}