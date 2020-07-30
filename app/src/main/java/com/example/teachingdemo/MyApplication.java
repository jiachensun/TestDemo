package com.example.teachingdemo;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.multidex.MultiDex;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.teachingdemo.hotfix.HotFixUtil;
import com.sjc.arouter_lib.IArouter;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author sjc
 * @Date 2020/5/8.
 * GitHub：
 * Email：jiachen.sun@credit.com
 * Description：
 */
public class MyApplication extends Application {

    private static MyApplication instance = null;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

        doHotFix();

        // multiDex  原理与热修复原理相同，在程序启动时通过ClassLoader加载其他dex中的类在通过反射放入BaseDexClassLoader中的pathList的dexElements
        MultiDex.install(this);

    }

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;
        try {
            ((IArouter) (Class.forName("com.sjc.customview.IArouterCustomViewImp").getConstructor().newInstance())).initActivity();
            ((IArouter) (Class.forName("com.sjc.mymvvm.IArouterMyMvvmImp").getConstructor().newInstance())).initActivity();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 阿里arouter
        ARouter.init(this);
    }

    public static MyApplication getMyApplication() {
        return instance;
    }



    /**
     * 1、获取当前应用的PathClassLoader
     * 2、反射获取到DexPathList属性对象pathList
     * 3、反射修改pathList的dexElements
     *   3.1、把补丁包patch.jar转化为Element[]
     *   3.2、获得pathList的dexElements属性（old）
     *   3.3、patch+old合并，并反射赋值给pathList的dexElements
     */
    private void doHotFix() {

        try {
            // pathListField
            Field pathListField = HotFixUtil.getFieldFromObject(getClassLoader(), "pathList");
            Object pathList = pathListField.get(getClassLoader());

            // 得到已有的dexElements
            Field dexElementsField = HotFixUtil.getFieldFromObject(pathList, "dexElements");
            Object[] dexElements = (Object[]) dexElementsField.get(pathList);

            // 封装自己的element[] 使用makeDexElements 静态方法
            List<File> fixFileList = new ArrayList<>();
            fixFileList.add(new File(getCacheDir() + File.separator + "Hotfix.dex"));
            List<IOException> suppressedExceptions = new ArrayList<>();
            Method makeDexElementsMethod = HotFixUtil.getMethodFromObject(pathList, "makeDexElements", List.class, File.class, List.class, ClassLoader.class);
            Object[] fixDexElements = (Object[]) makeDexElementsMethod.invoke(null, fixFileList, null, suppressedExceptions, getClassLoader());

            // 合并新的elements与老的elements
            Object[] newElements = (Object[]) Array.newInstance(dexElements.getClass().getComponentType(), dexElements.length + fixDexElements.length);
            System.arraycopy(fixDexElements, 0, newElements, 0, fixDexElements.length);
            System.arraycopy(dexElements, 0, newElements, fixDexElements.length, dexElements.length);

            // 将合并后的elements赋值给pathList的dexElements
            dexElementsField.set(pathList, newElements);

        } catch (Exception e) {
            Log.e("MyApplication", e.toString());
        }

    }
}
