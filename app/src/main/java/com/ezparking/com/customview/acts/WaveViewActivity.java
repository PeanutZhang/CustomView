package com.ezparking.com.customview.acts;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.ezparking.com.customview.R;
import com.ezparking.com.customview.views.WaveView;

public class WaveViewActivity extends AppCompatActivity {

    protected Button animationControl;
    protected WaveView waveview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_wave_view);
        initView();


    }

    private void initView() {
        animationControl = (Button) findViewById(R.id.animation_control);
        waveview = (WaveView) findViewById(R.id.waveview);
        animationControl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                waveview.startWave();
            }
        });
    }

}
