package com.zss.user.controller;

import com.zss.springcloud.common.dto.RestResultDto;
import com.zss.user.dto.UserDto;
import com.zss.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author fuguozhang
 * @email fuguozhang@jyblife.com
 * @date 2019/12/26 14:22
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public RestResultDto create(@RequestBody UserDto user){
        userService.create(user);
        return RestResultDto.success();
    }

    @GetMapping("/{id}")
    public RestResultDto getUser(@PathVariable Long id){
        UserDto user = userService.getUser(id);
        log.info("根据用户id：[{}]获取用户信息，用户名称：[{}]",id,user.getUsername());
        return RestResultDto.success(user);
    }

    @GetMapping("/getByIds")
    public RestResultDto getByIds(@RequestParam List<Long> ids){
        List<UserDto> userDtos = userService.getByIds(ids);
        return RestResultDto.success(userDtos);
    }

    @GetMapping("getByUsername")
    public RestResultDto getByUsername(@RequestParam String username){
        log.info("根据用户名获取用户，用户名：[{}]",username);
        UserDto user = userService.getByUsername(username);
        return RestResultDto.success(user);
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
