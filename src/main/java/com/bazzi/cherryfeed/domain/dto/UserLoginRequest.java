package com.bazzi.cherryfeed.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class UserLoginRequest {
    private String email;
    private String pw;
}
