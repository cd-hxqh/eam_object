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
 * 新建工单
 */
public class Work_AddNewActivity extends BaseActivity {

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
     * 工单汇报*
     */
    private LinearLayout reportLinearLayout;

    private WorkOrder workOrder = new WorkOrder();
    private TextView wonum;//工单号
    private EditText description;//工单描述
    private TextView glz;//管理组
    private TextView gls;//管理室
    private TextView glbz;//管理班组
    private TextView status;//状态
    private TextView statusdate;//状态日期
    private TextView jpnum;//作业计划
    private CheckBox udisjf;//是否按项目计费
    private CheckBox yfwh;//预防性维护
    private TextView reportedby;//报告人
    private TextView reportdate;//报告日期
    private CheckBox udisjj;//是否紧急维修
    private CheckBox udisaq;//是否安全
    private CheckBox udisbx;//是否保修
    private CheckBox udiscb;//是否抄表
    private TextView sdisplayname;//工作执行人
    private TextView udisplayname;//承包商负责人
    private TextView targstartdate;//计划开始时间
    private TextView targcompdate;//计划完成时间
    private TextView actstart;//实际开始时间
    private TextView actfinish;//实际完成时间
    private EditText udtjsj;//停机时间
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_work);
        geiIntentData();
        findViewById();
        initView();
    }

    /**
     * 获取数据*
     */
    private void geiIntentData() {
        String s = getIntent().getExtras().getString("worktype");
        workOrder.worktype = getIntent().getExtras().getString("worktype");
    }
    @Override
    protected void findViewById() {
        titlename = (TextView) findViewById(R.id.title_name);
        menuImageView = (ImageView) findViewById(R.id.title_add);
        backlayout = (RelativeLayout) findViewById(R.id.title_back);

        wonum = (TextView) findViewById(R.id.work_wonum);
        description = (EditText) findViewById(R.id.work_description);
        glz = (TextView) findViewById(R.id.work_glz);
        gls = (TextView) findViewById(R.id.work_gls);
        glbz = (TextView) findViewById(R.id.work_glbz);
        status = (TextView) findViewById(R.id.work_status);
        statusdate = (TextView) findViewById(R.id.work_statusdate);

        jpnum = (TextView) findViewById(R.id.work_jpnum);
        udisjf = (CheckBox) findViewById(R.id.work_isjf);
        yfwh = (CheckBox) findViewById(R.id.work_yfwh);
        reportedby = (TextView) findViewById(R.id.work_reportedby);
        reportdate = (TextView) findViewById(R.id.work_reportdate);
        udisjj = (CheckBox) findViewById(R.id.work_udisjj);
        udisaq = (CheckBox) findViewById(R.id.work_udisaq);
        udisbx = (CheckBox) findViewById(R.id.work_udisbx);
        udiscb = (CheckBox) findViewById(R.id.work_udiscb);
        sdisplayname = (TextView) findViewById(R.id.work_sdisplayname);
        udisplayname = (TextView) findViewById(R.id.work_udisplayname);
        targstartdate = (TextView) findViewById(R.id.work_targstartdate);
        targcompdate = (TextView) findViewById(R.id.work_targcompdate);
        actstart = (TextView) findViewById(R.id.work_actstart);
        actfinish = (TextView) findViewById(R.id.work_actfinish);
        udtjsj = (EditText) findViewById(R.id.work_udtjsj);
    }

    @Override
    protected void initView() {
        titlename.setText(getResources().getString(R.string.work_new));
        backlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        menuImageView.setImageResource(R.drawable.ic_drawer);
        menuImageView.setVisibility(View.VISIBLE);
        menuImageView.setOnClickListener(menuImageViewOnClickListener);

    }

    /**
     * 根据工单信息确定是否勾选
     * @param string
     * @return
     */
    private boolean ischeck(String string){
        if(string.equals("1")){
            return true;
        }else {
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
        View contentView = LayoutInflater.from(Work_AddNewActivity.this).inflate(
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
        reportLinearLayout = (LinearLayout) contentView.findViewById(R.id.work_report_id);
        planLinearlayout.setOnClickListener(planOnClickListener);
        taskLinearLayout.setOnClickListener(taskOnClickListener);
        reportLinearLayout.setOnClickListener(reportOnClickListener);

    }

    private View.OnClickListener planOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(Work_AddNewActivity.this,Work_PlanActivity.class);
            Bundle bundle = new Bundle();
            bundle.putParcelable("workOrder", workOrder);
            intent.putExtras(bundle);
            startActivity(intent);
            startActivity(intent);
        }
    };

    private View.OnClickListener taskOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(Work_AddNewActivity.this,AssignmentActivity.class);
            Bundle bundle = new Bundle();
            bundle.putParcelable("workOrder", workOrder);
            intent.putExtras(bundle);
            startActivity(intent);
            startActivity(intent);
        }
    };

    private View.OnClickListener reportOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

        }
    };
}
