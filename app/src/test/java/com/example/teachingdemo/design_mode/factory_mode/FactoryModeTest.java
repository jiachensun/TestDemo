package com.example.teachingdemo.design_mode.factory_mode;

import org.junit.Test;

/**
 * @Author sjc
 * @Date 2020/7/27
 * Description：工厂模式
 * 定义接口，不同功能实现同一个接口。使用工厂模式，根据不同的功能创建不同的接口实现，由接口统一调度
 */
public class FactoryModeTest {
    @Test
    public void main() {
        PhoneFactory factory = new PhoneFactory();
//        IPhone iPhone = factory.getPhone("sanxing");
//        Phone samsung = iPhone.newPhone();
//        samsung.setVersion("10");
//        samsung.setModel("Samsung A60");
//        System.out.println("FactoryModeTest " + samsung.toString());

        IPhone iPhone = factory.getPhone("xiaomi");
        Phone mi = iPhone.newPhone();
        mi.setVersion("10");
        mi.setModel("Mi 10");
        System.out.println("FactoryModeTest " + mi.toString());
    }
}
