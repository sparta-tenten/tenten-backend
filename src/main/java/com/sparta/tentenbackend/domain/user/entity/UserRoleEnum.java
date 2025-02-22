package com.sparta.tentenbackend.domain.user.entity;


import lombok.Getter;

@Getter
public enum UserRoleEnum {
    CUSTOMER("CUSTOMER"),
    OWNER("OWNER"),
    MANAGER("MANAGER"),
    MASTER("MASTER");


    private final String role;

    public String getAuthority() {
        return this.role;
    }

    UserRoleEnum(String role) {
        this.role = role;
    }

    public static class Authority {

        public static final String CUSTOMER = "CUSTOMER";
        public static final String OWNER = "OWNER";
        public static final String MANAGER = "MANAGER";
        public static final String MASTER = "MASTER";
    }

}

