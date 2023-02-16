package com.winter.portal.server;

import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class PortalServerApplicationTests {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private StringEncryptor encryptor;
    @Value("${spring.mvc.throw-exception-if-no-handler-found}")
    private boolean vluae;

    @Test
    public void contextLoads() {
        System.out.println(vluae);
    }

    @Test
    public void stringEncryptor() {
        String username = encryptor.encrypt("root");
        String password = encryptor.encrypt("123456");
        System.out.println(username);
        System.out.println(password);
    }

}
