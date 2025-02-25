package com.sparta.tentenbackend.domain.user.entity;

import lombok.Getter;

@Getter
public enum UserRoleEnum {
    CUSTOMER("ROLE_CUSTOMER"),
    OWNER("ROLE_OWNER"),
    MANAGER("ROLE_MANAGER"),
    MASTER("ROLE_MASTER");

    private final String role;

    public String getAuthority() {
        return this.role;
    }

    UserRoleEnum(String role) {
        this.role = role;
    }

    public static class Authority {

        public static final String CUSTOMER = "ROLE_CUSTOMER";
        public static final String OWNER = "ROLE_OWNER";
        public static final String MANAGER = "ROLE_MANAGER";
        public static final String MASTER = "ROLE_MASTER";
    }
}