package com.example.teachingdemo.design_mode.proxy_mode;

/**
 * @Author sjc
 * @Date 2020/7/27
 * Description：
 */
public class ProxyImpl implements IProxy {
    @Override
    public void doSomething(String thing) {
        System.out.println("执行中");
    }
}
