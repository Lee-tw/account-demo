package com.tw.account.controller.dto;

import com.tw.account.model.Account;

import javax.validation.constraints.*;

public class AccountDto {
    @NotBlank
    @Email
    private final String email;

    @NotBlank
    private final String name;

    @NotBlank
    private final String password;

    public AccountDto(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public Account toAccount() {
        Account account = new Account();
        account.setEmail(this.email);
        account.setName(this.name);
        account.setPassword(this.password);
        return account;
    }
}
