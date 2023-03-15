package com.bazzi.cherryfeed.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@AllArgsConstructor
@Getter
@NoArgsConstructor //public 또는 protected 의 기본 생성자가 필수이다. 기본 생성자를 꼭 넣어야 한다. //JPA기반 필수임 프록시 기술 쓸때 피룡함 나중에.
@Builder //해당 클래스에 해당하는 엔티티 객체를 만들 때 빌더 패턴을 이용해서 만들 수 있도록 지정해주는 어노테이션이다.
public class Couple {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //DB에서 값을 증가시키는 전략 IDENTITY -MySQL auto increment
    private Long id;             //커플아이디
    private Date createdAt;      //생성일
    @ManyToOne //지금 클레스 기준 다대일
    @JoinColumn(name = "request_by_id") //2.이름을 name값으로 지정하며 FK로 만들어준다.
    private User requestById; //1. 이 객체 테이블의 PK아이디를 가져와
    @ManyToOne
    @JoinColumn(name = "receive_by_id")
    private User receiveById;
    //private String requestById;  //생성요청자
    //private String receiveById;  //생성도착자

}
