package com.bazzi.cherryfeed.apps.calendar.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@ApiModel(value = "캘린더등록모델")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CalendarUpdateRequestDto {
    private Long PartiId1;      //참여자아이디1
    private Long PartiId2;      //참여자아이디2
    private String title;       //제목
    private Boolean isAllDay;  //종일 여부
    private Date startAt;       //시작일자
    private Date EndAt;         //종료일자
    private Long imgId;         //이미지아이디
    private String location;    //장소
    private String status;      //진행상황
    private String content;     //내용(다이어리)
    private Date alarmAt;       //알림일자
    private int type;          //유형 - 계획1,일정2
    private List<CheckListUpdateRequestDto> checkList;
}
