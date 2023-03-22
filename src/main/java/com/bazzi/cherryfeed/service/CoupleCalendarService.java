package com.bazzi.cherryfeed.service;

import com.bazzi.cherryfeed.domain.*;
import com.bazzi.cherryfeed.domain.dto.*;
import com.bazzi.cherryfeed.domain.dto.CalendarResponseDto;
import com.bazzi.cherryfeed.repository.CheckListRepository;
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
    private final CheckListRepository checkListRepository;

    public String createCalendar(String userEmail, CalendarRequestDto calendarRequestDto){
        User findedUser = userRepository.findUserByEmail(userEmail);
        Couple couple = findedUser.getCouple();
        //캘린더 저장
        CoupleCalendar coupleCalendar = CoupleCalendar.builder()
                .partiId1(calendarRequestDto.getPartiId1())
                .partiId2(calendarRequestDto.getPartiId2())
                .title(calendarRequestDto.getTitle())
                .isAllDay(calendarRequestDto.getIsAllDay())
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
        CoupleCalendar savedCalendar = coupleCalendarRepository.save(coupleCalendar);

        //체크리스트 저장
        List<CheckListRequestDto> checkListRequestDtoList = calendarRequestDto.getCheckList();
        for (CheckListRequestDto dto : checkListRequestDtoList) {
            CheckList checkList = CheckList.builder()
                    .content(dto.getContent())
                    .isFinish(dto.getIsFinish())
                    .calendarId(savedCalendar)
                    .build();
            checkListRepository.save(checkList);
        }

        return "SUCCES";
    }
    public List<CalendarResponseDto> readCalendar(String userEmail){
        User fidedUser = userRepository.findUserByEmail(userEmail);     //user
        Couple coupleId = fidedUser.getCouple();                        //couple_id(PK)

        List<CoupleCalendar> calendars = coupleCalendarRepository.findByCoupleId(coupleId);// 조회한 일정들을 list에 담는다.

        List<CalendarResponseDto> calendarResponseDtoList = new ArrayList<>();// 응답DTO를 담을 리스트를 생성한다.

        for (CoupleCalendar calendar : calendars) {
            List<CheckList> checkListList = checkListRepository.findByCalendarId(calendar);
            List<CheckListResponseDto> checkListResponseDtoList = new ArrayList<>();

            for (CheckList checkList : checkListList) {
                CheckListResponseDto checkListDto = CheckListResponseDto.builder()
                        .content(checkList.getContent())
                        .isFinish(checkList.getIsFinish())
                        .build();
                checkListResponseDtoList.add(checkListDto);
            }


            CalendarResponseDto dto= CalendarResponseDto.builder() //생성한 DTO리스트에 조회한 일정들을 담는다.
                    .id(calendar.getId())
                    .title(calendar.getTitle())
                    .isAllDay(calendar.getIsAllDay())
                    .startAt(calendar.getStartAt())
                    .endAt(calendar.getEndAt())
                    //이미지넣어야함.
                    .location(calendar.getLocation())
                    .status(calendar.getStatus())
                    .content(calendar.getContent())
                    .alarmAt(calendar.getAlarmAt())
                    .type(calendar.getType())
                    .checkList(checkListResponseDtoList)
                    .build();
            calendarResponseDtoList.add(dto);
        }
        return calendarResponseDtoList; //조회한 일정을 담은 응답 DTO를 반환한다.
    }
}
