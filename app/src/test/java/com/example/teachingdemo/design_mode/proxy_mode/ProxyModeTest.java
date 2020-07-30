package com.example.teachingdemo.design_mode.proxy_mode;

import org.junit.Test;

/**
 * @Author sjc
 * @Date 2020/7/27
 * Description：代理模式
 * 通过实现代理类，可以获得方法的执行前后，用于统计某个方法的执行状态
 */
public class ProxyModeTest {
    @Test
    public void main() {
        IProxy proxy = new ProxyImpl();
        // 执行前
        System.out.println("执行前");
        proxy.doSomething("执行中");
        // 执行后
        System.out.println("执行后");
    }
}
