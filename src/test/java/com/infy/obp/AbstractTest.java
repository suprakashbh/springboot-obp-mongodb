package com.infy.obp;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;



@RunWith(SpringRunner.class)
@SpringBootTest
public abstract class AbstractTest {
    protected String authToken;

    @Value("${obp.username}")
    private String username;

    @Value("${obp.password}")
    private String password;

    @Autowired
    private com.infy.obp.auth.DirectAuthenticationService directAuthenticationService;

    @Before
    public void init() {
        authToken = directAuthenticationService.login(username, password);
    }
}
