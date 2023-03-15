package com.bazzi.cherryfeed.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class WithdrawalDetail {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;            //서비스탈퇴이력아이디
    //private Long createdById;   //탈퇴 유저아이디
    private Long Status;        //탈퇴사유 1,2,3,4,5
    private String content;     //내용
    private Date createdAt;     //탈퇴일자

    @OneToOne
    @JoinColumn(name = "created_by_id") //신고한 유저아이디
    private User createdById;


}
