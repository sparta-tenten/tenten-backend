package com.sparta.tentenbackend.domain.user.dto;

import com.sparta.tentenbackend.domain.region.entity.Town;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class UserUpdateResponse {

    private String userName;
    private String email;
    private String address;
    private String detailAddress;
    private String phoneNumber;
    private Town town;

}



