package com.bazzi.cherryfeed.service;

import com.bazzi.cherryfeed.domain.Couple;
import com.bazzi.cherryfeed.domain.User;
import com.bazzi.cherryfeed.exception.AppException;
import com.bazzi.cherryfeed.exception.ErrorCode;
import com.bazzi.cherryfeed.repository.CoupleRepository;
import com.bazzi.cherryfeed.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CoupleService {
    private final UserRepository userRepository;
    private final CoupleRepository coupleRepository;

    @Transactional
    public String save (String email,String connectCode){
        User requestUser = userRepository.findUserByEmail(email);
        User receiveUser = userRepository.findUserByConnectCode(connectCode);
        
        if(receiveUser==null){
            throw new AppException(ErrorCode.USERNAME_DUPLICATED,connectCode +"존재하지 않은 코드입니다");
        }

        Couple couple1 = requestUser.getCouple();
        Couple couple2 = receiveUser.getCouple();

        String nickname1 = requestUser.getNickname();
        String nickname2 = receiveUser.getNickname();

        
        //이 방식 말고 나중에 Optional로 수정해야함.
        if(couple1!=null || couple2!=null){
            throw new AppException(ErrorCode.USERNAME_DUPLICATED,receiveUser.getEmail() +"님 커플은 이미 존재합니다.");
        }else{
            Couple couple = Couple.builder()
                    .coupleName(nickname1+"님과"+nickname2+"커플")
                    .stts(1)
                    .build();
            Couple createdCouple = coupleRepository.save(couple);
            requestUser.updateUserCoupleId(createdCouple);
            receiveUser.updateUserCoupleId(createdCouple);
            //userRepository.save(requestUser);
            //userRepository.save(receiveUser); //@Transactional 더티체킹방식으로 저장
        }
        return receiveUser.getEmail();
    }
    @Transactional
    public String deleteCouple(String email){
        User user = userRepository.findByEmail(email).get();
        Couple couple = coupleRepository.findById(user.getCouple().getId()).get();
        couple.updateCoupleStts(9);
        return "OK";
    }
}
