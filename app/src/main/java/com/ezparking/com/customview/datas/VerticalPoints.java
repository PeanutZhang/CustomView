package com.ezparking.com.customview.datas;

import android.graphics.PointF;

/**
 * Created by zyh
 */

public class VerticalPoints {

    private final float C = 0.551915024494f;

    public PointF topPoi = new PointF();
    public PointF middlePoi = new PointF();
    public PointF bottomPoi = new PointF();

    public VerticalPoints(float x, float y) {
        topPoi.x = x;
        topPoi.y = - y * C;
        middlePoi.x = x;
        middlePoi.y = 0;

        bottomPoi.x = x;
        bottomPoi.y = y * C;
    }

    public void setX(float x){
        topPoi.x = x;
        middlePoi.x =x;
        bottomPoi.x = x;
    }

}
