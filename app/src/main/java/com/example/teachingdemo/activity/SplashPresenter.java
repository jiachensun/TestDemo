package com.example.teachingdemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.teachingdemo.CountDown;
import com.example.teachingdemo.ICountDown;
import com.example.teachingdemo.R;
import com.example.teachingdemo.mvp.IMvpView;
import com.example.teachingdemo.mvp.LifeCircleImpl;
import com.example.teachingdemo.mvp.base.BaseMvpPresenter;

/**
 * @Author sjc
 * @Date 2020/5/9.
 * GitHub：
 * Email：jiachen.sun@credit.com
 * Description：
 */
public class SplashPresenter extends BaseMvpPresenter {
    private CountDown countDown;
    private SplashActivity splashActivity;

    public SplashPresenter(IMvpView view) {
        super(view);
        this.splashActivity = (SplashActivity) view;
    }

    /**
     * 开始倒计时
     */
    public void startCountDown() {
        countDown = new CountDown();
        countDown.start(5, new ICountDown() {
            @Override
            public void countDownTime(int count) {
                splashActivity.setCountDownTextView(String.format(splashActivity.getResources().getString(R.string.string_second_logo), count));
            }

            @Override
            public void finishCountDown() {
                splashActivity.setCountDownTextView(splashActivity.getResources().getString(R.string.string_skip));
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        countDown.cancel();
        Log.e("sjc - ", "onDestroy...");
    }

    @Override
    protected IMvpView getEmptyView() {
        return null;
    }
}
