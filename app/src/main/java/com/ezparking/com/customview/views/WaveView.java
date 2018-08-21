package com.ezparking.com.customview.views;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;


/**
 * Created by zyh
 */

public class WaveView extends View {

    private int mWaveLength = 500;
    private int mWaveCount;
    private int mOffset;
    private Path mPath;
    private Paint mPaint;
    private int mCenterX,mCenterY;
    private boolean isStart;
    private ValueAnimator mAnimator;

    public WaveView(Context context) {
        this(context,null);

    }

    public WaveView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {

        mPath = new Path();
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setColor(Color.parseColor("#59c3e2"));
        mPaint.setAntiAlias(true);

        mAnimator = ValueAnimator.ofInt(0,mWaveLength);
        mAnimator.setInterpolator(new LinearInterpolator());
        mAnimator.setDuration(1000);
        mAnimator.setRepeatCount(ValueAnimator.INFINITE);
        mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mOffset = (int) animation.getAnimatedValue();
                postInvalidate();
            }
        });

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
       mCenterX = w/2;
       mCenterY = h/2;
       mWaveCount = (int) Math.round(2* mCenterX/mWaveLength+1.5);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPath.reset();
        mPath.moveTo(-mWaveLength+mOffset,mCenterY);

        for (int i = 0; i < mWaveCount; i++) {
            mPath.quadTo((-mWaveLength* 3/4) + (i * mWaveLength)+mOffset,mCenterY+60,(-mWaveLength/2)+(i * mWaveLength )+mOffset,mCenterY);
            mPath.quadTo(-mWaveLength/4+(i * mWaveLength)+mOffset,mCenterY-60,i * mWaveLength+mOffset,mCenterY);
        }
        mPath.lineTo(2*mCenterX,2*mCenterY);
        mPath.lineTo(0,2*mCenterY);
        mPath.close();
        canvas.drawPath(mPath,mPaint);
    }

    public void startWave(){

        if(!isStart){
            mAnimator.start();
        }else {
            mAnimator.pause();
        }
        isStart = !isStart;
    }

}
