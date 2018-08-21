package com.ezparking.com.customview.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.ezparking.com.customview.Utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;


/**
 * Created by zyh
 */

public class SpiderRadar extends View {

    // 自己尝试画多边形雷达图
   private Paint mPaint,mTextPaint,mOverPaint;
   private int mWidth,mHeight;
   private Path mPath;
   private int mpolyCount = 6;
   private String[] titles = {
       "阿根廷","葡萄牙","法国","比利时","英格兰","乌拉圭"
   };
 private int duration  = 500;
   private Handler mHandler;
    private ExecutorService executorService;

    private boolean isAnimation;

    public SpiderRadar(Context context) {
        this(context,null);
    }

    public SpiderRadar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }
    private void init(Context context) {
       mPaint = new Paint();
       mPaint.setColor(Utils.randomColor());
       mPaint.setStrokeWidth(3);
       mPaint.setStyle(Paint.Style.STROKE);
       mPath = new Path();

       mTextPaint = new Paint();
       mTextPaint.setTextSize(16);
       mTextPaint.setTextAlign(Paint.Align.CENTER);
       mTextPaint.setColor(Color.RED);

       mOverPaint = new Paint();
       mOverPaint.setColor(Utils.randomColor());
       mTextPaint.setStyle(Paint.Style.FILL_AND_STROKE);
       mTextPaint.setAlpha(165);

       mHandler  = new Handler() {
           @Override
           public void handleMessage(Message msg) {
               super.handleMessage(msg);

               postInvalidate();
               mHandler.sendEmptyMessageDelayed(501,duration);
           }
       };

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }


    public void startAnimation(){
//        executorService = Executors.newSingleThreadExecutor();
//        executorService.submit(new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        });

        if(isAnimation) return;
        boolean b = mHandler.sendEmptyMessageDelayed(501, duration);
           isAnimation = b;
    }
    public void stopAnimation(){
        mHandler.removeMessages(501);
        isAnimation = false;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.translate(mWidth/2,mHeight/2);
        canvas.drawPoint(0,0,mPaint);
        int r = Math.min(mWidth/2,mHeight/2);        double angle = 360/ mpolyCount;

        int mradius = r -50;

        //绘制六边形和连线-----------------------------
        for (int j = 0; j < 5; j++) {
            int currentR = mradius/5 * (j+1);
             Path polygonPath = new Path();
            for (int i = 0; i < mpolyCount; i++) {
                double currentAngle = angle* i;
                double rad = currentAngle * Math.PI / 180;//需要把角度转换为弧度，不然结果不对,因为角度为60进制， 弧度为10进制
                double cos = Math.cos(rad);
                double sin = Math.sin(rad);
                float x = (float) (cos* currentR);
                float y = (float) (sin *currentR);
                if(i == 0){
                    polygonPath.moveTo(x,y);
                }
                polygonPath.lineTo(x,y);
            }
            polygonPath.close();
            mPath.addPath(polygonPath);
        }

        //往Path 里添加6条线 和文字绘制
        for (int i = 0; i < 6; i++) {
            double cos = Math.cos(angle * i *Math.PI / 180);
            double sin = Math.sin(angle* i*Math.PI/180);
            float x  = (float) (cos * mradius);
            float y  = (float) (sin*mradius);
            Path linePath = new Path();
            linePath.lineTo(x,y);
            mPath.addPath(linePath);
           float textX = (float) (cos * (mradius + 20));
           float textY = (float) (sin * (mradius +20));
           canvas.drawText(titles[i],textX,textY,mTextPaint);
        }

        canvas.drawPath(mPath,mPaint);

       // 绘制覆盖区， 随机生成一块区域
        Path polygonPath = new Path();
        for (int i = 0; i < mpolyCount; i++) {
            double valueRate = Math.random();//模拟一个条目的占比， 实际项目根据需求定义
            double cos = Math.cos(angle * i *Math.PI / 180);
            double sin = Math.sin(angle* i*Math.PI/180);
            float x  = (float) (cos * mradius * valueRate );
            float y  = (float) (sin*mradius * valueRate );
            if(i == 0){
                polygonPath.moveTo(x,y);
            }else {
                polygonPath.lineTo(x,y);
            }
           //绘制小圆点
            canvas.drawPoint(x,y,mOverPaint);
        }
         mOverPaint.setColor(Utils.randomColor());
         canvas.drawPath(polygonPath,mOverPaint);
    }
}
