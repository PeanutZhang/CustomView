package com.ezparking.com.customview.acts;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.ezparking.com.customview.R;
import com.ezparking.com.customview.views.EffectView;

public class EffectActivity extends AppCompatActivity implements View.OnClickListener {

    protected Button satrt;
    protected EffectView effect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_effect);
        initView();

    }

    private void initView() {
        satrt = (Button) findViewById(R.id.satrt_);
        satrt.setOnClickListener(EffectActivity.this);
        effect = (EffectView) findViewById(R.id.effect);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.satrt_) {
          effect.moveYourBody();
        }
    }
}
