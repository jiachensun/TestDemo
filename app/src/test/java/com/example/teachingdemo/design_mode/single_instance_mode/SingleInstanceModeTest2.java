package com.example.teachingdemo.design_mode.single_instance_mode;

/**
 * @Author sjc
 * @Date 2020/7/28
 * Description：单例模式
 * 懒汉 - 支持懒加载，不支持线程安全
 * 懒汉 - 支持懒加载，支持线程安全，每次都需要同步，性能低
 * 饿汉 - 支持线程安全，不支持懒加载
 * 双重检测
 * 静态内部类
 * 枚举
 */
public class SingleInstanceModeTest2 {

    private static SingleInstanceModeTest2 instance = null;

    public static SingleInstanceModeTest2 getInstance() {

        synchronized (SingleInstanceModeTest2.class) {
            if (instance == null) {
                instance = new SingleInstanceModeTest2();
            }

            return instance;
        }
    }
}
