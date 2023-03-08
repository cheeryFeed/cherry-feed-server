package com.bazzi.cherryfeed.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserJoinRequest {
    //회원가입에 필요한 필드들. 민규님 나중에 추가하겠습니다.
    private String userName;
    private String password;
}
