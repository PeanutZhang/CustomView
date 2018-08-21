package com.ezparking.com.customview.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.ezparking.com.customview.PieData;

import java.util.ArrayList;

/**
 * Created by zyh
 */

public class PieView extends View {

   private ArrayList<PieData> mPieDatas;
   private int mWidth,mHeight;
   private int mstartAnage;
   private Paint mPaint;
    // 颜色表(注意: 此处定义颜色使用的是ARGB，带Alpha通道的)
    private int[] mColors = {0xFFCCFF00, 0xFF6495ED, 0xFFE32636, 0xFF800000, 0xFF808000, 0xFFFF8C69, 0xFF808080,
            0xFFE6B800, 0xFF7CFC00};

    public PieView(Context context) {
        this(context,null);
    }

    public PieView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
       mWidth = w;
       mHeight = h;
        Log.e("zyh","--onSizedChanged is going------");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

     if(mPieDatas == null)return;

       canvas.translate(mWidth/2,mHeight/2);
       float r = (float) (Math.min(mWidth,mHeight)/2 *0.8);
        RectF arcRectf = new RectF(-r,-r,r,r);
        float currentStartAngle = mstartAnage;
        for (int i = 0; i < mPieDatas.size(); i++) {
            PieData pieData = mPieDatas.get(i);
            mPaint.setColor(pieData.getColor());
            canvas.drawArc(arcRectf,currentStartAngle,pieData.getAngle(),true,mPaint);
            currentStartAngle += pieData.getAngle();
        }
    }

    public void setData(ArrayList<PieData> data){
        mPieDatas = data;
        initData(mPieDatas);
        invalidate();//刷新数据
    }
    public void setStartAngle(int angle){
        if (angle < 0)return;
        mstartAnage = angle;
        invalidate();
    }
     private void initData(ArrayList<PieData> data){

         if(data == null || data.size() == 0){
             return;
         }
         float sumValue = 0;
         for (int i = 0; i < data.size(); i++) {
             PieData pieData = data.get(i);
             pieData.setColor(mColors[i % mColors.length]);
             sumValue += pieData.getValue();
         }

      float sumAngle = 0;

         for (int i = 0; i < data.size(); i++) {

             PieData pieData = data.get(i);

             float percent = pieData.getValue() / sumValue;
             float angle = percent * 360;

             pieData.setAngle(angle);
             sumAngle += angle;


         }





     }



}
