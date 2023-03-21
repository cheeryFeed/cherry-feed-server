package com.bazzi.cherryfeed.domain.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@ApiModel(value = "커플 연결 모델")
@AllArgsConstructor
@Getter
@NoArgsConstructor
public class CoupleConnectRequest {
    private String connectCode;

}
