package com.ezparking.com.customview.views;

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
import android.view.animation.LinearInterpolator;

/**
 * Created by zyh on 2018/8/30.
 */

public class EffectView extends View{

    private Paint mPaint;
    private Path  mPath;
    private ValueAnimator animator;
    private float moffset;
    private boolean isSwing;

    public EffectView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {


        animator = ValueAnimator.ofInt(0,230);
        animator.setDuration(1000);
        animator.setInterpolator(new LinearInterpolator());
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                moffset = (int) animation.getAnimatedValue();
                postInvalidate();
            }
        });



    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);
        mPaint.setColor(Color.GREEN);

        mPath = new Path();

        mPath.moveTo(100,800);
        mPath.lineTo(500,200);
        mPath.lineTo(900,1200);

        canvas.drawPath(mPath,mPaint);
        mPaint.setColor(Color.RED);
        canvas.translate(0,100);
        DashPathEffect effect = new DashPathEffect(new float[]{10,20,100,100},moffset);
        mPaint.setPathEffect(effect);
        canvas.drawPath(mPath,mPaint);



    }

    public void moveYourBody(){
        if(isSwing){
            animator.pause();
        }else {
            animator.start();
        }
        isSwing =!isSwing;
    }

}
