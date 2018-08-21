package com.ezparking.com.customview.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.ezparking.com.customview.Utils;

/**
 * Created by zyh
 */

public class PathLView extends View {

    private Paint mPaint;
    private int mWidth,mHeight;
    private Path mPath;

    public PathLView(Context context) {
        this(context,null);
    }

    public PathLView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
       init(context);
    }

    private void init(Context context) {

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(5);
        mPaint.setColor(Color.parseColor("#ff00ff"));
        mPaint.setStyle(Paint.Style.STROKE);
        mPath = new Path();

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
      mWidth = w;
      mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.translate(mWidth/2,mHeight/2);
        canvas.scale(1,-1);//反转画布y轴
        mPath.lineTo(100,100);
        RectF rct = new RectF(0,0,200,200);
//        mPath.addArc(rct,0,270);
        mPath.arcTo(rct,0,270);//圆弧的起点，会连接上次的最后一个点

        ////最后一个参数，是否移动最后一个点到圆弧起点，
        /// 为true最后一个点和圆弧起点不链接， 为flase时，同山歌方法效果一样
        mPath.arcTo(rct,0,270,true);
        canvas.drawPath(mPath,mPaint);

        Path srcPath = new Path();
        srcPath.addCircle(0,0,100, Path.Direction.CW);

        Path dstPath = new Path();
        //位移path，移动后的path,保存到dstPath中， 不影响原先的srcPath，
        //dx, dy 为原点平移
        srcPath.offset(150,0,dstPath);

        mPaint.setColor(Utils.randomColor());
        canvas.drawPath(srcPath,mPaint);
        mPaint.setColor(Utils.randomColor());
        canvas.drawPath(dstPath,mPaint);
    }
}
