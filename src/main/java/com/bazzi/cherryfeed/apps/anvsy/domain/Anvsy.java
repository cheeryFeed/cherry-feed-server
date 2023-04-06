package com.bazzi.cherryfeed.apps.anvsy.domain;

import com.bazzi.cherryfeed.apps.common.BaseEntity;
import com.bazzi.cherryfeed.apps.couple.domain.Couple;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@AllArgsConstructor
@Getter
@NoArgsConstructor
@Builder
public class Anvsy extends BaseEntity {
    private String anvsyNm;     //기념일 제목
    private Date anvsyAt;        //기념일자
    private Long imgId;         //이미지 아이디
    private int status;        //1:반복 , 2:목표
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "couple_id") //커플아이디
    private Couple coupleId;

    public void updateAnvsy(String anvsyNm, Long imgId, int status, Date anvsyAt) {
        this.anvsyNm = anvsyNm;
        this.imgId = imgId;
        this.status = status;
        this.anvsyAt = anvsyAt;
    }
}
