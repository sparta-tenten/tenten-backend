package com.sparta.tentenbackend.global.config;

import com.sparta.tentenbackend.domain.jwt.JwtAuthenticationFilter;
import com.sparta.tentenbackend.domain.jwt.JwtAuthorizationFilter;
import com.sparta.tentenbackend.domain.jwt.JwtUtil;
import com.sparta.tentenbackend.domain.jwt.service.JwtBlacklistService;
import com.sparta.tentenbackend.domain.user.entity.UserRoleEnum;
import com.sparta.tentenbackend.domain.user.entity.UserRoleEnum.Authority;
import com.sparta.tentenbackend.domain.user.security.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final JwtUtil jwtUtil;
    private final UserDetailsServiceImpl userDetailsService;
    private final AuthenticationConfiguration authenticationConfiguration;
    private final JwtBlacklistService jwtBlacklistService;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration)
        throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() throws Exception {
        JwtAuthenticationFilter filter = new JwtAuthenticationFilter(jwtUtil);
        filter.setAuthenticationManager(authenticationManager(authenticationConfiguration));
        return filter;
    }

    @Bean
    public JwtAuthorizationFilter jwtAuthorizationFilter() {
        return new JwtAuthorizationFilter(jwtUtil, userDetailsService, jwtBlacklistService);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable());

        http.authorizeHttpRequests(auth -> auth

                // 인증 없이 접근 가능
                .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/api/auth/**", "/").permitAll()

                // [로그인 API]
                .requestMatchers("/api/user/**").authenticated()

                // [가게 API] 관리자(Master) 또는 매니저(Manager)만 추가 가능, 점주(Owner)는 업데이트 가능
                .requestMatchers("/api/stores/**").permitAll()
                .requestMatchers("/api/manager/store")
                .hasAnyAuthority(UserRoleEnum.Authority.MANAGER, UserRoleEnum.Authority.MASTER)
                .requestMatchers("/api/owner/store")
                .hasAnyAuthority(UserRoleEnum.Authority.OWNER, UserRoleEnum.Authority.MANAGER,
                    UserRoleEnum.Authority.MASTER)
                .requestMatchers("/api/manager/store")
                .hasAnyAuthority(UserRoleEnum.Authority.MANAGER, UserRoleEnum.Authority.MASTER)

                // [메뉴 API] 점주(Owner), 매니저(Manager), 관리자(Master)만 접근 가능
                .requestMatchers("/api/menu").permitAll()
                .requestMatchers("/api/owner/menu")
                .hasAnyAuthority(UserRoleEnum.Authority.OWNER, UserRoleEnum.Authority.MANAGER,
                    UserRoleEnum.Authority.MASTER)

                // [주문 API] 고객(Customer)은 주문 가능, 점주(Owner), 매니저(Manager)는 상태 변경 가능
                .requestMatchers("/api/order").hasAuthority(UserRoleEnum.Authority.CUSTOMER)
//            .requestMatchers("/api/order/cancel/**")
//            .hasAnyAuthority(UserRoleEnum.Authority.OWNER, UserRoleEnum.Authority.MANAGER,
//                UserRoleEnum.Authority.MASTER)

                .requestMatchers("/api/owner/order/**")
                .hasAnyAuthority(UserRoleEnum.Authority.OWNER, UserRoleEnum.Authority.MANAGER,
                    UserRoleEnum.Authority.MASTER)

                .requestMatchers("/api/owner/menu/**")
                .hasAnyAuthority(UserRoleEnum.Authority.OWNER, UserRoleEnum.Authority.MANAGER,
                    UserRoleEnum.Authority.MASTER)

                //  [카테고리 API] 관리자(Master)만 카테고리 추가, 수정, 삭제 가능
                .requestMatchers("/api/master/category").hasAuthority(UserRoleEnum.Authority.MASTER)
                .requestMatchers("/api/category").permitAll()

                //  [리뷰 API]
                .requestMatchers(HttpMethod.GET, "/api/review")
                .permitAll() // 가게별 리뷰 목록 모든 사용자 허용, 그 외 모두
                .requestMatchers(HttpMethod.GET, "/api/review/my-reviews")
                .hasAuthority(UserRoleEnum.Authority.CUSTOMER)
                .requestMatchers(HttpMethod.GET, "/api/review/search")
                .hasAuthority(UserRoleEnum.Authority.CUSTOMER)
                .requestMatchers(HttpMethod.POST, "/api/review")
                .hasAuthority(UserRoleEnum.Authority.CUSTOMER)
                .requestMatchers(HttpMethod.PUT, "/api/review")
                .hasAuthority(UserRoleEnum.Authority.CUSTOMER)
                .requestMatchers(HttpMethod.DELETE, "/api/review")
                .hasAuthority(UserRoleEnum.Authority.CUSTOMER)

                //  [사장님의 리뷰 API] 사장님(Owner)만 리뷰 답글 작성, 수정, 삭제 가능
                .requestMatchers("/api/owner/review").hasAuthority(UserRoleEnum.Authority.OWNER)

                // TODO: [파일 업로드 API] 관리자(Master)만 파일 업로드/삭제 가능인지 확인
                .requestMatchers("/api/s3/**").hasAuthority(UserRoleEnum.Authority.MASTER)

                // [배송지 API]
                .requestMatchers("/api/address/**")
                .hasAuthority(Authority.CUSTOMER)

                // [결제 API]
                .requestMatchers("/api/payment/**")
                .hasAuthority(Authority.CUSTOMER)

                // [AI API] 가게 사장님이 메뉴 설명 작성할 때 사용
                .requestMatchers("/api/ai").hasAuthority(Authority.OWNER)

                // 기본적으로 모든 요청은 인증이 필요함
                .anyRequest().authenticated()

        );

        http.addFilterBefore(jwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(jwtAuthenticationFilter(), JwtAuthorizationFilter.class);

        http.sessionManagement(
            session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

//    SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);
        SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_THREADLOCAL);

        return http.build();
    }

}
