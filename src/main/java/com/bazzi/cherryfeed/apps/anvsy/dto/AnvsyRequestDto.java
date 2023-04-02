package com.bazzi.cherryfeed.apps.anvsy.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@ApiModel(value = "기념일등록요청모델")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AnvsyRequestDto {
    private String anvsyNm;
    private Date anvsyAt;
    private Long imgId;
    private int status;
}
