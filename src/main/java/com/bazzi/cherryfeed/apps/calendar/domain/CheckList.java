package com.bazzi.cherryfeed.apps.calendar.domain;

import com.bazzi.cherryfeed.apps.common.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@Getter
@NoArgsConstructor
@Builder
public class CheckList extends BaseEntity {
    private String content;      //내용(체크리스트)
    private Boolean isFinish;    //체크유무 true,false
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "calendar_id")  //일정고유ID
    private CoupleCalendar calendar;

    public void updateCheckList(String content, Boolean isFinish) {
        this.content = content;
        this.isFinish = isFinish;
    }
}
