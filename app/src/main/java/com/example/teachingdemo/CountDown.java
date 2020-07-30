package com.example.teachingdemo;

import android.os.Handler;
import android.os.HandlerThread;


/**
 * @Author sjc
 * @Date 2020/5/8.
 * GitHub：
 * Email：jiachen.sun@credit.com
 * Description：
 */
public class CountDown {
    private int count;
    private ICountDown splashCountDownListener;
    private Handler mHandler;
    private Runnable mRunnable;

    /**
     * 开始倒计时
     *
     * @param needCountDown 需要倒计时的时间
     * @param iCountDown 倒计时回调
     */
    public void start(int needCountDown, ICountDown iCountDown) {
        this.count = needCountDown;
        this.splashCountDownListener = iCountDown;
        beginCountDown();
    }

    /**
     * 结束倒计时
     */
    public void cancel() {
        if (null != mHandler) {
            mHandler.removeCallbacks(mRunnable);
        }
    }

    /**
     * 开始倒计时，使用HandlerThread
     */
    public void beginCountDown() {
        mRunnable = () -> {
            if (count > 0) {
                splashCountDownListener.countDownTime(count);
                count --;
                mHandler.postDelayed(mRunnable, 1000);
            } else {
                splashCountDownListener.finishCountDown();
                mHandler.removeCallbacks(mRunnable);
            }
        };

        HandlerThread handlerThread = new HandlerThread("SplashCountDownThread");
        handlerThread.start();
        mHandler = new Handler(handlerThread.getLooper());
        mHandler.post(mRunnable);
    }
}
