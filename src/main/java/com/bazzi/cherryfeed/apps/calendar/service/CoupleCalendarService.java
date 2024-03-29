package com.bazzi.cherryfeed.apps.calendar.service;

import com.bazzi.cherryfeed.apps.account.domain.Account;
import com.bazzi.cherryfeed.apps.calendar.domain.CheckList;
import com.bazzi.cherryfeed.apps.calendar.domain.CoupleCalendar;
import com.bazzi.cherryfeed.apps.calendar.dto.*;
import com.bazzi.cherryfeed.apps.couple.domain.Couple;
import com.bazzi.cherryfeed.apps.calendar.repository.CheckListRepository;
import com.bazzi.cherryfeed.apps.calendar.repository.CoupleCalendarRepository;
import com.bazzi.cherryfeed.apps.account.repository.AccountRepository;
import com.bazzi.cherryfeed.exception.AppException;
import com.bazzi.cherryfeed.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CoupleCalendarService {
    private final AccountRepository accountRepository;
    private final CoupleCalendarRepository coupleCalendarRepository;
    private final CheckListRepository checkListRepository;

    public String createCalendar(Long id, CalendarRequestDto.Create calendarRequestDto) {
        Account findedUser = accountRepository.findByIdFetchCouple(id).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND ,ErrorCode.USER_NOT_FOUND.getMessage()));
        Couple couple = findedUser.getCouple();
        Date startDate = null;
        Date endDate = null;

        if (couple == null) {
            throw new AppException(ErrorCode.COUPLE_NOT_FOUND);
        }
        try {
            String startAt = calendarRequestDto.getStartAt();
            String endAt = calendarRequestDto.getEndAt();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
            startDate = dateFormat.parse(startAt);
            endDate = dateFormat.parse(endAt);
        } catch (ParseException pe) {
            log.error(pe.toString());
        }
        //캘린더 저장
        CoupleCalendar coupleCalendar = CoupleCalendar.builder()
                .partiId1(calendarRequestDto.getPartiId1())
                .partiId2(calendarRequestDto.getPartiId2())
                .title(calendarRequestDto.getTitle())
                .isAllDay(calendarRequestDto.getIsAllDay())
                .startAt(startDate)
                .endAt(endDate)
                .imgId(calendarRequestDto.getImgId())
                .location(calendarRequestDto.getLocation())
                .status(calendarRequestDto.getStatus())
                .content(calendarRequestDto.getContent())
                .alarmAt(calendarRequestDto.getAlarmAt())
                .type(calendarRequestDto.getType())
                .couple(couple)
                .account(findedUser)
                .build();
        CoupleCalendar savedCalendar = coupleCalendarRepository.save(coupleCalendar);

        //체크리스트 저장
        List<CheckListRequestDto.Create> checkListRequestDtoList = calendarRequestDto.getCheckList();
        for (CheckListRequestDto.Create dto : checkListRequestDtoList) {
            CheckList checkList = CheckList.builder()
                    .content(dto.getContent())
                    .isFinish(dto.isFinish())
                    .calendar(savedCalendar)
                    .build();
            checkListRepository.save(checkList);
        }
        return "캘린더 등록완료.";
    }

    public List<CalendarRequestDto.Response> readCalendar(Long id) {
        Account fidedUser = accountRepository.findByIdFetchCouple(id).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND ,ErrorCode.USER_NOT_FOUND.getMessage()));     //user
        Couple couple = fidedUser.getCouple();                        //couple_id(PK)
        log.info(couple.toString());
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");

        List<CoupleCalendar> calendars = coupleCalendarRepository.findByCoupleId(couple.getId());// 조회한 일정들을 list에 담는다.

        List<CalendarRequestDto.Response> calendarResponseDtoList = new ArrayList<>();// 응답DTO를 담을 리스트를 생성한다.

        for (CoupleCalendar calendar : calendars) {
            List<CheckList> checkListList = checkListRepository.findByCalendarId(calendar.getId());
            List<CheckListRequestDto.Response> checkListResponseDtoList = new ArrayList<>();

            for (CheckList checkList : checkListList) {
                CheckListRequestDto.Response checkListDto = CheckListRequestDto.Response.builder()
                        .id(checkList.getId())
                        .content(checkList.getContent())
                        .isFinish(checkList.getIsFinish())
                        .build();
                checkListResponseDtoList.add(checkListDto);
            }


            CalendarRequestDto.Response dto = CalendarRequestDto.Response.builder() //생성한 DTO리스트에 조회한 일정들을 담는다.
                    .id(calendar.getId())
                    .title(calendar.getTitle())
                    .isAllDay(calendar.getIsAllDay())
                    .startAt(format.format(calendar.getStartAt()))
                    .endAt(format.format(calendar.getEndAt()))
                    .imgId(calendar.getImgId())
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

    @Transactional
    public String updateCalendar(Long id, CalendarRequestDto.Update calendarUpdateRequestDto) {
        CoupleCalendar coupleCalendar = coupleCalendarRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND ,ErrorCode.USER_NOT_FOUND.getMessage()));

        try {
            coupleCalendar.updateCalendar(calendarUpdateRequestDto);
        } catch (ParseException pe) {
            log.error(pe.toString());
        }

        List<CheckListRequestDto.Update> checkList = calendarUpdateRequestDto.getCheckList();//체크리스트
        for (CheckListRequestDto.Update checkListUpdateRequestDto : checkList) {
            Long checkid = checkListUpdateRequestDto.getId();
            String checkContent = checkListUpdateRequestDto.getContent();
            Boolean isFinish = checkListUpdateRequestDto.getIsFinish();
            CheckList findedCheckList = checkListRepository.findById(checkid).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND ,ErrorCode.USER_NOT_FOUND.getMessage()));
            findedCheckList.updateCheckList(checkContent, isFinish);
        }
        return "캘린더 수정완료.";
    }

    @Transactional
    public String deleteCalendar(Long id) {
        coupleCalendarRepository.deleteById(id);
        return "캘린더 삭제완료.";
    }
}
