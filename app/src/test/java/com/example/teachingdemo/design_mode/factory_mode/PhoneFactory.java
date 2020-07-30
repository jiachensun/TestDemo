package com.example.teachingdemo.design_mode.factory_mode;

/**
 * @Author sjc
 * @Date 2020/7/27
 * Description：
 */
public class PhoneFactory {
    public IPhone getPhone(String name) {
        if ("sanxing".equals(name)) {
            return new Samsung();
        } else if ("xiaomi".equals(name)) {
            return new Mi();
        }
        return null;
    }
}
