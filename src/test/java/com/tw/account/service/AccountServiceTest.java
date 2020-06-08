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

    /**
     * @InjectMocks 可以注入/实例化bean，但是不能注入/实例化其 dependency 的 bean，
     * 其 dependency 的 bean 为 null，没有 mockitoInterceptor 属性，只有本身的成员变量。
     * 但是当其 dependency 用以及 dependency 的 dependency 都用@Mock注解时，就可以也被注入了。
     * 此处 accountDao 为 accountService 的 dependency
     *
     * @Mock 可以注入/实例化bean，但是不会实例化该 bean 的 dependency，其 dependency 的 bean 为 null。
     * 会多一个属性：mockitoInterceptor。
     *
     * @mockBean 注解将 Mock 对象添加到 Spring 上下文中
     *
     * @Inject 可以注入/实例化 bean 和 bean 的整个 dependency graph，并且也注入/实例化这些 dependency 的 bean
     */
    @InjectMocks   // 将 accountDao 注入到 accountService 中
    //@Mock  此处使用 @Mock 则 accountService 和 accountDao 没有依赖关系
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
