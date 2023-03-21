package com.bazzi.cherryfeed.service;

import com.bazzi.cherryfeed.domain.Anvsy;
import com.bazzi.cherryfeed.domain.Couple;
import com.bazzi.cherryfeed.domain.CoupleCalendar;
import com.bazzi.cherryfeed.domain.User;
import com.bazzi.cherryfeed.domain.dto.CalendarResponseDto;
import com.bazzi.cherryfeed.domain.dto.CalendarRequestDto;
import com.bazzi.cherryfeed.domain.dto.CalendarResponseDto;
import com.bazzi.cherryfeed.repository.CoupleCalendarRepository;
import com.bazzi.cherryfeed.repository.CoupleRepository;
import com.bazzi.cherryfeed.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CoupleCalendarService {
    private final UserRepository userRepository;
    private final CoupleRepository coupleRepository;
    private final CoupleCalendarRepository coupleCalendarRepository;

    public String createCalendar(String userEmail, CalendarRequestDto calendarRequestDto){
        User findedUser = userRepository.findUserByEmail(userEmail);
        Couple couple = findedUser.getCouple();

        CoupleCalendar coupleCalendar = CoupleCalendar.builder()
                .partiId1(calendarRequestDto.getPartiId1())
                .partiId2(calendarRequestDto.getPartiId2())
                .title(calendarRequestDto.getTitle())
                .isAllDay(calendarRequestDto.isAllDay())
                .startAt(calendarRequestDto.getStartAt())
                .endAt(calendarRequestDto.getEndAt())
                .imgId(calendarRequestDto.getImgId())
                .location(calendarRequestDto.getLocation())
                .status(calendarRequestDto.getStatus())
                .content(calendarRequestDto.getContent())
                .alarmAt(calendarRequestDto.getAlarmAt())
                .type(calendarRequestDto.getType())
                .coupleId(couple)
                .createdById(findedUser)
                .build();
        coupleCalendarRepository.save(coupleCalendar);
        return "SUCCES";
    }
    public List<CalendarResponseDto> readCalendar(String userEmail){
        User fidedUser = userRepository.findUserByEmail(userEmail);     //user
        Couple coupleId = fidedUser.getCouple();                        //couple_id(PK)

        List<CoupleCalendar> calendars = coupleCalendarRepository.findByCoupleId(coupleId);// 조회한 일정들을 list에 담는다.

        List<CalendarResponseDto> calendarResponseDtoList = new ArrayList<>();// 응답DTO를 담을 리스트를 생성한다.

        for (CoupleCalendar calendar : calendars) {
            CalendarResponseDto dto= CalendarResponseDto.builder() //생성한 DTO리스트에 조회한 일정들을 담는다.
                    .id(calendar.getId())
                    .title(calendar.getTitle())
                    .isAllDay(calendar.isAllDay())
                    .startAt(calendar.getStartAt())
                    .endAt(calendar.getEndAt())
                    //이미지넣어야함.
                    .location(calendar.getLocation())
                    .status(calendar.getStatus())
                    .content(calendar.getContent())
                    .alarmAt(calendar.getAlarmAt())
                    .type(calendar.getType())
                    .build();
            calendarResponseDtoList.add(dto);
        }
        return calendarResponseDtoList; //조회한 일정을 담은 응답 DTO를 반환한다.
    }
}
