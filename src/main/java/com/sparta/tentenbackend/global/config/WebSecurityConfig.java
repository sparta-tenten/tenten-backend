package com.sparta.tentenbackend.global.config;


import com.sparta.tentenbackend.domain.jwt.JwtAuthenticationFilter;
import com.sparta.tentenbackend.domain.jwt.JwtAuthorizationFilter;
import com.sparta.tentenbackend.domain.jwt.JwtUtil;
import com.sparta.tentenbackend.domain.jwt.service.JwtBlacklistService;
import com.sparta.tentenbackend.domain.user.security.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
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
    public AuthenticationManager authenticationManager(
        AuthenticationConfiguration configuration)
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
        // CSRF 설정
        http.csrf((csrf) -> csrf.disable());

        // TODO: 접근권한 제한하기 ex) .requestMatchers("/owner").hasRole("OWNER")); .. 현재는 /api/**로 다 풀려있어서 수정해야함
        http.authorizeHttpRequests((authorizeHttpRequests) ->
            authorizeHttpRequests
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations())
                .permitAll() // resources 접근 허용 설정
                .requestMatchers(
                    "/swagger-ui/**", "/swagger-ui.html", "/v3/api-docs/**", "/api-docs/**",
                    "/swagger-resources/**", "/webjars/**").permitAll()
                .requestMatchers("/api/auth/**", "/", "/api/user/**").permitAll()
                .anyRequest().authenticated()
        );
        // 필터 관리
        http.addFilterBefore(jwtAuthorizationFilter(), JwtAuthenticationFilter.class);
        http.addFilterBefore(jwtAuthenticationFilter(),
            UsernamePasswordAuthenticationFilter.class);

        http.headers(headers -> headers
            .cacheControl(cache -> cache.disable()) // 캐싱 방지
            .frameOptions(frame -> frame.disable()) // 프레임 옵션 비활성화
            .addHeaderWriter((request, response) -> {
                response.setHeader("Access-Control-Expose-Headers",
                    "Authorization"); // ✅ Authorization 헤더 노출 허용
            })
        );

        http.sessionManagement((session) -> session
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();

    }
}

