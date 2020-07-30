package com.example.teachingdemo.activity;

import android.os.Bundle;
import android.os.Message;

import com.example.teachingdemo.ipc.interfaces.HandleMessageInterface;
import com.example.teachingdemo.mvp.IMvpView;
import com.example.teachingdemo.mvp.base.BaseMvpPresenter;

/**
 * @Author sjc
 * @Date 2020/5/9.
 * GitHub：
 * Email：jiachen.sun@credit.com
 * Description：
 */
public class MainPresenter extends BaseMvpPresenter implements HandleMessageInterface {

    /** 服务端返回的结果 */
    private static final String SERVER_RESULT_CODE = "SERVER_RESULT_CODE";
    private static final String SERVER_RESULT_DATA = "SERVER_RESULT_DATA";
    private static final String SERVER_RESULT_MESSAGE = "SERVER_RESULT_MESSAGE";

    private MainActivity mainActivity;

    public MainPresenter(IMvpView view) {
        super(view);
        mainActivity = (MainActivity) view;
    }

    @Override
    protected IMvpView getEmptyView() {
        return null;
    }


    @Override
    public void computeMessage(Message msg) {
        Bundle bundle = msg.getData();
        if (null == bundle) {
            mainActivity.showComputeResult("返回信息为空");
        } else {
            int resultCode = bundle.getInt(SERVER_RESULT_CODE);
            String message = bundle.getString(SERVER_RESULT_MESSAGE);
            if (resultCode == 0) {
                int data = bundle.getInt(SERVER_RESULT_DATA);
                mainActivity.showComputeResult(message + "，" + data);
            } else {
                mainActivity.showComputeResult(message);
            }
        }
    }
}
