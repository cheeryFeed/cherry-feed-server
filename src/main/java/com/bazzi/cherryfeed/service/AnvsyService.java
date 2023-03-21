package com.bazzi.cherryfeed.service;

import com.bazzi.cherryfeed.domain.Anvsy;
import com.bazzi.cherryfeed.domain.Couple;
import com.bazzi.cherryfeed.domain.User;
import com.bazzi.cherryfeed.domain.dto.AnvsyRequestDto;
import com.bazzi.cherryfeed.domain.dto.AnvsyResponseDto;
import com.bazzi.cherryfeed.repository.AnvsyRepository;
import com.bazzi.cherryfeed.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AnvsyService {
    private final UserRepository userRepository;
    private final AnvsyRepository anvsyRepository;

    public String createAnvsy(String userEmail, AnvsyRequestDto anvsyRequestDto){
        User fidedUser = userRepository.findUserByEmail(userEmail);   //user
        Couple coupleId = fidedUser.getCouple();                      //couple_id(PK)

        Anvsy anvsy = Anvsy.builder()
                .anvsyNm(anvsyRequestDto.getAnvsyNm())
                .anvsyAt(anvsyRequestDto.getAnvsyAt())
                .status(anvsyRequestDto.getStatus())
                .coupleId(coupleId)
                .build();
        anvsyRepository.save(anvsy);
        return "SUCCES";
    }
    public List<AnvsyResponseDto> readAnvsy(String userEmail){
        User fidedUser = userRepository.findUserByEmail(userEmail);     //user
        Couple coupleId = fidedUser.getCouple();                        //couple_id(PK)

        List<Anvsy> anvsies = anvsyRepository.findByCoupleId(coupleId); // 조회한 일정들을 list에 담는다.

        List<AnvsyResponseDto> anvsyResponseDtoList = new ArrayList<>();// 응답DTO를 담을 리스트를 생성한다.

        for (Anvsy anvsy : anvsies) {
            AnvsyResponseDto dto= AnvsyResponseDto.builder() //생성한 DTO리스트에 조회한 일정들을 담는다.
                    .id(anvsy.getId())
                    .anvsyNm(anvsy.getAnvsyNm())
                    .anvsyAt(anvsy.getAnvsyAt())
                    .status(anvsy.getStatus())
                    .build();
            anvsyResponseDtoList.add(dto);
        }
        return anvsyResponseDtoList; //조회한 일정을 담은 응답 DTO를 반환한다.
    }
}
