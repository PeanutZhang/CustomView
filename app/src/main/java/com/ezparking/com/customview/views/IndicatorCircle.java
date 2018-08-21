package com.ezparking.com.customview.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zyh
 */

public class IndicatorCircle extends View {

    private int mRoundCount = 5;
    private int mWidth,mHeight;
    private Paint mPaint,mStrokePaint;
    private Path mPath;
    private float[] circleCenters;

    private int mRadius = 20;


    public IndicatorCircle(Context context) {
        this(context,null);
    }

    public IndicatorCircle(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {

        mPaint = new Paint();
        mStrokePaint = new Paint();
        mStrokePaint.setStyle(Paint.Style.STROKE);
        mStrokePaint.setColor(Color.parseColor("#ff00ff"));
        mStrokePaint.setAntiAlias(true);
        mStrokePaint.setStrokeWidth(5);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
       mWidth = w;
       mHeight = h;

       circleCenters = new float[mRoundCount];
        for (int i = 0; i < mRoundCount; i++) {
            circleCenters[i] = mWidth/(mRoundCount+1)*(i+1);
        }

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(0,mHeight/2);

        for (int i = 0; i < mRoundCount; i++) {
            canvas.drawCircle(circleCenters[i],0,mRadius,mStrokePaint);
        }

    }
}
