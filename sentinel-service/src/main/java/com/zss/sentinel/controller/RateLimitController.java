package com.zss.sentinel.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.zss.sentinel.handler.CustomBlockHandler;
import com.zss.springcloud.common.dto.RestResultDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fuguozhang
 * @email fuguozhang@jyblife.com
 * @date 2020/1/14 11:28
 * 限流
 */
@RestController
@RequestMapping("/rateLimit")
public class RateLimitController {

    /**
     * 按资源名称限流，需要指定限流处理逻辑
     */
    @GetMapping("/byResource")
    @SentinelResource(value = "byResource",blockHandler = "handlerException")
    public RestResultDto byResource(){
        return new RestResultDto("200",null,"按资源名称限流");
    }

    /**
     * 按URL限流，有默认的限流处理逻辑
     */
    @GetMapping("/byUrl")
    @SentinelResource(value = "byUrl",blockHandler = "handlerException")
    public RestResultDto byUrl(){
        return new RestResultDto("200",null,"按URL限流");
    }

    public RestResultDto handlerException(BlockException exception) {
        return new RestResultDto("200",null,exception.getClass().getCanonicalName());
    }

    /**
     * 自定义通用的限流处理逻辑
     */
    @GetMapping("/customBlockHandler")
    @SentinelResource(value = "customBlockHandler",blockHandler = "handlerException",blockHandlerClass = CustomBlockHandler.class)
    public RestResultDto customBlockHandler(){
        return new RestResultDto("200",null,"自定义通用的限流处理逻辑");
    }
}
