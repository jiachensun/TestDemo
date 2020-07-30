package com.example.teachingdemo.jetpackmvp.view;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;

import com.example.teachingdemo.R;
import com.example.teachingdemo.jetpackmvp.base.JPBaseActivity;
import com.example.teachingdemo.jetpackmvp.base.JPBasePresenterImp;
import com.example.teachingdemo.jetpackmvp.presenter.IJPMvpPresenter;
import com.example.teachingdemo.jetpackmvp.presenter.JPMvpPresenterImp;

public class JPMvpActivity extends JPBaseActivity<IJPMvpPresenter> implements IJPMvpView {

    private IJPMvpPresenter presenter;
    private TextView showDataTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_j_p_mvp);

        showDataTextView = findViewById(R.id.tv_show_data);

        presenter.getData();
    }

    @Override
    public void showData(String data) {

        runOnUiThread(() -> showDataTextView.setText(data));
    }

    @Override
    public void showError(String error) {

    }

    @Override
    protected void initPresenter() {

        this.presenter = new JPMvpPresenterImp();
    }

    @Override
    public IJPMvpPresenter getPresenter() {
        return presenter;
    }
}