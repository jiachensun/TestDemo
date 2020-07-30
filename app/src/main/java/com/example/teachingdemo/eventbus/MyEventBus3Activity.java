package com.example.teachingdemo.eventbus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.example.teachingdemo.R;

import java.lang.ref.WeakReference;

public class MyEventBus3Activity extends AppCompatActivity {

    private Handler mHandler;
    private MyThread myThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_event_bus3);

        mHandler = new MyHandler(this);
        doInBackground();
    }

    private void doInBackground() {
        myThread = new MyThread(mHandler);
        myThread.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        myThread.stopThread();
        myThread = null;
        mHandler = null;
    }

    private static class MyThread extends Thread {

        private boolean isAction = true;
        private Handler myHandler;

        MyThread(Handler handler) {
            this.myHandler = handler;
        }

        @Override
        public void run() {
            super.run();
            if (isAction) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Message message = Message.obtain();
                message.what = 1;
                if (null != myHandler) {
                    myHandler.sendMessage(message);
                }
            }
        }

        public void stopThread() {
            this.isAction = false;
            myHandler = null;
        }
    }

    private static class MyHandler extends Handler {

        private WeakReference<Context> weakReference;

        MyHandler(Context context) {
            this.weakReference = new WeakReference<>(context);
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                Toast.makeText(weakReference.get(), "hahaha", Toast.LENGTH_SHORT).show();
            }
        }
    }
}