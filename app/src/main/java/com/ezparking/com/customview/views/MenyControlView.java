package com.ezparking.com.customview.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Region;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by zyh
 */

public class MenyControlView extends View {

    private Paint mPaint;
    private Path path_up, path_right, path_bottom, path_left, path_center;
    private Matrix mMaptrix;
    private int mWidth, mHeight;
    private Region region_up, region_right, region_bottom, region_left, region_center;

    private float down_x = -1;
    private float down_y = -1;

    private final int LEFT = 1;
    private final int UP = 2;
    private final int RIGHT = 3;
    private final int BOTTOM = 5;
    private final int CENTER = 0;

    private int mCurrentFlag = -1;
    private int mTouchFlag = -1;

    private int normalColor = Color.parseColor("#4D5266");
    private int selectColor = Color.parseColor("#DE9D81");

    private MenuClickListener menuClickListener;


    public MenyControlView(Context context) {
        this(context, null);
    }

    public MenyControlView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(normalColor);
        mPaint.setStrokeCap(Paint.Cap.ROUND);

        path_left = new Path();
        path_up = new Path();
        path_right = new Path();
        path_bottom = new Path();
        path_center = new Path();

        region_up = new Region();
        region_right = new Region();
        region_bottom = new Region();
        region_left = new Region();
        region_center = new Region();

        mMaptrix = new Matrix();

    }


    public void setMenuClickListener(MenuClickListener menuListener){
        this.menuClickListener = menuListener;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mWidth = w;
        mHeight = h;

        Region global_circle = new Region(-w, -h, w, h);//裁剪边界

        int minWidth = w > h ? h : w;
        minWidth *= 0.8f;
        int br = minWidth / 2;
        RectF bigCircle = new RectF(-br, -br, br, br);

        int sr = minWidth / 4;
        RectF smallCircle = new RectF(-sr, -sr, sr, sr);

        path_center.addCircle(0, 0, minWidth * 0.2f, Path.Direction.CW);
        region_center.setPath(path_center,global_circle);

        float bigSweepAngle = 84;
        float smallSweepAngle = -80;
        path_right.addArc(bigCircle, -40, bigSweepAngle);
        path_right.arcTo(smallCircle, 40, smallSweepAngle);
        path_right.close();
        region_right.setPath(path_right, global_circle);//将path 添加到region中

        path_bottom.addArc(bigCircle, 50, bigSweepAngle);
        path_bottom.arcTo(smallCircle, 130, smallSweepAngle);
        path_bottom.close();
        region_bottom.setPath(path_bottom, global_circle);

        path_left.addArc(bigCircle, 140, bigSweepAngle);
        path_left.arcTo(smallCircle, 220, smallSweepAngle);
        path_left.close();
        region_left.setPath(path_left, global_circle);

        path_up.addArc(bigCircle, 230, bigSweepAngle);
        path_up.arcTo(smallCircle, 310, smallSweepAngle);
        path_up.close();
        region_up.setPath(path_up, global_circle);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.translate(mWidth / 2, mHeight / 2);

        canvas.getMatrix().invert(mMaptrix);//获取逆矩阵

        canvas.drawPath(path_center, mPaint);
        canvas.drawPath(path_right, mPaint);
        canvas.drawPath(path_bottom, mPaint);
        canvas.drawPath(path_left, mPaint);
        canvas.drawPath(path_up, mPaint);

        //绘制触摸按钮的颜色
        mPaint.setColor(selectColor);
        if (mCurrentFlag == LEFT) {
            canvas.drawPath(path_left, mPaint);
        } else if (mCurrentFlag == UP) {
            canvas.drawPath(path_up, mPaint);
        } else if (mCurrentFlag == RIGHT) {
            canvas.drawPath(path_right, mPaint);
        } else if (mCurrentFlag == BOTTOM) {
            canvas.drawPath(path_bottom, mPaint);
        } else if (mCurrentFlag == CENTER) {
            canvas.drawPath(path_center, mPaint);
        }
        mPaint.setColor(normalColor);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        float[] pos = new float[2];
        pos[0] = event.getRawX();
        pos[1] = event.getRawY();
        mMaptrix.mapPoints(pos);//坐标转换
        int x = (int) pos[0];
        int y = (int) pos[1];
        int action = event.getActionMasked();

        switch (action) {

            case MotionEvent.ACTION_DOWN:
                mTouchFlag = calcuateTouchRegion(x, y);
                mCurrentFlag = mTouchFlag;
                break;
            case MotionEvent.ACTION_MOVE:
                mTouchFlag = calcuateTouchRegion(x, y);
                break;
            case MotionEvent.ACTION_UP:

                mTouchFlag = calcuateTouchRegion(x, y);
                if (mCurrentFlag == mTouchFlag && mCurrentFlag != -1 && menuClickListener != null) {
                    if (mCurrentFlag == CENTER) {
                        menuClickListener.onMenuCenterClick();
                    } else if (mCurrentFlag == LEFT) {
                        menuClickListener.onMenuLeftClick();
                    } else if (mCurrentFlag == UP) {
                        menuClickListener.onMenuUpClick();
                    } else if (mCurrentFlag == RIGHT) {
                        menuClickListener.onMenuRightClick();
                    } else if (mCurrentFlag == BOTTOM) {
                        menuClickListener.onMenuBottomClick();
                    }
                }
                mTouchFlag = mCurrentFlag = -1;
                break;
            case MotionEvent.ACTION_CANCEL:
                mTouchFlag = mCurrentFlag = -1;
                break;
        }
           invalidate();
        return true;
    }

    private int calcuateTouchRegion(int x, int y) {

        if (region_center.contains(x, y)) {
            return CENTER;
        } else if (region_left.contains(x, y)) {
            return LEFT;
        } else if (region_up.contains(x, y)) {
            return UP;
        } else if (region_right.contains(x, y)) {
            return RIGHT;
        } else if (region_bottom.contains(x, y)) {
            return BOTTOM;
        }
        return -1;
    }

   public interface MenuClickListener {
        void onMenuLeftClick();

        void onMenuUpClick();

        void onMenuRightClick();

        void onMenuBottomClick();

        void onMenuCenterClick();
    }

}
