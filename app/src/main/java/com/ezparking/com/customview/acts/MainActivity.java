package com.ezparking.com.customview.acts;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.ezparking.com.customview.R;
import com.ezparking.com.customview.views.WaveView;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lv;
    String[] titles = {"Canvas之绘制基本形状","Canvas画布操作","Canvas画图片和文字","Path初步","贝塞尔曲线","用Beizer画一个圆，和自定义View","WaveView水波效果",
            "自定义圆指示器","PathMeasure练习","Path和Matrix绘制遥控器按钮","足球场绘制","androidx workmanger使用","动画1"};
    Class[] clazzs = {Canvas_01Activity.class,CavasOperationActivity.class,DrawPic_bmpActivity.class,
    PatheViewActivity.class,BeizerViewActivity.class, BezierView2Activity.class,WaveViewActivity.class,IndicatorCircleActivity.class,
           PathMeasureActivity.class,MenuControlActivity.class,FootballFieldActivity.class,WorkMangerActivity.class,AnimationL1Activity.class
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = findViewById(R.id.listview);

        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,titles);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               MainActivity.this.startActivity(new Intent(MainActivity.this,clazzs[position]));
            }
        });


    }
}
