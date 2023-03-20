package com.bazzi.cherryfeed.service;

import com.bazzi.cherryfeed.domain.User;
import com.bazzi.cherryfeed.exception.AppException;
import com.bazzi.cherryfeed.exception.ErrorCode;
import com.bazzi.cherryfeed.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Slf4j
@RequiredArgsConstructor
@Service
public class JoinService {
    static int n = 8; // n자리 쿠폰
    static char[] chs = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
            'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
    private final UserRepository userRepository;
    //닉네임 중복체크 서비스 로직
    public String duplicationCheckNicknameService(String nickname){
        // user name 중복 체크
        userRepository.findByNickname(nickname)
                .ifPresent(user -> {
                    log.error(nickname+"는 이미 있습니다.");
                    throw new AppException(ErrorCode.USERNAME_DUPLICATED,nickname +"는 이미 있습니다.");
                });
        return "SUCCES";
    }
    //연결코드 생성 서비스 로직
    public String createCode() {
        Random rd = new Random();
        StringBuilder sb = new StringBuilder();
        String randomCode;
        for (int i = 0; i < n; i++) {
            char ch = chs[rd.nextInt(chs.length)];
            sb.append(ch);
        }
        return randomCode = sb.toString();


    }
    //생성한 연결코드 얻는 로직
    public String getCreateConnectCode(){
        String randomCode = createCode();
        Optional<User> findedCode = userRepository.findByConnectCode(randomCode);
        if(findedCode.isPresent()){
            return "중복코드";
        }else{
            return randomCode;
        }
    }
}
