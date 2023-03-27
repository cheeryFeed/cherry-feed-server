package com.bazzi.cherryfeed.service;

import com.bazzi.cherryfeed.domain.User;
import com.bazzi.cherryfeed.domain.WithdrawalDetail;
import com.bazzi.cherryfeed.domain.dto.UserInfoResponseDto;
import com.bazzi.cherryfeed.domain.dto.UserJoinRequestDto;
import com.bazzi.cherryfeed.domain.dto.UserRequestUpdateDto;
import com.bazzi.cherryfeed.domain.dto.WithdrawalRequestDto;
import com.bazzi.cherryfeed.exception.AppException;
import com.bazzi.cherryfeed.exception.ErrorCode;
import com.bazzi.cherryfeed.repository.UserRepository;
import com.bazzi.cherryfeed.repository.WithdrawalDetailRepository;
import com.bazzi.cherryfeed.utils.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final WithdrawalDetailRepository withdrawalDetailRepository;

    private final BCryptPasswordEncoder encoder;
    @Value("${jwt.token.secret}") //application.yml에
    private String key;
    private Long expireTimeMs = 1000 * 60 * 60l; //1시간

    public String join(UserJoinRequestDto userJoinRequestDto){
        String email      = userJoinRequestDto.getEmail();    //로그인 이메일
        String password   = userJoinRequestDto.getPassword(); //비밀번호
        String userName   = userJoinRequestDto.getUserName(); //회원이름
        String nickName   = userJoinRequestDto.getNickname(); //닉네임
        String birth = userJoinRequestDto.getBirth();//생년월일
        Boolean isTerms   = userJoinRequestDto.getIsTerms(); //이용약관
        String phone      = userJoinRequestDto.getPhone();    //휴대폰번호
        String gender     = userJoinRequestDto.getGender();   //성별
        String connectCode= userJoinRequestDto.getConnect_code();
        Long imgId        = userJoinRequestDto.getImgId();

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
                .connectCode(connectCode)
                .imgId(imgId)
                .status("1")
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

    @Transactional
    public String withdrawal(String email, WithdrawalRequestDto withdrawalRequestDto){
        User user = userRepository.findUserByEmail(email);
        WithdrawalDetail withdrawalDetail = WithdrawalDetail.builder()
                .status(withdrawalRequestDto.getStatus())
                .content(withdrawalRequestDto.getContent())
                .createdById(user)
                .build();
        withdrawalDetailRepository.save(withdrawalDetail);
        user.updateUserWithdrawal(withdrawalDetail,"9");
        return "SUCCES";
    }
    public UserInfoResponseDto readUser(String email){
        User user = userRepository.findByEmail(email).get();
        UserInfoResponseDto userInfoResponseDto=UserInfoResponseDto.builder()
                .id(user.getId())
                .isOpen(user.getIsOpen())
                .introduce(user.getIntroduce())
                .link(user.getLink())
                .coupleId(user.getCouple().getId())
                .birth(user.getBirth())
                .connectCode(user.getConnectCode())
                .email(user.getEmail())
                .gender(user.getGender())
                .imgId(user.getImgId())
                .isTerms(user.getIsTerms())
                .link(user.getLink())
                .phone(user.getPhone())
                .build();
        return userInfoResponseDto;
    }
    @Transactional
    public String userUpdate(String email, UserRequestUpdateDto userRequestUpdateDto){
        User user = userRepository.findByEmail(email).get();

        String introduce = userRequestUpdateDto.getIntroduce();
        String link = userRequestUpdateDto.getLink();
        Boolean isOpen = userRequestUpdateDto.getIsOpen();
        Long imgId = userRequestUpdateDto.getImgId();
        Boolean isTerms = userRequestUpdateDto.getIsTerms();
        String phone = userRequestUpdateDto.getPhone();
        String nickname = userRequestUpdateDto.getNickname();

        user.updateUserInfo(introduce,link,isOpen,imgId,isTerms,phone,nickname);
        return "SUCCES";
    }
}
