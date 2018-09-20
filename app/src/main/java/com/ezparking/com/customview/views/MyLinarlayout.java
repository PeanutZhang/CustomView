package com.ezparking.com.customview.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by zyh
 */

public class MyLinarlayout extends ViewGroup {


    public MyLinarlayout(Context context) {
        super(context);
    }

    public MyLinarlayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

       int widthMode = MeasureSpec.getMode(widthMeasureSpec);
       int width = MeasureSpec.getSize(widthMeasureSpec);
       int heightMode = MeasureSpec.getMode(heightMeasureSpec);
       int height = MeasureSpec.getSize(heightMeasureSpec);


       int childswidth = 0;
       int childsHeight = 0;
       int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
           MarginLayoutParams mlp = (MarginLayoutParams) child.getLayoutParams();
           measureChild(child,widthMeasureSpec,heightMeasureSpec);
           int childWidth = child.getMeasuredWidth()+mlp.leftMargin+mlp.rightMargin;
           int childHeight = child.getMeasuredHeight()+mlp.topMargin+mlp.bottomMargin;
           childswidth += childWidth;
           childsHeight += childHeight;
        }
        setMeasuredDimension((widthMode == MeasureSpec.EXACTLY)?width:childswidth,
                (heightMode == MeasureSpec.EXACTLY)?height:childsHeight);

     }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        int top =0;
        int childCount = getChildCount();
        for (int i = 0; i < childCount ; i++) {

            View child = getChildAt(i);
            MarginLayoutParams mlp = (MarginLayoutParams) child.getLayoutParams();
            int width = child.getMeasuredWidth();
            int height = child.getMeasuredHeight();
            child.layout(mlp.leftMargin,top+mlp.topMargin,width+mlp.leftMargin,height+mlp.topMargin);
            top +=height+mlp.bottomMargin;
        }



    }



 }
