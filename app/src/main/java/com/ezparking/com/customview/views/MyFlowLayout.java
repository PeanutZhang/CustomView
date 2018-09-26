package com.ezparking.com.customview.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by zyh
 */

public class MyFlowLayout extends ViewGroup {

    public MyFlowLayout(Context context) {
        super(context);
    }

    public MyFlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

       int measureWidth = MeasureSpec.getSize(widthMeasureSpec);
       int widthMode = MeasureSpec.getMode(widthMeasureSpec);
       int measureHeight = MeasureSpec.getSize(heightMeasureSpec);
       int heightMode = MeasureSpec.getMode(heightMeasureSpec);

       int lineWidth =0;
       int lineHeight= 0;
       int width = 0;
       int height = 0;
       int childCounts = getChildCount();
        for (int i = 0; i < childCounts; i++) {

            View child = getChildAt(i);
            measureChildWithMargins(child,widthMeasureSpec,0,heightMeasureSpec,0);
            MarginLayoutParams mlp = (MarginLayoutParams) child.getLayoutParams();

           int childwidth = child.getMeasuredWidth()+mlp.leftMargin+mlp.rightMargin;
           int childHeiht = child.getMeasuredHeight()+mlp.topMargin+mlp.bottomMargin;

           if(lineWidth + childwidth >  measureWidth ){
               width  = Math.max(lineWidth,childwidth);
               height += lineHeight;

               lineWidth = childwidth;
               lineHeight = childHeiht;

           }else {
               lineWidth += childwidth;
               height = Math.max(lineHeight,childHeiht);
           }
          if(i == childCounts -1){
               width = Math.max(lineWidth,childwidth);
               height += childHeiht;
          }

        }

      setMeasuredDimension((widthMode == MeasureSpec.EXACTLY)?measureWidth:width,
                           (heightMode == MeasureSpec.EXACTLY)?measureHeight:height);

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        int chidlCounts = getChildCount();
        int lineWidth = 0;
        int lineHeight = 0;
        int left = 0;
        int top = 0;

        for (int i = 0; i < chidlCounts; i++) {

         View child = getChildAt(i);
         MarginLayoutParams mlp = (MarginLayoutParams) child.getLayoutParams();

         int childwidth = child.getMeasuredWidth()+mlp.leftMargin+mlp.rightMargin;
         int childHeight = child.getMeasuredHeight()+mlp.topMargin+mlp.bottomMargin;

         if(childwidth + lineWidth > getMeasuredWidth()){

            left = 0;
            top += childHeight;
            lineHeight = childHeight;
            lineWidth = childwidth;

         }else {
             lineWidth += childwidth;
             lineHeight = Math.max(lineHeight,childHeight);
         }

         int lc = left+mlp.leftMargin;
         int tc = top +mlp.topMargin;
         int rc = lc+child.getMeasuredWidth();
         int bc = tc + child.getMeasuredHeight();

         child.layout(lc,tc,rc,bc);
         left += childwidth;
        }
    }


    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(),attrs);
    }

    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new MarginLayoutParams(p);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new MarginLayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
    }
}
