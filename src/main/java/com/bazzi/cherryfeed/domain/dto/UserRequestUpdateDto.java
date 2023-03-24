package com.bazzi.cherryfeed.domain.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

@ApiModel(value = "유저업데이트요청모델")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequestUpdateDto {
    private String nickname;       //닉네임 varchar(16)
    private Boolean isTerms;        //이용약관(개인정보) boolean
    private Long imgId;            //프로필사진 varchar(16)
    private String introduce;      //소개글 varchar(16)
    private String link;           //링크 varchar(16)
    private Boolean isOpen;        //계정 비공개 여부 boolean
    private String phone;          //휴대폰번호 varchar(20)
}
