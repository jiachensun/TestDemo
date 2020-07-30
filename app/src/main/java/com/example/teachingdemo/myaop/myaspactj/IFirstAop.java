package com.example.teachingdemo.myaop.myaspactj;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author sjc
 * @Date 2020/7/16
 * Description：
 */
@Retention(value = RetentionPolicy.CLASS)
@Target(value = ElementType.METHOD)
public @interface IFirstAop {

}
