package com.tw.account.controller.exception;

import com.tw.account.controller.errorCode.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice   // 用于定义 @ExceptionHandler、@InitBinder、@ModelAttribute，并应用到所有 @RequestMapping 中 （将其应用到所有 controller 上）
public class ControllerExceptionAdvice {

    /**
     * 在 controller 抛出对应的异常后执行
     *
     * @ModelAttribute 在Model上设置的值，对于所有被 @RequestMapping 注解的方法中，都可以通过 ModelMap 获取
     */
    @ExceptionHandler(AccountException.class)   // @ExceptionHandler 接受请求处理方法抛出的异常
    @ResponseStatus(HttpStatus.NOT_FOUND)       // 不加 @ResponseStatus，直接走@ExceptionHandler，返回状态码200
    @ResponseBody
    public String handleAccountException(AccountException e) {
        if (e.getErrorCode() == ErrorCode.USER_PASSWORD_ERROR.getCode()) {
            return "Password is wrong";
        }
        else if (e.getErrorCode() == ErrorCode.USER_NOT_EXIST_ERROR.getCode()) {
            return "User doesn't exist";
        }
        return "User exists";
    }
}
