package com.bazzi.cherryfeed.apps.couple.repository;

import com.bazzi.cherryfeed.apps.couple.domain.Couple;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoupleRepository extends JpaRepository<Couple, Long> {
   // @Query("select c from Couple c where c.receiveById in :userId")
    //List<Couple> findByUsersId(@Param("userId")Collection<Long> usersId);
}
