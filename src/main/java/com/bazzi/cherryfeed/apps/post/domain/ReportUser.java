package com.bazzi.cherryfeed.apps.post.domain;

import com.bazzi.cherryfeed.apps.calendar.domain.CoupleCalendar;
import com.bazzi.cherryfeed.apps.common.BaseEntity;
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
public class ReportUser extends BaseEntity {
    private int dctStts;               //신고유형
    private String dctContent;         //내용
    private Date dctDt;                //신고일자
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_no")      //신고게시물번호
    private Post post;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by_id") //신고한 유저아이디
    private CoupleCalendar coupleCalendar;
}
