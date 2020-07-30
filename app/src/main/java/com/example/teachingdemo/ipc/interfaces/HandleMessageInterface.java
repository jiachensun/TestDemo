package com.example.teachingdemo.ipc.interfaces;

import android.os.Message;

/**
 * @Author sjc
 * @Date 2020/5/16.
 * GitHub：
 * Email：jiachen.sun@credit.com
 * Description：
 */
public interface HandleMessageInterface {

    /**
     * 计算任务
     * @param msg 客户端message
     */
    void computeMessage(Message msg);
}
