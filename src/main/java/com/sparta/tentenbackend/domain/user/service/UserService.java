package com.sparta.tentenbackend.domain.user.service;


import com.sparta.tentenbackend.domain.region.entity.Town;
import com.sparta.tentenbackend.domain.region.repository.TownRepository;
import com.sparta.tentenbackend.domain.user.dto.SignupRequestDto;
import com.sparta.tentenbackend.domain.user.dto.UserUpdateRequestDto;
import com.sparta.tentenbackend.domain.user.dto.UserUpdateResponse;
import com.sparta.tentenbackend.domain.user.entity.User;
import com.sparta.tentenbackend.domain.user.entity.UserRoleEnum;
import com.sparta.tentenbackend.domain.user.repository.UserRepository;
import com.sparta.tentenbackend.domain.user.security.UserDetailsImpl;
import java.util.Optional;
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
    private final TownRepository townRepository;
    private final Environment env;
    private final JwtUtil jwtUtil;

    @Transactional
    public ResponseEntity<?> signup(SignupRequestDto requestDto) {
        String email = requestDto.getEmail();
        String password = bCryptPasswordEncoder.encode(requestDto.getPassword());

        // email 확인
        Optional<User> checkEmail = userRepository.findByEmail(email);
        if (checkEmail.isPresent()) {
            if (checkEmail.get().isDeleted()) {
                throw new IllegalArgumentException("이미 삭제된 계정입니다.");
            }
            throw new IllegalArgumentException("중복된 Email 입니다.");
        }

        // 법정동 코드 확인
        Town town = townRepository.findByCode(requestDto.getTownCode())
            .orElseThrow(() -> new IllegalArgumentException("잘못된 법정동 코드입니다."));

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
            requestDto.getAddress(), requestDto.getDetailAddress(), requestDto.getPhoneNumber(),
            town);

        userRepository.save(user);

        return ResponseEntity.ok("회원가입에 성공했습니다. [" + role + "] 권한이 부여되었습니다.");
    }

    @Transactional
    public ResponseEntity<?> deleteUser(UserDetailsImpl userDetails) {
        User user = userRepository.findByEmailAndIsDeletedFalse(userDetails.getUsername())
            .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다."));

        user.deleteUser();

        return ResponseEntity.ok("회원이 삭제되었습니다.");
    }

    @Transactional
    public ResponseEntity<String> login(LoginRequestDto requestDto, HttpServletResponse res) {
        String email = requestDto.getEmail();
        String password = requestDto.getPassword();

        // 사용자 확인
        User user = userRepository.findByEmailAndIsDeletedFalse(email).orElseThrow(
            () -> new IllegalArgumentException("등록된 사용자가 없습니다.")
        );

        // 비밀번호 확인
        if (!bCryptPasswordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        // JWT 생성 및 쿠키에 저장 후 Response 객체에 추가
        String token = jwtUtil.createToken(user.getEmail(), user.getRole());
        jwtUtil.addJwtToHeader(token, res);

        return ResponseEntity.ok("로그인에 성공했습니다.");

    }

    @Transactional
    public UserUpdateResponse updateUser(UserUpdateRequestDto requestDto,
        UserDetailsImpl userDetails) {

        String email = userDetails.getUsername();
        String password = bCryptPasswordEncoder.encode(requestDto.getPassword());
        // 사용자 확인
        User user = userRepository.findByEmailAndIsDeletedFalse(email).orElseThrow(
            () -> new IllegalArgumentException("등록된 사용자가 없습니다.")
        );


        Town town = townRepository.findByCode(requestDto.getTownCode())
            .orElseThrow(() -> new IllegalArgumentException("잘못된 법정동 코드입니다."));

        user.userUpdate(requestDto.getUserName(),password, requestDto.getAddress(),
            requestDto.getDetailAddress(), requestDto.getPhoneNumber(),town);
        userRepository.save(user);

        return new UserUpdateResponse(requestDto.getUserName(), email, requestDto.getAddress(),
            requestDto.getDetailAddress(), requestDto.getDetailAddress(),town);
    }

}
