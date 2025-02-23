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



}


