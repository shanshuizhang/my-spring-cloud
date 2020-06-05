package com.zss.sentinel.controller;

import com.zss.sentinel.service.UserService;
import com.zss.springcloud.common.dto.RestResultDto;
import com.zss.springcloud.common.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author fuguozhang
 * @email fuguozhang@jyblife.com
 * @date 2019/12/27 19:42
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserFeignController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public RestResultDto create(@RequestBody UserDto user){
        userService.create(user);
        return RestResultDto.success();
    }

    @GetMapping("/{id}")
    public RestResultDto getUser(@PathVariable Long id){
        return userService.getUser(id);
    }

    @GetMapping("/getByIds")
    public RestResultDto getByIds(@RequestParam List<Long> ids){
        return userService.getByIds(ids);
    }

    @GetMapping("/getByUsername")
    public RestResultDto getByUsername(@RequestParam String username){
        return userService.getByUsername(username);
    }

    @PostMapping("/update")
    public RestResultDto update(@RequestBody UserDto user) {
        userService.update(user);
        return RestResultDto.success();
    }

    @PostMapping("/delete/{id}")
    public RestResultDto delete(@PathVariable Long id) {
        userService.delete(id);
        return RestResultDto.success();
    }
}
