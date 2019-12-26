package com.zss.hystrix.service;

import com.zss.springcloud.common.dto.RestResultDto;

/**
 * @author fuguozhang
 * @email fuguozhang@jyblife.com
 * @date 2019/12/26 19:08
 */
public interface UserHystrixService {

    RestResultDto getUser(Long id);
}
