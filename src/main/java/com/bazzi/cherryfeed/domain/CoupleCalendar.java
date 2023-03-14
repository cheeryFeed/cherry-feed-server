package com.bazzi.cherryfeed.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity //JPA에서 관리할때 JPA가 사용하는 객체를 의미할때
@AllArgsConstructor
@Getter
@NoArgsConstructor //public 또는 protected 의 기본 생성자가 필수이다. 기본 생성자를 꼭 넣어야 한다. //JPA기반 필수임 프록시 기술 쓸때 피룡함 나중에.
@Builder //해당 클래스에 해당하는 엔티티 객체를 만들 때 빌더 패턴을 이용해서 만들 수 있도록 지정해주는 어노테이션이다.
public class CoupleCalendar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;            //일정 테이블 아이디
    private Long couple_id;     //커플아이디
    private Long createdById;   //등록아이디
    private Long PartiId1;      //참여자아이디1
    private Long PartiId2;      //참여자아이디2
    private String title;       //제목
    private boolean is_all_day; //종일 여부
    private Date startAt;       //시작일자
    private Date EndAt;         //종료일자
    private Long imgId;         //이미지아이디
    private String location;    //장소
    private String status;      //진행상황
    private String content;     //내용(다이어리)
    private Date alarmAt;       //알림일자
    private Long type;          //유형 - 계획1,일정2


}
