package com.ezparking.com.customview.acts;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.ezparking.com.customview.R;
import com.ezparking.com.customview.views.DoubleRangeBar;
import com.ezparking.com.customview.views.RangeCallBack;

public class DoubleRangeBarAct extends AppCompatActivity {

    protected TextView tvValue;
    protected DoubleRangeBar doubleRangebar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_double_range_bar);
        initView();


    }

    private void initView() {
        tvValue = (TextView) findViewById(R.id.tv_value);
        doubleRangebar = (DoubleRangeBar) findViewById(R.id.double_rangebar);
        doubleRangebar.setOnValueCallback(new RangeCallBack() {
            @Override
            public void onValueCallback(int leftValue, int rightValue) {
                tvValue.setText("leftValue= "+ leftValue + "  rightValue= "+rightValue);
            }
        });
    }
}
