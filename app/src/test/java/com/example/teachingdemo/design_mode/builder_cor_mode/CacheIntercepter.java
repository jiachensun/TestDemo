package com.example.teachingdemo.design_mode.builder_cor_mode;

/**
 * @Author sjc
 * @Date 2020/7/27
 * Description：
 */
public class CacheIntercepter extends MyIntercepter {
    @Override
    public void handleProcess() {
        System.out.println("CacheIntercepter Succeed");
        if (getNext() != null) {
            getNext().handleProcess();
        } else {
            System.out.println("All Succeed");
        }
    }
}
