package com.bazzi.cherryfeed.domain.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@ApiModel(value = "캘린더조회응답모델")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CalendarResponseDto {
    private Long id; //일정 아이디
    private String title;       //제목
    private boolean isAllDay; //종일 여부
    private Date startAt;       //시작일자
    private Date endAt;         //종료일자
    private Long imgId;         //이미지아이디
    private String location;    //장소
    private String status;      //진행상황
    private String content;     //내용(다이어리)
    private Date alarmAt;       //알림일자
    private int type;          //유형 - 계획1,일정2
    private List<CheckListResponseDto> checkList;
}
