package com.bazzi.cherryfeed.controller;

import com.bazzi.cherryfeed.domain.dto.CalendarRequestDto;
import com.bazzi.cherryfeed.domain.dto.CalendarResponseDto;
import com.bazzi.cherryfeed.domain.dto.CalendarUpdateRequestDto;
import com.bazzi.cherryfeed.service.CoupleCalendarService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "캘린더")
@RestController
@RequestMapping(value = "/api/v1/calender" , produces = "application/json; charset=utf8")
@RequiredArgsConstructor
public class CalendarController {
    private final CoupleCalendarService coupleCalendarService;

    @ApiOperation(value = "캘린더등록")
    @PostMapping("/post-calendar")
    public ResponseEntity<String> createCalendar(Authentication authentication, @RequestBody CalendarRequestDto calendarRequestDto){
        String userEmail = authentication.getName(); //이메일
        coupleCalendarService.createCalendar(userEmail, calendarRequestDto);
        return ResponseEntity.ok().body("등록완료");
    }

    @ApiOperation(value = "캘린더조회")
    @GetMapping
    public ResponseEntity<List> readCalendar(Authentication authentication){
        String userEmail = authentication.getName();//이메일
        List<CalendarResponseDto> calendarResponseDtoList = coupleCalendarService.readCalendar(userEmail);
        return ResponseEntity.ok().body(calendarResponseDtoList);
    }
    @ApiOperation(value = "캘린더수정")
    @PutMapping("/post-calendar/{id}")
    public ResponseEntity<String> updateCalendar(@PathVariable Long id, @RequestBody CalendarUpdateRequestDto calendarUpdateRequestDto){
        coupleCalendarService.updateCalendar(id, calendarUpdateRequestDto);
        return ResponseEntity.ok().body("수정완료");
    }
    @ApiOperation(value = "캘린더삭제")
    @DeleteMapping("/post-calendar/{id}")
    public ResponseEntity<String> updateCalendar(@PathVariable Long id){
        coupleCalendarService.deleteCalendar(id);
        return ResponseEntity.ok().body("삭제완료");
    }
}
