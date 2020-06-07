package com.tw.account.service;

import com.tw.account.dao.AccountDao;
import com.tw.account.model.Account;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class AccountServiceTest {

    @InjectMocks
    private AccountService accountService;

    @Mock
    private AccountDao accountDao;

    @BeforeEach
    void setUp() {
        initMocks(this);
    }

    @Test
    public void should_return_account_by_email() {
        String email = "aa@gamil.com";
        accountService.findAccountByEmail(email);

        verify(accountDao).findAccountByEmail(email);
    }

    @Test
    public void should_create_account_when_there_are_no_validation_errors() {
        Account account = Account.builder().build();
        accountService.createAccount(account);

        verify(accountDao).save(account);
    }
}
