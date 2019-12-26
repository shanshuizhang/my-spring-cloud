package com.zss.springcloud.common.dto;

import com.zss.springcloud.common.system.SystemCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author fuguozhang
 * @email fuguozhang@jyblife.com
 * @date 2019/12/26 14:33
 */
@Getter
@NoArgsConstructor
public class RestResultDto<T> {

    private String code;

    private T data;

    private String msg;

    public RestResultDto(String code, T data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    public static RestResultDto fail(SystemCode systemCode) {
        return fail(systemCode.getCode(), systemCode.getText());
    }


    public static <T> RestResultDto<T> fail(String code,String msg) {
        return new RestResultDto(code, null, msg);
    }

    public static RestResultDto success(){
        return success(null);
    }
    public static <T> RestResultDto<T> success(T data) {
        return new RestResultDto(SystemCode.SUCCESS.getCode(),data,"请求成功");
    }
}
