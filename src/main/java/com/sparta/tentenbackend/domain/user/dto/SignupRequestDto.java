
package com.sparta.tentenbackend.domain.user.dto;


import com.sparta.tentenbackend.domain.user.entity.UserRoleEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SignupRequestDto {

    @Pattern(regexp = "^[a-z0-9]{4,10}$", message = "유저네임은 4~10자의 소문자, 숫자만 가능합니다.")
    private String userName;
    @Pattern(
        regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,15}$",
        message = "비밀번호는 8~15자이며, 알파벳 대소문자, 숫자, 특수문자를 포함해야 합니다."
    )
    private String password;
    @Email(message = "올바른 이메일 형식을 입력하세요.")
    private String email;
    private String address;
    private String detailAddress;

    private String phoneNumber;

    private UserRoleEnum role;  // OWNER, MANAGER, MASTER 등 원하는 권한 요청
    private String adminToken;

    @NotBlank(message = "법정동 코드를 입력하세요.")
    private String townCode;

}
