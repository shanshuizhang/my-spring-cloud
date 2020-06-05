package com.zss.api.limit.annotation;

import java.lang.annotation.*;

@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Limit {
    int seconds();

    int maxCount();

    boolean needLogin() default false;

}
