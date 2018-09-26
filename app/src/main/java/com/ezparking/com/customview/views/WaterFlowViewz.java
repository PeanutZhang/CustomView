package com.ezparking.com.customview.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by zyh
 */

public class WaterFlowViewz extends ViewGroup {


    private int mColumn =3;
    private int hSpace = 20;
    private int vSpace = 20;
    private int [] top;
    private int childWidth;

    public WaterFlowViewz(Context context) {
        this(context,null);

    }

    public WaterFlowViewz(Context context, AttributeSet attrs) {
        super(context, attrs);
        top = new int[mColumn];
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int measureWidth = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int measureHeight = MeasureSpec.getSize(heightMeasureSpec);

        int childCounts = getChildCount();
        childWidth = (measureWidth - (mColumn -1)* hSpace ) / mColumn;
        int wrapWidth;
        if(childCounts < mColumn ){
            wrapWidth = childWidth * childCounts + (childCounts - 1) * hSpace;
        }else {
            wrapWidth = measureWidth;
        }
        clearTop();
        for (int i = 0; i < childCounts; i++) {
             View child = getChildAt(i);
            int childHeight = child.getMeasuredHeight() * childWidth / child.getMeasuredHeight();
            int minColumn = getMinHeightColumn();
            top[minColumn] += childHeight + vSpace;
        }
         int wrapHeiht = getMaxHeight();
         setMeasuredDimension((widthMode == MeasureSpec.AT_MOST )?wrapWidth:measureWidth,wrapHeiht);
    }

    private void clearTop() {
        for (int i = 0; i < top.length; i++) {
            top[i] = 0;
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        int childCounts = getChildCount();
        for (int i = 0; i < childCounts; i++) {
            View child = getChildAt(i);
            int childHeight = child.getMeasuredHeight() * childWidth / child.getMeasuredWidth();
            int minColum = getMinHeightColumn();
            int tleft = minColum * (childWidth + hSpace);
            int ttop = top[minColum];
            int tright = tleft + childWidth;
            int tbottom = ttop + childHeight;
            top[minColum] += vSpace + childHeight;
            child.layout(tleft, ttop, tright, tbottom);

        }



    }

    public void setItemClickListener(final itemClickListener itemClickListener){
        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            final int finalI = i;
            view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickListener.onItemClicklistener(v, finalI);
                }
            });
        }
    }

    public interface itemClickListener{
     void  onItemClicklistener(View v,int poisition);
    }

   private int getMinHeightColumn(){
        int mcolumnIndex = 0;

       for (int i = 0; i < top.length; i++) {
           if(top[i] < top[mcolumnIndex]){
               mcolumnIndex = i;
           }
       }
        return mcolumnIndex;
   }

   private int getMaxHeight(){
       int maxHeight = 0;
       for (int i = 0; i < top.length; i++) {
           if(top[i]> maxHeight){
               maxHeight = top[i];
           }
       }
       return  maxHeight;
   }


}
