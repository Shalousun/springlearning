package com.sunyu.redission.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

/**
 * api访问请求控制
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.TYPE})
public @interface RateLimit {

    /**
     * 允许访问的次数
     */
    int limits() default 100;

    /**
     * 时间段，多少时间段内运行访问count次
     */
    long timeOut() default 1;

    /**
     * 限流单位
     * @return
     */
    TimeUnit timeUnit() default TimeUnit.SECONDS;
}
