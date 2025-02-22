package com.sparta.tentenbackend.domain.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserUpdateRequestDto {

    @Pattern(
        regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,15}$",
        message = "비밀번호는 8~15자이며, 알파벳 대소문자, 숫자, 특수문자를 포함해야 합니다."
    )
    private String password;

    @Email(message = "올바른 이메일 형식을 입력하세요.")
    @NotBlank(message = "이메일은 필수 입력 항목입니다.")
    private String email;

    @NotBlank(message = "전화번호는 필수 입력 항목입니다.")
    private String phoneNumber;

    @NotBlank(message = "주소는 필수 입력 항목입니다.")
    private String address;

    @NotBlank(message = "상세 주소는 필수 입력 항목입니다.")
    private String detailAddress;


}