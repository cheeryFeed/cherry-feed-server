package com.bazzi.cherryfeed.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;

import java.nio.file.AccessDeniedException;

@AllArgsConstructor
@Getter

public enum ErrorCode {
    USERNAME_DUPLICATED(HttpStatus.CONFLICT, "This name is already registered."),
    RANDOM_CODE_DUPLICATED(HttpStatus.CONFLICT, "This code is already registered."),
    COUPLE_DUPLICATED(HttpStatus.CONFLICT, "A couple already exists."),
    COUPLE_NOT_FOUND(HttpStatus.NOT_FOUND, "There are no registered couples. Please use it after registering as a couple."),
    USERNAME_NOT_FOUND(HttpStatus.NOT_FOUND, "There is no member with that ID. Please enter again."),
    CONNECT_NOT_FOUND(HttpStatus.NOT_FOUND, "Connect code not found."),
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, "You entered the wrong password."),
    USER_NOT_FOUND(HttpStatus.UNAUTHORIZED, "The ID corresponding to the token could not be found."),
    ACCESS_DENIED(HttpStatus.UNAUTHORIZED, "Token Error");

    private HttpStatus httpStatus;
    private String message;
}
