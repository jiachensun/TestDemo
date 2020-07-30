package com.example.teachingdemo.mvp.presenter;

import com.example.teachingdemo.mvp.ILifeCircle;
import com.example.teachingdemo.mvp.IMvpView;
import com.example.teachingdemo.mvp.LifeCircleImpl;

import java.lang.ref.WeakReference;

/**
 * @Author sjc
 * @Date 2020/5/9.
 * GitHub：
 * Email：jiachen.sun@credit.com
 * Description：
 */
public abstract class BaseLifeCircleMvpPresenter<T extends IMvpView> implements ILifeCircle {
    private WeakReference<T> weakReference;

    protected BaseLifeCircleMvpPresenter() {
        super();
    }

    public BaseLifeCircleMvpPresenter(IMvpView iMvpView) {
        super();
        attachView(iMvpView);
        LifeCircleImpl lifeCircleImpl = iMvpView.getLifeCircleImpl();
        lifeCircleImpl.savePresenter(this);
    }

    @Override
    public void attachView(IMvpView iMvpView) {
        if (weakReference == null) {
            weakReference = new WeakReference(iMvpView);
        } else {
            T view = (T) weakReference.get();
            if (view != iMvpView) {
                weakReference = new WeakReference(iMvpView);
            }
        }
    }

    @Override
    public void onDestroy() {
        weakReference = null;
    }

    protected T getView() {
        T view = weakReference != null ? (T) weakReference.get() : null;
        if (view == null) {
            return getEmptyView();
        }
        return view;
    }

    protected abstract T getEmptyView();
}
