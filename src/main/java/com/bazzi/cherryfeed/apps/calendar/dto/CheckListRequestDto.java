package com.bazzi.cherryfeed.apps.calendar.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@ApiModel(value = "체크리스트등록모델")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CheckListRequestDto {
    private String content;      //내용(체크리스트)
    private Boolean isFinish;    //체크유무 true,false
}
