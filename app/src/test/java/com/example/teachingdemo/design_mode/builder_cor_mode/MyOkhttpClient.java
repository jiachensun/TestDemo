package com.example.teachingdemo.design_mode.builder_cor_mode;

/**
 * @Author sjc
 * @Date 2020/7/27
 * Description：
 */
public class MyOkhttpClient {
    private MyIntercepter firstIntercepter;
    private MyIntercepter currentIntercepter;
    private RequestIntercepter requestIntercepter;
    private ResponseIntercepter responseIntercepter;
    private CacheIntercepter cacheIntercepter;

    public RequestIntercepter getRequestIntercepter() {
        return requestIntercepter;
    }

    public void setRequestIntercepter(RequestIntercepter requestIntercepter) {
        this.requestIntercepter = requestIntercepter;
        setCurrentIntercepter(requestIntercepter);
    }

    public ResponseIntercepter getResponseIntercepter() {
        return responseIntercepter;
    }

    public void setResponseIntercepter(ResponseIntercepter responseIntercepter) {
        this.responseIntercepter = responseIntercepter;
        setCurrentIntercepter(responseIntercepter);
    }

    public CacheIntercepter getCacheIntercepter() {
        return cacheIntercepter;
    }

    public void setCacheIntercepter(CacheIntercepter cacheIntercepter) {
        this.cacheIntercepter = cacheIntercepter;
        setCurrentIntercepter(cacheIntercepter);
    }

    public void setCurrentIntercepter(MyIntercepter intercepter) {
        if (currentIntercepter != null) {
            currentIntercepter.setNext(intercepter);
        }
        this.currentIntercepter = intercepter;
        if (firstIntercepter == null) {
            firstIntercepter = intercepter;
        }
    }

    public static final class Builder {
        private MyOkhttpClient okhttpClient;

        Builder() {
            okhttpClient = new MyOkhttpClient();
        }

        Builder setRequestIntercepter(RequestIntercepter requestIntercepter) {
            okhttpClient.setRequestIntercepter(requestIntercepter);
            return this;
        }

        Builder setResponseIntercepter(ResponseIntercepter responseIntercepter) {
            okhttpClient.setResponseIntercepter(responseIntercepter);
            return this;
        }

        Builder setCacheIntercepter(CacheIntercepter cacheIntercepter) {
            okhttpClient.setCacheIntercepter(cacheIntercepter);
            return this;
        }

        MyOkhttpClient build() {
            return okhttpClient;
        }
    }

    public void start() {
        firstIntercepter.handleProcess();
    }
}
