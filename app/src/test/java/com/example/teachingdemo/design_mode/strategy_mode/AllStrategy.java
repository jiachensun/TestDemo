package com.example.teachingdemo.design_mode.strategy_mode;

/**
 * @Author sjc
 * @Date 2020/7/27
 * Description：
 */
public class AllStrategy {
    private ISomeInterface someInterface;

    public void setSomeInterface(ISomeInterface someInterface) {
        this.someInterface = someInterface;
    }

    public void doSomeThing() {
        someInterface.method2();
        someInterface.method1();
        someInterface.method3();
    }
}
