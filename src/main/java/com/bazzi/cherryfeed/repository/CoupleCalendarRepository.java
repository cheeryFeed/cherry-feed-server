package com.bazzi.cherryfeed.repository;

import com.bazzi.cherryfeed.domain.Anvsy;
import com.bazzi.cherryfeed.domain.Couple;
import com.bazzi.cherryfeed.domain.CoupleCalendar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CoupleCalendarRepository extends JpaRepository<CoupleCalendar,Long> {
    List<CoupleCalendar> findByCoupleId(Couple couple);
}
