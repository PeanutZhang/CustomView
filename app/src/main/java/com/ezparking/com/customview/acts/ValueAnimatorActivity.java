package com.ezparking.com.customview.acts;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ViewUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.ezparking.com.customview.R;
import com.ezparking.com.customview.Utils;

public class ValueAnimatorActivity extends AppCompatActivity implements View.OnClickListener {

    protected Button bt1;
    protected Button bt2;
    protected Button bt3;
    protected Button bt4;
    protected Button strong;
    protected Button center;
    private boolean isExpand;
    private int mRadius =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_value_animator);
        initView();
        mRadius= Utils.dip2px(this,150f);
        Log.e("zyh","---mRadius-->  "+mRadius);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.center){
            if(isExpand){
                isExpand = false;
                doCloseAnimation(bt1,4);
                doCloseAnimation(bt2,3);
                doCloseAnimation(bt3,2);
                doCloseAnimation(bt4,1);
                doCloseAnimation(strong,0);
            }else {
                isExpand = true;
                doOpenAnimation(bt1,4);
                doOpenAnimation(bt2,3);
                doOpenAnimation(bt3,2);
                doOpenAnimation(bt4,1);
                doOpenAnimation(strong,0);
            }

        }else {
            Toast.makeText(this,"点击了-》 "+view,Toast.LENGTH_SHORT).show();
            isExpand = false;
            doCloseAnimation(bt1,4);
            doCloseAnimation(bt2,3);
            doCloseAnimation(bt3,2);
            doCloseAnimation(bt4,1);
            doCloseAnimation(strong,0);
        }
    }

    private void doOpenAnimation(Button btn ,int index){

        if(btn.getVisibility() == View.GONE){
            btn.setVisibility(View.VISIBLE);
        }
        double degree = Math.PI/2 * index/4;
        int offsetX = -(int)(mRadius * Math.sin(degree));
        int offsetY = -(int)(mRadius * Math.cos(degree));
        ObjectAnimator translateX = ObjectAnimator.ofFloat(btn,"translationX",0,offsetX);
        ObjectAnimator translateY = ObjectAnimator.ofFloat(btn,"translationY",0,offsetY);
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(btn,"scaleX",0,1f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(btn,"scaleY",0,1f);
        ObjectAnimator alpha = ObjectAnimator.ofFloat(btn,"alpha",0,1f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(300);
        animatorSet.playTogether(translateX,translateY,scaleX,scaleY,alpha);
        animatorSet.start();
    }
    private void doCloseAnimation(Button btn,int index){


        if(btn.getVisibility() == View.GONE){
            btn.setVisibility(View.VISIBLE);
        }
        double degree = Math.PI/2 * index/4;
        int offsetX = -(int)(mRadius * Math.sin(degree));
        int offsetY = -(int)(mRadius * Math.cos(degree));


        ObjectAnimator translateX = ObjectAnimator.ofFloat(btn,"translationX",offsetX,0);
        ObjectAnimator translateY = ObjectAnimator.ofFloat(btn,"translationY",offsetY,0);
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(btn,"scaleX",1f,0.1f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(btn,"scaleY",1f,0.1f);
        ObjectAnimator alpha = ObjectAnimator.ofFloat(btn,"alpha",1f,0);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(300);
        animatorSet.playTogether(translateX,translateY,scaleX,scaleY,alpha);
        animatorSet.start();

    }





    private void initView() {
        bt1 = (Button) findViewById(R.id.bt1);
        bt1.setOnClickListener(ValueAnimatorActivity.this);
        bt2 = (Button) findViewById(R.id.bt2);
        bt2.setOnClickListener(ValueAnimatorActivity.this);
        bt3 = (Button) findViewById(R.id.bt3);
        bt3.setOnClickListener(ValueAnimatorActivity.this);
        bt4 = (Button) findViewById(R.id.bt4);
        bt4.setOnClickListener(ValueAnimatorActivity.this);
        strong = (Button) findViewById(R.id.strong);
        strong.setOnClickListener(ValueAnimatorActivity.this);
        center = (Button) findViewById(R.id.center);
        center.setOnClickListener(ValueAnimatorActivity.this);
    }
}
