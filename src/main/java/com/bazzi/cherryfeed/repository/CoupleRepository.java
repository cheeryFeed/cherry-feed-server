package com.bazzi.cherryfeed.repository;

import com.bazzi.cherryfeed.domain.Couple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface CoupleRepository extends JpaRepository<Couple, Long> {
   // @Query("select c from Couple c where c.receiveById in :userId")
    //List<Couple> findByUsersId(@Param("userId")Collection<Long> usersId);
}
