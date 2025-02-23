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
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        User user = userRepository.findByUserNameAndIsDeletedFalse(userName)
            .orElseThrow(() -> new UsernameNotFoundException(("해당 유저이름을 찾을 수 없습니다. " + userName)));

        return new UserDetailsImpl(user);


    }

}

