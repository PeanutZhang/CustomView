package com.ezparking.com.customview.views;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;

import java.util.ArrayList;

/**
 * Created by zyh
 */

public class FootballFieldView extends View {

    private Paint mLinePaint;
    private Path mPath,measurePathdst;
    private final float MUN = 12.0f;//(1 4.5)
    private int mWidth,mHeight;
    private PathMeasure pathMeasure;
    private float mPathPercent;
    private ValueAnimator mAnimator;
    private boolean isAnimation;
    private float mPathPathLength;
    private ArrayList<Float> pathLengths = new ArrayList<>();

    public FootballFieldView(Context context) {
        this(context,null);
    }

    public FootballFieldView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {

        mLinePaint = new Paint();
        mLinePaint.setColor(Color.WHITE);
        mLinePaint.setStrokeWidth(12);
        mLinePaint.setStyle(Paint.Style.STROKE);
        mLinePaint.setStrokeCap(Paint.Cap.ROUND);
        mLinePaint.setAntiAlias(true);
        mPath = new Path();
        measurePathdst = new Path();

        initlatizePath();

        mAnimator = ValueAnimator.ofFloat(0,1);
        mAnimator.setInterpolator(new LinearInterpolator());
        mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mPathPercent = (float) animation.getAnimatedValue()/m;
                invalidate();
            }
        });
        mAnimator.setDuration(5000);
        mAnimator.setRepeatCount(2);


    }


    public void startAnimator(){

        if(isAnimation){
            if(mAnimator.isRunning()){
                mAnimator.pause();
            }
        }else {
            mAnimator.start();
        }
        isAnimation = !isAnimation;
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
        //点球点
        canvas.drawPoint(0,-38f*MUN,mLinePaint);
        canvas.drawPoint(0,38f*MUN,mLinePaint);
        canvas.drawPath(mPath,mLinePaint);
    }

    private void initlatizePath() {
        RectF outsideRecF = new RectF(-34*MUN,-52*MUN,34*MUN,52*MUN);
        mPath.addRect(outsideRecF, Path.Direction.CW);
        mPath.moveTo(-34*MUN,0);
        mPath.lineTo(34*MUN,0);

        RectF centerCircle = new RectF(-9.15f*MUN,-9.15f*MUN,9.15f*MUN,9.15f*MUN);
        mPath.addCircle(0,0,9.15f*MUN, Path.Direction.CW);

        //球门--
        RectF ballDoorBottom = new RectF(-5f*MUN,48.56f*MUN,5f*MUN,52*MUN);
        RectF ballDoorTop = new RectF(-5f*MUN,-52*MUN,5f*MUN,-48.56f*MUN);
        mPath.addRect(ballDoorBottom, Path.Direction.CW);
        mPath.addRect(ballDoorTop, Path.Direction.CW);

        //大禁区
        RectF bigForbidTop = new RectF(-20f*MUN,-52*MUN,20f*MUN,-32f*MUN);
        RectF bigForbidBottom = new RectF(-20f*MUN,32f*MUN,20f*MUN,52*MUN);
        mPath.addRect(bigForbidTop, Path.Direction.CW);
        mPath.addRect(bigForbidBottom, Path.Direction.CW);

        //小禁区
        RectF smallForbidTop = new RectF(-10f*MUN,-52*MUN,10f*MUN,-42f*MUN);
        RectF smallForbidBottom = new RectF(-10f*MUN,42f*MUN,10f*MUN,52f*MUN);
        mPath.addRect(smallForbidTop, Path.Direction.CW);
        mPath.addRect(smallForbidBottom, Path.Direction.CW);


        //上点球弧--
        Path pathRec = new Path();
        pathRec.addRect(bigForbidTop, Path.Direction.CW);
        Path path2 = new Path();
        path2.addCircle(0,-38f*MUN,9.15f*MUN, Path.Direction.CW);
        path2.op(pathRec, Path.Op.DIFFERENCE);
        mPath.addPath(path2);

        //下点球弧
        Path path3 = new Path();
        path3.addRect(bigForbidBottom, Path.Direction.CW);
        Path path4 = new Path();
        path4.addCircle(0,38f*MUN,9.15f*MUN, Path.Direction.CW);
        path4.op(path3, Path.Op.DIFFERENCE);
        mPath.addPath(path4);
        pathMeasure = new PathMeasure(mPath,false);
        while (pathMeasure.nextContour()){
          mPathPathLength += pathMeasure.getLength();
          pathLengths.add( pathMeasure.getLength());
          m++;
            Log.e("zyh","pahtleng--->  "+mPathPathLength+ "  m + . "+m);
        }
    }
    int m = 0;
}
