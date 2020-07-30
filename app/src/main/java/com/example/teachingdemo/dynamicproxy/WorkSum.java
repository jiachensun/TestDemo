package com.example.teachingdemo.dynamicproxy;

import android.util.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author sjc
 * @Date 2020/6/30
 * Description：
 */
public class WorkSum {

    // 普通方式
    public void sum(int number) {

        IWorker iWorker = new WorkerImp();
        int result = iWorker.working(number);

        Log.i("dynamicProxy", "result = " + result);
    }

    /**
     * 动态代理
     *
     * 通过jdk动态代理，proxy.newProxyInstance（该代理接口的类加载器），返回该代理接口已经实例化的对象
     * 初始化一个invocationHandler，将代理接口的实现实例对象传入，用于内部反射invoke调用
     * 调用该对象的方法即可
     */
    public void sumDynamicProxy(int number) {

        MyInvocationHandler myInvocationHandler = new MyInvocationHandler(new WorkerImp2());

        // 返回该代理接口已经实例化的对象
        IWorker iWorker  = (IWorker) Proxy.newProxyInstance(IWorker.class.getClassLoader(), new Class<?>[]{IWorker.class}, myInvocationHandler);
        int result = iWorker.working(number);
        Log.i("dynamicProxy", "result = " + result);
    }

    private static class MyInvocationHandler implements InvocationHandler {

        private IWorker iWor = null;

        public MyInvocationHandler(IWorker iWorker) {
            this.iWor = iWorker;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

            // 增加项
            long startTime = System.currentTimeMillis();

            // 反射invoke调用一下
            Object result = method.invoke(iWor, args);

            // 增加项
            long endTime = System.currentTimeMillis();
            Log.i("dynamicProxy", "time = " + (endTime - startTime));

            return result;
        }
    }
}
