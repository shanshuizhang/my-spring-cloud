package com.zss.sentinel.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.zss.springcloud.common.dto.RestResultDto;

/**
 * @author fuguozhang
 * @email fuguozhang@jyblife.com
 * @date 2020/1/14 15:36
 */
public class CustomBlockHandler {

    public RestResultDto handlerException(BlockException exception){
        return new RestResultDto("200",null,"自定义限流信息");
    }
}
