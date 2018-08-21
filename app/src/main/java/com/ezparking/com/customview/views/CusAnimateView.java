package com.ezparking.com.customview.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.ezparking.com.customview.R;

import java.util.logging.LogRecord;

/**
 * Created by zyh
 */

public class CusAnimateView extends View {

    private int mR;
    private int currentPage = -1;
    private int maxPage = 13;
    private int mDurateion = 800;
    private int mWdith;
    private int mHeight;
    private Bitmap mBitmap;
    private Paint mPaint;
    private static Handler  mHandler;

    private static final int ANIM_NULL = 0;         //动画状态-没有
    private static final int ANIM_CHECK = 1;        //动画状态-开启
    private static final int ANIM_UNCHECK = 2;      //动画状态-结束

    private int animState = ANIM_NULL;
    private boolean isCheck = false;

    public CusAnimateView(Context context) {
        this(context,null);

    }

    public CusAnimateView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(measureWidth(widthMeasureSpec),measureHeight(heightMeasureSpec));
        Log.e("zyh","onMeasure is going-----");
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.e("zyh","----onSizeChanged-> width-> "+ w+ " height-> "+h );
        mWdith = w;
        mHeight = h;
    }

    @SuppressLint("HandlerLeak")
    private void init(Context context) {
     mBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.checkmark);
     mPaint = new Paint();
     mPaint.setAntiAlias(true);
     mPaint.setColor(Color.GRAY);
     mPaint.setStyle(Paint.Style.FILL);
     mHandler = new Handler(){
         @Override
         public void handleMessage(Message msg) {
             super.handleMessage(msg);
             Log.e("zyh","--handleMsg--is going----------------");
             if(currentPage >= 0 && currentPage < maxPage){
                 if(animState == ANIM_NULL){
                     return;
                 }else if(animState == ANIM_CHECK){
                     currentPage++;
                 }else if(animState == ANIM_UNCHECK){
                     currentPage --;
                 }
                 if(currentPage != maxPage){// 否则会闪一次
                     invalidate();
                 }
                 this.sendEmptyMessageDelayed(501,mDurateion/maxPage);
            //     Log.e("zyh", "currentPage" + currentPage);
             }else {
//                 invalidate();
                 if(isCheck){
                     currentPage = maxPage -1;//12-
                 }else {
                     currentPage = -1;
                  }
                 animState = ANIM_NULL;
             }
         }
     };

    }

    public void check(){
        if(animState != ANIM_NULL || isCheck)return;
        animState = ANIM_CHECK;
        currentPage = 0;
        mHandler.sendEmptyMessageDelayed(501,mDurateion/maxPage);
        isCheck = true;
    }
     public void unCheck(){
        if(animState != ANIM_NULL || !isCheck)return;
        animState = ANIM_UNCHECK;
        currentPage = maxPage  - 1;
        mHandler.sendEmptyMessageDelayed(501,mDurateion/maxPage);
        isCheck = false;

     }
    /**
     * 设置动画时长
     * @param animDuration
     */
    public void setAnimDuration(int animDuration) {
        if (animDuration <= 0)
            return;
        this.mDurateion = animDuration;
    }

    /**
     * 设置背景圆形颜色
     * @param color
     */
    public void setBackgroundColor(int color){
        mPaint.setColor(color);
    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //移动画布
        canvas.translate(mWdith/2,mHeight/2);
        mR = Math.min(mWdith,mHeight)/2 - 80;
        canvas.drawCircle(0,0,mR,mPaint);
        Log.e("zyh","---onDraw currentPage--->  "+currentPage);
        int sideLength = mBitmap.getHeight();
        Rect src = new Rect(sideLength *currentPage,0,sideLength*(currentPage+1),sideLength);
        Rect dst = new Rect(-mR+50,-mR+50,mR -50,mR - 50);
        canvas.drawBitmap(mBitmap,src,dst,null);

    }



    private int measureWidth(int widthSpec){
        int width = 0;
        int specMode = MeasureSpec.getMode(widthSpec);
        int specSize = MeasureSpec.getSize(widthSpec);
        if(specMode == MeasureSpec.EXACTLY){
            width = specSize;
        }else {
            width = 200;
            if(specMode == MeasureSpec.AT_MOST){
                width = Math.min(width,specSize);
            }
        }
      return  width;
    }
    private int measureHeight(int heightSpec){
        int heiht = 0;
        int specMode = MeasureSpec.getMode(heightSpec);
        int specSize = MeasureSpec.getSize(heightSpec);
        if(specMode == MeasureSpec.EXACTLY){
            heiht = specSize;
        }else {
            heiht = 200;
            if(specMode == MeasureSpec.AT_MOST){
                heiht = Math.min(heiht,specSize);
            }
        }
        return  heiht;
    }

}
