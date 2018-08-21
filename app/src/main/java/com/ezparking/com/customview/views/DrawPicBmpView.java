package com.ezparking.com.customview.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Picture;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.PictureDrawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.ezparking.com.customview.R;

/**
 * Created by zyh
 */

public class DrawPicBmpView extends View {

    private Bitmap mbitMap;

    public DrawPicBmpView(Context context) {
        this(context,null);

    }

    private Picture mPicture;
    private Paint mPaint;

    public DrawPicBmpView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init(context);

    }

    private void init(Context context) {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.BLUE);

        //picture 用法1
        mPicture = new Picture();
        Canvas canvas = mPicture.beginRecording(400, 400);
        canvas.translate(200,200);
        canvas.drawCircle(0,0,100,mPaint);
        mPicture.endRecording();

        mbitMap = BitmapFactory.decodeResource(context.getResources(), R.drawable.img1);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        mPicture.draw(canvas);//会影响canvas
        canvas.drawPicture(mPicture,new RectF(0,0,mPicture.getWidth(),300));//不影响canvas

        // 使用pictureDrawable 绘制图片
        PictureDrawable drawable = new PictureDrawable(mPicture);
        drawable.setBounds(0,0,200,mPicture.getHeight());
        drawable.draw(canvas);
        canvas.translate(200,200);

        //绘制bitmap----
        //canvas.drawBitmap(mbitMap,new Matrix(),new Paint());
        //需要绘制bitmap 的区域
        Rect src = new Rect(0,0,mbitMap.getWidth()/2,mbitMap.getHeight()/2);
        //需要绘制在view 显示的区域
        Rect dst = new Rect(0,0,162,108);
        canvas.drawBitmap(mbitMap,src,dst,new Paint());
    }

}
