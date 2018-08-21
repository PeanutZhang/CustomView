package com.ezparking.com.customview.datas;

/**
 * Created by zyh
 */

import android.graphics.PointF;

/**
 * 把贝塞尔曲线的点坐标封装起来，水平方向的点
 */
public class HorizontalPoints {

    private final float C = 0.551915024494f;
    public PointF leftPoi = new PointF();
    public PointF middlePoi = new PointF();
    public PointF rightPoi = new PointF();

    public HorizontalPoints(float x, float y) {

        leftPoi.x = - x * C;
        leftPoi.y = y;
        middlePoi.x = 0;
        middlePoi.y = y;
        rightPoi.x = x * C;
        rightPoi.y = y;
    }

    public void setY(Float y){
        leftPoi.y = y;
        middlePoi.y = y;
        rightPoi.y = y;
    }

}
