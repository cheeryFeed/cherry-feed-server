package com.bazzi.cherryfeed.service;

import com.bazzi.cherryfeed.domain.User;
import com.bazzi.cherryfeed.exception.AppException;
import com.bazzi.cherryfeed.exception.ErrorCode;
import com.bazzi.cherryfeed.repository.UserRepository;
import com.bazzi.cherryfeed.utils.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;
    @Value("${jwt.token.secret}") //application.yml에
    private String key;
    private Long expireTimeMs = 1000 * 60 * 60l; //1시간

    public String join(String userName, String password){
        // user name 중복 체크
        userRepository.findByUserName(userName)
                .ifPresent(user -> {
                    throw new AppException(ErrorCode.USERNAME_DUPLICATED,userName +"는 이미 있습니다.");
                });
        // 저장
        User user = User.builder()
                .userName(userName)
                .password(encoder.encode(password)) //인코딩
                .build();
        userRepository.save(user);
        return "SUCCES";
    }

    public String login(String userName, String password){
        // userName없음
        User selectedUser = userRepository.findByUserName(userName)
                .orElseThrow(() -> new AppException(ErrorCode.USERNAME_NOT_FOUND, userName + " 이 없습니다."));

        // password틀림
        log.info("selectedPw:{} pw:{}", selectedUser.getPassword() , password);
        if(!encoder.matches(password , selectedUser.getPassword())) {
            throw new AppException(ErrorCode.INVALID_PASSWORD,"패스워드를 잘못 입력 헀습니다.");
        }

        //맞았을경우
        String token = JwtTokenUtil.createToken(selectedUser.getUserName() , key, expireTimeMs);
        
        // 앞에서 Exception안나면 토큰 발행
        return token;
    }
}
