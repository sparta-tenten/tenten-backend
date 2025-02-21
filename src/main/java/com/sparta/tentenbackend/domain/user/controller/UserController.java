package com.sparta.tentenbackend.domain.user.controller;


import com.sparta.tentenbackend.domain.user.dto.SignupRequestDto;
import com.sparta.tentenbackend.domain.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import com.sparta.tentenbackend.domain.jwt.JwtUtil;
import com.sparta.tentenbackend.domain.jwt.service.JwtBlacklistService;
import com.sparta.tentenbackend.domain.user.dto.LoginRequestDto;
import com.sparta.tentenbackend.domain.user.dto.SignupRequestDto;
import com.sparta.tentenbackend.domain.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "User", description = "유저 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")

public class UserController {

    private final UserService userService;

    @PostMapping("/auth/sign-up")
    @Operation(summary = "유저 회원가입")
    public ResponseEntity<?> signup(
        @Valid @RequestBody  SignupRequestDto request) {
         String response = String.valueOf(userService.signup(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final JwtBlacklistService jwtBlacklistService;

    @PostMapping("/auth/sign-up")
    @Operation(summary = "유저 회원가입")
    public ResponseEntity<?> signup(
        @Valid @RequestBody SignupRequestDto request) {
        String response = String.valueOf(userService.signup(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @PostMapping("/user/login")
    @Operation(summary = "유저 로그인")
    public ResponseEntity<?> login(
        @Valid @RequestBody LoginRequestDto request, HttpServletResponse res) {
        String response = String.valueOf(userService.login(request, res));
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }


    @PostMapping("/user/logout")
    @Operation(summary = "유저 로그아웃")
    public ResponseEntity<?> logout(HttpServletRequest req) {

        String token = jwtUtil.getTokenFromRequest(req);

        if (token != null) {
            jwtBlacklistService.addToBlacklist(token);
        }

        return ResponseEntity.ok("로그아웃 성공");

    }

}



