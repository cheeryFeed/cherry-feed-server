package com.bazzi.cherryfeed.apps.account.domain;

import com.bazzi.cherryfeed.apps.common.BaseEntity;
import lombok.*;
import javax.persistence.*;

@Entity //JPA에서 관리할때 JPA가 사용하는 객체를 의미할때
@AllArgsConstructor
@Getter
@NoArgsConstructor //public 또는 protected 의 기본 생성자가 필수이다. 기본 생성자를 꼭 넣어야 한다. //JPA기반 필수임 프록시 기술 쓸때 피룡함 나중에.
@Builder //해당 클래스에 해당하는 엔티티 객체를 만들 때 빌더 패턴을 이용해서 만들 수 있도록 지정해주는 어노테이션이다.
public class WithdrawalDetail extends BaseEntity {

    private Long status;        //탈퇴사유 1,2,3,4,5
    private String content;     //내용
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by_id") //탈퇴한아이디
    private Account account;
}
