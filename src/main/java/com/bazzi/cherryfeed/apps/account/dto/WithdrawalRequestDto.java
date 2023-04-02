package com.bazzi.cherryfeed.apps.account.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@ApiModel(value = "회원탈퇴모델")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class WithdrawalRequestDto {
    Long status;    //탈퇴사유
    String content;  //내용
}
