package com.ezparking.com.customview.acts;

import android.service.carrier.CarrierService;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ezparking.com.customview.R;
import com.ezparking.com.customview.views.CusAnimateView;


public class DrawPic_bmpActivity extends AppCompatActivity implements View.OnClickListener{

    Button btn1, btn2 ;
    private CusAnimateView cusAnimateView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_pic_bmp);
        btn1 = findViewById(R.id.check);
        btn2 = findViewById(R.id.uncheck);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        cusAnimateView = findViewById(R.id.cusanimate);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.check:
               cusAnimateView.check();
                break;
            case R.id.uncheck:
                cusAnimateView.unCheck();
                break;
        }




    }


}
