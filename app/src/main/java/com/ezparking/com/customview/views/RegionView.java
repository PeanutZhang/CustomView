package com.ezparking.com.customview.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.RegionIterator;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zyh on 2018/8/28.
 */

public class RegionView extends View {

    private Paint rectPint,regionPaint;
    private Rect rect1,rect2;

    public RegionView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {

        rectPint = new Paint();
        rectPint.setColor(Color.GREEN);
        rectPint.setStyle(Paint.Style.STROKE);
        rectPint.setStrokeWidth(3);
        rectPint.setAntiAlias(true);

        regionPaint = new Paint();
        regionPaint.setColor(Color.RED);
        regionPaint.setStyle(Paint.Style.FILL);
        regionPaint.setAntiAlias(true);

        rect1 = new Rect(200,200,500,300);
        rect2 = new Rect(300,100,400,400);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawRect(rect1,rectPint);
        canvas.drawRect(rect2,rectPint);


        Path ovalPath = new Path();
        ovalPath.addOval(new RectF(400,600,600,1600), Path.Direction.CCW);

          Region reg = new Region();
          Region region = new Region(400,600,600,1600);
          reg.setPath(ovalPath,region);
        drawRegion(canvas,reg,rectPint);


        Region region1 = new Region(rect1);
        Region region2 = new Region(rect2);



//        region1.op(region2, Region.Op.INTERSECT);//相交区域
//         region1.op(region2, Region.Op.DIFFERENCE);//未相交区域补集
//         region1.op(region2, Region.Op.REPLACE);//替换
//         region1.op(region2, Region.Op.REVERSE_DIFFERENCE);//翻转补集
           region1.op(region2, Region.Op.XOR);//异并集
           drawRegion(canvas,region1,regionPaint);


    }

    private void drawRegion(Canvas canvas, Region region1, Paint regionPaint) {

        RegionIterator iterator = new RegionIterator(region1);
        Rect rect = new Rect();
        while (iterator.next(rect)){
            canvas.drawRect(rect,regionPaint);
        }

    }
}
