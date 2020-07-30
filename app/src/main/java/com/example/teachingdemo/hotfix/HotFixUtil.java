package com.example.teachingdemo.hotfix;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

/**
 * @Author sjc
 * @Date 2020/6/9.
 * GitHub：
 * Email：jiachen.sun@credit.com
 * Description：
 */
public class HotFixUtil {

    /**
     * 将assets目录下的dex文件拷贝到本地私有目录下
     */
    public static boolean copyDexToPrivateDir(Context context, String dexName) {

        File originFile = null;
        try {
            InputStream inputStream = context.getAssets().open(dexName);
            File dexOutPutDir = context.getCacheDir();
            originFile = new File(dexOutPutDir, dexName);
            FileOutputStream fileOutputStream = new FileOutputStream(originFile);
            byte[] bytes = new byte[1024];
            int len = 0;
            while ((len = inputStream.read(bytes)) != -1) {
                fileOutputStream.write(bytes, 0, len);
            }
            fileOutputStream.close();
            inputStream.close();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * 获取dex类加载器
     *
     * @param context 当前上下文
     * @param dexName 需要加载的dex
     * @return 加载指定的dex的类加载器
     */
    public static DexClassLoader getDexClassLoader(Context context, String dexName) {

        // dex输出目录
        File dexOutPutDir = context.getDir("dex", Context.MODE_PRIVATE);
        // dex目录
        String dexPath = context.getCacheDir().getAbsolutePath() + File.separator + dexName;

        return new DexClassLoader(dexPath, dexOutPutDir.getAbsolutePath(), null, context.getClassLoader());
    }

    /**
     * 从指定对象中，获得字段
     *
     * @param object 对象
     * @param fieldName 字段名
     * @return 字段
     */
    public static Field getFieldFromObject(Object object, String fieldName) {

        if (null == object || TextUtils.isEmpty(fieldName)) {
            return null;
        }

        Class<?> objectClass = object.getClass();

        while (objectClass != Object.class) {

            try {
                if (null != objectClass) {
                    Field field = objectClass.getDeclaredField(fieldName);
                    field.setAccessible(true);
                    return field;
                }
            } catch (NoSuchFieldException e) {
                //
            }
            if (null != objectClass) {
                objectClass = objectClass.getSuperclass();
            }
        }
        return null;
    }

    /**
     * 从指定对象中，获得方法
     *
     * @param object 对象
     * @param methodName 方法名
     * @return 方法
     */
    public static Method getMethodFromObject(Object object, String methodName, Class<?>... classes) {

        if (null == object || TextUtils.isEmpty(methodName)) {
            return null;
        }

        Class<?> objectClass = object.getClass();

        while (objectClass != Object.class) {

            try {
                if (null != objectClass) {
                    Method method = objectClass.getDeclaredMethod(methodName, classes);
                    method.setAccessible(true);
                    return method;
                }
            } catch (NoSuchMethodException e) {
                //
            }
            if (null != objectClass) {
                objectClass = objectClass.getSuperclass();
            }
        }
        return null;
    }
}
