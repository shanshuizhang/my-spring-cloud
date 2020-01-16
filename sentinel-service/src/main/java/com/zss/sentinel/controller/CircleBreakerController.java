package com.zss.sentinel.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.zss.springcloud.common.dto.RestResultDto;
import com.zss.springcloud.common.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author fuguozhang
 * @email fuguozhang@jyblife.com
 * @date 2020/1/14 15:46
 * 熔断
 */
@RestController
@RequestMapping("/circleBreaker")
@Slf4j
public class CircleBreakerController {

    @Value("${service-url.user-service}")
    private String userServiceURL;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/fallback/{id}")
    @SentinelResource(value = "fallback",fallback = "handleFallback")
    public RestResultDto fallback(@PathVariable Long id){
        return restTemplate.getForObject(userServiceURL + "/user/{1}",RestResultDto.class,id);
    }

    public RestResultDto handleFallback(Long id){
        UserDto defaultUser = UserDto.builder().username("牛霸天").age(id.intValue()).build();
        return new RestResultDto("200",defaultUser,"服务降级返回");
    }

    @GetMapping("/fallbackException/{id}")
    @SentinelResource(value = "fallbackException" ,fallback = "handleFallback2",exceptionsToIgnore = NullPointerException.class)
    public RestResultDto fallbackException(@PathVariable Long id){
        if(id == 1){
            throw new IndexOutOfBoundsException();
        }else if(id == 2){
            throw new NullPointerException();
        }
        return restTemplate.getForObject(userServiceURL + "/user/{1}",RestResultDto.class,id);
    }

    public RestResultDto handleFallback2(Long id,Throwable e){
        log.error("服务降级handleFallback2，id:{},异常类型：{}",id,e.getClass());
        UserDto defaultUser = UserDto.builder().username("驴霸天").age(id.intValue()).build();
        return new RestResultDto("200",defaultUser,"服务降级返回");
    }
}
