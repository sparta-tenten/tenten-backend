package com.sparta.tentenbackend.domain.order.aop;

import com.sparta.tentenbackend.domain.order.entity.Order;
import com.sparta.tentenbackend.domain.order.service.OrderRepositoryService;
import com.sparta.tentenbackend.global.exception.UnauthorizedException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class OwnerAuthorizationAspect {

    private final OrderRepositoryService orderRepositoryService;

    @Pointcut("@annotation(com.sparta.tentenbackend.domain.order.annotation.CheckOrderOwner)")
    private void checkOrderOwner() {
    }

    @Before("checkOrderOwner()")
    public void checkOrderOwner(JoinPoint joinPoint) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new UnauthorizedException("인증되지 않은 사용자입니다.");
        }

        String userEmail = authentication.getName(); // 현재 로그인한 사용자 정보

        // orderId 추출 (PathVariable 또는 RequestBody에서)
        Object orderId = extractOrderId(joinPoint);
        if (orderId == null) {
            throw new IllegalArgumentException("orderId가 필요합니다.");
        }

        // orderId를 이용해 해당 주문의 소유주 확인
        Order order = orderRepositoryService.getOrderById((UUID) orderId);

        if (!userEmail.equals(order.getStore().getUser().getEmail())) {
            throw new UnauthorizedException("해당 주문에 대한 접근 권한이 없습니다.");
        }

        log.info("✅ 검증 성공~!");
    }

    private Object extractOrderId(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Object[] args = joinPoint.getArgs();
        Parameter[] parameters = method.getParameters();

        // 1️⃣ PathVariable에서 orderId 찾기
        for (int i = 0; i < parameters.length; i++) {
            if (parameters[i].isAnnotationPresent(PathVariable.class)) {
                return args[i]; // @PathVariable이 붙은 파라미터 값 반환
            }
        }

//        // 2️⃣ RequestBody에서 orderId 찾기 (DTO 객체 내부에서)
//        for (Object arg : args) {
//            if (arg != null && arg.getClass().isAnnotationPresent(RequestBody.class)) {
//                // orderId 필드를 찾음
//                try {
//                    Field field = arg.getClass().getDeclaredField("orderId");
//                    field.setAccessible(true);
//                    return field.get(arg);
//                } catch (NoSuchFieldException | IllegalAccessException e) {
//                    log.warn("DTO에서 orderId 필드를 찾을 수 없음");
//                }
//            }
//        }

        return null;
    }
}
