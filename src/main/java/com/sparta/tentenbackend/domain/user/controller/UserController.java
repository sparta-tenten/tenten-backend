
package com.sparta.tentenbackend.domain.user.controller;


import com.sparta.tentenbackend.domain.region.entity.Town;
import com.sparta.tentenbackend.domain.user.dto.SignupRequestDto;
import com.sparta.tentenbackend.domain.user.dto.UserProfileResponseDto;
import com.sparta.tentenbackend.domain.user.dto.UserUpdateRequestDto;
import com.sparta.tentenbackend.domain.user.dto.UserUpdateResponse;
import com.sparta.tentenbackend.domain.user.entity.User;
import com.sparta.tentenbackend.domain.user.security.UserDetailsImpl;
import com.sparta.tentenbackend.domain.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import com.sparta.tentenbackend.domain.jwt.JwtUtil;
import com.sparta.tentenbackend.domain.jwt.service.JwtBlacklistService;
import com.sparta.tentenbackend.domain.user.dto.LoginRequestDto;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "User", description = "유저 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Slf4j
public class UserController {

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


    @GetMapping("/user/me")
    @Operation(summary = "유저 정보 조회")
    public UserProfileResponseDto getUserProfile(
        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        if (userDetails == null) {
            log.error("userDetails가 null입니다.");
            throw new RuntimeException("인증 정보가 존재하지 않습니다.");
        }

        User user = userDetails.getUser();
        return new UserProfileResponseDto(user.getUserName(), user.getEmail(), user.getAddress(),
            user.getDetailAddress(),
            user.getPhoneNumber(), user.getRole().getRole().substring(5));
    }


    @PutMapping("/user/me")
    @Operation(summary = "유저 정보 수정")
    public UserUpdateResponse updateUserprofile(
        @Valid @RequestBody UserUpdateRequestDto userUpdateRequestDto,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        if (userDetails == null) {
            log.error("userDetails가 null입니다.");
            throw new RuntimeException("인증 정보가 존재하지 않습니다.");
        }

        UserUpdateResponse userUpdateResponse = userService.updateUser(userUpdateRequestDto,
            userDetails);

        return userUpdateResponse;
    }


    @DeleteMapping("/user/me")
    @Operation(summary = "유저 삭제")
    public ResponseEntity<?> deleteUser(@AuthenticationPrincipal UserDetailsImpl userDetails) {

        return ResponseEntity.ok(userService.deleteUser(userDetails));
    }


    @GetMapping("/test")
    @Operation(summary = "유저 정보 조회")
    public ResponseEntity<?> getTest(
    ) {

        return ResponseEntity.status(HttpStatus.CREATED).body("권한부여 완료");
    }


}
