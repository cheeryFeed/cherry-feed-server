package com.bazzi.cherryfeed.apps.account.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@ApiModel(value = "유저조회응답모델")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserInfoResponseDto {
    private Long id;
    private String email;
    private String nickname;
    private String birth;
    private Boolean isTerms;
    private String connectCode;
    private Long imgId;
    private String introduce;
    private String link;
    private Boolean isOpen;
    private String phone;
    private String gender;
    private Long coupleId;
}
