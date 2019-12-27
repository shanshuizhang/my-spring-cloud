package com.zss.feign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author fuguozhang
 * @email fuguozhang@jyblife.com
 * @date 2019/12/27 19:26
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class FeignServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(FeignServiceApplication.class, args);
    }
}
