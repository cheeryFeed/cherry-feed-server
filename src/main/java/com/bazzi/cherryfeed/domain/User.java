package com.bazzi.cherryfeed.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@AllArgsConstructor
@Getter
@NoArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;       //이름
    private String password;       //비밀번호 varchar(30)

    private String email;          //이메일 varchar(50)
    private String nickname;       //닉네임 varchar(16)
    private String birth_dt;       //생년월일 date
    private String create_dt;      //생성일 varchar(16)
    private String modify_dt;      //수정일 varchar(16)
    private String social_provider;//social_provider varchar(16)
    private String social_id;      //social_id varchar(16)
    private String user_terms;     //이용약관(개인정보) varchar(16)
    private String connect_cd;     //고유연결코드 varchar(16)
    private String profile_img;    //프로필사진 varchar(16)
    private String introduce;      //소개글 varchar(16)
    private String link;           //링크 varchar(16)
    private String acct_yn;        //계정 비공개 여부 varchar(16)
    private String stts;           //상태 varchar(16)
    private String tmnt_dt;        //해지일 varchar(16)
    private String ph_no;          //휴대폰번호 varchar(20)
    private String gender;         //성별 varchar(1)

}
