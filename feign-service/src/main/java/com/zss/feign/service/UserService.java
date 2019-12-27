package com.zss.feign.service;

import com.zss.springcloud.common.dto.RestResultDto;
import com.zss.springcloud.common.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author fuguozhang
 * @email fuguozhang@jyblife.com
 * @date 2019/12/27 19:31
 */
@FeignClient(name = "user-service")
public interface UserService {

    @PostMapping("/user/create")
    RestResultDto create(@RequestBody UserDto user);

    @GetMapping("/user/{id}")
    RestResultDto getUser(@PathVariable Long id);

    @GetMapping("/user/getByIds")
    RestResultDto getByIds(@RequestParam List<Long> ids);

    @GetMapping("/user/getByUsername")
    RestResultDto getByUsername(@RequestParam String username);

    @PostMapping("/user/update")
    RestResultDto update(@RequestBody UserDto user) ;

    @PostMapping("/user/delete/{id}")
    RestResultDto delete(@PathVariable Long id) ;
}
