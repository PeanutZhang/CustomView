package com.ezparking.com.customview.acts;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.ezparking.com.customview.R;
import com.ezparking.com.customview.views.FootballFieldView;

public class FootballFieldActivity extends AppCompatActivity {

    protected Button startDraw;
    protected FootballFieldView footballfieldview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_football_field);
        initView();
    }

    private void initView() {
        startDraw = (Button) findViewById(R.id.start_draw);
        footballfieldview = (FootballFieldView) findViewById(R.id.footballfieldview);
        startDraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                footballfieldview.startAnimator();
            }
        });
    }
}
