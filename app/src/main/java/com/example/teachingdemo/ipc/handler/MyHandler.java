package com.example.teachingdemo.ipc.handler;

import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;

import com.example.teachingdemo.ipc.interfaces.HandleMessageInterface;


/**
 * @Author sjc
 * @Date 2020/5/16.
 * GitHub：
 * Email：jiachen.sun@credit.com
 * Description：
 */
public class MyHandler extends Handler {

    private static final int SERVER_COMPUTE_MESSAGE_WHAT = 2000;
    private HandleMessageInterface myCallback;

    public MyHandler(HandleMessageInterface callback) {
        this.myCallback = callback;
    }

    @Override
    public void handleMessage(@NonNull Message msg) {
        if (null == myCallback) {
            return;
        }
        switch (msg.what) {
            case SERVER_COMPUTE_MESSAGE_WHAT:
                myCallback.computeMessage(msg);
                break;
            default:
                break;
        }

        super.handleMessage(msg);
    }
}
