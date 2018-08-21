package com.ezparking.com.customview.acts;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.ezparking.com.customview.R;
import com.ezparking.com.customview.views.MenyControlView;

public class MenuControlActivity extends AppCompatActivity {

    protected MenyControlView menuControlview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_menu_control);
        initView();
    }

    private void initView() {
        menuControlview = (MenyControlView) findViewById(R.id.menu_controlview);
        menuControlview.setMenuClickListener(new MenyControlView.MenuClickListener() {
            @Override
            public void onMenuLeftClick() {
                Toast.makeText(getBaseContext(),"click the left menut",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onMenuUpClick() {
              Toast.makeText(getBaseContext(),"click the up menu ",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onMenuRightClick() {
                Toast.makeText(getBaseContext(),"click the Right menu ",Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onMenuBottomClick() {
                Toast.makeText(getBaseContext(),"click the Bottom menu ",Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onMenuCenterClick() {
                Toast.makeText(getBaseContext(),"click the Center menu ",Toast.LENGTH_SHORT).show();

            }
        });
    }
}
