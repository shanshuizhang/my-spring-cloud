package com.zss.burbine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

/**
 * @author fuguozhang
 * @email fuguozhang@jyblife.com
 * @date 2019/12/27 18:14
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableTurbine
public class TurbineServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(TurbineServiceApplication.class, args);
    }
}
