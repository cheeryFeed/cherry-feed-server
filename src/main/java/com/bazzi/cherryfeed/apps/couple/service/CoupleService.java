package com.bazzi.cherryfeed.apps.couple.service;

import com.bazzi.cherryfeed.apps.couple.domain.Couple;
import com.bazzi.cherryfeed.apps.account.domain.Account;
import com.bazzi.cherryfeed.exception.AppException;
import com.bazzi.cherryfeed.exception.ErrorCode;
import com.bazzi.cherryfeed.apps.couple.repository.CoupleRepository;
import com.bazzi.cherryfeed.apps.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CoupleService {
    private final AccountRepository accountRepository;
    private final CoupleRepository coupleRepository;

    @Transactional
    public String createCouple(Long id, String connectCode) {
        // n+1문제 해결
        Account requestUser = accountRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND ,ErrorCode.USER_NOT_FOUND.getMessage()));
        Account receiveUser = accountRepository.findByConnectCode(connectCode).orElseThrow(() -> new AppException(ErrorCode.CONNECT_NOT_FOUND, ErrorCode.CONNECT_NOT_FOUND.getMessage()));

        if (requestUser.getCouple() != null || receiveUser.getCouple() != null) {
            throw new AppException(ErrorCode.COUPLE_DUPLICATED, ErrorCode.COUPLE_DUPLICATED.getMessage());
        } else {
            //  MapStruct --> 이친구를 찾아서 도입하는것도 추천.
            Couple couple = Couple.builder()
                    .coupleName(requestUser.getNickname() + "님과" + receiveUser.getNickname() + "커플")
                    .stts(1)
                    .build();
            Couple createdCouple = coupleRepository.save(couple);
            requestUser.updateUserCoupleId(createdCouple);
            receiveUser.updateUserCoupleId(createdCouple);
        }
        return requestUser.getUserName() + "님과" + receiveUser.getUserName() + "님이 연결되었습니다. 커플테이블 저장완료";
    }

    @Transactional
    public void deleteCouple(Long id) {
        Optional<Account> userOptional = accountRepository.findByIdFetchCouple(id);

        if (userOptional.isPresent()) {
            Account user = userOptional.get();
            if (user.getCouple() != null) {
                user.getCouple().updateCoupleStts(9);
            }
        } else {
            throw new AppException(ErrorCode.USER_NOT_FOUND, ErrorCode.USER_NOT_FOUND.getMessage());
        }
    }
    /*20230902 위소스로 변경
    @Transactional
    public void deleteCouple(Long id) {
        accountRepository.findByIdFetchCouple(id).ifPresentOrElse(
                user -> {
                    if (user.getCouple() != null) {
                        user.getCouple().updateCoupleStts(9);
                    }
                },
                () -> {
                    throw new AppException(ErrorCode.USER_NOT_FOUND ,ErrorCode.USER_NOT_FOUND.getMessage());
                }
        );
    }
     */
}
