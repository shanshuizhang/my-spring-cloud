package com.zss.nacos.service.impl;

import com.zss.nacos.service.UserService;
import com.zss.springcloud.common.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fuguozhang
 * @email fuguozhang@jyblife.com
 * @date 2019/12/26 14:23
 */
@Service
public class UserServiceImpl implements UserService {
    @Override
    public void create(UserDto user) {

    }

    @Override
    public UserDto getUser(Long id) {
        return UserDto.builder()
                .username("张山")
                .password("123")
                .age(11)
                .gender(id.intValue())
                .build();
    }

    @Override
    public List<UserDto> getByIds(List<Long> ids) {
        List<UserDto> users = new ArrayList<>();
        ids.forEach(id ->{
            users.add(getUser(id));
        });
        return users;
    }

    @Override
    public UserDto getByUsername(String username) {
        return UserDto.builder()
                .username(username)
                .password("123")
                .age(11)
                .gender(1)
                .build();
    }

    @Override
    public void update(UserDto user) {

    }

    @Override
    public void delete(Long id) {

    }
}
