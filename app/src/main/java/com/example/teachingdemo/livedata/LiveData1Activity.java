package com.example.teachingdemo.livedata;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.Observer;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.lifecycle.ViewModelStore;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.teachingdemo.R;
import com.example.teachingdemo.global.GlobalInstance;

public class LiveData1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_data1);

        GlobalInstance.getInstance().getMutableLiveData().observe(this, s -> {
            Toast.makeText(LiveData1Activity.this, s, Toast.LENGTH_SHORT).show();
        });
    }

    public void onClickJump2(View view) {
        startActivity(new Intent(this, LiveData2Activity.class));
    }

    public void onClickSend(View view) {
        GlobalInstance.getInstance().getMutableLiveData().setValue("你成功了");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        GlobalInstance.getInstance().getMutableLiveData().removeObservers(this);
    }
}