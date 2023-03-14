package com.bazzi.cherryfeed.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class WithdrawalRequest {
    String createdById; //탈퇴아이디
    Long status;    //탈퇴사유
    String content;  //내용
    Date created_at; //탈퇴일자
    
}
