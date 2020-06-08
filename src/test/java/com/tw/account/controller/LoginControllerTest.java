package com.tw.account.controller;

import com.google.gson.Gson;
import com.tw.account.model.Account;
import com.tw.account.service.AccountService;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataAccessException;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 通常 @WebMvcTest 将仅限于单个controller，并结合使用 @MockBean 作为所需的协作者提供模拟实现。
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest(LoginController.class)
public class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;

    private Account account;

    private Account invalidAccount;

    @BeforeEach
    public void setUp() {
        initMocks(this);

        String email = "aa@gmail.com";
        String name = "lee";
        String password = "123";
        String wrongPassword = "111";

        account = Account.builder().email(email).name(name).password(password).build();
        invalidAccount = Account.builder().email(email).name(name).password(wrongPassword).build();
    }

    @Test
    public void should_return_success_when_login_with_valid_account() throws Exception {
        Gson gson = new Gson();
        String jsonObject = gson.toJson(account);

        when(accountService.findAccountByEmail("aa@gmail.com")).thenReturn(Optional.of(account));
        mockMvc.perform(post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonObject))
                .andExpect(status().isOk());
    }

    @Test
    public void should_return_invalid_when_login_with_invalid_account() throws Exception {
        Gson gson = new Gson();
        String jsonObject = gson.toJson(invalidAccount);

        when(accountService.findAccountByEmail("aa@gmail.com")).thenReturn(Optional.of(account));

        mockMvc.perform(post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonObject))
                .andExpect(status().isNotFound());
    }

    @Test
    public void should_create_account_successfully() throws Exception {
        String email = "aa1@gmail.com";
        String name = "lee";
        String password = "123";
        Account newAccount = Account.builder().email(email).name(name).password(password).build();

        Gson gson = new Gson();
        String jsonObject = gson.toJson(newAccount);

        mockMvc.perform(post("/accounts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonObject))
                .andExpect(status().isOk());
    }

    @Test
    public void should_create_account_unsuccessfully_when_email_exists() throws Exception {
        Gson gson = new Gson();
        String jsonObject = gson.toJson(account);

        when(accountService.createAccount(account)).thenThrow(Mockito.mock(DataAccessException.class));

        mockMvc.perform(post("/accounts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonObject))
                .andExpect(status().isNotFound());
    }
}
