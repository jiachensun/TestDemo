package com.example.teachingdemo.eventbus;

/**
 * @Author sjc
 * @Date 2020/6/30
 * Description：
 */
public class MyMessageEvent {

    public int code;
    public String data;

    public MyMessageEvent(int code, String data) {
        this.code = code;
        this.data = data;
    }
}
