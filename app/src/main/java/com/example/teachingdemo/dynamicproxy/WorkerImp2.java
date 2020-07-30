package com.example.teachingdemo.dynamicproxy;

import android.util.Log;

/**
 * @Author sjc
 * @Date 2020/6/30
 * Description：
 */
public class WorkerImp2 implements IWorker {
    @Override
    public int working(int number) {
        int sum = 0;
        for (int i = 0; i < number; i++) {
            sum += i;
        }
        return sum;
    }
}
