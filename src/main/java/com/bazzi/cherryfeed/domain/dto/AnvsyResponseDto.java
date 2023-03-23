package com.bazzi.cherryfeed.domain.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
@ApiModel(value = "기념일등록응답모델")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnvsyResponseDto {
    private Long id;
    private String anvsyNm;
    private Date anvsyAt;
    private Long imgId;
    private int status;
}
