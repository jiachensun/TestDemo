package com.example.teachingdemo.jetpackmvp.presenter;

import android.util.Log;

import com.example.teachingdemo.jetpackmvp.base.JPBasePresenterImp;
import com.example.teachingdemo.jetpackmvp.model.IJPMvpModel;
import com.example.teachingdemo.jetpackmvp.model.JPMvpModelImp;
import com.example.teachingdemo.jetpackmvp.view.IJPMvpView;

/**
 * @Author sjc
 * @Date 2020/6/16
 */
public class JPMvpPresenterImp extends JPBasePresenterImp<IJPMvpView> implements IJPMvpPresenter {

    private IJPMvpModel mvpModel;

    public JPMvpPresenterImp() {

        mvpModel = new JPMvpModelImp();
    }

    @Override
    public void getData() {

        mvpModel.getData(data -> {

            Log.i("JPMvpPresenterImp", data);
            getView().showData(data);
        });
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }
}
