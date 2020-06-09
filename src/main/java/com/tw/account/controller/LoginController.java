package com.tw.account.controller;

import com.tw.account.controller.dto.AccountInputDTO;
import com.tw.account.controller.errorCode.ErrorCode;
import com.tw.account.controller.exception.AccountException;
import com.tw.account.model.Account;
import com.tw.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
public class LoginController {

    private final String SUCCESS = "Success";

    @Autowired
    private AccountService accountService;

    @PostMapping("/login")
    public String login(@Valid @RequestBody AccountInputDTO accountInputDTO) {
        Account account = accountInputDTO.toAccount();
        Optional<Account> accountByEmail = accountService.findAccountByEmail(account.getEmail());
        if (accountByEmail.isPresent()) {
            if (accountByEmail.get().getPassword().equals(account.getPassword())) {
                return SUCCESS;
            }
            //return WRONG_PASSWORD;
            throw new AccountException(ErrorCode.USER_PASSWORD_ERROR);
        }
        //return USER_NOT_EXIST;
        throw new AccountException(ErrorCode.USER_NOT_EXIST_ERROR);
    }

    @PostMapping("/accounts")
    public String createAccount(@Valid @RequestBody AccountInputDTO accountInputDTO) {
        Account account = accountInputDTO.toAccount();
        try {
            accountService.createAccount(account);
        } catch (DataAccessException e) {
            throw new AccountException(ErrorCode.USER_EXISTS);
        }
        return SUCCESS;
    }
}
