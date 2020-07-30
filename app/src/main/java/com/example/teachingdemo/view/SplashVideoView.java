package com.example.teachingdemo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.VideoView;

/**
 * @Author sjc
 * @Date 2020/5/8.
 * GitHub：
 * Email：jiachen.sun@credit.com
 * Description：
 */
public class SplashVideoView extends VideoView {
    public SplashVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // 想要修改view大小，必须复写onMeasure方法
        int videoWidth = getDefaultSize(0, widthMeasureSpec);
        int videoHeight = getDefaultSize(0, heightMeasureSpec);
        setMeasuredDimension(videoWidth, videoHeight);
    }

}
