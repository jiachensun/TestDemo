package com.example.teachingdemo.jetpackmvp.model;

/**
 * @Author sjc
 * @Date 2020/6/16.
 * GitHub：
 * Email：jiachen.sun@credit.com
 * Description：
 */
public class JPMvpModelImp implements IJPMvpModel {

    @Override
    public void getData(IMvpDataCallback dataCallback) {

        if (null != dataCallback) {
            dataCallback.setData("我是卖报的小行家");
        }
    }
}
