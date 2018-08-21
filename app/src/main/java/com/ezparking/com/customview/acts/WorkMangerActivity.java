package com.ezparking.com.customview.acts;

import android.arch.lifecycle.LiveData;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.ezparking.com.customview.R;
import com.ezparking.com.customview.datas.Constant;
import com.ezparking.com.customview.model.ZyhWork;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;
import androidx.work.WorkStatus;

public class WorkMangerActivity extends AppCompatActivity implements View.OnClickListener {

    protected Button startOntimework;
    protected Button stopOnework;
    protected Button getworkdata;
    protected Button startPeriodwork;
    protected Button stopPeriodwork;
    protected Button getPeriodworkdata;
    private WorkManager mWorkManger;
    private WorkRequest oneWorker;
    private UUID onworkId;
    private PeriodicWorkRequest periodWork;
    private UUID periodUuid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_work_manger);
        initView();
        initWorkerManger();

    }

    private void initWorkerManger() {
        mWorkManger = WorkManager.getInstance();
        Constraints constraints = new Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build();
        Data inputData = new Data.Builder()
                .putString(Constant.KEY_1, "i'm from workMangerAct msg")
                .putInt(Constant.KEY_2, 568555)
                .build();
        oneWorker = new OneTimeWorkRequest.Builder(ZyhWork.class)
                .setInitialDelay(500, TimeUnit.MILLISECONDS)//设置延迟时间
                .setInputData(inputData)//传递参数
                .setConstraints(constraints)
                .build();
        onworkId = oneWorker.getId();

        periodWork = new PeriodicWorkRequest.Builder(ZyhWork.class, 5 * 1000, TimeUnit.MILLISECONDS)//有个坑啊，源码中最少间隔是15分钟 吓死我了
                .setInputData(new Data.Builder().putInt(Constant.KEY_2, Constant.PERIOD).build())
                .build();
        periodUuid = periodWork.getId();

    }


    private void initView() {
        startOntimework = (Button) findViewById(R.id.start_ontimework);
        stopOnework = (Button) findViewById(R.id.stop_onework);
        startOntimework.setOnClickListener(this);
        stopOnework.setOnClickListener(this);
        getworkdata = (Button) findViewById(R.id.getworkdata);
        getworkdata.setOnClickListener(this);
        startPeriodwork = (Button) findViewById(R.id.start_periodwork);
        startPeriodwork.setOnClickListener(this);
        stopPeriodwork = (Button) findViewById(R.id.stop_periodwork);
        stopPeriodwork.setOnClickListener(this);
        getPeriodworkdata = (Button) findViewById(R.id.get_periodworkdata);
        getPeriodworkdata.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.start_ontimework:
                mWorkManger.enqueue(oneWorker);
                break;
            case R.id.stop_onework:
                mWorkManger.cancelAllWork();
                break;
            case R.id.getworkdata:
                Log.e("zyh", "getWorkData is going----------");
                LiveData<WorkStatus> statusData = mWorkManger.getStatusById(onworkId);
                WorkStatus status = statusData.getValue();
                if (status != null) {
                    Data outputData = status.getOutputData();
                    int outInt = outputData.getInt(Constant.KEY_3, 0);
                    String outInfo = outputData.getString(Constant.KEY_5, "");
                    Log.e("zyh", "workstatus.getData--- int= " + outInt + "  outstring--> " + outInfo);
                }
                break;

            case R.id.start_periodwork:
                mWorkManger.enqueue(periodWork);
                break;
            case R.id.stop_periodwork:
                mWorkManger.cancelWorkById(periodUuid);
                break;
            case R.id.get_periodworkdata:
                Log.e("zyh", "getperiodWorkData is going----------");
                LiveData<WorkStatus> statusData2 = mWorkManger.getStatusById(periodUuid);

                WorkStatus status2 = statusData2.getValue();
                if (status2 != null) {
                    Data outputData = status2.getOutputData();
                    int outInt = outputData.getInt(Constant.KEY_3, 0);
                    String outInfo = outputData.getString(Constant.KEY_5, "");
                    Log.e("zyh", "workstatus.getData--- int= " + outInt + "  outstring--> " + outInfo);
                }
                break;
        }


    }
}
