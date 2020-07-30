package com.example.teachingdemo.design_mode.factory_mode;

/**
 * @Author sjc
 * @Date 2020/7/27
 * Description：
 */
public class Mi implements IPhone {
    @Override
    public Phone newPhone() {
        Phone phone = new Phone();
        phone.setBrand("小米");
        return phone;
    }
}
