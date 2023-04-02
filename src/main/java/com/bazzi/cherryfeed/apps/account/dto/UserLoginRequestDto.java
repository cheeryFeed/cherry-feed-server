package com.bazzi.cherryfeed.apps.account.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@ApiModel(value = "로그인모델")
@AllArgsConstructor
@Getter
@NoArgsConstructor
public class UserLoginRequestDto {
    private String email;
    private String pw;
}
