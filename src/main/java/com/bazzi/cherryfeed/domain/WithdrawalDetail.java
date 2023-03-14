package com.bazzi.cherryfeed.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
public class WithdrawalDetail {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;            //탈퇴아이디
    private Long createdById;   //탈퇴 유저아이디
    private Long Status;        //탈퇴사유 1,2,3,4,5
    private String content;     //내용
    private Date createdAt;     //탈퇴일자

}
