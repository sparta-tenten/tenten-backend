
package com.sparta.tentenbackend.domain.jwt;

import com.sparta.tentenbackend.domain.jwt.service.JwtBlacklistService;
import com.sparta.tentenbackend.domain.user.security.UserDetailsImpl;
import com.sparta.tentenbackend.domain.user.security.UserDetailsServiceImpl;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j(topic = "JWT 검증 및 인가")
@RequiredArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserDetailsServiceImpl userDetailsService;
    private final JwtBlacklistService jwtBlacklistService;

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res,
        FilterChain filterChain) throws ServletException, IOException {

        String tokenValue = jwtUtil.getTokenFromRequest(req);

        // 토큰이 없으면 필터 체인 실행 후 종료
        if (!StringUtils.hasText(tokenValue)) {
            log.warn("JWT Token이 없습니다. 인증을 건너뜁니다.");
            filterChain.doFilter(req, res);
            return;
        }

        // 블랙리스트에 포함된 토큰인지 확인
        if (jwtBlacklistService.isBlacklisted(tokenValue)) {
            log.error("로그아웃된 토큰입니다.");
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        log.info("🔑 최종 JWT Token: " + tokenValue);

        // "Bearer " 접두어가 있을 경우 제거
        try {
            // JWT 토큰 검증
            if (!jwtUtil.validateToken(tokenValue)) {
                log.error("Invalid JWT Token");
                filterChain.doFilter(req, res);
                return;
            }

            // 사용자 정보 가져와서 인증 설정
            Claims info = jwtUtil.getUserInfoFromToken(tokenValue);
            setAuthentication(info.getSubject());
        } catch (Exception e) {
            log.error("JWT 처리 중 오류 발생: " + e.getMessage());
        }
        filterChain.doFilter(req, res);
    }


    public void setAuthentication(String username) {
        log.info("🔍 JwtAuthorizationFilter 실행됨");

        // ✅ UserDetails 가져오기
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        if (!(userDetails instanceof UserDetailsImpl)) {
            log.error("❌ UserDetails가 UserDetailsImpl이 아닙니다. 인증 실패");
            return;
        }

        log.info("👤 유저 권한: {}", userDetails.getAuthorities());

        //  Authentication 객체 생성
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null,
            userDetails.getAuthorities());

        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authentication);
        SecurityContextHolder.setContext(context);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        //  SecurityContext 유지 전략 (ThreadLocal)
        SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_THREADLOCAL);
//        SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);

        log.info(" 최종 SecurityContext Authentication: {}",
            SecurityContextHolder.getContext().getAuthentication());
    }


    // 인증 객체 생성
    private Authentication createAuthentication(String username) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        return new UsernamePasswordAuthenticationToken(userDetails, null,
            userDetails.getAuthorities());
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getRequestURI();

        // Swagger 관련 요청은 필터 적용 제외
        return path.startsWith("/swagger-ui") ||
            path.startsWith("/v3/api-docs") ||
            path.startsWith("/swagger-resources") ||
            path.startsWith("/webjars/") ||
            path.startsWith("/api/auth/") ||
            path.equals("/");
    }
}
