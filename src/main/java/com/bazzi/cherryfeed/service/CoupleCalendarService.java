package com.bazzi.cherryfeed.service;

import com.bazzi.cherryfeed.domain.Couple;
import com.bazzi.cherryfeed.domain.CoupleCalendar;
import com.bazzi.cherryfeed.domain.User;
import com.bazzi.cherryfeed.domain.dto.CalendarRequest;
import com.bazzi.cherryfeed.repository.CoupleCalendarRepository;
import com.bazzi.cherryfeed.repository.CoupleRepository;
import com.bazzi.cherryfeed.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CoupleCalendarService {
    private final UserRepository userRepository;
    private final CoupleRepository coupleRepository;
    private final CoupleCalendarRepository coupleCalendarRepository;

    public String createCalendar(String userEmail, CalendarRequest calendarRequest){
        User findedUser = userRepository.findUserByEmail(userEmail);
        Couple couple = findedUser.getCouple();

        CoupleCalendar coupleCalendar = CoupleCalendar.builder()
                .partiId1(calendarRequest.getPartiId1())
                .partiId2(calendarRequest.getPartiId2())
                .title(calendarRequest.getTitle())
                .isAllDay(calendarRequest.isAllDay())
                .startAt(calendarRequest.getStartAt())
                .endAt(calendarRequest.getEndAt())
                .imgId(calendarRequest.getImgId())
                .location(calendarRequest.getLocation())
                .status(calendarRequest.getStatus())
                .content(calendarRequest.getContent())
                .alarmAt(calendarRequest.getAlarmAt())
                .type(calendarRequest.getType())
                .coupleId(couple)
                .createdById(findedUser)
                .build();
        coupleCalendarRepository.save(coupleCalendar);
        return "SUCCES";
    }
}
