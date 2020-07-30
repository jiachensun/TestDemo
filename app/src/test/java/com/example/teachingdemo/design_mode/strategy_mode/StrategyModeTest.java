package com.example.teachingdemo.design_mode.strategy_mode;

import org.junit.Test;

/**
 * @Author sjc
 * @Date 2020/7/27
 * Description：策略模式
 * 定义统一接口，使用策略模式在不同环境下使用不同的接口实现类，根据不同的策略选择不同的行为
 */
public class StrategyModeTest {
    @Test
    public void main() {
        AllStrategy allstrategy = new AllStrategy();
//        allstrategy.setSomeInterface(new SomeInterfaceImpl1());
        allstrategy.setSomeInterface(new SomeInterfaceImpl2());
        allstrategy.doSomeThing();
    }
}
