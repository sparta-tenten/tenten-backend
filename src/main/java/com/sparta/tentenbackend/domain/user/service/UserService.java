package com.sparta.tentenbackend.domain.user.service;


import com.sparta.tentenbackend.domain.jwt.JwtUtil;
import com.sparta.tentenbackend.domain.user.dto.LoginRequestDto;
import com.sparta.tentenbackend.domain.user.dto.SignupRequestDto;
import com.sparta.tentenbackend.domain.user.entity.User;
import com.sparta.tentenbackend.domain.user.entity.UserRoleEnum;
import com.sparta.tentenbackend.domain.user.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
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
    private final JwtUtil jwtUtil;

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

        // 유저 등록
        User user = new User(requestDto.getUserName(), password, requestDto.getEmail(), role,
            requestDto.getAddress(), requestDto.getDetailAddress(), requestDto.getPhoneNumber());

        userRepository.save(user);

        return ResponseEntity.ok("회원가입에 성공했습니다. [" + role + "] 권한이 부여되었습니다.");
    }


    @Transactional
    public ResponseEntity<String> login(LoginRequestDto requestDto, HttpServletResponse res) {
        String username = requestDto.getUserName();
        String password = requestDto.getPassword();

        // 사용자 확인
        User user = userRepository.findByUserName(username).orElseThrow(
            () -> new IllegalArgumentException("등록된 사용자가 없습니다.")
        );

        // 비밀번호 확인
        if (!bCryptPasswordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        // JWT 생성 및 쿠키에 저장 후 Response 객체에 추가
        String token = jwtUtil.createToken(user.getUserName(), user.getRole());
        jwtUtil.addJwtToHeader(token, res);

        return ResponseEntity.ok("로그인에 성공했습니다.");

    }

}
