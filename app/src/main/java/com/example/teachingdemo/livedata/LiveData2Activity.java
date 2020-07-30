package com.example.teachingdemo.livedata;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.LruCache;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.example.teachingdemo.R;
import com.example.teachingdemo.global.GlobalInstance;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;

import javax.net.ssl.SSLSocketFactory;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LiveData2Activity extends AppCompatActivity {

    @BindView(R.id.et_input_livedata)
    EditText etInputLivedata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_data2);
        ButterKnife.bind(this);

        GlobalInstance.getInstance().getMutableLiveData().observe(this, s -> {
            Toast.makeText(LiveData2Activity.this, s, Toast.LENGTH_SHORT).show();
        });


    }

    public void onClickUpload(View view) {
        GlobalInstance.getInstance().getMutableLiveData().setValue(etInputLivedata.getText().toString().trim());
    }
}