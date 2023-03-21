package com.bazzi.cherryfeed.domain.dto;

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
    String createdById; //탈퇴아이디
    Long status;    //탈퇴사유
    String content;  //내용
    Date created_at; //탈퇴일자
    
}
