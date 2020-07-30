package com.example.teachingdemo.retrofixtest;

import android.app.Activity;
import android.util.Log;

import androidx.annotation.Nullable;

import java.lang.reflect.Method;
import java.sql.Time;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @Author sjc
 * @Date 2020/6/30
 * Description：
 */
public class MyRetrofit {

    /**
     * https://quan.suning.com/getSysTime.do
     *
     * 如果要使用retrofit转换器，需要引入对应的json解析转换器并在创建Retrofit的时候添加上对应的Converterfactory
     */
    public void getCurrentTime() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://quan.suning.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        IGetCurrentTime getCurrentTime = retrofit.create(IGetCurrentTime.class);
        Call<TimeBean> time = getCurrentTime.getTime();
        time.enqueue(new Callback<TimeBean>() {
            @Override
            public void onResponse(Call<TimeBean> call, Response<TimeBean> response) {
                TimeBean body = response.body();
                Log.i("MyRetrofit", body.getSysTime1() + ", " + body.getSysTime2());
            }

            @Override
            public void onFailure(Call<TimeBean> call, Throwable t) {
                Log.e("MyRetrofit", t.toString());
            }
        });
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        return super.equals(obj);
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
