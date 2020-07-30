package com.example.teachingdemo.eventbus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.teachingdemo.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MyEventBus1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_event_bus);

        EventBus.getDefault().register(this);
    }

    @Subscribe()
    public void onMessageReceive(MyMessageEvent event) {
        // 收到EventBus消息
        if (event.code == 0) {
            Toast.makeText(this, "MyEventBus1Activity, " + event.data, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

//        ViewModel;
//        LiveData ;
//        MutableLiveData;
        EventBus.getDefault().unregister(this);
    }

    public void onClickJump2(View view) {

        startActivity(new Intent(this, MyEventBus3Activity.class));
    }

    public void onClickSend(View view) {
        EventBus.getDefault().post(new MyMessageEvent(1, "你成功了"));
    }
}