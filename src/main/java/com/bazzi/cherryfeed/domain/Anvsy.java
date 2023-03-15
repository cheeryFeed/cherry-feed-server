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
public class Anvsy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //DB에서 값을 증가시키는 전략 IDENTITY -MySQL auto increment
    private Long id;             //기념일id
    //private Long couple_id;     //커플아이디
    private String anvsyNm;     //기념일 제목
    private Date anvsyAt;        //기념일자
    private Long imgId;         //이미지 아이디
    private Long status;        //1:반복 , 2:목표
    @ManyToOne
    @JoinColumn(name = "couple_id") //커플아이디
    private Couple coupleId;
}
