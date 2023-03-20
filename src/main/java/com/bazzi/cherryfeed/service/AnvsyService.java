package com.bazzi.cherryfeed.service;

import com.bazzi.cherryfeed.domain.Anvsy;
import com.bazzi.cherryfeed.domain.Couple;
import com.bazzi.cherryfeed.domain.User;
import com.bazzi.cherryfeed.domain.dto.AnvsyRequest;
import com.bazzi.cherryfeed.repository.AnvsyRepository;
import com.bazzi.cherryfeed.repository.CoupleRepository;
import com.bazzi.cherryfeed.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AnvsyService {
    private final UserRepository userRepository;
    private final CoupleRepository coupleRepository;
    private final AnvsyRepository anvsyRepository;

    public String createAnvsy(String userEmail,AnvsyRequest anvsyRequest){
        User fidedUser = userRepository.findUserByEmail(userEmail);   //user
        Couple coupleId = fidedUser.getCouple();                      //couple_id(PK)

        Anvsy anvsy = Anvsy.builder()
                .anvsyNm(anvsyRequest.getAnvsyNm())
                .anvsyAt(anvsyRequest.getAnvsyAt())
                .status(anvsyRequest.getStatus())
                .coupleId(coupleId)
                .build();
        return "SUCCES";
    }
}
