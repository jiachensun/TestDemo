package com.example.teachingdemo.mvp.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.teachingdemo.mvp.IMvpView;
import com.example.teachingdemo.mvp.LifeCircleImpl;

/**
 * @Author sjc
 * @Date 2020/5/9.
 * GitHub：
 * Email：jiachen.sun@credit.com
 * Description：
 */
public abstract class BaseMvpActivity extends AppCompatActivity implements IMvpView {
    private LifeCircleImpl lifeCircleImpl;

    @Override
    public LifeCircleImpl getLifeCircleImpl() {
        if (lifeCircleImpl == null) {
            this.lifeCircleImpl = new LifeCircleImpl();
        }
        return lifeCircleImpl;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = this.getIntent();
        if (intent == null) {
            intent = new Intent();
        }
        getLifeCircleImpl();
        if (lifeCircleImpl != null) {
            lifeCircleImpl.onCreate(savedInstanceState,intent,null);
            lifeCircleImpl.onActivityCreated(savedInstanceState,intent,null);
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        getLifeCircleImpl();
        if (lifeCircleImpl != null) {
            lifeCircleImpl.onNewIntent(intent);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        getLifeCircleImpl();
        if (lifeCircleImpl != null) {
            lifeCircleImpl.onStart();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        getLifeCircleImpl();
        if (lifeCircleImpl != null) {
            lifeCircleImpl.onResume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        getLifeCircleImpl();
        if (lifeCircleImpl != null) {
            lifeCircleImpl.onPause();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        getLifeCircleImpl();
        if (lifeCircleImpl != null) {
            lifeCircleImpl.onStop();
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        getLifeCircleImpl();
        if (lifeCircleImpl != null) {
            lifeCircleImpl.onReStart();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getLifeCircleImpl();
        if (lifeCircleImpl != null) {
            lifeCircleImpl.onDestroy();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        getLifeCircleImpl();
        if (lifeCircleImpl != null) {
            lifeCircleImpl.onSaveInstanceState(outState);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        getLifeCircleImpl();
        if (lifeCircleImpl != null) {
            lifeCircleImpl.onActivityResult(requestCode,resultCode,data);
        }
    }
}
