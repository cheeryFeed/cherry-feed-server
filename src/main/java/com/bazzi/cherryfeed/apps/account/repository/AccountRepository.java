package com.bazzi.cherryfeed.apps.account.repository;


import com.bazzi.cherryfeed.apps.account.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByEmail(String email);

    Optional<Account> findByNickname(String nickname);

    Optional<Account> findByConnectCode(String code);

    @Query(value = "select user,couple from Account user left join fetch Couple couple on user.couple = couple where user.id = :id")
    Optional<Account> findByIdFetchCouple(@Param("id") Long id);

    @Query(value = "select user,couple from Account user left join fetch Couple couple on user.couple = couple where user.connectCode = :connectCode")
    Optional<Account> findByConnectCodeFetchCouple(@Param("connectCode") String connectCode);
    //boolean existsByCoupleId

    Optional<Account> findByRefreshToken(String refreshToken);

}