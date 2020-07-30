package com.example.teachingdemo.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;

import com.example.teachingdemo.R;
import com.example.teachingdemo.annotation.AptActivityLayout;
import com.example.teachingdemo.global.GlobalInstance;

import java.util.concurrent.ThreadFactory;

@AptActivityLayout(activityLayoutId = R.layout.activity_login)
public class LoginActivity extends BaseActivity {

    @Override
    protected void initData(Bundle bundle) {
        GlobalInstance.getInstance().saveContext(this);

//        Bitmap.createBitmap().compress()

        ThreadFactory aa = new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return null;
            }
        };
    }

    class Bbbb implements ThreadFactory {

        @Override
        public Thread newThread(Runnable r) {
            return null;
        }
    }
}
