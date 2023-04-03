package com.bazzi.cherryfeed.apps.account.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class AccountDto {
    @ApiModel(value = "회원가입 모델")
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class Create {
        @ApiModelProperty(value = "아이디", required = true)
        private String userName;
        @ApiModelProperty(value = "비밀번호", required = true)
        private String password;
        @ApiModelProperty(value = "이메일", required = true)
        private String email;          //이메일 varchar(50)
        @ApiModelProperty(value = "닉네임", required = true)
        private String nickname;       //닉네임 varchar(16)
        @ApiModelProperty(value = "생일 - 8자 문자타입", required = true)
        private String birth;       //생년월일 date

        private String social_provider;//social_provider varchar(16)
        private String socialId;      //social_id varchar(16)
        private Boolean isTerms;     //이용약관(개인정보) Boolean
        @ApiModelProperty("휴대폰번호")
        private String phone;          //휴대폰번호 varchar(20)
        @ApiModelProperty("성별 - M,F")
        private String gender;         //성별 varchar(1)
        @ApiModelProperty(value = "연결코드", required = true)
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
    @ApiModel(value = "유저조회응답모델")
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Response {
        @ApiModelProperty(value = "고객아이디")
        private Long id;
        @ApiModelProperty(value = "이메일")
        private String email;
        @ApiModelProperty(value = "닉네임")
        private String nickname;
        @ApiModelProperty(value = "생일 - 문자형8자")
        private String birth;
        @ApiModelProperty(value = "이용약관")
        private Boolean isTerms;
        @ApiModelProperty(value = "연결코드")
        private String connectCode;
        @ApiModelProperty(value = "이미지아이디")
        private Long imgId;
        @ApiModelProperty(value = "소개글")
        private String introduce;
        @ApiModelProperty(value = "링크")
        private String link;
        @ApiModelProperty(value = "계정 비공개 여부")
        private Boolean isOpen;
        @ApiModelProperty(value = "휴대폰번호")
        private String phone;
        @ApiModelProperty(value = "성별 - M,F")
        private String gender;
        @ApiModelProperty(value = "커플아이디")
        private Long coupleId;
    }
    @ApiModel(value = "유저업데이트요청모델")
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Update {
        @ApiModelProperty(value = "닉네임")
        private String nickname;
        @ApiModelProperty(value = "이용약관 - M,F")
        private Boolean isTerms;
        @ApiModelProperty(value = "이미지아이디")
        private Long imgId;
        @ApiModelProperty(value = "소개글")
        private String introduce;
        @ApiModelProperty(value = "링크")
        private String link;
        @ApiModelProperty(value = "계정 공개 비공개 여부 - 공개 true")
        private Boolean isOpen;
        @ApiModelProperty(value = "휴대폰번호")
        private String phone;
    }
    @ApiModel(value = "회원탈퇴모델")
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class Delete {
        @ApiModelProperty(value = "탈퇴사유")
        private Long status;   
        @ApiModelProperty(value = "내용")
        private String content; 
    }

    @ApiModel(value = "로그인모델")
    @AllArgsConstructor
    @Getter
    @NoArgsConstructor
    public static class Login {
        @ApiModelProperty(value = "이메일")
        private String email;
        @ApiModelProperty(value = "비밀번호")
        private String pw;
    }
}