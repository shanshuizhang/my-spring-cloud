package com.zss.springcloud.common.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @author fuguozhang
 * @email fuguozhang@jyblife.com
 * @date 2019/12/26 15:10
 */
@Data
@Builder
public class UserDto {

    private String username;

    private String password;

    private Integer age;

    private Integer gender;
}
