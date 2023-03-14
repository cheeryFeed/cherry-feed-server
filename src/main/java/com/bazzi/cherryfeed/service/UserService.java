package com.bazzi.cherryfeed.service;

import com.bazzi.cherryfeed.domain.User;
import com.bazzi.cherryfeed.domain.dto.UserJoinRequest;
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

    public String join(UserJoinRequest userJoinRequestDto){
        String email      = userJoinRequestDto.getEmail();    //로그인 이메일
        String password   = userJoinRequestDto.getPassword(); //비밀번호
        String userName   = userJoinRequestDto.getUserName(); //회원이름
        String nickName   = userJoinRequestDto.getNickname(); //닉네임
        String birth   = userJoinRequestDto.getBirth(); //생년월일
        String isTerms = userJoinRequestDto.getIsTerms(); //이용약관
        String phone      = userJoinRequestDto.getPhone();    //휴대폰번호
        String gender     = userJoinRequestDto.getGender();   //성별

        // user name 중복 체크
        userRepository.findByEmail(email)
                .ifPresent(user -> {
                    throw new AppException(ErrorCode.USERNAME_DUPLICATED,email +"는 이미 있습니다.");
                });
        // 저장
        User user = User.builder()
                .email(email)
                .pw(encoder.encode(password)) //인코딩
                .userName(userName)
                .nickname(nickName)
                .birth(birth)
                .isTerms(isTerms)
                .phone(phone)
                .gender(gender)
                .build();

        userRepository.save(user);
        return "SUCCES";
    }

    public String login(String email, String password){
        // userName없음
        User selectedUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new AppException(ErrorCode.USERNAME_NOT_FOUND, email + " 이 없습니다."));

        // password틀림
        log.info("selectedPw:{} pw:{}", selectedUser.getPw() , password);
        if(!encoder.matches(password , selectedUser.getPw())) {
            throw new AppException(ErrorCode.INVALID_PASSWORD,"패스워드를 잘못 입력 헀습니다.");
        }

        //맞았을경우
        String token = JwtTokenUtil.createToken(selectedUser.getEmail() , key, expireTimeMs);
        
        // 앞에서 Exception안나면 토큰 발행
        return token;
    }
}
