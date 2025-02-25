package com.sparta.tentenbackend.domain.user.security;


import com.sparta.tentenbackend.domain.user.entity.User;
import com.sparta.tentenbackend.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userRepository.findByEmailAndIsDeletedFalse(email)
            .orElseThrow(() -> new UsernameNotFoundException(("해당 이메일을 찾을 수 없습니다!!" + email)));

        return new UserDetailsImpl(user);


    }

}

