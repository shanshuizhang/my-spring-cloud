package com.zss.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author fuguozhang
 * @email fuguozhang@jyblife.com
 * @date 2019/12/25 17:51
 */
@EnableEurekaServer
@SpringBootApplication
public class EurekaSecurityServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaSecurityServerApplication.class,args);
    }
}
