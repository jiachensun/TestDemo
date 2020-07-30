package com.example.teachingdemo.design_mode.template_mode;

import org.junit.Test;

/**
 * @Author sjc
 * @Date 2020/7/27
 * Description：模板模式
 * 父类定义并实现公共的方法并需要执行特定步骤，子类如果有特定的功能，由父类定义抽象方法，子类去实现特定方法。
 */
public class TemplateModeTest {
    @Test
    public void main() {
        new MyPresenter();
    }
}
