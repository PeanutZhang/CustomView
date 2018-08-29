package com.ezparking.com.customview.acts;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.ezparking.com.customview.R;
import com.ezparking.com.customview.views.WriteTextBezier;

public class WriteBeizerActivity extends AppCompatActivity implements View.OnClickListener {

    protected Button reset;
    protected WriteTextBezier writebezier;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_write_beizer);
        initView();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.reset) {
              writebezier.reset();
        }
    }

    private void initView() {
        reset = (Button) findViewById(R.id.reset);
        reset.setOnClickListener(WriteBeizerActivity.this);
        writebezier = (WriteTextBezier) findViewById(R.id.writebezier);
    }
}
