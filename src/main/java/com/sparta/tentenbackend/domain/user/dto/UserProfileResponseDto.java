package com.sparta.tentenbackend.domain.user.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileResponseDto {

    private String userName;
    private String email;
    private String address;
    private String detailAddress;
    private String phoneNumber;
    private String role;

}