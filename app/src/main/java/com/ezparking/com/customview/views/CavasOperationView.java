package com.ezparking.com.customview.views;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.Random;

/**
 * Created by zyh
 */

public class CavasOperationView extends View {

    private Paint mPaint;
    private int mWidth,mHeight;
    private Random random;


    //canvas 画布操作实践
    public CavasOperationView(Context context) {
        this(context,null);
    }

    public CavasOperationView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int measureWidth = measurewidth(widthMeasureSpec);
        int measureHeiht = measureHeight(heightMeasureSpec);
        Log.e("zyh"," measure-->   measurewidht--> "+measureWidth +" measureHeight--> "+measureHeiht);
        setMeasuredDimension(measureWidth,measureHeiht);

    }

    /**
     * 测量view 的高度
     * @param heightMeasureSpec
     * @return
     */
    private int measureHeight(int heightMeasureSpec) {
       int result = 0;
       int specMode = MeasureSpec.getMode(heightMeasureSpec);
       int specSize = MeasureSpec.getSize(heightMeasureSpec);

       if(specMode == MeasureSpec.EXACTLY){
           result = specSize;
       }else {
           result = 500;//默认
           if(specMode == MeasureSpec.AT_MOST){
               result = Math.min(500,specSize);
           }
       }

        return result;
    }

    /**
     * 测量view 宽度
     * @param widthMeasureSpec
     * @return
     */
    private int measurewidth(int widthMeasureSpec) {

        int specMode = MeasureSpec.getMode(widthMeasureSpec);
        int specSize = MeasureSpec.getSize(widthMeasureSpec);
        int result = 0 ;
        if(specMode == MeasureSpec.EXACTLY){
            result = specSize;
        }else {
            result = 500;//测量控件的大小
            if(specMode == MeasureSpec.AT_MOST){
                result = Math.min(500,specSize);
            }
        }

        return result;
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(5);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setColor(Color.RED);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
//        int meausureWidth = getMeasuredWidth();
//        int measureheigt = getMeasuredHeight();
//      Log.e("zyh","onDraw-->  getwidth = "+width + " getMeaureWidth = "+meausureWidth+
//      "\r\n height= "+height+ " getMeasureHeight = "+measureheigt);//得到的结果是相同的
       int r = Math.min(width,height);
        canvas.translate(width/2,height/2);
        canvas.drawLine(-width/2 + 100,0,width/2 - 100,0,mPaint);
        mPaint.setColor(Color.BLUE);
        canvas.drawLine(0,- height/2 + 100,0,height/2 - 100,mPaint);
        mPaint.setColor(Color.GREEN);
        mPaint.setStrokeWidth(20);
        canvas.drawPoint(0,0,mPaint);

        RectF rect1 = new RectF(0,-300,300,0);
        mPaint.setStrokeWidth(5);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.BLACK);
        canvas.drawRect(rect1,mPaint);

        canvas.save();
//        canvas.scale(0.5f,0.5f);//缩放比例
         canvas.scale(-0.5f,-0.5f,150,0);//设置缩放中心点

        mPaint.setColor(Color.BLUE);
        canvas.drawRect(rect1,mPaint);

        canvas.restore();

        canvas.save();
        mPaint.setStrokeWidth(5);
        RectF rectF = new RectF(-320,-320,-20,-20);

        for (int i = 0; i < 1052; i++) {
            mPaint.setColor(randomColor());
            canvas.scale(0.92f,0.92f,-170,-170);
            canvas.drawRect(rectF,mPaint);
        }
        canvas.restore();

        canvas.drawCircle(0,0,200,mPaint);
        canvas.drawCircle(0,0,230,mPaint);
        for (int i = 0; i < 360; i +=10) {
            mPaint.setColor(randomColor());
            canvas.drawLine(0,200,0,230,mPaint);
            canvas.rotate(10);
        }

    }

    private int randomColor() {

        if (random  == null) {
            random = new Random();
        }
        int r = random.nextInt(256);// random.nextInt(255) [0,255)
       int g = random.nextInt(256);
       int b = random.nextInt(256);
        int color = Color.rgb( r, g, b);

        return color;
    }
}
