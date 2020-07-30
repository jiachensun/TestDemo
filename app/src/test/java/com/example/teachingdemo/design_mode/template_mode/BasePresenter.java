package com.example.teachingdemo.design_mode.template_mode;

/**
 * @Author sjc
 * @Date 2020/7/27
 * Description：
 */
public abstract class BasePresenter {

    BasePresenter() {
        initData();
    }

    private void initData() {
        System.out.println("BasePresenter initData");
        needMethod1();
        needMethod2();
    }

    protected abstract void needMethod1();
    protected abstract void needMethod2();
}
