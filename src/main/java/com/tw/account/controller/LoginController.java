package com.tw.account.controller;

import com.tw.account.model.Account;
import com.tw.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class LoginController {

    private final String SUCCESS = "success";
    private final String INVALID = "invalid";

    @Autowired
    private AccountService accountService;

    @GetMapping("/login")
    public String login(@RequestParam("name") String name, @RequestParam("password") String password, @RequestParam("email") String email) {
        Account account = new Account(email, name, password);
        Optional<Account> accountByEmail = accountService.findAccountByEmail(account.getEmail());
        if (accountByEmail.isPresent() &&
                accountByEmail.get().getName().equals(name) &&
                accountByEmail.get().getPassword().equals(password)){
            return SUCCESS;
        }
        return INVALID;
    }

    @PostMapping("/accounts")
    public String createAccount(@RequestBody Account account) {
        accountService.createAccount(account);
        return SUCCESS;
    }
}
