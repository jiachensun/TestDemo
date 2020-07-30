package com.sjc.myrefreshlayout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.sjc.myrefresh.MyRefreshLayout;

public class RefreshActivity extends AppCompatActivity {

    private MyRefreshLayout refreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refresh);

        refreshLayout = findViewById(R.id.refresh_view);
        refreshLayout.setDefaultManager();
    }
}
