package com.sjc.customview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * @Author sjc
 * @Date 2020/7/1
 * Description：
 */
public class MyDrawWidget extends View {

    private Paint mPaint;
    private Bitmap originBitmap;
    private Bitmap mBitmap;
    private Matrix mMatrix;

    public MyDrawWidget(Context context) {
        super(context);
        Log.i("MyDrawWidget", "MyDrawWidget(Context context)");
        init();
    }

    public MyDrawWidget(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        Log.i("MyDrawWidget", "MyDrawWidget(Context context, @Nullable AttributeSet attrs)");
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(2);

        originBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.a);
        mMatrix = new Matrix();
        mMatrix.preScale(0.2f, 0.2f);
        mBitmap = Bitmap.createBitmap(originBitmap, 0, 0, originBitmap.getWidth(), originBitmap.getHeight(), mMatrix, false);

        magicalBitmap();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 圆
        canvas.drawCircle(60, 50, 50, mPaint);
        // 矩形
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawRect(10, 120, 110, 170, mPaint);
        mPaint.setColor(Color.GREEN);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setStrokeWidth(5);
        canvas.drawRoundRect(10, 190, 110, 240, 10, 20, mPaint);
        mPaint.setColor(Color.YELLOW);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setStrokeWidth(5);
        canvas.drawRoundRect(10, 260, 110, 310, 20, 10, mPaint);
        // Bitmap
        canvas.drawBitmap(mBitmap, 10, 330, null);
    }

    // 会动的Bitmap
    private boolean isAdding = true;
    private float scale = 0.2f;
    private void magicalBitmap() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (isAdding) {
                        scale = scale + 0.001f;
                        if (scale >= 0.25f) {
                            isAdding = false;
                        }
                    } else {
                        scale = scale - 0.001f;
                        if (scale <= 0.2f) {
                            isAdding = true;
                        }
                    }
                    Matrix mMatrix1 = new Matrix();
                    mMatrix1.preScale(scale, scale);
                    Bitmap originBitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.a);
                    mBitmap = Bitmap.createBitmap(originBitmap1, 0, 0, originBitmap1.getWidth(), originBitmap1.getHeight(), mMatrix1, false);
                    postInvalidate();
                }
            }
        }).start();
    }

    @Override
    public void layout(int l, int t, int r, int b) {
        super.layout(l, t, r, b);
    }
}
