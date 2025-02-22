package com.sparta.tentenbackend.domain.user.service;


import com.sparta.tentenbackend.domain.region.entity.Town;
import com.sparta.tentenbackend.domain.user.dto.SignupRequestDto;
import com.sparta.tentenbackend.domain.user.entity.User;
import com.sparta.tentenbackend.domain.user.entity.UserRoleEnum;
import com.sparta.tentenbackend.domain.user.repository.UserRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {


    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final Environment env;

    @Transactional
    public ResponseEntity<String> signup(SignupRequestDto requestDto) {
        String username = requestDto.getUserName();
        String password = bCryptPasswordEncoder.encode(requestDto.getPassword());

        // 회원 중복 확인
        Optional<User> checkUsername = userRepository.findByUserName(username);
        if (checkUsername.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
        }

        // email 중복확인
        String email = requestDto.getEmail();
        Optional<User> checkEmail = userRepository.findByEmail(email);
        if (checkEmail.isPresent()) {
            throw new IllegalArgumentException("중복된 Email 입니다.");
        }

        // TODO: 법정동 코드 테이블 만들고 db에 넣기
        // DB에서 해당 법정동 코드 찾기
//        Town town = townRepository.findByName(townName)
//            .orElseThrow(() -> new IllegalArgumentException("해당 법정동을 찾을 수 없습니다: " + townName));

        // 권한 부여, 기본 권한: CUSTOMER
        UserRoleEnum role = UserRoleEnum.CUSTOMER;

        System.out.println("====="+requestDto.getRole());

        // OWNER, MANAGER, MASTER 요청 시 Secret Code 확인
        if (requestDto.getRole() != null
            && requestDto.getRole() != UserRoleEnum.CUSTOMER) {
            String expectedToken = env.getProperty(
                "admin.token." + requestDto.getRole().name().toLowerCase());

            if (!expectedToken.equals(requestDto.getAdminToken())) {
                throw new IllegalArgumentException("유효하지 않은 토큰입니다.");
            }

            role = requestDto.getRole();
        }

        System.out.println(role);

        // 유저 등록
        User user = new User(requestDto.getUserName(), password, requestDto.getEmail(), role,
            requestDto.getAddress(), requestDto.getDetailAddress(), requestDto.getPhoneNumber());

        userRepository.save(user);

        return ResponseEntity.ok("회원가입에 성공했습니다. [" + role + "] 권한이 부여되었습니다.");
    }

}
