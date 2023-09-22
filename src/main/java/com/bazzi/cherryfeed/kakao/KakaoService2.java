package com.bazzi.cherryfeed.kakao;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class KakaoService2 {
    public Map<String,Object> execKakaoLogin(String authorize_code) { //getAceesToken(), getUserInfo() 호출
        // 엑세스 토큰 받기
        String accessToken = getAccessToken(authorize_code);
        // 사용자 정보 읽어오기
        Map<String,Object> userInfo = getUserInfo2(accessToken);

        System.out.println(userInfo.toString());
        return userInfo;
    }
    public String getAccessToken (String code) { //인가 코드를 받아서 카카오에 AccessToken을 요청하고, 전달받은 AccessToken을 return 한다.
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        String clientId = "8ed1b4474867bf6a28a760fff84ef34c";
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("grant_type", "authorization_code");
        map.add("client_id", clientId);
//        map.add("client_secret", clientSecret);
        map.add("redirect_uri", "http://cherryfeed.shop/kakao");
        map.add("code", code);

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);

        String accessTokenResponse = restTemplate.postForObject("https://kauth.kakao.com/oauth/token", entity, String.class);
        System.out.println(accessTokenResponse.toString());
        JSONObject jsonObject = JSONObject.fromObject(accessTokenResponse.toString());
        String accessToken = jsonObject.getString("access_token");
        return accessToken;
        //return "redirect:webauthcallback://success?customToken="+result.get("customToken").toString();
    }
    public Map<String, Object> getUserInfo (String access_Token) { //AccessToken을 받아서 User정보를 획득하고 return 한다.

        //    요청하는 클라이언트마다 가진 정보가 다를 수 있기에 HashMap타입으로 선언
        Map<String, Object> userInfo = new HashMap<>();
        String reqURL = "https://kapi.kakao.com/v2/user/me";
        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            //    요청에 필요한 Header에 포함될 내용
            conn.setRequestProperty("Authorization", "Bearer " + access_Token);
            int responseCode = conn.getResponseCode();
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";
            while ((line = br.readLine()) != null) {
                result += line;
            }
            JsonElement element = JsonParser.parseString(result);
            JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
            JsonObject kakao_account = element.getAsJsonObject().get("kakao_account").getAsJsonObject();
            String nickname = properties.getAsJsonObject().get("nickname").getAsString();
            //String profile_image = properties.getAsJsonObject().get("profile_image").getAsString();
            String email = kakao_account.getAsJsonObject().get("email").getAsString();
            int id = element.getAsJsonObject().get("id").getAsInt();
            //long id = element.getAsJsonObject().get("id").getAsLong(); // 사용자 고유 아이디 추가
            userInfo.put("nickname", nickname);
            userInfo.put("email", email);
            userInfo.put("id", id);
            log.info("id ="+id);
            log.info("nickname ="+nickname);
            log.info("email ="+email);
            //userInfo.put("profile_image", profile_image);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userInfo;
    }

    public Map<String, Object> getUserInfo2(String access_Token) {
        Map<String, Object> userInfo = new HashMap<>();
        String reqURL = "https://kapi.kakao.com/v2/user/me";
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(access_Token);


        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        //JSONObject properties = new JSONObject();
        JSONArray properties = new JSONArray();
        properties.add("kakao_account.email");
        properties.add("kakao_account.gender");
        properties.add("kakao_account.birthday");
        body.add("property_keys",properties.toString());

        HttpEntity<MultiValueMap<String,String>> entity = new HttpEntity(body,headers);




        ResponseEntity<String> response = new RestTemplate().exchange(reqURL, HttpMethod.POST, entity, String.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            JsonElement element = JsonParser.parseString(response.getBody());
            //JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
            JsonObject kakao_account = element.getAsJsonObject().get("kakao_account").getAsJsonObject();
            //String nickname = properties.getAsJsonObject().get("nickname").getAsString();
            String email = kakao_account.getAsJsonObject().get("email").getAsString();
            int id = element.getAsJsonObject().get("id").getAsInt();
            //String email = kakao_account.getAsJsonObject().get("email").getAsString();
            //String id = "KAKAO_"+element.getAsJsonObject().get("id").toString();
            //userInfo.put("nickname", nickname);
            userInfo.put("email", email);
            userInfo.put("id", id);
            log.info("id =" + id);
            //log.info("nickname =" + nickname);
            log.info("email =" + email);
        } else {
            throw new RuntimeException("Failed to get user info from Kakao API");
        }
        return userInfo;
    }
}