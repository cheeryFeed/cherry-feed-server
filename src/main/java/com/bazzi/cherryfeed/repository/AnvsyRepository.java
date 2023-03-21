package com.bazzi.cherryfeed.repository;

import com.bazzi.cherryfeed.domain.Anvsy;
import com.bazzi.cherryfeed.domain.Couple;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnvsyRepository extends JpaRepository<Anvsy, Long> {
    List<Anvsy> findByCoupleId(Couple couple);
}
