package com.example.teachingdemo.jetpackmvp.view;

import android.util.Log;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

import com.example.teachingdemo.jetpackmvp.base.IJPMvpLifeCycle;

/**
 * @Author sjc
 * @Date 2020/6/16
 * Description：
 */
public class JPMvpLifeCycleImp implements LifecycleObserver {

    private final String TAG = "JPMvpLifeCycleImp";
    private IJPMvpLifeCycle mvpLifeCycle;

    public JPMvpLifeCycleImp(IJPMvpLifeCycle lifeCycle) {

        this.mvpLifeCycle = lifeCycle;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onCreate() {
        Log.i(TAG, "onCreate: ");
        mvpLifeCycle.onCreate();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onStart() {
        Log.i(TAG, "onStart: ");
        mvpLifeCycle.onStart();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume() {
        Log.i(TAG, "onResume: ");
        mvpLifeCycle.onResume();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPause() {
        Log.i(TAG, "onPause: ");
        mvpLifeCycle.onPause();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onStop() {
        Log.i(TAG, "onStop: ");
        mvpLifeCycle.onStop();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy() {
        Log.i(TAG, "onDestroy: ");
        mvpLifeCycle.onDestroy();
    }
}
