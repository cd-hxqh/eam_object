package cdhxqh.shekou.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cdhxqh.shekou.R;
import cdhxqh.shekou.model.WorkOrder;

/**
 * Created by think on 2015/10/29.
 */
public class Work_detailsActivity extends BaseActivity {

    private TextView titlename;
    private ImageView menuImageView;
    private RelativeLayout backlayout;
    private PopupWindow popupWindow;

    /**
     * 工作计划*
     */
    private LinearLayout planLinearlayout;
    /**
     * 任务分配*
     */
    private LinearLayout taskLinearLayout;
    /**
     * 实际情况
     */
    private LinearLayout realinfoLinearLayout;
    /**
     * 故障汇报*
     */
    private LinearLayout reportLinearLayout;

    private WorkOrder workOrder;
    private LinearLayout work_numlayout;
    private TextView wonum;//工单号
    private EditText description;//工单描述
    private TextView worktype;//工作类型
    private TextView assetnum;//设备
    private TextView woeq1;//管理组
    private TextView woeq2;//管理室
    private TextView woeq3;//管理班组
    private TextView status;//状态
    private TextView statusdate;//状态日期
    private LinearLayout work_plan_details_layout;
    private TextView jpnum;//作业计划
    private CheckBox udisjf;//是否按项目计费
    private LinearLayout pmnum_layout;
    private TextView pmnum;//预防性维护
    private TextView udcreateby;//创建人
    private TextView udcreatedate;//创建日期
    private TextView reportedby;//报告人
    private TextView reportdate;//报告日期
    private CheckBox udisjj;//是否紧急维修
    private TextView udyxj;//优先级
    private CheckBox udisaq;//是否安全
    private CheckBox udisbx;//是否保修
    private CheckBox udiscb;//是否抄表
    private LinearLayout work_plan_info_layout;
    private TextView lead;//工作执行人
//    private TextView udisplayname;//承包商负责人
    private TextView targstartdate;//计划开始时间
    private TextView targcompdate;//计划完成时间
    private LinearLayout work_real_info_layout;
    private TextView actstart;//实际开始时间
    private TextView actfinish;//实际完成时间
    private EditText udtjsj;//实际维修时间
    private EditText udtjtime;//停机时间
    private LinearLayout work_udremark_layout;
    private EditText udremark;//备注

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workdetails);
        geiIntentData();
        findViewById();
        initView();
    }

    /**
     * 获取数据*
     */
    private void geiIntentData() {
        workOrder = (WorkOrder) getIntent().getSerializableExtra("workOrder");
    }

    @Override
    protected void findViewById() {
        titlename = (TextView) findViewById(R.id.title_name);
        menuImageView = (ImageView) findViewById(R.id.title_add);
        backlayout = (RelativeLayout) findViewById(R.id.title_back);

        work_numlayout = (LinearLayout) findViewById(R.id.work_numlayout);
        wonum = (TextView) findViewById(R.id.work_wonum);
        description = (EditText) findViewById(R.id.work_description);
        worktype = (TextView) findViewById(R.id.work_worktype);
        assetnum = (TextView) findViewById(R.id.work_assetnum);
        woeq1 = (TextView) findViewById(R.id.work_glz);
        woeq2 = (TextView) findViewById(R.id.work_gls);
        woeq3 = (TextView) findViewById(R.id.work_glbz);
        status = (TextView) findViewById(R.id.work_status);
        statusdate = (TextView) findViewById(R.id.work_statusdate);

        work_plan_details_layout = (LinearLayout) findViewById(R.id.work_plan_details);
        jpnum = (TextView) findViewById(R.id.work_jpnum);
        udisjf = (CheckBox) findViewById(R.id.work_isjf);
        pmnum_layout = (LinearLayout) findViewById(R.id.work_pmnum_layout);
        pmnum = (TextView) findViewById(R.id.work_pmnum);
        udcreateby = (TextView) findViewById(R.id.work_udcreateby);
        udcreatedate = (TextView) findViewById(R.id.work_udcreatedate);
        reportedby = (TextView) findViewById(R.id.work_reportedby);
        reportdate = (TextView) findViewById(R.id.work_reportdate);
        udisjj = (CheckBox) findViewById(R.id.work_udisjj);
        udyxj = (TextView) findViewById(R.id.work_udyxj);
        udisaq = (CheckBox) findViewById(R.id.work_udisaq);
        udisbx = (CheckBox) findViewById(R.id.work_udisbx);
        udiscb = (CheckBox) findViewById(R.id.work_udiscb);

        work_plan_info_layout = (LinearLayout) findViewById(R.id.work_plan_info);
        lead = (TextView) findViewById(R.id.work_lead);
//        udisplayname = (TextView) findViewById(R.id.work_udisplayname);
        targstartdate = (TextView) findViewById(R.id.work_targstartdate);
        targcompdate = (TextView) findViewById(R.id.work_targcompdate);
        work_real_info_layout = (LinearLayout) findViewById(R.id.work_real_info);
        actstart = (TextView) findViewById(R.id.work_actstart);
        actfinish = (TextView) findViewById(R.id.work_actfinish);
        udtjsj = (EditText) findViewById(R.id.work_udtjsj);
        udtjtime = (EditText) findViewById(R.id.work_udtjtime);
        work_udremark_layout = (LinearLayout) findViewById(R.id.work_udremark_layout);
        udremark = (EditText) findViewById(R.id.work_udremark);
    }

    @Override
    protected void initView() {
        titlename.setText(getResources().getString(R.string.work_details));
        backlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        menuImageView.setImageResource(R.drawable.ic_drawer);
        menuImageView.setVisibility(View.VISIBLE);
        menuImageView.setOnClickListener(menuImageViewOnClickListener);


        wonum.setText(workOrder.wonum);
        description.setText(workOrder.description);
        worktype.setText(workOrder.worktype);
        assetnum.setText(workOrder.assetnum);
        woeq1.setText(workOrder.woeq1);
        woeq2.setText(workOrder.woeq2);
        woeq3.setText(workOrder.woeq3);
        status.setText(workOrder.status);
        statusdate.setText(workOrder.statusdate);

        jpnum.setText(workOrder.jpnum);
        udisjf.setChecked(ischeck(workOrder.udisjf));
        pmnum.setText(workOrder.pmnum);
//        yfwh.setText(ischeck(workOrder.udyfwh));
        udcreateby.setText(workOrder.udcreateby);
        udcreatedate.setText(workOrder.udcreatedate);
        reportedby.setText(workOrder.reportedby);
        reportdate.setText(workOrder.reportdate);
        udisjj.setChecked(ischeck(workOrder.udisjj));
        udyxj.setText(workOrder.udyxj);
        udisaq.setChecked(ischeck(workOrder.udisaq));
        udisbx.setChecked(ischeck(workOrder.udisbx));
        udiscb.setChecked(ischeck(workOrder.udiscb));
        lead.setText(workOrder.lead);
//        udisplayname.setText(workOrder.udisplayname);
        targstartdate.setText(workOrder.targstartdate.equals("null") ? "" : workOrder.targstartdate);
        targcompdate.setText(workOrder.targcompdate.equals("null") ? "" : workOrder.targcompdate);
        actstart.setText(workOrder.udactstart.equals("null") ? "" : workOrder.udactstart);
        actfinish.setText(workOrder.udactfinish.equals("null") ? "" : workOrder.udactfinish);
        udtjsj.setText(workOrder.udtjsj);
        udtjtime.setText(workOrder.udtjtime);
        udremark.setText(workOrder.udremark);

        setLayout();
    }

    //按照工单类型修改布局
    private void setLayout() {
        switch (workOrder.worktype) {
            case "CM"://故障工单
                break;
            case "EM"://抢修工单
                break;
            case "EV"://事故工单
                break;
            case "PJ"://项目工单
                break;
            case "PM"://预防性维护工单
                pmnum_layout.setVisibility(View.VISIBLE);
                break;
            case "RS"://可维修备件工单
                break;
            case "SR"://状态维修工单
                break;
        }
    }

    //生成菜单
    private void decisionLayout() {
        switch (workOrder.worktype) {
            case "CM"://故障工单
                break;
            case "EM"://抢修工单
                break;
            case "EV"://事故工单
                break;
            case "PJ"://项目工单
                planLinearlayout.setVisibility(View.GONE);
                taskLinearLayout.setVisibility(View.GONE);
                reportLinearLayout.setVisibility(View.GONE);
                break;
            case "PM"://预防性维护工单
                pmnum_layout.setVisibility(View.VISIBLE);
                planLinearlayout.setVisibility(View.GONE);
                taskLinearLayout.setVisibility(View.GONE);
                reportLinearLayout.setVisibility(View.GONE);
                break;
            case "RS"://可维修备件工单
                break;
            case "SR"://状态维修工单
                planLinearlayout.setVisibility(View.GONE);
                taskLinearLayout.setVisibility(View.GONE);
                reportLinearLayout.setVisibility(View.GONE);
                break;
        }
    }

    /**
     * 根据工单信息确定是否勾选
     *
     * @param string
     * @return
     */
    private boolean ischeck(String string) {
        if (string.equals("1")) {
            return true;
        } else {
            return false;
        }
    }

    private View.OnClickListener menuImageViewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            showPopupWindow(menuImageView);
        }
    };

    /**
     * 初始化showPopupWindow*
     */
    private void showPopupWindow(View view) {

        // 一个自定义的布局，作为显示的内容
        View contentView = LayoutInflater.from(Work_detailsActivity.this).inflate(
                R.layout.work_popup_window, null);


        popupWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setTouchable(true);
        popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setTouchInterceptor(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {


                return false;
                // 这里如果返回true的话，touch事件将被拦截
                // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
            }
        });

        // 如果不设置PopupWindow的背景，无论是点击外部区域还是Back键都无法dismiss弹框
        // 我觉得这里是API的一个bug
        popupWindow.setBackgroundDrawable(getResources().getDrawable(
                R.drawable.popup_background_mtrl_mult));

        // 设置好参数之后再show
        popupWindow.showAsDropDown(view);

        planLinearlayout = (LinearLayout) contentView.findViewById(R.id.work_plan_id);
        taskLinearLayout = (LinearLayout) contentView.findViewById(R.id.work_task_id);
        realinfoLinearLayout = (LinearLayout) contentView.findViewById(R.id.work_realinfo_id);
        reportLinearLayout = (LinearLayout) contentView.findViewById(R.id.work_report_id);
        planLinearlayout.setOnClickListener(planOnClickListener);
        taskLinearLayout.setOnClickListener(taskOnClickListener);
        realinfoLinearLayout.setOnClickListener(realinfoOnClickListener);
        reportLinearLayout.setOnClickListener(reportOnClickListener);
        decisionLayout();

    }

    private View.OnClickListener planOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(Work_detailsActivity.this, Work_PlanActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("workOrder", workOrder);
            intent.putExtras(bundle);
            startActivity(intent);
            popupWindow.dismiss();
        }
    };

    private View.OnClickListener taskOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(Work_detailsActivity.this, AssignmentActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("workOrder", workOrder);
            intent.putExtras(bundle);
            startActivity(intent);
            popupWindow.dismiss();
        }
    };

    private View.OnClickListener realinfoOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(Work_detailsActivity.this, Work_RealinfoActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("workOrder", workOrder);
            intent.putExtras(bundle);
            startActivity(intent);
            popupWindow.dismiss();
        }
    };

    private View.OnClickListener reportOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(Work_detailsActivity.this, Work_FailurereportActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("workOrder", workOrder);
            intent.putExtras(bundle);
            startActivity(intent);
            popupWindow.dismiss();
        }
    };
}
