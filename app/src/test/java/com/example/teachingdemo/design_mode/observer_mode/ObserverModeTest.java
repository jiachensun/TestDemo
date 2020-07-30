package com.example.teachingdemo.design_mode.observer_mode;

import org.junit.Test;

/**
 * @Author sjc
 * @Date 2020/7/27
 * Description：
 */
public class ObserverModeTest {
    @Test
    public void main() {
        MyObserveable observeable = new MyObserveable();
        observeable.subject(new IMyOberver() {
            @Override
            public void start() {
                System.out.println("我监测到了1");
            }
        });

        observeable.subject(new IMyOberver() {
            @Override
            public void start() {
                System.out.println("我监测到了2");
            }
        });

        int a = 0;
        while (true) {
            try {
                Thread.currentThread().sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            a ++;
            if (a % 2 == 0) {
                observeable.notifyAllObserver();
            }
            if (a == 10) {
                break;
            }
        }
    }
}
