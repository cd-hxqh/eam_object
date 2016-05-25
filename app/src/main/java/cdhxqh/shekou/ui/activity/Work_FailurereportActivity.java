package cdhxqh.shekou.ui.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import cdhxqh.shekou.R;
import cdhxqh.shekou.api.HttpManager;
import cdhxqh.shekou.api.HttpRequestHandler;
import cdhxqh.shekou.api.JsonUtils;
import cdhxqh.shekou.bean.Results;
import cdhxqh.shekou.model.Failurereport;
import cdhxqh.shekou.model.WorkOrder;
import cdhxqh.shekou.ui.widget.SwipeRefreshLayout;

/**
 * Created by think on 2015/11/11.
 * 故障汇报页面
 */
public class Work_FailurereportActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener{

    private TextView titlename;
    private ImageView menuImageView;
    private RelativeLayout backlayout;
    private TextView type;
    private TextView question;
    private TextView cause;
    private TextView rememdy;
    private SwipeRefreshLayout refresh_layout = null;
    private WorkOrder workOrder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_report);

        geiIntentData();
        findViewById();
        initView();
    }

    private void geiIntentData() {
        workOrder = (WorkOrder) getIntent().getSerializableExtra("workOrder");
    }
    @Override
    protected void findViewById() {
        titlename = (TextView) findViewById(R.id.title_name);
        menuImageView = (ImageView) findViewById(R.id.title_add);
        backlayout = (RelativeLayout) findViewById(R.id.title_back);
        question = (TextView) findViewById(R.id.work_failurereport_question);
        cause = (TextView) findViewById(R.id.work_failurereport_cause);
        rememdy = (TextView) findViewById(R.id.work_failurereport_rememdy);
        refresh_layout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
    }

    @Override
    protected void initView() {
        titlename.setText(getResources().getString(R.string.work_report));
        backlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        refresh_layout.setColor(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        refresh_layout.setRefreshing(true);
        refresh_layout.setOnRefreshListener(this);

        getdata();
    }

    private void getdata() {
        HttpManager.getData(this, HttpManager.getfailurereportUrl(workOrder.worktype, workOrder.wonum), new HttpRequestHandler<Results>() {
            @Override
            public void onSuccess(Results results) {
                Log.i(TAG, "data=" + results);
            }

            @Override
            public void onSuccess(Results results, int currentPage, int showcount) {
                ArrayList<Failurereport> failurereports = JsonUtils.parsingFailurereport(Work_FailurereportActivity.this, results.getResultlist());
                addListData(failurereports);
                refresh_layout.setRefreshing(false);
            }

            @Override
            public void onFailure(String error) {
                refresh_layout.setRefreshing(false);
            }
        });
    }

    private void addListData(ArrayList<Failurereport> list) {
        if (list.size() == 3) {
            for(int i = 0;i < list.size();i ++){
                if(list.get(i).type.equals("问题")){
                    question.setText(list.get(i).failurecode);
                }else if(list.get(i).type.equals("原因")){
                    cause.setText(list.get(i).failurecode);
                }else if(list.get(i).type.equals("补救措施")){
                    rememdy.setText(list.get(i).failurecode);
                }
            }
        }
    }

    //下拉刷新触发事件
    @Override
    public void onRefresh() {
        getdata();
    }
}
