package com.zss.user.service;

import com.zss.user.dto.UserDto;

import java.util.List;

/**
 * @author fuguozhang
 * @email fuguozhang@jyblife.com
 * @date 2019/12/26 14:22
 */
public interface UserService {

    void create(UserDto user);

    UserDto getUser(Long id);

    List<UserDto> getByIds(List<Long> ids);

    UserDto getByUsername(String username);

    void update(UserDto user);

    void delete(Long id);
}
