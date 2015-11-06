package cdhxqh.shekou.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import cdhxqh.shekou.R;
import cdhxqh.shekou.model.Assignment;
import cdhxqh.shekou.model.Woactivity;

/**
 * Created by think on 2015/11/6.
 * 任务分配详情页面
 */
public class AssignmentDetailsActivity extends BaseActivity {
    /**
     * 返回按钮
     */
    private ImageView backImageView;
    /**
     * 标题
     */
    private TextView titleTextView;

    private Assignment assignment = new Assignment();
    private TextView taskid; //任务
    private TextView laborcode; //员工
    private TextView craftcode; //工种
    private TextView skilllevel; //技能级别
    private TextView contractnum; //员工合同
    private TextView vendor; //供应商
    private TextView scheduledate; //调度开始时间
    private TextView laborhrs;//时数
    private TextView status;//状态
    private TextView orgid; //组织
    private TextView siteid; //地点
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment_details);

        geiIntentData();
        findViewById();
        initView();
    }

    private void geiIntentData() {
        assignment = (Assignment) getIntent().getParcelableExtra("assignment");
    }
    @Override
    protected void findViewById() {
        backImageView = (ImageView) findViewById(R.id.title_back_id);
        titleTextView = (TextView) findViewById(R.id.title_name);

        taskid = (TextView) findViewById(R.id.work_assinment_taskid);
        laborcode = (TextView) findViewById(R.id.work_assinment_laborcode);
        craftcode = (TextView) findViewById(R.id.work_assinment_craftcode);
        skilllevel = (TextView) findViewById(R.id.work_assinment_skilllevel);
        contractnum = (TextView) findViewById(R.id.work_assinment_contractnum);
        vendor = (TextView) findViewById(R.id.work_assinment_vendor);
        scheduledate = (TextView) findViewById(R.id.work_assinment_scheduledate);
        laborhrs = (TextView) findViewById(R.id.work_assinment_laborhrs);
        status = (TextView) findViewById(R.id.work_assinment_status);
        orgid = (TextView) findViewById(R.id.work_assinment_orgid);
        siteid = (TextView) findViewById(R.id.work_assinment_siteid);
    }

    @Override
    protected void initView() {
        backImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        titleTextView.setText(getResources().getString(R.string.title_activity_workplandetails));

        taskid.setText(assignment.taskid);
        laborcode.setText(assignment.laborcode);
        craftcode.setText(assignment.craftcode);
        skilllevel.setText(assignment.skilllevel);
        contractnum.setText(assignment.contractnum);
        vendor.setText(assignment.vendor);
        scheduledate.setText(assignment.scheduledate);
        laborhrs.setText(assignment.laborhrs);
        status.setText(assignment.status);
        orgid.setText(assignment.orgid);
        siteid.setText(assignment.siteid);
    }
}
