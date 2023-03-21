package com.bazzi.cherryfeed.controller;

import com.bazzi.cherryfeed.domain.dto.CalendarRequest;
import com.bazzi.cherryfeed.service.CoupleCalendarService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "캘린더")
@RestController
@RequestMapping(value = "/api/v1/calender" , produces = "application/json; charset=utf8")
@RequiredArgsConstructor
public class CoupleCalendarController {
    private final CoupleCalendarService coupleCalendarService;

    @ApiOperation(value = "캘린더등록")
    @PostMapping("/post-calendar")
    public ResponseEntity<String> createCalendar(Authentication authentication, @RequestBody CalendarRequest calendarRequest){
        String userEmail = authentication.getName(); //이메일
        coupleCalendarService.createCalendar(userEmail,calendarRequest);
        return ResponseEntity.ok().body("등록완료");
    }
}
