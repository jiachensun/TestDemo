package com.example.teachingdemo.design_mode.template_mode;

/**
 * @Author sjc
 * @Date 2020/7/27
 * Description：
 */
public class MyPresenter extends BasePresenter {
    @Override
    protected void needMethod1() {
        System.out.println("MyPresenter needMethod1");
    }

    @Override
    protected void needMethod2() {
        System.out.println("MyPresenter needMethod2");
    }
}
