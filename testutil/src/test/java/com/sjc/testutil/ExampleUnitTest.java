package com.sjc.testutil;

import android.util.Log;

import org.junit.Test;

import java.lang.reflect.Method;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testGc() {
        while (true) {
            new aaa();
        }
    }

    public class aaa {
        public byte[] aa = new byte[100 * 1024];
    }

    @Test
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
}