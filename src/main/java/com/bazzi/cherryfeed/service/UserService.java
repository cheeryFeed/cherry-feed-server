package com.bazzi.cherryfeed.service;

import com.bazzi.cherryfeed.domain.User;
import com.bazzi.cherryfeed.exception.AppException;
import com.bazzi.cherryfeed.exception.ErrorCode;
import com.bazzi.cherryfeed.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;
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
}
