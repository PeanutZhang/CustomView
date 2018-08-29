package com.ezparking.com.customview.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;


/**
 * Created by zyh on 2018/8/30.
 */

public class WriteTextBezier extends View {

    private float mPreX,mPreY;
    private Path mPath;
    private Paint mPint;

    public WriteTextBezier(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {

        mPath = new Path();
        mPint = new Paint();
        mPint.setStrokeWidth(3);
        mPint.setStyle(Paint.Style.STROKE);
        mPint.setColor(Color.parseColor("#ff00ff"));
        mPint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawPath(mPath,mPint);

    }


    public void reset(){
        mPath.reset();
        invalidate();
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mPreX = event.getX();
                mPreY = event.getY();
                mPath.moveTo(mPreX,mPreY);
                return true;


            case MotionEvent.ACTION_MOVE:

                float endX = (mPreX +event.getX())/2;
                float endY = (mPreY + event.getY())/2;
                mPath.quadTo(mPreX,mPreY,endX,endY);
                mPreX = event.getX();
                mPreY = event.getY();
                invalidate();
                break;


        }


        return super.onTouchEvent(event);
    }
}
