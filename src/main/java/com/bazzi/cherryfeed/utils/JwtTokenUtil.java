package com.bazzi.cherryfeed.utils;

import io.jsonwebtoken.*;

import java.util.Date;

public class JwtTokenUtil {

    public static String getUserName(String token, String key) { //토큰에서 값 꺼내기
        return Jwts.parser().setSigningKey(key).parseClaimsJws(token)
                .getBody().get("userName", String.class);
    }

    public static String getEmail(String token, String key) { //토큰에서 값 꺼내기
        return Jwts.parser().setSigningKey(key).parseClaimsJws(token)
                .getBody().get("email", String.class);
    }

    public static Long getId(String token, String key) { //토큰에서 값 꺼내기
        return Jwts.parser().setSigningKey(key).parseClaimsJws(token)
                .getBody().get("id", Long.class);
    }


    public static boolean isExpired(String token, String key) {
        return Jwts.parser().setSigningKey(key).parseClaimsJws(token)
                .getBody().getExpiration().before(new Date());
    }

    public static String createToken(Long id, String key, long expireTimeMs) { //expireTime을 받을것임.
        Claims claims = Jwts.claims(); //map이랑 비슷
        claims.put("id", id); //토큰을 열면 email이 들어가있을것임

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis())) //만든날짜
                .setExpiration(new Date(System.currentTimeMillis() + expireTimeMs)) //끝나는날짜
                .signWith(SignatureAlgorithm.HS256, key) //SignatureAlgorithm.ES256알고리즘으로 key를 이용해 암호화할것임.
                .compact()
                ;
    }

    public static String createRefreshToken(String key, long expireTimeMs) {
        return Jwts.builder()
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expireTimeMs))
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();
    }

    // jwt 토큰의 유효성 + 로그아웃 확인 + 만료일자만 초과한 토큰이면 return true;
    public static boolean validateTokenExceptExpiration(String jwtToken,String secretKey) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
            return claims.getBody().getExpiration().before(new Date());
        } catch(ExpiredJwtException e) {
            return true;
        } catch (Exception e) {
            return false;
        }
    }



}
