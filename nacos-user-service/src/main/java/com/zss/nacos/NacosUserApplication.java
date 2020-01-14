package com.zss.nacos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author fuguozhang
 * @email fuguozhang@jyblife.com
 * @date 2020/1/13 18:20
 */
@SpringBootApplication
@EnableDiscoveryClient
public class NacosUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(NacosUserApplication.class, args);
    }
}
