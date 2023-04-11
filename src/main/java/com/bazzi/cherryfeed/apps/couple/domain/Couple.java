package com.bazzi.cherryfeed.apps.couple.domain;

import com.bazzi.cherryfeed.apps.account.domain.Account;
import com.bazzi.cherryfeed.apps.common.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@Getter
@NoArgsConstructor
@Builder
public class Couple extends BaseEntity {
    private String coupleName;   //커플이름
    private int stts;            //커플 상태 1등록 9해지

    @OneToMany(mappedBy = "couple")  //FK가 없는쪽에 mappedBy를 써주는것이 좋다.
    private List<Account> users = new ArrayList<>();

    public void updateCoupleStts(int stts) {
        this.stts = stts;
    }

}
