package com.e2i1.linkeepserver.resolver;

import com.e2i1.linkeepserver.common.annotation.UserSession;
import com.e2i1.linkeepserver.domain.users.entity.UsersEntity;
import com.e2i1.linkeepserver.domain.users.service.UsersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * request 요청이 들어오면 실행되는 resolver (AOP 방식으로 동작)
 * Interceptor 뒷단의 컨트롤러 등에서 @UserSession애노테이션 있는지,
 * 파라미터 타입이 User클래스인지 체크
 * → 둘 다 만족하면 resolveArgument() 동작하면서 사용자가 유효한지 체크
 * → RequestContextHolder에서 userId꺼내와서 User 객체 만들어준다
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class UserSessionResolver implements HandlerMethodArgumentResolver {

    private final UsersService userService;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        // 지원하는 파라미터 체크, 애노테이션 체크

        // 1. 애노테이션이 있는지 check : UserSession 이라는 애노테이션이 달려있는지 check
        boolean annotation = parameter.hasParameterAnnotation(UserSession.class);

        // 2. 파라미터의 타입 check : 매개변수로 들어오는 parameter 가 User 클래스가 맞는지 check
        boolean parameterType = parameter.getParameterType().equals(UsersEntity.class);

        return (annotation && parameterType);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
        NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        // support parameter 에서 true 반환시 여기 실행

        // AuthorizationInterceptor 에서 넣었던 request context holder 에서 userId 찾아오기
        RequestAttributes requestContext = RequestContextHolder.getRequestAttributes();
        Object userId = requestContext.getAttribute("userId", RequestAttributes.SCOPE_REQUEST);

        log.info("userId = {}",Long.parseLong(userId.toString()));
        UsersEntity userEntity = userService.getUserWithThrow(Long.parseLong(userId.toString()));

        // 사용자 정보 세팅
        return UsersEntity.builder()
            .Id(userEntity.getId())
            .nickname(userEntity.getNickname())
            .email(userEntity.getEmail())
            .description(userEntity.getDescription())
            .imgUrl(userEntity.getImgUrl())
            .thumbnailUrl(userEntity.getThumbnailUrl())
            .status(userEntity.getStatus())
            .createdAt(userEntity.getCreatedAt())
            .updateAt(userEntity.getUpdateAt())
            .build();
    }
}