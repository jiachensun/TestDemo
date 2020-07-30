package com.example.teachingdemo.global;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

/**
 * @Author sjc
 * @Date 2020/5/20.
 * GitHub：
 * Email：jiachen.sun@credit.com
 * Description：
 */
public class GlobalInstance {

    private volatile static GlobalInstance instance = null;

    public static GlobalInstance getInstance() {
        if (instance == null) {
            synchronized (GlobalInstance.class) {
                if (instance == null) {
                    instance = new GlobalInstance();
                }
            }
        }
        return instance;
    }

    private static Context mContext;

    public void saveContext(Context context) {
        this.mContext = context;
    }

    // livedata
    private MutableLiveData<String> mutableLiveData;
    public MutableLiveData<String> getMutableLiveData() {
        if (null == mutableLiveData) {
            mutableLiveData = new MutableLiveData<>();
        }
        return mutableLiveData;
    }
    public void setMutableLiveData(MutableLiveData<String> mutableLiveData) {
        this.mutableLiveData = mutableLiveData;
    }
}
