package com.zss.hystrix.controller;

import com.zss.hystrix.dto.UserDto;
import com.zss.hystrix.service.UserHystrixService;
import com.zss.springcloud.common.dto.RestResultDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

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

    @GetMapping("/testCommond/{id}")
    public RestResultDto testCommond(@PathVariable Long id){
        return userService.testCommond(id);
    }

    @GetMapping("/testException/{id}")
    public RestResultDto testException(@PathVariable Long id){
        return userService.testException(id);
    }

    @GetMapping("/testCache/{id}")
    public RestResultDto testCache(@PathVariable Long id){
        userService.testCache(id);
        userService.testCache(id);
        return userService.testCache(id);
    }

    @GetMapping("/testRemoveCache/{id}")
    public RestResultDto testRemoveCache(@PathVariable Long id){
        userService.testCache(id);
        userService.testRemoveCache(id);
        return userService.testCache(id);
    }

    @GetMapping("/testCollapser")
    public RestResultDto testCollapser() throws InterruptedException,ExecutionException {
        Future<UserDto> future1 =userService.getUserFutrue(1L);
        Future<UserDto> future2 =userService.getUserFutrue(2L);
        future1.get();
        future2.get();
        TimeUnit.MILLISECONDS.sleep(200);
        Future<UserDto> future3 =userService.getUserFutrue(3L);
        return RestResultDto.success(future3.get());
    }
}
