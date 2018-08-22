package com.ezparking.com.customview.acts;

import android.animation.ValueAnimator;
import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ezparking.com.customview.R;

import javax.crypto.Cipher;

public class AnimationL1Activity extends AppCompatActivity implements View.OnClickListener {

    protected TextView btnStartAniSet;
    protected TextView tvAniSet,btn2;
    private Animation animationSet;
    private Button botton;
    private TranslateAnimation transAnimation;
    private ValueAnimator animator;
    private TextView tv_circ;
    private ValueAnimator animator2;
    private int htop;
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

        switch (view.getId()){
            case R.id.btn_startAniSet:
                tvAniSet.startAnimation(animationSet);
                btn2.startAnimation(transAnimation);
                break;
            case R.id.tv_btn2:
                Toast.makeText(getBaseContext(),"clicked me ",Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_start_property:
                animator.start();
                break;
        }

    }

    private void initView() {
        btnStartAniSet = (TextView) findViewById(R.id.btn_startAniSet);
        btnStartAniSet.setOnClickListener(AnimationL1Activity.this);
        tvAniSet = (TextView) findViewById(R.id.tv_aniSet);
        btn2 = findViewById(R.id.tv_btn2);
        botton = findViewById(R.id.btn_start_property);
        tv_circ = findViewById(R.id.tv_show);

        btn2.setOnClickListener(this);
        botton.setOnClickListener(this);
        animationSet = AnimationUtils.loadAnimation(this, R.anim.anim_set_01);
        animationSet.setDuration(3000);


        transAnimation = new TranslateAnimation(0,60,0,320);
        transAnimation.setDuration(2000);
        transAnimation.setFillAfter(true);

        animator = ValueAnimator.ofInt(0,320);
        animator.setDuration(2000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
              int offset = (int) animation.getAnimatedValue();
              btn2.layout(offset,offset,offset+btn2.getWidth(),offset+btn2.getHeight());
            }
        });

        tv_circ.post(new Runnable() {
           @Override
           public void run() {
               htop = tv_circ.getTop();
               Log.e("zyh","measure top--> "+ htop);
               animator2 = ValueAnimator.ofFloat(htop,600f+htop);
               animator2.setDuration(2000)
                       .setRepeatMode(ValueAnimator.REVERSE);
               animator2.setRepeatCount(ValueAnimator.INFINITE);

               animator2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                   @Override
                   public void onAnimationUpdate(ValueAnimator animation) {
                       float curOffset = (float) animation.getAnimatedValue();
                       tv_circ.layout(tv_circ.getLeft(), (int) (curOffset),
                               tv_circ.getRight(), (int) (tv_circ.getHeight()+ curOffset)
                       );
                   }
               });
           }
       });

    }

    public void startAnimation(View view) {
        switch (view.getId()){
            case R.id.btn_property2:
              animator2.start();
            break;

            case R.id.cancel_animation:
                animator2.cancel();
                break;


        }
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
