package com.ezparking.com.customview.model;

import android.support.annotation.NonNull;
import android.util.Log;

import com.ezparking.com.customview.datas.Constant;

import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.impl.Extras;

/**
 * Created by zyh
 */

public class ZyhWork extends Worker{

    private int count;

    @NonNull
    @Override
    public Result doWork() {

        //获取传来的信息---
        Data inputData = getInputData();
        String info1 = inputData.getString(Constant.KEY_1,"");
        int m = inputData.getInt(Constant.KEY_2,0);
        Log.e("zyh","zyhworke getInputData--  info1 = "+info1 +" int = "+m);
        Log.e("zyh","doWork is going--> thread--> "+Thread.currentThread().getName());
        Log.e("zyh","dowork is going----"+count++);

        if( m !=Constant.PERIOD){
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 3; i++) {
                    try {
                        Log.e("zyh","run is going----"+i);
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }


        }).start();
        }

        //返回给信息给workmanger--
        Data outputData = new Data.Builder()
                .putString(Constant.KEY_5,"i 'm from zyhwork.java-----")
                .putInt(Constant.KEY_3,911)
                .build();
        setOutputData(outputData);
        return Result.SUCCESS;
    }

    @Override
    public void onStopped(boolean cancelled) {
        super.onStopped(cancelled);

        Log.e("zyh","zyhwork onStopped is i");
    }
}
