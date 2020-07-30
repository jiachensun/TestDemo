package com.example.teachingdemo.jetpackmvp.base;

/**
 * @Author sjc
 * @Date 2020/6/16.
 * GitHub：
 * Email：jiachen.sun@credit.com
 * Description：
 */
public interface IJPBasePresenter extends IJPMvpLifeCycle {

    void attachView(IJPBaseView view);

    void detachView();
}
