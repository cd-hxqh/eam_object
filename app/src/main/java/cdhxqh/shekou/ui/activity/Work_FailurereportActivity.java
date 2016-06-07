package cdhxqh.shekou.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import cdhxqh.shekou.Dao.FailurelistDao;
import cdhxqh.shekou.R;
import cdhxqh.shekou.api.HttpManager;
import cdhxqh.shekou.api.HttpRequestHandler;
import cdhxqh.shekou.api.JsonUtils;
import cdhxqh.shekou.bean.Results;
import cdhxqh.shekou.config.Constants;
import cdhxqh.shekou.model.Failurereport;
import cdhxqh.shekou.model.Option;
import cdhxqh.shekou.model.WorkOrder;
import cdhxqh.shekou.ui.widget.SwipeRefreshLayout;

/**
 * Created by think on 2015/11/11.
 * 故障汇报页面
 */
public class Work_FailurereportActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {

    private TextView titlename;
    private ImageView menuImageView;
    private RelativeLayout backlayout;
    String type;//类型
    private String question_text;
    private String cause_text;
    private String rememdy_text;
    private TextView question;//问题
    private TextView cause;//原因
    private TextView rememdy;//补救措施
    private String failurelist;
    private SwipeRefreshLayout refresh_layout = null;
    private WorkOrder workOrder;
    private boolean ishistory;//是否是从主表传过来的信息
    private Button confirm;//确定

    private ArrayList<Failurereport> failurereportList = new ArrayList<>();
    private ArrayList<Failurereport> failurereportList1 = new ArrayList<>();//修改前信息

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
        failurereportList = (ArrayList<Failurereport>) getIntent().getSerializableExtra("failurereportList");
        type = workOrder.failurecode;
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
        confirm = (Button) findViewById(R.id.confirm);
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

        refresh_layout.setOnRefreshListener(this);

        confirm.setOnClickListener(confirmOnClickListener);

        if (failurereportList == null||failurereportList.size()==0) {
            refresh_layout.setRefreshing(true);
            getdata();
            ishistory = false;
        }else {
            addListData(failurereportList);
            ishistory = true;
        }

        question.setOnClickListener(new LayoutOnClickListener(Constants.FAILURE_QUESTION, type));
        cause.setOnClickListener(new LayoutOnClickListener(Constants.FAILURE_CAUSE, question.getText().toString()));
        rememdy.setOnClickListener(new LayoutOnClickListener(Constants.FAILURE_REMEMDY, rememdy.getText().toString()));
    }

    private void getdata() {
        HttpManager.getData(this, HttpManager.getfailurereportUrl(workOrder.worktype, workOrder.wonum), new HttpRequestHandler<Results>() {
            @Override
            public void onSuccess(Results results) {
                Log.i(TAG, "data=" + results);
                ArrayList<Failurereport> failurereports = JsonUtils.parsingFailurereport(Work_FailurereportActivity.this, results.getResultlist());
                addListData(failurereports);
                failurereportList = failurereports;
                failurereportList1 = failurereports;
                refresh_layout.setRefreshing(false);
            }

            @Override
            public void onSuccess(Results results, int currentPage, int showcount) {

            }

            @Override
            public void onFailure(String error) {
                refresh_layout.setRefreshing(false);
            }
        });
    }

    private void addListData(ArrayList<Failurereport> list) {
        if (list.size() != 0) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).type.equals("问题")) {
                    question.setText(list.get(i).failurecode);
                    question_text = list.get(i).failurecode;
                } else if (list.get(i).type.equals("原因")) {
                    cause.setText(list.get(i).failurecode);
                    cause_text = list.get(i).failurecode;
                } else if (list.get(i).type.equals("补救措施")) {
                    rememdy.setText(list.get(i).failurecode);
                    rememdy_text = list.get(i).failurecode;
                }
            }
        }
    }

    private View.OnClickListener confirmOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = getIntent();
//            if(question_text.equals(question.getText().toString())
//                    &&cause_text.equals(cause.getText().toString())
//                    &&rememdy_text.equals(rememdy.getText().toString())) {//如果内容没有修改
//                intent.putExtra("failurereportList",failurereportList);
//            }else {
//                Failurereport woactivity = getWoactivity();
//                if(woactivity.optiontype==null||!woactivity.optiontype.equals("add")) {
//                    woactivity.optiontype = "update";
//                }
                intent.putExtra("failurereportList", getFailurereportList());
                Toast.makeText(Work_FailurereportActivity.this, "任务本地修改成功", Toast.LENGTH_SHORT).show();
//            }
//            intent.putExtra("position", position);
            Work_FailurereportActivity.this.setResult(3000, intent);
            finish();
        }
    };

    private ArrayList<Failurereport> getFailurereportList(){
//        if(question_text.equals(question.getText().toString())
//                &&cause_text.equals(cause.getText().toString())
//                &&rememdy_text.equals(rememdy.getText().toString())) {//如果内容没有修改
//            return failurereportList;
//        }else{
            ArrayList<Failurereport> failurereports = new ArrayList<>();
            Failurereport failurereport;
            if (!question_text.equals(question.getText().toString())){
                if (!question.getText().toString().equals("")){
                    failurereport = getfailurereport("问题");
                    failurereport.failurecode = question.getText().toString();
                    failurereports.add(failurereport);
                }else {
                    return failurereports;
                }
            }
            if (!cause_text.equals(cause.getText().toString())){
                if (!cause.getText().toString().equals("")){
                    failurereport = getfailurereport("原因");
                    failurereport.failurecode = cause.getText().toString();
                    failurereports.add(failurereport);
                }else {
                    return failurereports;
                }
            }
            if (!rememdy_text.equals(rememdy.getText().toString())){
                if (!rememdy.getText().toString().equals("")){
                    failurereport = getfailurereport("补救措施");
                    failurereport.failurecode = rememdy.getText().toString();
                    failurereports.add(failurereport);
                }else {
                    return failurereports;
                }
            }
            return failurereports;
//        }
    }

    private Failurereport getfailurereport(String type){
        if (failurereportList.size() != 0) {
            for (int i = 0; i < failurereportList.size(); i++) {
                if (failurereportList.get(i).type.equals(type)) {
                    return failurereportList.get(i);
                }
            }
        }
        return null;
    }

    private class LayoutOnClickListener implements View.OnClickListener {
        int requestCode;
        String failurecode;

        private LayoutOnClickListener(int requestCode) {
            this.requestCode = requestCode;
        }

        private LayoutOnClickListener(int requestCode, String failurecode) {
            this.requestCode = requestCode;
            this.failurecode = failurecode;
        }

        @Override
        public void onClick(View view) {
            if (requestCode == Constants.FAILURE_QUESTION && type.equals("")) {
                Toast.makeText(Work_FailurereportActivity.this, "请选择类型", Toast.LENGTH_SHORT).show();
            } else if (requestCode == Constants.FAILURE_CAUSE && question.getText().toString().equals("")) {
                Toast.makeText(Work_FailurereportActivity.this, "请选择问题", Toast.LENGTH_SHORT).show();
            } else if (requestCode == Constants.FAILURE_REMEMDY && cause.getText().toString().equals("")) {
                Toast.makeText(Work_FailurereportActivity.this, "请选择原因", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(Work_FailurereportActivity.this, OptionActivity.class);
                intent.putExtra("requestCode", requestCode);
                if (requestCode == Constants.FAILURE_QUESTION) {
                    intent.putExtra("failurecode", failurecode);
                } else if (requestCode == Constants.FAILURE_CAUSE) {
                    intent.putExtra("failurecode", failurelist == null ? getCauseList() : failurelist);
                } else if (requestCode == Constants.FAILURE_REMEMDY) {
                    intent.putExtra("failurecode", failurelist == null ? getRememdyList() : failurelist);
                }
                startActivityForResult(intent, requestCode);
            }
        }
    }

    //当故障数据原本存在，只修改原因时得到failurelist
    private String getCauseList() {
        String parent = new FailurelistDao(Work_FailurereportActivity.this).queryForParent(type,"");//第一层类型的parent
        return new FailurelistDao(this).queryForParent(question.getText().toString(),parent);
    }

    //当故障数据原本存在，只修改措施时得到failurelist
    private String getRememdyList() {
        String parent = new FailurelistDao(Work_FailurereportActivity.this).queryForParent(type,"");//第一层类型的parent
        String parent2 = new FailurelistDao(this).queryForParent(question.getText().toString(), parent);//第二次原因的parent
        return new FailurelistDao(this).queryForParent(cause.getText().toString(),parent2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Option option;
        switch (resultCode) {
            case Constants.FAILURE_QUESTION:
                option = (Option) data.getSerializableExtra("option");
                question.setText(option.getName());
                cause.setText("");
                rememdy.setText("");
                failurelist = option.getValue();
                break;
            case Constants.FAILURE_CAUSE:
                option = (Option) data.getSerializableExtra("option");
                cause.setText(option.getName());
                rememdy.setText("");
                failurelist = option.getValue();
                break;
            case Constants.FAILURE_REMEMDY:
                option = (Option) data.getSerializableExtra("option");
                rememdy.setText(option.getName());
//                failurelist = option.getValue();
                break;
        }
    }

    //下拉刷新触发事件
    @Override
    public void onRefresh() {
        refresh_layout.setRefreshing(true);
        getdata();
    }
}
