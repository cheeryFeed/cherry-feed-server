package com.bazzi.cherryfeed.apps.post.domain;

import com.bazzi.cherryfeed.apps.calendar.domain.CoupleCalendar;
import com.bazzi.cherryfeed.apps.account.domain.Account;
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
public class Post extends BaseEntity {
    private String postNm;      //게시물제목
    @Lob
    private String postContent;
    private String location;    //장소
    private Long imgId;         //이미지아이디
    private Long postView;      //조회수
    private Long scrap;         //스크랩수

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by_id") //등록자아이디
    private Account account;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cal_id") //일정고유ID
    private CoupleCalendar calId;

    public void updatePost(CoupleCalendar calId, String postNm, String postContent, String location, Long imgId) {
        this.postNm = postNm;
        this.postContent = postContent;
        this.location = location;
        this.imgId = imgId;
        this.calId = calId;
    }
}
