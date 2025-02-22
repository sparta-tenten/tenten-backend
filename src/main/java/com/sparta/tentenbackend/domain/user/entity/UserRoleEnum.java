package com.sparta.tentenbackend.domain.user.entity;


import lombok.Getter;

@Getter
public enum UserRoleEnum {
    CUSTOMER("CUSTOMER"),
    OWNER("OWNER"),
    MANAGER("MANAGER"),
    MASTER("MASTER");


    private final String role;

    UserRoleEnum(String role) {
        this.role = role;
    }
}

