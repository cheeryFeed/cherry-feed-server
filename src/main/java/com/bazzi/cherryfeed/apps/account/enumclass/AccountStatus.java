package com.bazzi.cherryfeed.apps.account.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AccountStatus {
    REGISTERED(1,"등록","사용자 등록상태"),
    UNREGISTERED(9,"해지","사용자 해지상태")
    ;

    private Integer id;
    private String title;
    private String description;
}
