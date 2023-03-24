package com.bazzi.cherryfeed.controller;


import com.bazzi.cherryfeed.domain.dto.CoupleConnectRequestDto;
import com.bazzi.cherryfeed.service.CoupleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@Api(tags = "커플")
@RestController
@RequestMapping(value = "/api/v1/connection" , produces = "application/json; charset=utf8")
@RequiredArgsConstructor
public class CoupleController { //토큰이 발급된 대상만 이용가능한 컨트롤러이다.
    private final CoupleService coupleService;

    @ApiOperation(value = "커플연결등록")
    @PostMapping("/connecting")
    public ResponseEntity<String> connecting(Authentication authentication, @RequestBody CoupleConnectRequestDto connectCodeDto) {
        String email = authentication.getName();//이메일
        String receiveByUserEmail = coupleService.save(email, connectCodeDto.getConnectCode());

        return ResponseEntity.ok().body(authentication.getName() + 
                "님과"+receiveByUserEmail+"님이 연결되었습니다. 커플테이블 저장완료");
    }
    @ApiOperation(value = "커플 해지")
    @DeleteMapping("/unconnecting")
    public ResponseEntity<String> deleteConnecting(Authentication authentication){
        String email = authentication.getName();//이메일
        coupleService.deleteCouple(email);
        return ResponseEntity.ok().body("상태9변경완료.");
    }
}
