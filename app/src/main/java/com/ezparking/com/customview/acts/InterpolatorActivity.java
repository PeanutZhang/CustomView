package com.ezparking.com.customview.acts;

import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ezparking.com.customview.R;
import com.ezparking.com.customview.animations.CharEvaluator;

public class InterpolatorActivity extends AppCompatActivity {

    protected Button start;
    protected Button stop;
    protected TextView charShow;
    private ValueAnimator mcharAnimator;
    private ObjectAnimator objectAnimator;
    private ImageView ivPhone;
    private ValueAnimator animatorValuesHolders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_interpolator);
        initView();
        initAnimation();
    }

    private void initAnimation() {

        mcharAnimator = ValueAnimator.ofObject(new CharEvaluator(),new Character('A'),new Character('Z'));
        mcharAnimator.setDuration(10*1000);
        mcharAnimator.setRepeatCount(5);
        mcharAnimator.setRepeatMode(ValueAnimator.REVERSE);
        mcharAnimator.setInterpolator(new AccelerateInterpolator());
        mcharAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                char curChar = (char) animation.getAnimatedValue();
                charShow.setText(String.valueOf(curChar));
            }
        });
        objectAnimator = ObjectAnimator.ofFloat(charShow,"translationY",0,800,0);
        objectAnimator.setDuration(5*1000);
        objectAnimator.setInterpolator(new LinearInterpolator());


        initPhoneRingring();

    }

    private void initPhoneRingring() {


        // init roate animation
        Keyframe keyframeRote0 = Keyframe.ofFloat(0,0f);
        Keyframe keyframeRote1 = Keyframe.ofFloat(0.1f,20f);
        Keyframe keyframeRote2 = Keyframe.ofFloat(0.2f,-20f);
        Keyframe keyframeRote3 = Keyframe.ofFloat(0.3f,20f);
        Keyframe keyframeRote4 = Keyframe.ofFloat(0.4f,-20f);
        Keyframe keyframeRote5 = Keyframe.ofFloat(0.5f,20f);
        Keyframe keyframeRote6 = Keyframe.ofFloat(0.6f,-20f);
        Keyframe keyframeRote7 = Keyframe.ofFloat(0.7f,20f);
        Keyframe keyframeRote8 = Keyframe.ofFloat(0.8f,-20f);
        Keyframe keyframeRote9 = Keyframe.ofFloat(0.9f,20f);
        Keyframe keyframeRote10 = Keyframe.ofFloat(1.0f,0f);
        PropertyValuesHolder roateValuesHolder = PropertyValuesHolder.ofKeyframe("rotation",keyframeRote0,keyframeRote1,keyframeRote2,keyframeRote3,keyframeRote4,
                keyframeRote5,keyframeRote6,keyframeRote6,keyframeRote7,keyframeRote8,keyframeRote9,keyframeRote10);


      //
        Keyframe scaleXframe0 = Keyframe.ofFloat(0,1f);
        Keyframe scaleXframe1 = Keyframe.ofFloat(0.1f,1.1f);
        Keyframe scaleXframe2 = Keyframe.ofFloat(0.2f,1.1f);
        Keyframe scaleXframe3 = Keyframe.ofFloat(0.3f,1.1f);
        Keyframe scaleXframe4 = Keyframe.ofFloat(0.4f,1.1f);
        Keyframe scaleXframe5 = Keyframe.ofFloat(0.5f,1.1f);
        Keyframe scaleXframe6 = Keyframe.ofFloat(0.6f,1.1f);
        Keyframe scaleXframe7 = Keyframe.ofFloat(0.7f,1.1f);
        Keyframe scaleXframe8 = Keyframe.ofFloat(0.8f,1.1f);
        Keyframe scaleXframe9 = Keyframe.ofFloat(0.9f,1.1f);
        Keyframe scaleXframe10 = Keyframe.ofFloat(1f,1f);
        PropertyValuesHolder scaleXValuesHolder = PropertyValuesHolder.ofKeyframe("scaleX",scaleXframe0,scaleXframe1,scaleXframe2,scaleXframe3,scaleXframe4,scaleXframe5,scaleXframe6,scaleXframe7,scaleXframe8,scaleXframe9,scaleXframe10);


        Keyframe scaleYframe0 = Keyframe.ofFloat(0,1f);
        Keyframe scaleYframe1 = Keyframe.ofFloat(0.1f,1.1f);
        Keyframe scaleYframe2 = Keyframe.ofFloat(0.2f,1.1f);
        Keyframe scaleYframe3 = Keyframe.ofFloat(0.3f,1.1f);
        Keyframe scaleYframe4 = Keyframe.ofFloat(0.4f,1.1f);
        Keyframe scaleYframe5 = Keyframe.ofFloat(0.5f,1.1f);
        Keyframe scaleYframe6 = Keyframe.ofFloat(0.6f,1.1f);
        Keyframe scaleYframe7 = Keyframe.ofFloat(0.7f,1.1f);
        Keyframe scaleYframe8 = Keyframe.ofFloat(0.8f,1.1f);
        Keyframe scaleYframe9 = Keyframe.ofFloat(0.9f,1.1f);
        Keyframe scaleYXframe10 = Keyframe.ofFloat(1f,1f);
        PropertyValuesHolder scaleYValuesHolder = PropertyValuesHolder.ofKeyframe("ScaleY",scaleYframe0,scaleYframe1,scaleYframe2,scaleYframe3,scaleYframe4,scaleYframe5,scaleYframe6,scaleYframe7,scaleYframe8,scaleYframe9,scaleYXframe10);


        animatorValuesHolders = ObjectAnimator.ofPropertyValuesHolder(ivPhone,roateValuesHolder, scaleXValuesHolder, scaleYValuesHolder);
        animatorValuesHolders.setDuration(2000);
        animatorValuesHolders.setInterpolator(new AccelerateInterpolator());
        animatorValuesHolders.setRepeatCount(5);


    }

    public void interpolatorAnimator(View view) {
            switch (view.getId()){

                case R.id.start_:
                    mcharAnimator.start();
                    break;
                case R.id.stop_:
                    mcharAnimator.cancel();
                    break;
                case R.id.btn_objectAnimator:
                    objectAnimator.start();
                    break;
                 case R.id.btn_phoneRingring:
                     animatorValuesHolders.start();
                     break;
            }

    }

    private void initView() {
        start = (Button) findViewById(R.id.start_);
        stop = (Button) findViewById(R.id.stop_);
        charShow = (TextView) findViewById(R.id.char_show);
        ivPhone = findViewById(R.id.image_phone);

    }
}
