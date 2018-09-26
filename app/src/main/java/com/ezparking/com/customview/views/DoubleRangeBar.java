package com.ezparking.com.customview.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.ezparking.com.customview.R;
import com.ezparking.com.customview.Utils;
import com.ezparking.com.customview.datas.CirclePoint;

/**
 * Created by zyh
 */

public class DoubleRangeBar extends View {

    private int mLineHeight;
    private int mLineFormalColor;
    private int mLineSecletedColor;
    private int mCircleColor;

    private Paint mPaintFoamal,mPanitSelect, mPaintCircleleft,mPaintCircleRight,mPaintStroke;


    private int mCircleRadius, mCircleStrokeWidth;
    private int mLineCornerRadius;
    private int mRealWidth;
    private RectF mDefaultLineRect;

    private CirclePoint leftCircle,rightCircle;

    private int partCounts = 5;
    private int perNumber ;
    private int maxValue = 100;
    private int minValue = 0;


    public DoubleRangeBar(Context context) {
        this(context,null);
    }

    public DoubleRangeBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.DoubleRangeBar,0,R.style.double_rangebar_default_value);
        Log.e("zyh","ta.getIndexCount-->  "+ta.getIndexCount());
        for (int i = 0; i < ta.getIndexCount(); i++) {
            int attr = ta.getIndex(i);
            switch (attr){

                case R.styleable.DoubleRangeBar_rect_line_height:
                    mLineHeight = ta.getDimensionPixelSize(attr, Utils.dip2px(context,5));
                    break;
                case R.styleable.DoubleRangeBar_rect_line_normal_color:
                    mLineFormalColor = ta.getColor(attr, ContextCompat.getColor(context,R.color.color_default_normal));
                    break;
                case R.styleable.DoubleRangeBar_rect_line_selected_color:
                    mLineSecletedColor = ta.getColor(attr,ContextCompat.getColor(context,R.color.color_seclected_));
                    break;
                case R.styleable.DoubleRangeBar_circle_radius_:
                    mCircleRadius = ta.getDimensionPixelSize(attr,Utils.dip2px(context,8));
                    break;
                case R.styleable.DoubleRangeBar_circle_stroke_width:
                     mCircleStrokeWidth = ta.getDimensionPixelSize(attr, Utils.dip2px(context,1));
                    break;

                case R.styleable.DoubleRangeBar_circle_color_:
                     mCircleColor = ta.getColor(attr,ContextCompat.getColor(context,R.color.color_2465f1));
                    break;

            }

        }
          ta.recycle();
          init(context);
    }





    private void init(Context context) {

        mPaintFoamal = new Paint();
        mPaintFoamal.setColor(mLineFormalColor);
        mPaintFoamal.setAntiAlias(true);
        mPaintFoamal.setStyle(Paint.Style.FILL_AND_STROKE);

        mPaintCircleleft = new Paint();
        mPaintCircleleft.setColor(mCircleColor);
        mPaintCircleleft.setAntiAlias(true);
        mPaintCircleleft.setStyle(Paint.Style.FILL);

        mPaintCircleRight = new Paint();
        mPaintCircleRight.setColor(Color.parseColor("#FF4A30DF"));
        mPaintCircleRight.setAntiAlias(true);
        mPaintCircleRight.setStyle(Paint.Style.FILL);

        mPaintStroke = new Paint();
        mPaintStroke.setStyle(Paint.Style.FILL);
        mPaintStroke.setColor(Color.parseColor("#999999"));
        mPaintStroke.setAntiAlias(true);


        mDefaultLineRect = new RectF();
        mLineCornerRadius = mLineHeight/2;


        leftCircle = new CirclePoint();
        rightCircle = new CirclePoint();


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.e("zyh","zz onMeasure is going--->   ");
        int meaureWidth = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);

        int measureHeight = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int widthsize = getPaddingLeft()+getPaddingRight()+ mCircleRadius*2 + mCircleStrokeWidth *2;
        int heightsize = getPaddingTop()+getPaddingBottom()+ mCircleRadius*2+ mCircleStrokeWidth *2;
        int width,height;
        if(widthMode == MeasureSpec.EXACTLY){
            width = meaureWidth;
        }else if(widthMode == MeasureSpec.AT_MOST) {
            width = Math.min(meaureWidth,widthsize);
        }else {
            width = widthsize;
        }

        if(heightMode == MeasureSpec.EXACTLY){
            height = measureHeight;
        }else if(heightMode == MeasureSpec.AT_MOST){
            height = Math.min(measureHeight,heightsize);
        }else{
            height = heightsize;
        }
        setMeasuredDimension(width,height);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.e("zyh","--onSizeChanged invoked-----------");
        mRealWidth = w - getPaddingLeft() + getPaddingRight();

        mDefaultLineRect.left = getPaddingLeft()+mCircleRadius+ mCircleStrokeWidth;
        mDefaultLineRect.top = getPaddingTop()+mCircleRadius+ mCircleStrokeWidth -mLineCornerRadius;
        mDefaultLineRect.right = mRealWidth - getPaddingRight() - mCircleStrokeWidth - mCircleRadius;
        mDefaultLineRect.bottom = getPaddingTop()+mLineCornerRadius+mCircleRadius+ mCircleStrokeWidth;

       leftCircle.cx = getPaddingLeft()+ mCircleStrokeWidth +mCircleRadius;
       leftCircle.cy = getPaddingTop()+mCircleRadius+ mCircleStrokeWidth;

       rightCircle.cx = mRealWidth - getPaddingRight() - mCircleRadius - mCircleStrokeWidth;
       rightCircle.cy = getPaddingTop() + mCircleStrokeWidth + mCircleRadius;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

     drawDefaultLineRect(canvas);
     drawCircle(canvas);
    }

    private void drawCircle(Canvas canvas) {
        canvas.drawCircle(leftCircle.cx,leftCircle.cy,mCircleRadius+mCircleStrokeWidth,mPaintStroke);
        canvas.drawCircle(leftCircle.cx,leftCircle.cy,mCircleRadius, mPaintCircleleft);
        canvas.drawCircle(rightCircle.cx,rightCircle.cy,mCircleRadius+mCircleStrokeWidth,mPaintStroke);
        canvas.drawCircle(rightCircle.cx,rightCircle.cy,mCircleRadius, mPaintCircleRight);
    }

    private void drawDefaultLineRect(Canvas canvas) {

        canvas.drawRoundRect(mDefaultLineRect,mLineCornerRadius,mLineCornerRadius,mPaintFoamal);

    }
}
