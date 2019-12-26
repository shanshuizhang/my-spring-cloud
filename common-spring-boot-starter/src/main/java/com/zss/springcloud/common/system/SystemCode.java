package com.zss.springcloud.common.system;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * @author fuguozhang
 * @email fuguozhang@jyblife.com
 * @date 2019/12/26 14:46
 */
@Getter
@ToString
@AllArgsConstructor
public enum SystemCode {

    SUCCESS("0","success"),
    ERR_INVAILD_PARAM("106", "invalid param"),
    ERR_OTHER_SYSTEM_ERROR("114", "system error");

    private String code;

    private String text;
}
