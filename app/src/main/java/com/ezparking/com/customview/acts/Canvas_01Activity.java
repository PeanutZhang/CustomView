package com.ezparking.com.customview.acts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ezparking.com.customview.PieData;
import com.ezparking.com.customview.R;
import com.ezparking.com.customview.views.PieView;

import java.util.ArrayList;

public class Canvas_01Activity extends AppCompatActivity implements View.OnClickListener{

     private ArrayList<PieData> mdatas;
    private PieView pieView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas_01);

        Button button = findViewById(R.id.setPieData);
        pieView = findViewById(R.id.zyhpie);
        button.setOnClickListener(this);


        float value = 30;
        mdatas = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            PieData data = new PieData(value);
            value += 30;
            mdatas.add(data);
        }
    }

    @Override
    public void onClick(View v) {
        pieView.setData(mdatas);
    }
}
