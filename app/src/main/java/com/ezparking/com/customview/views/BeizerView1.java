package com.ezparking.com.customview.views;

import android.content.Context;
import android.content.pm.ComponentInfo;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by zyh
 */

public class BeizerView1 extends View {

    private Paint mPaint,mControlPaint,mPoitntPaint;
    private int mCenterX,mCenterY;
    private PointF mControlP,mStartP,mEndP;
    private Path mPath;


    public BeizerView1(Context context) {
        this(context,null);
    }

    public BeizerView1(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
          mPaint = new Paint();
          mPaint.setColor(Color.parseColor("#ff00ff"));
          mPaint.setAntiAlias(true);
          mPaint.setStyle(Paint.Style.STROKE);
          mPaint.setStrokeCap(Paint.Cap.ROUND);
          mPaint.setStrokeWidth(5);

          mControlPaint = new Paint();
          mControlPaint.setColor(Color.GRAY);
          mControlPaint.setStrokeWidth(10);
          mControlPaint.setStrokeCap(Paint.Cap.ROUND);

          mPoitntPaint = new Paint();
          mPoitntPaint.setColor(Color.GREEN);
          mPoitntPaint.setStrokeWidth(20);
          mPoitntPaint.setStrokeCap(Paint.Cap.ROUND);

          mPath = new Path();

          mControlP = new PointF();
          mStartP = new PointF();
          mEndP = new PointF();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

           mCenterX = w/2;
           mCenterY = h /2;

          mStartP.x =300;
          mStartP.y = mCenterY;
          mEndP.x = w - 300;
          mEndP.y = mCenterY;

          mControlP.x = mCenterX;
          mControlP.y = mCenterY-200;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

       mControlP.x = event.getX();
       mControlP.y= event.getY();
       invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawPoint(mStartP.x,mStartP.y,mPoitntPaint);
        canvas.drawPoint(mControlP.x,mControlP.y, mPoitntPaint);
        canvas.drawPoint(mEndP.x, mEndP.y,mPoitntPaint);

        canvas.drawLine(mStartP.x,mStartP.y,mControlP.x,mControlP.y,mControlPaint);
        canvas.drawLine(mControlP.x,mControlP.y,mEndP.x,mEndP.y,mControlPaint);
        mPath.reset();
        mPath.moveTo(mStartP.x, mStartP.y);
        mPath.quadTo(mControlP.x,mControlP.y,mEndP.x,mEndP.y);
        canvas.drawPath(mPath,mPaint);
    }
}
