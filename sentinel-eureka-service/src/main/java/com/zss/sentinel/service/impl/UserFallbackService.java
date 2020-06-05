package com.zss.sentinel.service.impl;

import com.zss.sentinel.service.UserService;
import com.zss.springcloud.common.dto.RestResultDto;
import com.zss.springcloud.common.dto.UserDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserFallbackService implements UserService {
    @Override
    public RestResultDto create(UserDto user) {
        return RestResultDto.fail("500","服务被降级");
    }

    @Override
    public RestResultDto getUser(Long id) {
        UserDto userDto = UserDto.builder()
                .username("张山")
                .password("123")
                .age(11)
                .gender(id.intValue())
                .build();
        return RestResultDto.success(userDto);
    }

    @Override
    public RestResultDto getByIds(List<Long> ids) {
        return RestResultDto.fail("500","服务被降级");
    }

    @Override
    public RestResultDto getByUsername(String username) {
        UserDto userDto = UserDto.builder()
                .username(username)
                .password("123")
                .age(11)
                .gender(1)
                .build();
        return RestResultDto.success(userDto);
    }

    @Override
    public RestResultDto update(UserDto user) {
        return RestResultDto.fail("500","服务被降级");
    }

    @Override
    public RestResultDto delete(Long id) {
        return RestResultDto.fail("500","服务被降级");
    }
}
