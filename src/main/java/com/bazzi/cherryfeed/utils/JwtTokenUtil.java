package com.bazzi.cherryfeed.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtTokenUtil {

    public static String getUserName(String token, String key){ //토큰에서 값 꺼내기
        return Jwts.parser().setSigningKey(key).parseClaimsJws(token)
                .getBody().get("userName", String.class);
    }

    public static boolean isExpired(String token, String key){
        return Jwts.parser().setSigningKey(key).parseClaimsJws(token)
                .getBody().getExpiration().before(new Date());
    }
    public static String createToken(String userName , String key , long expireTimeMs) { //expireTime을 받을것임.
        Claims claims = Jwts.claims(); //map이랑 비슷
        claims.put("userName", userName); //토큰을 열면 userName이 들어가있을것임

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis())) //만든날짜
                .setExpiration(new Date(System.currentTimeMillis() + expireTimeMs)) //끝나는날짜
                .signWith(SignatureAlgorithm.HS256, key) //SignatureAlgorithm.ES256알고리즘으로 key를 이용해 암호화할것임.
                .compact()
                ;
    }
}
