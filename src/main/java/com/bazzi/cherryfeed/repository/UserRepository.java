package com.bazzi.cherryfeed.repository;

import com.bazzi.cherryfeed.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
public interface UserRepository extends JpaRepository<User, Long> {

}