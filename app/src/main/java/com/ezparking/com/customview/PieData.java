package com.ezparking.com.customview;

/**
 * Created by zyh
 */

public class PieData {

    private float value;
    private float angle;
    private float percent;
    private int color = 0;

    public PieData(float value) {
        this.value = value;

    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public float getPercent() {
        return percent;
    }

    public void setPercent(float percent) {
        this.percent = percent;
    }
}
