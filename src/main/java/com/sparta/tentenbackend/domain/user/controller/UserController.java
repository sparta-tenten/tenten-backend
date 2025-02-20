package com.sparta.tentenbackend.domain.user.controller;


import com.sparta.tentenbackend.domain.user.dto.SignupRequestDto;
import com.sparta.tentenbackend.domain.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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


}



