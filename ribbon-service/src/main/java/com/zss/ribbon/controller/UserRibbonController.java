package com.zss.ribbon.controller;

import com.zss.ribbon.dto.UserDto;
import com.zss.springcloud.common.dto.RestResultDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/**
 * @author fuguozhang
 * @email fuguozhang@jyblife.com
 * @date 2019/12/26 16:11
 */
@RestController
@RequestMapping("/user-ribbon")
@Slf4j
public class UserRibbonController {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${service-url.user-service}")
    private String userServiceURL;

    @GetMapping("/{id}")
    public RestResultDto getUser(@PathVariable Long id){
        return restTemplate.getForObject(userServiceURL + "/user/{a}",RestResultDto.class,id);
    }

    @GetMapping("/getByUsername")
    public RestResultDto getByUsername(@RequestParam String username){
        log.info("根据用户名获取用户，用户名：[{}]",username);
        return restTemplate.getForObject(userServiceURL + "/user/getByUsername?username={1}",RestResultDto.class,username);
    }

    @PostMapping("/create")
    public RestResultDto create(@RequestBody UserDto user) {
        return restTemplate.postForObject(userServiceURL + "/user/create", user, RestResultDto.class);
    }

    @PostMapping("/update")
    public RestResultDto update(@RequestBody UserDto user) {
        return restTemplate.postForObject(userServiceURL + "/user/update", user, RestResultDto.class);
    }

    @PostMapping("/delete/{id}")
    public RestResultDto delete(@PathVariable Long id) {
        return restTemplate.postForObject(userServiceURL + "/user/delete/{}", null, RestResultDto.class, id);
    }

}
