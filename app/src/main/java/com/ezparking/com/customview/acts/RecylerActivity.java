package com.ezparking.com.customview.acts;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.ezparking.com.customview.R;
import com.ezparking.com.customview.adapter.RecAdapter;

import java.util.ArrayList;
import java.util.List;

public class RecylerActivity extends AppCompatActivity {

    protected RecyclerView recyclerview;
    private List<String> datas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_recyler);
        initData();
        initView();

    }

    private void initData() {

        datas = new ArrayList<>(52);
        Log.e("zyh","before  add  data.size--> "+datas.size());
        for (int i = 0; i < 56; i++) {
            datas.add("<" + i +">");
        }
        Log.e("zyh","after  add  data.size--> "+datas.size());
    }

    private void initView() {
        recyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerview.setLayoutManager(layoutManager);
        RecAdapter adapter = new RecAdapter(datas);
        recyclerview.setAdapter(adapter);
    }
}
