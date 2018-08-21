package com.ezparking.com.customview.acts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.ezparking.com.customview.R;
import com.ezparking.com.customview.views.SpiderRadar;

public class PatheViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pathe_view);
        final SpiderRadar spiderRadar = findViewById(R.id.spiderradar);
        findViewById(R.id.btn_refresh).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             spiderRadar.startAnimation();
            }
        });
        findViewById(R.id.stop_animation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spiderRadar.stopAnimation();
            }
        });
    }
}
