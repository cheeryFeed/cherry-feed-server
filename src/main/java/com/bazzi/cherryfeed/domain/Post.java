package com.bazzi.cherryfeed.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@AllArgsConstructor
@Getter
@NoArgsConstructor //public 또는 protected 의 기본 생성자가 필수이다. 기본 생성자를 꼭 넣어야 한다. //JPA기반 필수임 프록시 기술 쓸때 피룡함 나중에.
@Builder //해당 클래스에 해당하는 엔티티 객체를 만들 때 빌더 패턴을 이용해서 만들 수 있도록 지정해주는 어노테이션이다.
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //DB에서 값을 증가시키는 전략 IDENTITY -MySQL auto increment
    private Long id;             //게시물번호 아이디
    //private Long createdById;   //등록자아이디
    //private Long calId;         //일정고유ID
    private String postNm;      //게시물제목
    private String postContent;
    private String location;    //장소
    @CreationTimestamp
    private Date postAt ;       //작성일자
    private Long imgId;         //이미지아이디
    private Long postView;      //조회수
    private Long scrap;         //스크랩수

    @ManyToOne
    @JoinColumn(name = "created_by_id") //등록자아이디
    User createdById;

    @ManyToOne
    @JoinColumn(name = "cal_id") //일정고유ID
    private CoupleCalendar calId;

    public void updatePost(CoupleCalendar calId,String postNm,String postContent,String location,Long imgId){
        this.postNm=postNm;
        this.postContent=postContent;
        this.location=location;
        this.imgId=imgId;
        this.calId=calId;
    }





}
