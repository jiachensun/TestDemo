package com.example.teachingdemo.myaop.myaspactj;

import android.content.Context;
import android.util.Log;

import androidx.fragment.app.Fragment;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @Author sjc
 * @Date 2020/7/16
 * Description：
 */
@Aspect
public class IFirstAopImp {
    private static final String POINTCUT_ONMETHOD = "execution(@com.example.teachingdemo.myaop.myaspactj.IFirstAop * *(..))";

    /**
     * 找到切点
     * 只要是用@IFirstAop修饰的，都切入进来
     */
    @Pointcut(POINTCUT_ONMETHOD)
    public void pointActionMethod() {}

    // 从切点上扩展
    @Around("pointActionMethod()")
    public Object aroundClickButton(ProceedingJoinPoint joinPoint) throws Throwable {
        Context mContext = null;
        Object aThis = joinPoint.getThis();
        if (aThis == null) {
            throw new IllegalAccessException("JDK环境错误");
        }
        if (aThis instanceof Context) {
            mContext = (Context) aThis;
        } else if (aThis instanceof Fragment) {
            mContext = ((Fragment) aThis).getActivity();
        }
        if (mContext == null) {
            throw new IllegalAccessException("context is null or permission is null");
        }

        Object result = null;
        // 可以增加网络的判断或权限的判断 如果满足继续执行原来的代码

        Log.i("IFirstAopImp", "AspectJ ok go go go");

        // 继续执行原来的代码
        result = joinPoint.proceed();
        return result;
    }
}
