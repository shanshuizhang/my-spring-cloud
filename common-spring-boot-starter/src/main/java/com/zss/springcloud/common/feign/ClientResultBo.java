package com.zss.springcloud.common.feign;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.zss.springcloud.common.system.SystemCode;
import lombok.Data;

/**
 * 客户端结果
 *
 * @param <T>
 */
@Data
public class ClientResultBo<T> {

    @JsonProperty("code")
    private String code;

    @JsonProperty("data")
    private T data;

    @JsonProperty("msg")
    @JsonAlias("message")
    private String msg;

    @JsonIgnoreProperties
    public boolean isSuccess() {
        if (code == null) {
            return false;
        }

        return SystemCode.SUCCESS.getCode().equals(code.trim());
    }
}
