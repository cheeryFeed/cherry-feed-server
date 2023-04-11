package com.bazzi.cherryfeed.kakao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

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
    @RequestMapping("/kakao")
    //카카오에서 Redirect 요청으로 들어온 인가코드(authorization code)를 받아서 kakaoService의 execKakaoLogin() 메서드에 authorization code를 넘겨준다
    public String kakaoSignIn(@RequestParam("code") String code) {
//        Map<String,Object> result = kakaoService.execKakaoLogin(code);
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        String clientId = "8ed1b4474867bf6a28a760fff84ef34c";
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("grant_type", "authorization_code");
        map.add("client_id", clientId);
//        map.add("client_secret", clientSecret);
        map.add("redirect_uri", "http://localhost:8090/kakao");
        map.add("code", code);

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);

        String accessTokenResponse = restTemplate.postForObject("https://kauth.kakao.com/oauth/token", entity, String.class);
        System.out.println(accessTokenResponse.toString());
        return null;
        //return "redirect:webauthcallback://success?customToken="+result.get("customToken").toString();
    }
}
