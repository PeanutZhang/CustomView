package com.ezparking.com.customview.acts;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.ezparking.com.customview.R;

public class AnimationL1Activity extends AppCompatActivity implements View.OnClickListener {

    protected TextView btnStartAniSet;
    protected TextView tvAniSet;
    private Animation animationSet;
    private Button btnChange;
    private ValueAnimator colorAnimator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_animation_l1);
        initView();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_startAniSet) {
          tvAniSet.startAnimation(animationSet);
        }

    }

    private void initView() {
        btnStartAniSet = (TextView) findViewById(R.id.btn_startAniSet);
        btnStartAniSet.setOnClickListener(AnimationL1Activity.this);
        tvAniSet = (TextView) findViewById(R.id.tv_aniSet);
        animationSet = AnimationUtils.loadAnimation(this, R.anim.anim_set_01);
        animationSet.setDuration(3000);

       btnChange = findViewById(R.id.btn_changeColor);
       btnChange.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               colorAnimator.start();
           }
       });
        colorAnimator = ValueAnimator.ofInt(0xFFFF00FF,0x550011FF);
        colorAnimator.setEvaluator(new ArgbEvaluator());
        colorAnimator.setDuration(5000);
        colorAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int color = (int) animation.getAnimatedValue();
                tvAniSet.setBackgroundColor(color);
            }
        });

    }
}
