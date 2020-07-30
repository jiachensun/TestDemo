package com.example.teachingdemo.jetpackmvp.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.teachingdemo.jetpackmvp.view.JPMvpLifeCycleImp;

/**
 * @Author sjc
 * @Date 2020/6/16.
 * GitHub：
 * Email：jiachen.sun@credit.com
 * Description：
 */
public abstract class JPBaseActivity<T extends IJPBasePresenter> extends AppCompatActivity implements IJPBaseView {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 初始化presenter
        initPresenter();
        // 附加指定view
        getPresenter().attachView(this);
        // 为presenter注册监听者
        getLifecycle().addObserver(new JPMvpLifeCycleImp(getPresenter()));
    }

    protected abstract void initPresenter();

    protected abstract T getPresenter();
}
