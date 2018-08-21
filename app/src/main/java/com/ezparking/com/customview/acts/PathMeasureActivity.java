package com.ezparking.com.customview.acts;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.ezparking.com.customview.R;
import com.ezparking.com.customview.views.ArrowCircle;

public class PathMeasureActivity extends AppCompatActivity {

    protected Button startmeasure;
    protected ArrowCircle meaurepathview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_path_measure);
        initView();
    }

    private void initView() {
        startmeasure = (Button) findViewById(R.id.startmeasure);
        meaurepathview = (ArrowCircle) findViewById(R.id.meaurepathview);
        startmeasure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                meaurepathview.startRoate();
            }
        });
    }
}
