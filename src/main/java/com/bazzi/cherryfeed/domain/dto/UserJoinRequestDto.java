package com.bazzi.cherryfeed.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@ApiModel(value = "회원가입 모델")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserJoinRequestDto {
    //회원가입에 필요한 필드들. 민규님 나중에 추가하겠습니다.
    @ApiModelProperty(value = "아이디",required = true)
    private String userName;
    @ApiModelProperty(value = "비밀번호",required = true)
    private String password;


    @ApiModelProperty
    private String email;          //이메일 varchar(50)
    private String nickname;       //닉네임 varchar(16)
    private String birth;       //생년월일 date

    private String social_provider;//social_provider varchar(16)
    private String socialId;      //social_id varchar(16)
    private Boolean isTerms;     //이용약관(개인정보) Boolean
    private String phone;          //휴대폰번호 varchar(20)
    private String gender;         //성별 varchar(1)
    private String connect_code;     //고유연결코드 varchar(16)
    private Long imgId;
    //private String profile_img;    //프로필사진 varchar(16)
    //private String introduce;      //소개글 varchar(16)
    //private String link;           //링크 varchar(16)
    //private String acct_yn;        //계정 비공개 여부 varchar(1)
    //private String stts;           //상태 varchar(16)
    //private String tmnt_dt;        //해지일 varchar(16)
    //private String create_dt;      //생성일 varchar(16)
    //private String modify_dt;      //수정일 varchar(16)

}
