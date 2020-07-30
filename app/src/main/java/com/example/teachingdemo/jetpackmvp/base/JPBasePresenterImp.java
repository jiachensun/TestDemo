package com.example.teachingdemo.jetpackmvp.base;

import android.util.Log;

import java.lang.ref.WeakReference;

/**
 * @Author sjc
 * @Date 2020/6/16
 * Description：
 */
public abstract class JPBasePresenterImp<T extends IJPBaseView> implements IJPBasePresenter, IJPMvpLifeCycle {

    private WeakReference<T> weakReference;

    @Override
    public void attachView(IJPBaseView view) {

        weakReference = new WeakReference(view);
    }

    @Override
    public void detachView() {

        if (null != weakReference) {
            weakReference.clear();
            weakReference = null;
        }
    }

    protected T getView() {

        T view = weakReference != null ? (T) weakReference.get() : null;
        return view;
    }

    @Override
    public void onDestroy() {

        detachView();
        Log.i("JPBasePresenterImp", "onDestory");
    }
}
