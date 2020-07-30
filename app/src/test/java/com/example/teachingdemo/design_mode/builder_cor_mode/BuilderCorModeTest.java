package com.example.teachingdemo.design_mode.builder_cor_mode;

import org.junit.Test;

/**
 * @Author sjc
 * @Date 2020/7/27
 * Description：
 */
public class BuilderCorModeTest {
    @Test
    public void main() {
        MyOkhttpClient myOkhttpClient = new MyOkhttpClient.Builder().setCacheIntercepter(new CacheIntercepter())
                .setResponseIntercepter(new ResponseIntercepter()).setRequestIntercepter(new RequestIntercepter()).build();
        myOkhttpClient.start();
    }
}
