package com.ezparking.com.customview.views;

import android.arch.persistence.room.util.StringUtil;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
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


    private Paint mPaintFoamal,mPanitSelect, mPaintCircleleft,mPaintCircleRight,mPaintStroke,
            mPaintBottomText,mPaintSeclect;


    private int mCircleRadius, mCircleStrokeWidth;
    private int mLineCornerRadius;
    private int mRealWidth;
    private RectF mDefaultLineRect,mSeclectRect;

    private CirclePoint leftCircle,rightCircle;

    private int partCounts = 5;
    private int perNumber ;
    private int maxValue = 100;
    private int minValue = 0;
    private int descrSpace;
    private int bottomTextSize;
    private float mTouchX;
    private boolean isTouchLeft;


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

                case R.styleable.DoubleRangeBar_max_value:
                    maxValue = ta.getInteger(attr,100);
                    break;
                case R.styleable.DoubleRangeBar_min_value:
                    minValue = ta.getInteger(attr,0);
                    break;
                case R.styleable.DoubleRangeBar_part_counts_:
                    partCounts = ta.getInteger(attr,5);
                    break;

                case R.styleable.DoubleRangeBar_descibe_space_:
                    descrSpace = ta.getDimensionPixelSize(attr, Utils.dip2px(context,5));
                    break;
                case R.styleable.DoubleRangeBar_bottom_text_size:
                    bottomTextSize = ta.getDimensionPixelSize(attr, Utils.sp2px(context,13));
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
        mPaintCircleleft.setColor(Color.RED);
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

        mPaintBottomText = new Paint();
        mPaintBottomText.setTextSize(bottomTextSize);
        mPaintBottomText.setStrokeWidth(5);
        mPaintBottomText.setColor(Color.parseColor("#FF30CE2A"));
        mPaintBottomText.setAntiAlias(true);

        mPaintSeclect = new Paint();
        mPaintSeclect.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaintSeclect.setColor(Color.parseColor("#ff00ff"));
        mPaintSeclect.setAntiAlias(true);


        mDefaultLineRect = new RectF();
        mSeclectRect = new RectF();
        mLineCornerRadius = mLineHeight/2;


        leftCircle = new CirclePoint();
        rightCircle = new CirclePoint();

        perNumber = maxValue/partCounts;

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
        int heightsize = getPaddingTop()+getPaddingBottom()+ mCircleRadius*2+ mCircleStrokeWidth * 2 + bottomTextSize+descrSpace;
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
        Log.e("zyh","--onSizeChanged invoked-----------"+ w + " height-> "+ h);
        mRealWidth = w - getPaddingLeft() - getPaddingRight();

        mDefaultLineRect.left = getPaddingLeft()+mCircleRadius+ mCircleStrokeWidth;
        mDefaultLineRect.top = getPaddingTop()+mCircleRadius+ mCircleStrokeWidth -mLineCornerRadius;
        mDefaultLineRect.right = w - getPaddingRight() - mCircleStrokeWidth - mCircleRadius;
        mDefaultLineRect.bottom = getPaddingTop()+mLineCornerRadius+mCircleRadius+ mCircleStrokeWidth;

        mSeclectRect.left= leftCircle.cx;
        mSeclectRect.top = getPaddingTop()+mCircleStrokeWidth+mCircleRadius - mLineCornerRadius;
        mSeclectRect.right= rightCircle.cx;
        mSeclectRect.bottom = getPaddingTop() + mLineCornerRadius+mCircleRadius +mCircleStrokeWidth;

        leftCircle.cx = getPaddingLeft()+ mCircleStrokeWidth +mCircleRadius;
        leftCircle.cy = getPaddingTop()+mCircleRadius+ mCircleStrokeWidth;

        rightCircle.cx = w - getPaddingRight() - mCircleRadius - mCircleStrokeWidth;
        rightCircle.cy = getPaddingTop() + mCircleStrokeWidth + mCircleRadius;




    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

     drawDefaultLineRect(canvas);
     drawSeclectRect(canvas);
     drawCircle(canvas);
     drawbottomText(canvas);

    }

    private void drawSeclectRect(Canvas canvas) {

        canvas.drawRoundRect(mSeclectRect,mLineCornerRadius,mLineCornerRadius,mPaintSeclect);
    }

    private void drawbottomText(Canvas canvas) {

        int perbottomTextHSpace = (mRealWidth- mCircleStrokeWidth*2 - mCircleRadius*2)/ partCounts;
        int startX = getPaddingLeft()+mCircleStrokeWidth+mCircleRadius;
        int bottomY = getPaddingTop()+(mCircleRadius+mCircleStrokeWidth)* 2 + descrSpace;
        Log.e("zyh","---------PartCounts---->  "+partCounts+"  "+mRealWidth);
        for (int i = 0; i <= partCounts; i++) {

            String text = String.valueOf(perNumber* i);
            float textWidth = mPaintBottomText.measureText(text);
            float x = startX + i * perbottomTextHSpace - textWidth/2;
            float y = bottomY + bottomTextSize;
            canvas.drawText(text,x,y,mPaintBottomText);

        }



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

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.e("zyh","actionDown---");

                mTouchX = event.getX();
                isTouchLeft = checkIsTouchLeftCircle(mTouchX);
                if(isTouchLeft){
                  leftCircle.cx = (int) mTouchX;
                }else {
                    rightCircle.cx = (int) mTouchX;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e("zyh","actionMove---");
                 float moveX = event.getX();
                 if(leftCircle.cx == rightCircle.cx){//处理两圆重合时
                     if(isTouchLeft){
                         if(leftCircle.cx == getWidth() - getPaddingRight() - (mCircleStrokeWidth +mCircleRadius ) ){
                             isTouchLeft = true;
                             leftCircle.cx = (int) moveX;
                         }else {
                             isTouchLeft = false;
                             rightCircle.cx = (int) moveX;
                         }
                     }else {
                         if(rightCircle.cx == getPaddingLeft()+ mCircleRadius+ mCircleStrokeWidth){
                             isTouchLeft = false;
                             rightCircle.cx = (int) moveX;
                         }else {
                             isTouchLeft = true;
                             leftCircle.cx = (int) moveX;
                         }
                     }

                 }else {
                     if(isTouchLeft){
                         leftCircle.cx = leftCircle.cx - rightCircle.cx >=0? rightCircle.cx:(int) moveX;
                     }else {
                         rightCircle.cx = rightCircle.cx - leftCircle.cx <= 0 ? leftCircle.cx:(int) moveX;
                     }

                 }

                break;

        }

         //控制两个点的位置防止越界 和圆交叉后切换控制点
        if(isTouchLeft){
            if(leftCircle.cx > rightCircle.cx){
                leftCircle.cx = rightCircle.cx;
            }else {
                if(leftCircle.cx < getPaddingLeft()+mCircleStrokeWidth+mCircleStrokeWidth){
                    leftCircle.cx = getPaddingLeft()+mCircleStrokeWidth+mCircleRadius;
                }
                if(leftCircle.cx > getWidth() - getPaddingRight() - mCircleStrokeWidth - mCircleRadius){
                    leftCircle.cx = getWidth() - getPaddingRight() - mCircleRadius -mCircleStrokeWidth;
                }
            }
        }else {

            if(rightCircle.cx < leftCircle.cx){
                rightCircle.cx = leftCircle.cx;
            }else {
                if (rightCircle.cx > getWidth() - getPaddingRight()-mCircleRadius- mCircleStrokeWidth){
                    rightCircle.cx = getWidth() - getPaddingRight()-mCircleRadius- mCircleStrokeWidth;
                }

                if(rightCircle.cx < getPaddingLeft() + mCircleStrokeWidth + mCircleRadius){
                    rightCircle.cx = getPaddingLeft() + mCircleRadius+ mCircleStrokeWidth;
                }
            }

        }


        postInvalidate();
        return true;
    }

    private boolean checkIsTouchLeftCircle(float mtouchX) {
        if(Math.abs(leftCircle.cx - mtouchX) > Math.abs(rightCircle.cx - mtouchX)){
            return false;
        }else{
            return true;
        }
    }
}
