package com.bazzi.cherryfeed.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AnvsyRequest {
    private String anvsyNm;
    private Date anvsyAt;
    //이미지 추가해야함
    private int status;
}
