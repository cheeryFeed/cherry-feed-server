package com.bazzi.cherryfeed.repository;

import com.bazzi.cherryfeed.domain.CheckList;
import com.bazzi.cherryfeed.domain.CoupleCalendar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CheckListRepository extends JpaRepository<CheckList,Long> {
    List<CheckList> findByCalendarId(CoupleCalendar calendar);
}
