package com.bazzi.cherryfeed.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/users" , produces = "application/json; charset=utf8")
public class TokenAfterTestController { //토큰이 발급된 대상만 이용가능한 컨트롤러이다. 테스트용으로 만들었습니다.
    @PostMapping("test")
    public ResponseEntity<String> testUse(Authentication authentication) {
        return ResponseEntity.ok().body(authentication.getName() + "님의 토큰으로 컨트롤러 접근이 완료되었습니다. ");
    }
}
