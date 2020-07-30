package com.example.teachingdemo.myaop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.teachingdemo.R;
import com.example.teachingdemo.myaop.myaspactj.IFirstAop;
import com.sjc.apt_annotation.MyAnnotation;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class TestAopActivity extends AppCompatActivity {

    private ICount newIcount;
    @MyAnnotation
    public TestClassName abc;
    @MyAnnotation
    public TestClassName1 def;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_aop);

//        beginProxy();
        newIcount = new ICountImp();
        // 初始化自己的APT，用于实例化对象
        new SJCObject_TestAopActivity().init(this);
    }

    private void beginProxy() {
        ICount iCount = new ICountImp();
        MyDynamicProxyHandler handler = new MyDynamicProxyHandler(iCount);
        newIcount = (ICount) Proxy.newProxyInstance(ICount.class.getClassLoader(), iCount.getClass().getInterfaces(), handler);
    }

    class MyDynamicProxyHandler implements InvocationHandler {

        private ICount iCount;

        MyDynamicProxyHandler(ICount i) {
            this.iCount = i;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("Proxy invoke");
            printMsg();
            // 有返回值的需要return  无返回值的return null
            return method.invoke(iCount, args);
//            return null;
        }
    }

    @IFirstAop
    public void onClickButton1(View view) {
        abc.succeed();
        def.succeed();
        Log.i("TestAopActivity", "onClickButton1");
        Toast.makeText(this, "getCount=" + newIcount.getCount(), Toast.LENGTH_SHORT).show();
    }

    @IFirstAop
    public void onClickButton2(View view) {
        Toast.makeText(this, "getNum=" + newIcount.getNum(), Toast.LENGTH_SHORT).show();
    }

    @IFirstAop
    public void onClickButton3(View view) {
        Toast.makeText(this, "getSize=" + newIcount.getSize(), Toast.LENGTH_SHORT).show();
    }

    static class ICountImp implements ICount {

        @Override
        public int getCount() {
            return 1;
        }

        @Override
        public int getNum() {
            return 2;
        }

        @Override
        public int getSize() {
            return 3;
        }
    }

    private void printMsg() {
        Toast.makeText(this, "printMsg begin", Toast.LENGTH_SHORT).show();
    }
}