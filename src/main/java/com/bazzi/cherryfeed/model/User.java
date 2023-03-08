package com.bazzi.cherryfeed.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity //JPA로 사용하겠다. 
@Data
public class User {
    @Id //PK
    @GeneratedValue(strategy = GenerationType.IDENTITY) //옵션 조사
    private Long id;

    private String username;

    private String password;
}