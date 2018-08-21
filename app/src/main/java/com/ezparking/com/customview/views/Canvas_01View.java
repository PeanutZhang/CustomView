package com.ezparking.com.customview.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.ezparking.com.customview.Utils;

/**
 * Created by zyh
 */

public class Canvas_01View extends View {

    private Paint mPaint;

    public Canvas_01View(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
       init();
    }

    private void init() {

        mPaint = new Paint();
        mPaint.setColor(Color.parseColor("#000000"));
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeCap(Paint.Cap.ROUND);//设置画笔圆角
        mPaint.setStrokeWidth(Utils.dip2px(getContext(),5));

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

       //绘制几个点
        canvas.drawPoint(200,200,mPaint);
        canvas.drawPoint(200,500,mPaint);
        canvas.drawPoint(500,200,mPaint);
        canvas.drawPoint(500,500,mPaint);
        canvas.drawPoint(350,350,mPaint);

        mPaint.setColor(Color.RED);
        canvas.drawLine(600,200,900,200,mPaint);
        canvas.drawLine(600,300,900,300,mPaint);
        canvas.drawLine(600,400,900,400,mPaint);


        mPaint.setStyle(Paint.Style.STROKE);
        //draw a rect
        canvas.drawRect(100,100,1200,800,mPaint);
        mPaint.setStrokeWidth(2);
        mPaint.setColor(Color.BLUE);
        Rect rect = new Rect(150,150,1100,700);
        canvas.drawRect(rect,mPaint);

        mPaint.setStyle(Paint.Style.FILL);
        RectF roundRect = new RectF(200,900,1000,1200);
        mPaint.setColor(Color.GRAY);
        canvas.drawRect(roundRect,mPaint);
        mPaint.setColor(Color.BLUE);
        canvas.drawRoundRect(roundRect,400,150,mPaint);

        RectF arcRect = new RectF(200,1300,600,1700);
        mPaint.setColor(Color.GRAY);
        canvas.drawRect(arcRect,mPaint);
        mPaint.setColor(Color.BLUE);
        canvas.drawArc(arcRect,270,90,true,mPaint);

    }
}
