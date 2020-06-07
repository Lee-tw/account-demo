package com.tw.account.controller;

import com.tw.account.controller.dto.AccountDto;
import com.tw.account.model.Account;
import com.tw.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
public class LoginController {

    private final String SUCCESS = "success";
    private final String WRONG_PASSWORD = "password is wrong";
    private final String USER_NOT_EXIST = "user doesn't exist";

    @Autowired
    private AccountService accountService;

    @PostMapping("/login")
    public String login(@Valid @RequestBody AccountDto accountDto) {
        Account account = accountDto.toAccount();
        Optional<Account> accountByEmail = accountService.findAccountByEmail(account.getEmail());
        if (accountByEmail.isPresent()) {
            if (accountByEmail.get().getPassword().equals(account.getPassword())) {
                return SUCCESS;
            }
            return WRONG_PASSWORD;
            //throw new AccountException(ErrorCode.USER_PASSWORD_ERROR_ERROR);
        }
        return USER_NOT_EXIST;
        //throw new AccountException(ErrorCode.USER_NOT_EXIST_ERROR);
    }

    @PostMapping("/accounts")
    public String createAccount(@Valid @RequestBody AccountDto accountDto) {
        Account account = accountDto.toAccount();
        accountService.createAccount(account);
        return SUCCESS;
    }
}
