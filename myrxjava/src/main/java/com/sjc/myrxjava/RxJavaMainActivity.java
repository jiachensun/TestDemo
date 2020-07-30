package com.sjc.myrxjava;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageInstaller;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RxJavaMainActivity extends AppCompatActivity {

    private TextView showTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java_main);

        showTextView = findViewById(R.id.showTextView);

        // 自定义subcribe
//        myObservableSub = new MyObservableSub();
//        Observable<String> observable = Observable.create(myObservableSub).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread());
//        observable.subscribe(observer);

        // 间隔类型
//        Observable<Long> interval = Observable.interval(0, 1, TimeUnit.SECONDS).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread());
        // just类型
//        Observable<String> stringObservable = Observable.just("1,2,3,4,5,6").subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread());
        // range类型
        Observable<Integer> integerObservable = Observable.range(4, 100).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread());
        integerObservable.subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Integer integer) {
                Log.i("RxJavaTest", "aaaaaaaaaaaaaa" + integer);
                showTextView.setText(showTextView.getText().toString() + integer + "\n");
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

//        ActivityManager;
//        WindowManager;
//        PackageManager packageManager = getPackageManager();
//        PackageInstaller packageInstaller = getPackageManager().getPackageInstaller();
//        Looper
//        packageManager.install
    }

    private MyObservableSub myObservableSub;
    private int i = 0;

    static class MyObservableSub implements ObservableOnSubscribe<String> {

        private ObservableEmitter<String> emitter;

        @Override
        public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Throwable {
            this.emitter = emitter;
        }

        public void setValue(String value) {
            emitter.onNext(value);
        }
    }
}
