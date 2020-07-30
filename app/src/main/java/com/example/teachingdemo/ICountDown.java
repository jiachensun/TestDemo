package com.example.teachingdemo;

/**
 * @Author sjc
 * @Date 2020/5/8.
 * GitHub：
 * Email：jiachen.sun@credit.com
 * Description：
 */
public interface ICountDown {
    /**
     * 倒计时实时时间回调
     *
     * @param count 实时时间
     */
    void countDownTime(int count);

    /**
     * 倒计时结束
     */
    void finishCountDown();
}
