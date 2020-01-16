package com.zss.sentinel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author fuguozhang
 * @email fuguozhang@jyblife.com
 * @date 2020/1/14 11:23
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class SentinelApplication {
    public static void main(String[] args) {
        SpringApplication.run(SentinelApplication.class, args);
    }
}
