package com.tw.account.service;

import com.tw.account.controller.exception.AccountException;
import com.tw.account.dao.AccountDao;
import com.tw.account.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountDao accountDao;

    public Optional<Account> findAccountByEmail(String email) {
        return accountDao.findAccountByEmail(email);
    }

    public int createAccount(Account account) {
        accountDao.save(account);
        return account.getId();
    }
}
