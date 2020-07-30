package com.example.teachingdemo.dynamicproxy;

import android.util.Log;

/**
 * @Author sjc
 * @Date 2020/6/30
 * Description：
 */
public class WorkerImp implements IWorker {

    /**
     * 静态代理，需要开发时直接写成java文件并编译成class文件
     *
     * 需求：
     * 知道运行前与运行后的耗时
     *
     * 需要修改源码，在代理实现的地方插入代码
     */
    @Override
    public int working(int number) {

        // 增加项
        long startTime = System.currentTimeMillis();

        int sum = 0;
        for (int i = 0; i < number; i++) {
            sum += i;
        }

        // 增加项
        long endTime = System.currentTimeMillis();
        Log.i("dynamicProxy", "time = " + (endTime - startTime));

        return sum;
    }
}
