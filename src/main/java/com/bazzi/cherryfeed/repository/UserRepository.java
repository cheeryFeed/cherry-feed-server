package com.bazzi.cherryfeed.repository;


import com.bazzi.cherryfeed.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> { //관리할 엔티티,PK아이디
    //조회:findBy ,readBy ,queryBy , getBy 이렇게 시작해야함
    Optional<User> findByEmail(String email);
    Optional<User> findByNickname(String nickname);
    Optional<User> findByConnectCode(String code);
    User findUserById(Long id);
    User findUserByEmail(String email);
    User findUserByConnectCode(String code);

}