package com.bazzi.cherryfeed.controller;


import com.bazzi.cherryfeed.domain.dto.CoupleConnectRequest;
import com.bazzi.cherryfeed.service.CoupleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/connection" , produces = "application/json; charset=utf8")
@RequiredArgsConstructor
public class CoupleController { //토큰이 발급된 대상만 이용가능한 컨트롤러이다.
    private final CoupleService coupleService;
    @PostMapping("/connecting")
    public ResponseEntity<String> connecting(Authentication authentication, @RequestBody CoupleConnectRequest connectCodeDto) {
        String email = authentication.getName();//이메일
        String receiveByUserEmail = coupleService.save(email, connectCodeDto.getConnectCode());

        return ResponseEntity.ok().body(authentication.getName() + 
                "님과"+receiveByUserEmail+"님이 연결되었습니다. 커플테이블 저장완료");
    }
}
