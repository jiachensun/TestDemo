package com.sjc.mymvvm.viewmodel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Person;
import android.content.Intent;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.sjc.mymvvm.BubbleActivity;
import com.sjc.mymvvm.R;
import com.sjc.mymvvm.databinding.ActivityViewModelMainBinding;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;


public class ViewModelMainActivity extends AppCompatActivity implements IView {

    private EditText changeEditText;
    private TextView changeTextView;
    private TextView changeTextView1;

    @Inject
    public MyViewModel viewModelByDagger;

    private MyViewModel viewModelByMvp;

    private ActivityViewModelMainBinding dataBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        useMvp();
        useViewModel();

        // 使用Android 11的bubble气泡功能
        createBubble();
    }

    /**
     * mvp
     */
    private void useMvp() {
        setContentView(R.layout.activity_view_model_main);
        // Mvp使用
        changeTextView = findViewById(R.id.shwoChangeTextview);
        changeTextView1 = findViewById(R.id.shwoChangeTextview1);
        viewModelByMvp = ViewModelProviders.of(this).get(MyViewModel.class);
        viewModelByMvp.attachView(this);
        changeEditText = findViewById(R.id.changeEditText);
        changeEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                viewModelByMvp.getData(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    /**
     * mvvm+dagger2
     */
    private void useViewModel() {
        // databinding要放在edittext监听之前绑定 ViewModel dataBinding绑定
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_view_model_main);
        // Dagger2 绑定
        DaggerMainComponent.create().inject(this);
        dataBinding.setViewmodel(viewModelByDagger);

        viewModelByDagger.getName().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                dataBinding.setViewmodel(viewModelByDagger);
            }
        });
        viewModelByDagger.getName().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                dataBinding.setViewmodel(viewModelByDagger);
            }
        });
    }


    @Override
    public void updateUi(final String text) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                changeTextView.setText(text);
                changeTextView1.setText(text);
            }
        });
    }

    private void createBubble() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.P) {
            Intent bubbleIntent = new Intent(this, BubbleActivity.class);
            PendingIntent bubblePendingIntent = PendingIntent.getActivity(this, 0, bubbleIntent, 0);
            Notification.BubbleMetadata bubbleMetadata = null;
            bubbleMetadata = new Notification.BubbleMetadata.Builder().
                    setDesiredHeight(200).setIcon(Icon.createWithResource(this, R.drawable.bubble_icon)).setIntent(bubblePendingIntent).build();

            Person person = new Person.Builder().setBot(true).setName("BubbleBot").setImportant(true).build();
            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(new NotificationChannel("SJC-DEFAULT", "SJC", NotificationManager.IMPORTANCE_DEFAULT));
            Notification notification = new Notification.Builder(this, "SJC-DEFAULT").
                    setContentIntent(bubblePendingIntent).setSmallIcon(R.drawable.bubble_icon).addPerson(person).setBubbleMetadata(bubbleMetadata).build();
            notificationManager.notify(123, notification);
        }
    }
}