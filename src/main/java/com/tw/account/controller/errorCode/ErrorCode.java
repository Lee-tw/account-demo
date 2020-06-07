package com.tw.account.controller.errorCode;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum ErrorCode {
    INTERNAL_SERVER_ERROR(1001, "Internal server error"),
    USER_NOT_EXIST_ERROR(1002, "User does not exist"),
    USER_PASSWORD_ERROR_ERROR(1003, "Password is not correct");

    private int code;
    private String msg;
}
