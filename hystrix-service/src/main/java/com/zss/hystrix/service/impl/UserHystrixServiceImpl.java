package com.zss.hystrix.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.zss.hystrix.dto.UserDto;
import com.zss.hystrix.service.UserHystrixService;
import com.zss.springcloud.common.dto.RestResultDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

/**
 * @author fuguozhang
 * @email fuguozhang@jyblife.com
 * @date 2019/12/26 19:09
 */
@Service
@Slf4j
public class UserHystrixServiceImpl implements UserHystrixService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${service-url.user-service}")
    private String userServiceURL;

    @Override
    @HystrixCommand(fallbackMethod = "fallBackUser")
    public RestResultDto getUser(Long id) {
        return restTemplate.getForObject(userServiceURL + "/user/{1}",RestResultDto.class,id);
    }

    private RestResultDto fallBackUser(@PathVariable Long id){
        UserDto user = UserDto.builder()
                .username("李四")
                .password("234")
                .age(id.intValue())
                .gender(0)
                .build();
        return RestResultDto.success(user);
    }
}
