package com.bazzi.cherryfeed.apps.calendar.domain;

import com.bazzi.cherryfeed.apps.couple.domain.Couple;
import com.bazzi.cherryfeed.apps.account.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity //JPA에서 관리할때 JPA가 사용하는 객체를 의미할때
@AllArgsConstructor
@Getter
@NoArgsConstructor //public 또는 protected 의 기본 생성자가 필수이다. 기본 생성자를 꼭 넣어야 한다. //JPA기반 필수임 프록시 기술 쓸때 피룡함 나중에.
@Builder //해당 클래스에 해당하는 엔티티 객체를 만들 때 빌더 패턴을 이용해서 만들 수 있도록 지정해주는 어노테이션이다.
public class CoupleCalendar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;            //일정 테이블 아이디
    //private Long couple_id;     //커플아이디
    //private Long createdById;   //등록아이디
    private Long partiId1;      //참여자아이디1
    private Long partiId2;      //참여자아이디2
    private String title;       //제목
    private Boolean isAllDay; //종일 여부
    private Date startAt;       //시작일자
    private Date endAt;         //종료일자
    private Long imgId;         //이미지아이디
    private String location;    //장소
    private String status;      //진행상황
    private String content;     //내용(다이어리)
    private Date alarmAt;       //알림일자
    private int type;          //유형 - 계획1,일정2
    @ManyToOne
    @JoinColumn(name = "couple_id")     //커플아이디 //2.이름을 name값으로 지정하며 FK로 만들어준다.
    private Couple coupleId;                      //1. 이 객체 테이블의 PK아이디를 가져와
    @ManyToOne
    @JoinColumn(name = "created_by_id") //등록아이디
    private User createdById;

    @OneToMany(mappedBy = "calendarId")
    private List<CheckList> checkLists= new ArrayList<>(); //캘린더 하나에 들어있는 체크리스트들ㅋ

    public void updateCalendar(Long partiId1,Long partiId2,String title,Boolean isAllDay,Date startAt,Date endAt
                               ,Long imgId,String location,String status,String content,Date alarmAt){
        this.partiId1=partiId1;
        this.partiId2=partiId2;
        this.title=title;
        this.isAllDay=isAllDay;
        this.startAt=startAt;
        this.endAt=endAt;
        this.imgId=imgId;
        this.location=location;
        this.status=status;
        this.content=content;
        this.alarmAt=alarmAt;
    }
}
