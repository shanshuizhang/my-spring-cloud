package com.zss.hystrix.dashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * @author fuguozhang
 * @email fuguozhang@jyblife.com
 * @date 2019/12/27 17:36
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableHystrixDashboard
public class HystrixDashBoardAppliaction {
    public static void main(String[] args) {
        SpringApplication.run(HystrixDashBoardAppliaction.class, args);
    }
}
