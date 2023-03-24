package com.bazzi.cherryfeed.domain.dto;

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
    Long id;
    String email;
    String nickname;
    Date birth;
    Boolean isTerms;
    String connectCode;
    Long imgId;
    String introduce;
    String link;
    Boolean isOpen;
    String phone;
    String gender;
    Long coupleId;
}
