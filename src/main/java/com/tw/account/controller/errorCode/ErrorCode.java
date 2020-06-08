package com.tw.account.controller.errorCode;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum ErrorCode {
    USER_EXISTS(1001, "User exists"),
    USER_NOT_EXIST_ERROR(1002, "User does not exist"),
    USER_PASSWORD_ERROR(1003, "Password is not correct");

    private int code;
    private String msg;
}
