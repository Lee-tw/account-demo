package com.tw.account.controller.dto;

import com.tw.account.model.Account;
import lombok.AllArgsConstructor;

import javax.validation.constraints.*;

/**
 * DTO 主要负责前端数据与业务层的交互
 */
@AllArgsConstructor
public class AccountInputDTO {
    @NotBlank
    @Email
    private final String email;

    @NotBlank
    private final String name;

    @NotBlank
    private final String password;

    public Account toAccount() {
        Account account = new Account();
        account.setEmail(this.email);
        account.setName(this.name);
        account.setPassword(this.password);
        return account;
    }
}
