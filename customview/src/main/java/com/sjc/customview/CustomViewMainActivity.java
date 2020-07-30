package com.sjc.customview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.PictureInPictureParams;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ImageView;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class CustomViewMainActivity extends AppCompatActivity {

    private ImageView largeImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.loadlargebitmap);

//        largeImageView = findViewById(R.id.iv_show_large_image);

        childThread();
        testThreadPool();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    public void childThread() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("ExampleUnitTest" + Thread.currentThread().getName());
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("ExampleUnitTest" + Thread.currentThread().getName());
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                System.out.println("ExampleUnitTest" + Thread.currentThread().getName());
                                new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        System.out.println("ExampleUnitTest" + Thread.currentThread().getName());
                                    }
                                }, "--------------thread5").start();
                            }
                        }, "--------------thread4").start();
                    }
                }, "--------------thread2").start();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("ExampleUnitTest" + Thread.currentThread().getName());
                    }
                }, "--------------thread3").start();
            }
        }, "--------------thread1").start();
    }

    public void testThreadPool() {
        BlockingQueue<Runnable> blockingQueue = new LinkedBlockingDeque<>(3);
        NameTreadFactory nameTreadFactory = new NameTreadFactory();
        int corePoolSize = Runtime.getRuntime().availableProcessors();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 10, TimeUnit.SECONDS, blockingQueue, nameTreadFactory);
        // 存在10个任务
        for (int i = 0; i < 30; i ++) {
            MyTask myTask = new MyTask("task-" + i);
            threadPoolExecutor.execute(nameTreadFactory.newThread(myTask));
        }
    }

    static class NameTreadFactory implements ThreadFactory {

        private final AtomicInteger mThreadNum = new AtomicInteger(1);

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r, "my-thread-" + mThreadNum.getAndIncrement());
            Log.i("ThreadFactory", t.getName() + " has been created");
            return t;
        }
    }

    private static volatile Integer aaa = 0;
    static class MyTask implements Runnable {
        private String name;

        public MyTask(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            while (true) {
                synchronized (aaa) {
                    if (aaa >= 1000) {
                        break;
                    }
                    aaa++;
                    Log.i("ThreadPoolExecutor", Thread.currentThread().getName() + ", " + name + ", aaa = " + aaa);
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
