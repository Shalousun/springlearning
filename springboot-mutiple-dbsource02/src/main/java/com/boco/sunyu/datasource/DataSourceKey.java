package com.boco.sunyu.datasource;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;


@Component
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.METHOD})
@Documented
public @interface DataSourceKey {
    String value() default "";
}
