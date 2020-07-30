package com.example.teachingdemo.design_mode.cor_mode;

/**
 * @Author sjc
 * @Date 2020/7/27
 * Description：
 */
public abstract class RequestHandler {

    public RequestHandler handler;

    public void setNext(RequestHandler h) {
        this.handler = h;
    }

    public RequestHandler getNext() {
        return handler;
    }

    protected abstract void handleRequest(String request);
}
