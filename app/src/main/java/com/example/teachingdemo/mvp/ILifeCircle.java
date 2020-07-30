package com.example.teachingdemo.mvp;

import android.content.Intent;
import android.os.Bundle;


/**
 * @Author sjc
 * @Date 2020/5/9.
 * GitHub：
 * Email：jiachen.sun@credit.com
 * Description：
 */
public interface ILifeCircle {

    /**
     * Activity正在被创建
     */
    void onCreate(Bundle savedInstanceState, Intent intent, Bundle getArguments);

    /**
     * fragment的activity创建完成
     */
    void onActivityCreated(Bundle savedInstanceState, Intent intent, Bundle getArguments);

    /**
     * Activity正在被启动，还未到前台，不能交互
     */
    void onStart();

    /**
     * Activity已经可见并且已经来前台
     */
    void onResume();

    /**
     * Activity正在暂停
     */
    void onPause();

    /**
     * Activity即将停止
     */
    void onStop();

    /**
     * Activity正在重新启动
     */
    void onReStart();

    /**
     * Activity即将被销毁
     */
    void onDestroy();

    /**
     * fragemt销毁view
     */
    void onViewDestroy();

    /**
     * singletask启动方式
     */
    void onNewIntent(Intent intent);

    /**
     * 回到上一个activity后的回调
     */
    void onActivityResult(int requestCode, int resultCode, Intent data);

    /**
     * 保存当前activity信息
     */
    void onSaveInstanceState(Bundle bundle);

    /**
     * fragement最开始启动
     */
    void attachView(IMvpView iMvpView);
}
