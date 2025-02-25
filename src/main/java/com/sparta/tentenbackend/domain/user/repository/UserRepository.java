package com.sparta.tentenbackend.domain.user.repository;

import com.sparta.tentenbackend.domain.user.entity.User;
import java.awt.print.Book;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    Optional<User> findByUserName(String userName);
    Optional<User> findByEmail(String email);

    Optional<User> findByUserNameAndIsDeletedFalse(String userName); // 삭제되지 않은 사용자 조회
    Optional<User> findByEmailAndIsDeletedFalse(String email); // 삭제되지 않은 이메일 조회


}
