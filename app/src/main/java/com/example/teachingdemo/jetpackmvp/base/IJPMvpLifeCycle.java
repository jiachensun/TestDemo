package com.example.teachingdemo.jetpackmvp.base;

/**
 * @Author sjc
 * @Date 2020/6/16
 * Description：
 */
public interface IJPMvpLifeCycle {

    void onCreate();

    void onStart();

    void onResume();

    void onPause();

    void onStop();

    void onDestroy();
}
