package com.example.teachingdemo.room;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import com.example.teachingdemo.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class JetPackRoomActivity extends AppCompatActivity {
    private List<UserDatabase> infos = new ArrayList<>();
    private RoomAdapter roomAdapter;
    private Handler mHandler;
    private ThreadPoolExecutor threadPool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jet_pack_room);

        initRecyclerview();
        mHandler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message msg) {
                roomAdapter.notifyItemInserted(infos.size() - 1);
                return true;
            }
        });

        threadPool = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 10, TimeUnit.SECONDS, new LinkedBlockingDeque<>());

        FloatingActionButton floatingActionButton = findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserDatabase user = new UserDatabase();
                user.setUser_name("abc" + System.currentTimeMillis());
                boolean isMale = System.currentTimeMillis() % 2 == 0;
                user.setUser_sex(isMale ? "男" : "女");

                // 添加到recyclerview的数组内
                infos.add(user);

                // 异步插入
                RoomTask task = new RoomTask(JetPackRoomActivity.this, mHandler);
                task.insertUser(user);
                threadPool.execute(task);
            }
        });
    }

    private void initRecyclerview() {
        RecyclerView recyclerView = findViewById(R.id.room_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        roomAdapter = new RoomAdapter();
        roomAdapter.setInfos(infos);
        recyclerView.setAdapter(roomAdapter);
    }

    static class RoomTask implements Runnable {
        private UserDatabase userDatabase;
        private Context context;
        private Handler mHandler;
        private int state = 0;

        public RoomTask(Context context, Handler handler) {
            this.context = context;
            this.mHandler = handler;
        }

        public void insertUser(UserDatabase user) {
            this.userDatabase = user;
            this.state = 1;
        }

        @Override
        public void run() {
            switch (state) {
                case 1:
                    UserDatabaseDelegate.getUserDelegate(context.getApplicationContext()).getUserDao().insertUser(userDatabase);
                    mHandler.sendEmptyMessage(1);
                    break;
                default:
                    break;
            }
        }
    }
}