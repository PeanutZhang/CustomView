package com.ezparking.com.customview.views;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.view.animation.LinearInterpolator;

/**
 * Created by zyh on 2018/8/30.
 */

public class WaveView2 extends View{

    private int mWaveLength = 400;
    private Paint mPaint;
    private Path mPath;
    int[] colors = {Color.GREEN,Color.BLUE,Color.RED};
    private int moffsetX;
    private int verticalY= 300;
    private boolean isWaving;
    private ValueAnimator animator;

    public WaveView2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init();

    }

    private void init() {

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        LinearGradient gradient = new LinearGradient(0f,0f,getWidth(),0f,
               colors,null, Shader.TileMode.CLAMP);
//        mPaint.setColor(Color.GREEN);
        mPaint.setShader(gradient);

        mPath = new Path();

        animator = ValueAnimator.ofInt(0,mWaveLength);
        animator.setDuration(1000);
        animator.setInterpolator(new LinearInterpolator());
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                moffsetX = (int) animation.getAnimatedValue();
                postInvalidate();
            }
        });

    }

    public void swicthWave(){

        if(isWaving){
           animator.pause();
        }else {
            animator.start();
        };
        isWaving = !isWaving;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPath.reset();
        int halfLength = mWaveLength/2;
        mPath.moveTo(-mWaveLength+moffsetX,verticalY);
        for (int i = -mWaveLength; i <= getWidth()+mWaveLength; i+= mWaveLength) {

            mPath.rQuadTo(halfLength/2,-100,halfLength,0);

            mPath.rQuadTo(halfLength/2,100,halfLength,0);

        }

        mPath.lineTo(getWidth(),getHeight());
        mPath.lineTo(0,getHeight());
        canvas.drawPath(mPath,mPaint);


    }
}
