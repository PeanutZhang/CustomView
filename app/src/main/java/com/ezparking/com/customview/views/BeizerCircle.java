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

import com.ezparking.com.customview.datas.HorizontalPoints;
import com.ezparking.com.customview.datas.VerticalPoints;

/**
 * Created by zyh
 */

/**
 * 练习用Beizer画一个圆
 */
public class BeizerCircle extends View {

    private int mCenterX, mCenterY;
    private float mcircleR = 100 ;
    private HorizontalPoints mTopPoints,mBottomPoints;
    private VerticalPoints  mLeftPoints,mRightPoints;
//    private VerticalPointsK mLeftPoints;
//    private VerticalPointsK mRightPoints;
    private Paint mPaint,mCartesianPaint;
    private Path mPath;
    private static final float C =  0.551915024494f;//贝塞尔曲线计算的常量

    private float mTransYcoefficient = -1f;
    private Canvas mCanvas;


    public BeizerCircle(Context context) {
        this(context,null);
    }

    public BeizerCircle(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {

        mPaint = new Paint();
        mPaint.setColor(Color.parseColor("#ff00ff"));
        mPaint.setStrokeWidth(3);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);

        mCartesianPaint = new Paint();
        mCartesianPaint.setAntiAlias(true);
        mCartesianPaint.setStrokeWidth(12);

        mPath = new Path();

        //初始化数据点 控制点
        mTopPoints = new HorizontalPoints(mcircleR,-mcircleR);
        mBottomPoints = new HorizontalPoints(mcircleR,mcircleR);
        mLeftPoints = new VerticalPoints(-mcircleR,mcircleR);
        mRightPoints = new VerticalPoints(mcircleR,mcircleR);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mCenterX = w/2;
        mCenterY = h/2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPath.reset();
        if (mCanvas == null) {
            mCanvas = canvas;
        }
        if(mTransYcoefficient < 0){//移到view 中心
            canvas.translate(mCenterX,mCenterY);
            canvas.drawLine(100 - mCenterX,0,mCenterX -100,0,mCartesianPaint);
            canvas.drawLine(0,100 -mCenterY,0,mCenterY -100,mCartesianPaint);
        }else if(mTransYcoefficient > 0 && mTransYcoefficient <= 1){//向下平移
             canvas.translate(mCenterX,mCenterY+ (mCenterY - mcircleR)*mTransYcoefficient);
             mBottomPoints.setY(mcircleR*(1+mTransYcoefficient/2));
             mTopPoints.setY(-mcircleR);//变形
        }else if(mTransYcoefficient > 1 && mTransYcoefficient <= 2){//从底部向上移动
            canvas.translate(mCenterX,2*mCenterY-mcircleR -(mCenterY - mcircleR)*(mTransYcoefficient-1));
            mBottomPoints.setY(mcircleR);
            mTopPoints.setY(-mcircleR*((mTransYcoefficient -1 )/2+1));//动态变形
        }
//       changeCircleCoordinate(mTransYcoefficient);
        mPath.moveTo(mTopPoints.middlePoi.x,mTopPoints.middlePoi.y);
        //添加path ,从top 顺时针画贝塞尔曲线
       mPath.cubicTo(mTopPoints.rightPoi.x,mTopPoints.rightPoi.y,mRightPoints.topPoi.x,mRightPoints.topPoi.y,mRightPoints.middlePoi.x,mRightPoints.middlePoi.y);
       mPath.cubicTo(mRightPoints.bottomPoi.x,mRightPoints.bottomPoi.y,mBottomPoints.rightPoi.x,mBottomPoints.rightPoi.y,mBottomPoints.middlePoi.x,mBottomPoints.middlePoi.y);
       mPath.cubicTo(mBottomPoints.leftPoi.x,mBottomPoints.leftPoi.y,mLeftPoints.bottomPoi.x,mLeftPoints.bottomPoi.y,mLeftPoints.middlePoi.x,mLeftPoints.middlePoi.y);
       mPath.cubicTo(mLeftPoints.topPoi.x,mLeftPoints.topPoi.y,mTopPoints.leftPoi.x,mTopPoints.leftPoi.y,mTopPoints.middlePoi.x,mTopPoints.middlePoi.y);
       canvas.drawPath(mPath,mPaint);

    }

    public void transformX(){
        mRightPoints.setX(mcircleR * 1.5f);
        invalidate();
    }
    //通过属性动画平移贝塞尔图形
    public void startAnimation(){

        ValueAnimator animator = ValueAnimator.ofFloat(0,2.0f);
        animator.setDuration(2000);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mTransYcoefficient = (float) animation.getAnimatedValue();
                postInvalidate();
            }
        });
       animator.start();

    }

    private void changeCircleCoordinate(float mTime) {
        float mTempTime;

        if(mTime > 1) {
            mTempTime = mTime - 1;
        } else {
            mTempTime = mTime;
        }

        if(mTempTime > 0.1 && mTempTime <= 0.2) {

            if(mTime > 1) {
                mBottomPoints.setY(mcircleR - mTempTime*mcircleR*5);
            } else {
                mTopPoints.setY(-mcircleR + mTempTime*mcircleR*5);
            }

        } else {
            mTopPoints.setY(-mcircleR);
            mBottomPoints.setY(mcircleR);
            mTopPoints.middlePoi.x = mBottomPoints.middlePoi.x = 0;
            mTopPoints.leftPoi.x = mBottomPoints.leftPoi.x =  -mcircleR*C;
            mTopPoints.rightPoi.x = mBottomPoints.rightPoi.x =  mcircleR*C;
            mRightPoints.setX(mcircleR);
            mLeftPoints.setX(-mcircleR);
            mRightPoints.middlePoi.y = mLeftPoints.middlePoi.y = 0;
            mRightPoints.topPoi.y = mLeftPoints.topPoi.y = -mcircleR * C;
            mRightPoints.bottomPoi.y = mLeftPoints.bottomPoi.y = mcircleR*C;
        }
    }

}
