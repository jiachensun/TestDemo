package com.example.teachingdemo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author sjc
 * @Date 2020/5/8.
 * GitHub：
 * Email：jiachen.sun@credit.com
 * Description：用于加载activity layout注解
 * 元注解：1、对类，2、运行时
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface AptActivityLayout {
    int activityLayoutId() default -1;
}
