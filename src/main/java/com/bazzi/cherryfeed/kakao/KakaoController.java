package com.bazzi.cherryfeed.kakao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.Map;

@Controller
public class KakaoController {
    @Autowired
    KakaoService kakaoService;
    @RequestMapping("test")
    @ResponseBody
    public String testConnect() {
        return "연결성공";
    }
    @RequestMapping("kakao/sign_in")
    //카카오에서 Redirect 요청으로 들어온 인가코드(authorization code)를 받아서 kakaoService의 execKakaoLogin() 메서드에 authorization code를 넘겨준다
    public String kakaoSignIn(@RequestParam("code") String code) {
        Map<String,Object> result = kakaoService.execKakaoLogin(code);
        return "redirect:webauthcallback://success?customToken="+result.get("customToken").toString();
    }
}
