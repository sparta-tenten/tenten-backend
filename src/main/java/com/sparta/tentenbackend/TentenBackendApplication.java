package com.sparta.tentenbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class TentenBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(TentenBackendApplication.class, args);
    }

}
