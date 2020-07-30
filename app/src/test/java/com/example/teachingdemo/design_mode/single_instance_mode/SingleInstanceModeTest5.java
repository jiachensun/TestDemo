package com.example.teachingdemo.design_mode.single_instance_mode;

/**
 * @Author sjc
 * @Date 2020/7/28
 * Description：单例模式
 * 懒汉 - 支持懒加载，不支持线程安全
 * 懒汉 - 支持懒加载，支持线程安全
 * 饿汉 - 支持线程安全，不支持懒加载
 * 双重检测
 * 静态内部类，既支持懒加载，也支持线程安全
 * 枚举
 */
public class SingleInstanceModeTest5 {

    public static SingleInstanceModeTest5 getInstance() {
        return SingleInstance.instance;
    }

    private static final class SingleInstance {
        private static SingleInstanceModeTest5 instance = new SingleInstanceModeTest5();
    }
}
