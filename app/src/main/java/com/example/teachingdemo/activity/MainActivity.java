package com.example.teachingdemo.activity;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.teachingdemo.R;
import com.example.teachingdemo.annotation.AptActivityLayout;
import com.example.teachingdemo.datastructure.tree.TreeNodeTest;
import com.example.teachingdemo.dynamicproxy.WorkSum;
import com.example.teachingdemo.eventbus.MyEventBus1Activity;
import com.example.teachingdemo.hotfix.HotfixActivity;
import com.example.teachingdemo.ipc.handler.MyHandler;
import com.example.teachingdemo.jetpackmvp.view.JPMvpActivity;
import com.example.teachingdemo.livedata.LiveData1Activity;
import com.example.teachingdemo.myaop.TestClassName1;
import com.example.teachingdemo.recyclerview.RecelviewActivity;
import com.example.teachingdemo.retrofixtest.MyRetrofit;
import com.sjc.apt_annotation.MyAnnotation;
import com.sjc.arouter_lib.Arouter;
import com.sjc.arouter_lib.ArouterConstant;
import com.sjc.backgroundservice.IMyAidlInterface;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author bairong
 */
@AptActivityLayout(activityLayoutId = R.layout.activity_main)
public class MainActivity extends BaseActivity {

    private static final String SERVICE_ACTION = "com.sjc.backgroundservice.compute.service";
    private static final String SERVICE_ACTION_AIDL = "com.sjc.backgroundservice.average.service";

    private static final String SERVICE_BROADCAST_ACTION = "com.sjc.backgroundservice.broadcast.receiver";
    private static final String SERVICE_PACKAGE_NAME = "com.sjc.backgroundservice";
    private static final int COMPUTE_MESSAGE_WHAT = 1000;

    /**
     * 客户端定义的，需要返回的what
     */
    private static final String SERVER_COMPUTE_RESULT_WHAT = "SERVER_COMPUTE_RESULT_WHAT";
    /**
     * 客户端传入的参数
     */
    private static final String CLIENT_COMPUTE_PARAM_A = "CLIENT_COMPUTE_PARAM_A";
    private static final String CLIENT_COMPUTE_PARAM_B = "CLIENT_COMPUTE_PARAM_B";

    @BindView(R.id.tv_compute_result)
    TextView tvComputeResult;
    @BindView(R.id.main_recyclerview)
    RecyclerView mainRecyclerview;

    private MainPresenter mainPresenter;
    private Messenger sendMessenger;
    private Messenger replyMessenger;
    private int paramA = 0;
    private int paramB = 0;
    private boolean isServerConnected;
    private ArrayList<String> buttonNames = new ArrayList<>(Arrays.asList(
            "连接服务", "计算", "错误计算", "广播计算", "内存泄露", "计算AIDL", "recycler"
            , "hotfix", "TreeMap", "jpmvp", "静态代理", "动态代理", "Retrofit", "EventBus"
            , "LiveData", "Arouter自定义View", "ArouterMVVM"));

    @Override
    protected void initData(Bundle bundle) {
        mainPresenter = new MainPresenter(this);

        MainAdapter adapter = new MainAdapter(this::onClick);
        adapter.setData(buttonNames);
        mainRecyclerview.setLayoutManager(new GridLayoutManager(this, 3));
        mainRecyclerview.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mainRecyclerview.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL));
        mainRecyclerview.setAdapter(adapter);
    }

    /**
     * 点击计算
     */
    private void onClickCompute() {
        Log.i("MainActivity", "onClickCompute");
        if (!isServerConnected) {
            showComputeResult("服务连接失败");
            return;
        }
        paramA = 0;
        paramB = 100;
        sendMessageToComputeService();
    }

    /**
     * 点击计算，错误
     */
    private void onClickComputeError() {
        Log.i("MainActivity", "onClickComputeError");
        if (!isServerConnected) {
            showComputeResult("服务连接失败");
            return;
        }
        paramA = 0;
        paramB = 0;
        sendMessageToComputeService();

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                ApplistMatch.matchApplist(MainActivity.this);
//            }
//        }).start();
    }

    /**
     * bind 服务
     */
    private void bindComputeService() {
        Intent bindIntent = new Intent();
        bindIntent.setAction(SERVICE_ACTION);
        bindIntent.setPackage(SERVICE_PACKAGE_NAME);
        ComputeServiceConnection computeServiceConnection = new ComputeServiceConnection();
        bindService(bindIntent, computeServiceConnection, Context.BIND_AUTO_CREATE);
    }

    /**
     * 展示计算结果
     */
    @SuppressLint("SetTextI18n")
    public void showComputeResult(String result) {
        runOnUiThread(() -> {
            tvComputeResult.setText(tvComputeResult.getText().toString() + result + "\n");
        });
    }

    /**
     * 向服务端发送数据
     */
    private void sendMessageToComputeService() {
        replyMessenger = new Messenger(new MyHandler(mainPresenter));

        Message message = Message.obtain();
        message.what = COMPUTE_MESSAGE_WHAT;
        Bundle bundle = new Bundle();
        bundle.putInt(CLIENT_COMPUTE_PARAM_A, paramA);
        bundle.putInt(CLIENT_COMPUTE_PARAM_B, paramB);
        bundle.putInt(SERVER_COMPUTE_RESULT_WHAT, 2000);
        message.setData(bundle);
        message.replyTo = replyMessenger;

        try {
            sendMessenger.send(message);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private void onClickComputeByBroadcast() {
        Intent intent = new Intent(SERVICE_BROADCAST_ACTION);
        intent.setComponent(new ComponentName(SERVICE_PACKAGE_NAME, SERVICE_PACKAGE_NAME + ".receiver.MyBoastcastReceiver"));
        intent.putExtra(CLIENT_COMPUTE_PARAM_A, 1);
        intent.putExtra(CLIENT_COMPUTE_PARAM_B, 100);
        sendBroadcast(intent);
    }

    /**
     * 计算平均数 通过AIDL方式
     */
    private void onClickAverageByAidl() {
        Intent intent = new Intent(SERVICE_ACTION_AIDL);
        intent.setPackage(SERVICE_PACKAGE_NAME);
        bindService(intent, new AverageServiceConnection(), Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    private void onClick(String name) {

        if (TextUtils.equals(name, buttonNames.get(0))) {
            // 连接服务
            bindComputeService();
        } else if (TextUtils.equals(name, buttonNames.get(1))) {
            // 计算
            onClickCompute();
        } else if (TextUtils.equals(name, buttonNames.get(2))) {
            // 错误计算
            onClickComputeError();
        } else if (TextUtils.equals(name, buttonNames.get(3))) {
            // 广播计算
            onClickComputeByBroadcast();
        } else if (TextUtils.equals(name, buttonNames.get(4))) {
            // 内存泄露
            startActivity(new Intent(this, LoginActivity.class));
        } else if (TextUtils.equals(name, buttonNames.get(5))) {
            // 计算AIDL
            onClickAverageByAidl();
        } else if (TextUtils.equals(name, buttonNames.get(6))) {
            // recycler
            startActivity(new Intent(this, RecelviewActivity.class));
        } else if (TextUtils.equals(name, buttonNames.get(7))) {
            // hotfix
            startActivity(new Intent(this, HotfixActivity.class));
        } else if (TextUtils.equals(name, buttonNames.get(8))) {
            // TreeMap
            TreeNodeTest.getTreeMap();
        } else if (TextUtils.equals(name, buttonNames.get(9))) {
            // jpmvp
            startActivity(new Intent(this, JPMvpActivity.class));
        } else if (TextUtils.equals(name, buttonNames.get(10))) {
            // 静态代理
            WorkSum workSum = new WorkSum();
            workSum.sum(10000);
        } else if (TextUtils.equals(name, buttonNames.get(11))) {
            // 动态代理
            WorkSum workSum = new WorkSum();
            workSum.sumDynamicProxy(10000);
        } else if (TextUtils.equals(name, buttonNames.get(12))) {
            // Retrofit
            MyRetrofit retrofit = new MyRetrofit();
            retrofit.getCurrentTime();
        } else if (TextUtils.equals(name, buttonNames.get(13))) {
            // EventBus
            startActivity(new Intent(this, MyEventBus1Activity.class));
        } else if (TextUtils.equals(name, buttonNames.get(14))) {
            // LiveData
            startActivity(new Intent(this, LiveData1Activity.class));
        } else if (TextUtils.equals(name, buttonNames.get(15))) {
            // Arouter 自定义View
            startActivity(new Intent(this, Arouter.getInstance().getActivity(ArouterConstant.CUSTOM_PATH)));
        } else if (TextUtils.equals(name, buttonNames.get(16))) {
            // Arouter MVVM
            startActivity(new Intent(this, Arouter.getInstance().getActivity(ArouterConstant.MVVM_PATH)));
        }
    }

    class ComputeServiceConnection implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.i("MainActivity", "onServiceConnected");
            showComputeResult("服务连接成功");
            isServerConnected = true;
            //连接成功
            sendMessenger = new Messenger(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.i("MainActivity", "onServiceDisconnected");
            showComputeResult("关闭服务连接");
            isServerConnected = false;
        }

        @Override
        public void onBindingDied(ComponentName name) {
            Log.i("MainActivity", "onBindingDied");
            showComputeResult("服务连接关闭");
            isServerConnected = false;
        }
    }

    class AverageServiceConnection implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            // 连接成功
            try {
                String numbers = "1,3,4,5,6,4,3,45,6,7,4,3,9,6,43,23";
                showComputeResult(numbers);
                float result = IMyAidlInterface.Stub.asInterface(service).getAverage(numbers);
                showComputeResult(String.valueOf(result));
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }
}
