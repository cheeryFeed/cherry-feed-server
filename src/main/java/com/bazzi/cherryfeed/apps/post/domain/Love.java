package com.bazzi.cherryfeed.apps.post.domain;

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
public class Love extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by_id") //좋아요 누른 아이디
    private Account createdById;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by_no") //게시물번호
    private Post createdByNo;

}
