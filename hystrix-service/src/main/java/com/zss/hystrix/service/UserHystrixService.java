package com.zss.hystrix.service;

import com.zss.hystrix.dto.UserDto;
import com.zss.springcloud.common.dto.RestResultDto;

import java.util.concurrent.Future;

/**
 * @author fuguozhang
 * @email fuguozhang@jyblife.com
 * @date 2019/12/26 19:08
 */
public interface UserHystrixService {

    RestResultDto getUser(Long id);

    RestResultDto testCommond(Long id);

    RestResultDto testException(Long id);

    RestResultDto testCache(Long id);

    RestResultDto testRemoveCache(Long id);

    Future<UserDto> getUserFutrue(Long id);
}
