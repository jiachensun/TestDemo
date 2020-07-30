package com.sjc.customview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * @Author sjc
 * @Date 2020/7/1
 * Description：
 */
public class MyDrawWidget1 extends View {

    private Bitmap originBitmap;
    private BitmapFactory.Options options;
    private Matrix mMatrix;
    private Rect mSrcRect;
    private Rect mDstRect;
    private int dstBottom;

    public MyDrawWidget1(Context context) {
        super(context);
        Log.i("MyDrawWidget", "MyDrawWidget(Context context)");
        init();
    }

    public MyDrawWidget1(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        Log.i("MyDrawWidget", "MyDrawWidget(Context context, @Nullable AttributeSet attrs)");
        init();
    }

    private void init() {
        originBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.androidhotfix);
        mMatrix = new Matrix();
        mSrcRect = new Rect(0, 0, 0, 0);
        mDstRect = new Rect(0, 0, 0, 0);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.i("MyDrawWidget", "onDraw = " + getWidth() + ", " + getHeight());
        // Bitmap
        float scale = getWidth() / (float) originBitmap.getWidth();
        mMatrix.preScale(scale, scale);
        float bitHeight = originBitmap.getHeight() * scale;
        mDstRect.right = getWidth();
        mDstRect.bottom = 200;
        canvas.drawBitmap(originBitmap, mSrcRect, mDstRect, null);
    }

    float lastY = 0;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            lastY = event.getY();
        } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
//            if (las)
            invalidate();
        }
        return true;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        return super.dispatchTouchEvent(event);
    }
}
