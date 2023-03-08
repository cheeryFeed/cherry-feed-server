package com.bazzi.cherryfeed.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@ApiModel(value = "회원가입 모델")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserJoinRequest {
    //회원가입에 필요한 필드들. 민규님 나중에 추가하겠습니다.
    @ApiModelProperty(value = "아이디",required = true)
    private String userName;
    @ApiModelProperty(value = "비밀번호",required = true)
    private String password;
}
