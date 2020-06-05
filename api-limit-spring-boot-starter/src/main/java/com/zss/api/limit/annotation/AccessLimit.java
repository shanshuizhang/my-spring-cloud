package com.zss.api.limit.annotation;

import java.lang.annotation.*;

@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AccessLimit {

    /**
     * 名字
     */
    String name() default "";

    /**
     * key
     * @return
     */
    String key() default "";

    /**
     * key的前缀
     * @return
     */
    String prefix() default "";

    /**
     * 给定的时间范围 单位(秒)
     * @return
     */
    int period();

    /**
     * 一定时间内最多访问次数
     * @return
     */
    int count();

    /**
     * 限流的类型(用户自定义key 或者 请求ip)
     */
    LimitType limitType() default LimitType.CUSTOMER;

    enum LimitType{
        CUSTOMER,
        IP
    }
}
