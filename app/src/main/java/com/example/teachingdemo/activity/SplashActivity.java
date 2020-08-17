package com.example.teachingdemo.activity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.teachingdemo.R;
import com.example.teachingdemo.annotation.AptActivityLayout;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author sjc
 */
@AptActivityLayout(activityLayoutId = R.layout.activity_splash)
public class SplashActivity extends BaseActivity {

    @BindView(R.id.vv_splash_movie)
    VideoView vvSplashMovie;
    @BindView(R.id.tv_count_down)
    TextView tvCountDown;

    private Handler mHandler;

    @Override
    protected void initData(Bundle bundle) {
        mHandler = new Handler();
        playVideo();
        startCountDown();
    }

    /**
     * 播放视频
     */
    private void playVideo() {
        vvSplashMovie.setVideoURI(Uri.parse(getResources().getString(R.string.string_android_resouce)
                + getPackageName() + File.separator + R.raw.splash_movie));
        vvSplashMovie.setOnPreparedListener(MediaPlayer::start);
        vvSplashMovie.setOnCompletionListener(MediaPlayer::start);
    }

    /**
     * 开始倒计时
     */
    private void startCountDown() {
        SplashPresenter splashPresenter = new SplashPresenter(this);
        splashPresenter.startCountDown();
    }

    /**
     * 设置倒计时view显示信息
     */
    public void setCountDownTextView(String text) {
        runOnUiThread(() -> {
            tvCountDown.setText(text);
        });
    }

    @OnClick(R.id.tv_count_down)
    public void onViewClicked() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    class MyThreaddd extends Thread {

        @Override
        public void run() {
            super.run();

            // 让出CPU的占用权
            yield();

            // 使线程休眠，不会释放锁
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // 是线程休眠，会释放锁，让其他线程执行。可通过notify唤醒
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
