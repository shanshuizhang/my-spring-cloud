package com.zss.oauth2.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;

/**
 * @author fuguozhang
 * @email fuguozhang@jyblife.com
 * @date 2020/1/13 14:39
 */
@SpringBootApplication
@EnableOAuth2Sso
public class OAuth2ClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(OAuth2ClientApplication.class, args);
    }
}
