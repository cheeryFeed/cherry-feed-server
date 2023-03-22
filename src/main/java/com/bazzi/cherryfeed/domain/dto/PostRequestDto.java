package com.bazzi.cherryfeed.domain.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@ApiModel(value = "게시물등록요청모델")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PostRequestDto {
    private String postNm;      //게시물제목
    private String postContent; //게시물 내용
    private String location;    //장소
    private Long imgId;         //이미지아이디
    private Long calendarId;    //캘린더 아이디
}
