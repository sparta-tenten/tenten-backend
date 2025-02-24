package com.sparta.tentenbackend.domain.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserUpdateRequestDto {


    @Pattern(regexp = "^[a-z0-9]{4,10}$", message = "유저네임은 4~10자의 소문자, 숫자만 가능합니다.")
    private String userName;

    @Pattern(
        regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,15}$",
        message = "비밀번호는 8~15자이며, 알파벳 대소문자, 숫자, 특수문자를 포함해야 합니다."
    )
    private String password;


    @NotBlank(message = "전화번호는 필수 입력 항목입니다.")
    private String phoneNumber;

    @NotBlank(message = "주소는 필수 입력 항목입니다.")
    private String address;

    @NotBlank(message = "상세 주소는 필수 입력 항목입니다.")
    private String detailAddress;

    @NotBlank(message = "법정동 코드는 필수 입력 항목입니다.")
    private String townCode;


}
