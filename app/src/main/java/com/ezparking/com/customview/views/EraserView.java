package com.ezparking.com.customview.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.ezparking.com.customview.R;

/**
 * Created by zyh
 */

public class EraserView extends View {

    private Bitmap bitmapDst;
    private Bitmap bitmapSrc;
    private Paint mPaint;
    private Path mPath;
    private float mPreX, mPreY;

    public EraserView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        mPath = new Path();
        bitmapSrc = BitmapFactory.decodeResource(getResources(), R.drawable.jh, null);
        bitmapDst = Bitmap.createBitmap(bitmapSrc.getWidth(), bitmapSrc.getHeight(), Bitmap.Config.ARGB_8888);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(30);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.GREEN);


    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        int layerId = canvas.saveLayer(0, 0, getWidth(), getHeight(), null, Canvas.ALL_SAVE_FLAG);

         Canvas c = new Canvas(bitmapDst);
         c.drawPath(mPath, mPaint);

        canvas.drawBitmap(bitmapDst, 0, 0, mPaint);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT));
        canvas.drawBitmap(bitmapSrc, 0, 0, mPaint);

        mPaint.setXfermode(null);
        canvas.restoreToCount(layerId);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:

                mPath.moveTo(event.getX(), event.getY());
                mPreX = event.getX();
                mPreY = event.getY();
                Log.e("zyh", "aciontDown");
                return true;

            case MotionEvent.ACTION_MOVE:
                Log.e("zyh", "actionMove");
                float ex = (mPreX+event.getX())/2;
                float ey = (mPreY+event.getY())/2;
                mPath.quadTo(mPreX,mPreY,ex,ey);
                mPreY = event.getY();
                mPreX = event.getX();
                break;
        }

       postInvalidate();
        return super.onTouchEvent(event);
    }
}
