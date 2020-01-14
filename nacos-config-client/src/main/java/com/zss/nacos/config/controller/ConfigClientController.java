package com.zss.nacos.config.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fuguozhang
 * @email fuguozhang@jyblife.com
 * @date 2020/1/14 10:11
 */
@RestController
@RequestMapping("/config")
@RefreshScope
@Slf4j
public class ConfigClientController {

    @Value("${config.info}")
    private String configInfo;

    @GetMapping("/info")
    public String getConfigInfo(){
        log.info("获取的配置信息configInfo：{}",configInfo);
        return configInfo;
    }
}
