package com.ezparking.com.customview.acts;

        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;

        import com.ezparking.com.customview.R;
        import com.ezparking.com.customview.views.BeizerCircle;

public class BezierView2Activity extends AppCompatActivity implements View.OnClickListener{

    private BeizerCircle beizerCircleView;
    private Button bt ,bt2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bezier_view2);
        beizerCircleView = findViewById(R.id.beizercircle);
        bt = findViewById(R.id.btn_transform);
        bt2 = findViewById(R.id.start_animation);
        bt.setOnClickListener(this);
        bt2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.btn_transform:
                beizerCircleView.transformX();
                break;
            case R.id.start_animation:
                beizerCircleView.startAnimation();
                break;

        }
    }
}
