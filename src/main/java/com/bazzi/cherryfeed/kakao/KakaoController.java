package com.bazzi.cherryfeed.kakao;

import com.bazzi.cherryfeed.apps.account.dto.AccountDto;
import com.bazzi.cherryfeed.apps.account.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class KakaoController {
    private final KakaoService kakaoService;
    private final KakaoService2 kakaoService2;
    private final UserService userService;

    @RequestMapping("/kakao")
    //카카오에서 Redirect 요청으로 들어온 인가코드(authorization code)를 받아서 kakaoService의 execKakaoLogin() 메서드에 authorization code를 넘겨준다
    public ResponseEntity<AccountDto.ResponseToken> kakaoSignIn(@RequestParam("code") String code) {
        Map<String,Object> userInfo = kakaoService2.execKakaoLogin(code);
        return ResponseEntity.ok().body(userService.kakaoLogin(userInfo));
    }

    @RequestMapping("/kakaoToken")
    //카카오에서 Redirect 요청으로 들어온 인가코드(authorization code)를 받아서 kakaoService의 execKakaoLogin() 메서드에 authorization code를 넘겨준다
    public ResponseEntity<AccountDto.ResponseToken> kakaoSignIn2(@RequestParam("token") String token) {
        Map<String,Object> userInfo = kakaoService2.getUserInfo2(token);
        return ResponseEntity.ok().body(userService.kakaoLogin(userInfo));
    }
}
