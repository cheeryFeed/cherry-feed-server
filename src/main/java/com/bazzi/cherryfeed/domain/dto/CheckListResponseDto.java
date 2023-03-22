package com.bazzi.cherryfeed.domain.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@ApiModel(value = "체크리스트조회응답모델")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CheckListResponseDto {
    private String content;      //내용(체크리스트)
    private boolean isFinish;    //체크유무 true,false
}
