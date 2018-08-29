package com.ezparking.com.customview.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zyh on 2018/8/28.
 */

public class TextBaseLine extends View {

    private int baseLineX = 0;
    private int baseLineY = 400;
    private Paint paintLine,paintText;

    public TextBaseLine(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {

        paintLine = new Paint();
        paintLine.setAntiAlias(true);
        paintLine.setStrokeWidth(3);
        paintLine.setColor(Color.BLACK);

        paintText = new Paint();
        paintText.setTextSize(120);
        paintText.setStrokeWidth(3);
        paintText.setAntiAlias(true);
        paintText.setColor(Color.BLUE);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paintLine.setColor(Color.BLACK);
        canvas.drawLine(baseLineX,baseLineY,1600,baseLineY,paintLine);


        paintText.setTextAlign(Paint.Align.LEFT);//指定x所对应矩形的位置
        // x , y   y指的是文字基线的位置， x则表示所绘文字所在的矩形的相对位置
        canvas.drawText("张玉浩zhangyuhao",baseLineX,baseLineY,paintText);

        //计算四条线 ascent descent top bottom
        Paint.FontMetrics fontMetrics = paintText.getFontMetrics();
        float asscent = baseLineY + fontMetrics.ascent;
        float descent = baseLineY + fontMetrics.descent;
        float top = baseLineY + fontMetrics.top;
        float bottom = baseLineY + fontMetrics.bottom;
        float center = asscent + (descent - asscent)/2;

          paintLine.setColor(Color.GREEN);
          canvas.drawLine(baseLineX,asscent,1600,asscent,paintLine);
        canvas.drawLine(baseLineX,descent,1600,descent,paintLine);
        paintLine.setColor(Color.BLUE);
        canvas.drawLine(baseLineX,top,1600,top,paintLine);
        canvas.drawLine(baseLineX,bottom,1600,bottom,paintLine);
        paintLine.setColor(Color.parseColor("#ff00ff"));
        canvas.drawLine(baseLineX,center,1600,center,paintLine);
    }
}
