package com.ezparking.com.customview.datas

import android.graphics.PointF

/**
 * Created by zyh
 *
 */
class VerticalPointsK {

 private  val C = 0.551915024494f
     var topPoi :PointF = PointF()
    var middlePoi:PointF = PointF()
     var bottomPoi:PointF = PointF()

    constructor(x:Float,y:Float){
        topPoi.x = x
        topPoi.y = - y*C
        middlePoi.x = x
        middlePoi.y = y*C
    }

    fun setX(x:Float) {
        topPoi.x = x
        middlePoi.x =x
        bottomPoi.x = x
    }


}