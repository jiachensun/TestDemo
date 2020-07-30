package com.example.teachingdemo.design_mode.single_instance_mode;

/**
 * @Author sjc
 * @Date 2020/7/28
 * Description：单例模式
 * 懒汉 - 支持懒加载，不支持线程安全
 * 懒汉 - 支持懒加载，支持线程安全
 * 饿汉 - 支持线程安全，不支持懒加载
 * 双重检测
 * 静态内部类
 * 枚举
 */
public class SingleInstanceModeTest1 {

    private static SingleInstanceModeTest1 instance = null;

    public static SingleInstanceModeTest1 getInstance() {
        if (instance == null) {
            instance = new SingleInstanceModeTest1();
        }

        return instance;
    }

    private synchronized void myWaitTest() {
        System.out.println("aaa");
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }
}
