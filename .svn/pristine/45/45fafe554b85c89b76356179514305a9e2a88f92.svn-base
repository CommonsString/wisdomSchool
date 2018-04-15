package com.cj.witcommon.aop;

import java.lang.annotation.*;

/**
 * 日志记录
 * Created by a1668 on 2018/4/14.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD, ElementType.PARAMETER })
@Documented
public @interface Log {
    String name() default "无描述"; //用于写方法作用
}