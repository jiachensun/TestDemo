package com.example.teachingdemo.design_mode.cor_mode;

/**
 * @Author sjc
 * @Date 2020/7/27
 * Description：
 */
public class HandleTwo extends RequestHandler {
    @Override
    protected void handleRequest(String request) {
        if ("2".equals(request)) {
            System.out.println("我是第二个处理者，我已经处理完了");
        }
        if (getNext() != null) {
            getNext().handleRequest("3");
        } else {
            System.out.println("全部处理完成");
        }
    }
}
