package com.example.teachingdemo.eventbus;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.teachingdemo.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class MyEventBus2Activity extends AppCompatActivity {

    private EditText etInputEventbus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_event_bus2);

        etInputEventbus = findViewById(R.id.et_input_eventbus);

        EventBus.getDefault().register(this);
    }

    public void onClickUpload(View view) {
        EventBus.getDefault().post(new MyMessageEvent(0, etInputEventbus.getText().toString().trim()));
    }

    @Subscribe
    public void onEventBusReceive(MyMessageEvent event) {

        if (event.code == 1) {
            Toast.makeText(this, "MyEventBus2Activity, " + event.data, Toast.LENGTH_SHORT);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}