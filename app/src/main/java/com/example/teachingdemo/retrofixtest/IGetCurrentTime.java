package com.example.teachingdemo.retrofixtest;

import java.util.Observable;

import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;

/**
 * @Author sjc
 * @Date 2020/6/30
 * Description：
 */
public interface IGetCurrentTime {

    @GET("getSysTime.do")
    Call<TimeBean> getTime();
}
