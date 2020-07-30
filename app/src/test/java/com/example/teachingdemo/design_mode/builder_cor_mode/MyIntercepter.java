package com.example.teachingdemo.design_mode.builder_cor_mode;

/**
 * @Author sjc
 * @Date 2020/7/27
 * Description：
 */
public abstract class MyIntercepter {
    private MyIntercepter intercepter;

    public void setNext(MyIntercepter i) {
        this.intercepter = i;
    }

    public MyIntercepter getNext() {
        return intercepter;
    }

    public abstract void handleProcess();
}
