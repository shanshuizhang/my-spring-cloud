package com.zss.user.service.impl;

import com.zss.user.dto.UserDto;
import com.zss.user.service.UserService;
import org.springframework.stereotype.Service;

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
                .gender(1)
                .build();
    }

    @Override
    public List<UserDto> getByIds(List<Long> ids) {
        return null;
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
