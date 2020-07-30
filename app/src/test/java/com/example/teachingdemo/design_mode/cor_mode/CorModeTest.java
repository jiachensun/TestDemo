package com.example.teachingdemo.design_mode.cor_mode;

import org.junit.Test;

/**
 * @Author sjc
 * @Date 2020/7/27
 * Description：责任链模式
 * 定义抽象处理类，设置next执行顺序，设置抽象执行方法（子类去实现），然后分发事件
 */
public class CorModeTest {
    @Test
    public void main() {
        HandleOne handleOne = new HandleOne();
        HandleTwo handleTwo = new HandleTwo();
        HandleThree handleThree = new HandleThree();

        handleOne.setNext(handleTwo);
        handleTwo.setNext(handleThree);

        handleOne.handleRequest("1");
    }
}
