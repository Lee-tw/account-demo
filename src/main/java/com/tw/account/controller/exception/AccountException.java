package com.tw.account.controller.exception;

import com.tw.account.controller.errorCode.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
//@SuppressWarnings("PMD.MissingSerialVersionUID")   用在取消一些编译器产生的警告对代码左侧行列的遮挡，有时候这会挡住我们断点调试时打的断点。
public class AccountException extends RuntimeException {
    private int errorCode;
    private String errorMsg;

    public AccountException(ErrorCode errorCode) {
        this.errorCode = errorCode.getCode();
        this.errorMsg = errorCode.getMsg();
    }
}