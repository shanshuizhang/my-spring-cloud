package com.zss.hystrix.controller;

import com.zss.hystrix.service.UserHystrixService;
import com.zss.springcloud.common.dto.RestResultDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fuguozhang
 * @email fuguozhang@jyblife.com
 * @date 2019/12/26 19:14
 */
@RestController
@RequestMapping("/user-hystrix")
@Slf4j
public class UserHystrixController {

    @Autowired
    private UserHystrixService userService;

    @GetMapping("/testFallback/{id}")
    public RestResultDto getUser(@PathVariable Long id){
        return userService.getUser(id);
    }
}
