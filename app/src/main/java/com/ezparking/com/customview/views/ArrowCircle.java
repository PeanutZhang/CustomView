package com.ezparking.com.customview.views;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.ezparking.com.customview.R;

/**
 * Created by zyh
 */

public class ArrowCircle extends View{


    private Paint mPaint;
    private Path mPath;
    private int mWidth,mHeight;
    private int  mRadius = 200;
    private Bitmap mBitmap;
    private Matrix mMatrix;
    private float currentDistance = 0.005f;
    private PathMeasure mPathMeasure;
    private float[] pos;
    private float[] tan;
    private ValueAnimator animator;
    private boolean isAnimator;


    public ArrowCircle(Context context) {
        this(context,null);
    }

    public ArrowCircle(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {

        pos = new float[2];
        tan = new float[2];

        mPaint = new Paint();
        mPaint.setStrokeWidth(5);
        mPaint.setColor(Color.parseColor("#59c3e2"));
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setAntiAlias(true);
        mPath = new Path();
        mMatrix = new Matrix();
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 2;
        mBitmap = BitmapFactory.decodeResource(context.getResources(),R.drawable.arrow,options);

        animator = ValueAnimator.ofFloat(0,1f);
        animator.setDuration(3000);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                currentDistance = (float) animation.getAnimatedValue();
                postInvalidate();
            }
        });

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.translate(mWidth/2,mHeight/2);
        mPath.addCircle(0,0,mRadius, Path.Direction.CW);
        if (mPathMeasure == null) {
            mPathMeasure = new PathMeasure(mPath,false);
        }else {
            mPathMeasure.setPath(mPath,false);
        }
        if(currentDistance >=1 ){
            currentDistance = 0;
        }
       mPathMeasure.getPosTan(mPathMeasure.getLength()*currentDistance,pos,tan);
      float degree = (float) (Math.atan2(tan[1],tan[0])*180/Math.PI);
        mMatrix.reset();
        mMatrix.postRotate(degree,mBitmap.getWidth()/2,mBitmap.getHeight()/2);
        mMatrix.postTranslate(pos[0]-mBitmap.getWidth()/2,pos[1] - mBitmap.getHeight()/2);
        canvas.drawPath(mPath,mPaint);
        canvas.drawBitmap(mBitmap,mMatrix,mPaint);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    public void startRoate(){
        if(isAnimator){
            animator.pause();
        }else {
            animator.start();
        }
        isAnimator = !isAnimator;
    }

}
