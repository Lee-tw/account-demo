package com.tw.account.controller;

import com.tw.account.model.Account;
import com.tw.account.service.AccountService;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(LoginController.class)
public class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    //@MockBean
    @InjectMocks
    private AccountService accountService;

    private Account account;

    @BeforeEach
    public void setUp() {
        initMocks(this);

        String email = "aa@gmail.com";
        String name = "lee";
        String password = "123";

        account = Account.builder().email(email).name(name).password(password).build();
    }

    @Test
    public void should_return_success_when_login_with_valid_account() throws Exception {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("email", "aa@gmail.com");
        params.add("name", "lee");
        params.add("password", "123");

        when(accountService.findAccountByEmail("aa@gmail.com")).thenReturn(Optional.of(account));

        MvcResult mvcResult = mockMvc.perform(get("/login").params(params)).andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        Assert.assertEquals("success", content);
    }

    @Test
    public void should_return_invalid_when_login_with_invalid_account() throws Exception {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("email", "aa@gmail.com");
        params.add("name", "lee");
        params.add("password", "111");

        when(accountService.findAccountByEmail("aa@gmail.com")).thenReturn(
                Optional.of(account)
        );

        String content = mockMvc.perform(get("/login").params(params))
                .andReturn()
                .getResponse()
                .getContentAsString();
        Assert.assertEquals("invalid", content);
    }

    @Test
    public void should_create_account_successfully() throws Exception {
        String email = "aa1@gmail.com";
        String name = "lee";
        String password = "123";

        String accountAsJson = "{ \"email\": \"" + email + "\", " +
                "\"password\": \"" + password + "\", " +
                "\"name\": \"" + name + "\"" +
                "}";

        mockMvc.perform(post("/accounts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(accountAsJson))
                .andExpect(status().isOk());
    }

    @Test
    public void should_create_account_unsuccessfully_when_email_exists() throws Exception {
        String email = "aa@gmail.com";
        String name = "lee";
        String password = "123";

//        Account badAccount = new Account(email, name, password);
//
//        String accountAsJson = "{ \"email\": \"" + email + "\", " +
//                "\"password\": \"" + password + "\", " +
//                "\"name\": \"" + name + "\"" +
//                "}";
//
//        when(accountService.createAccount(badAccount)).thenThrow();
//
//        mockMvc.perform(post("/accounts")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(accountAsJson))
//                .andExpect(status().is5xxServerError());
    }
}
