package cdhxqh.shekou.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import cdhxqh.shekou.R;
import cdhxqh.shekou.config.Constants;
import cdhxqh.shekou.model.Option;
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
//    /**
//     * 任务分配*
//     */
//    private LinearLayout taskLinearLayout;
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
    private LinearLayout description_layout;
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
    private LinearLayout udisjj_layout;
    private TextView udyxj;//优先级
    private CheckBox udisaq;//是否安全
    private CheckBox udisbx;//是否保修
    private CheckBox udiscb;//是否抄表
    private TextView udprojapprnum;//立项编号
    private LinearLayout udprojapprnum_layout;
    private TextView udevnum;//事故号
    private LinearLayout udevnum_layout;
    private TextView udbugnum;//项目预算
    private TextView wudbugnum_text;
    private LinearLayout udbugnum_layout;
    private LinearLayout work_plan_info_layout;
    private TextView lead;//工作执行人
    private LinearLayout work_em_info_layout;
    private TextView udqxbz;//抢修班组
    private TextView lead1;//抢修负责人
    private TextView supervisor;//抢修执行人
    private TextView udsupervisor2;//抢修执行人2
    private LinearLayout work_failure_info_layout;
    private TextView failurecode;//故障子机构
    private TextView udgzlbdm;//故障类别
    private EditText udworkmemo;//工作备注
    private CheckBox udisyq;//是否跟进
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

    private Button delete;
    private Button revise;
    private Button work_flow;

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
        description_layout = (LinearLayout) findViewById(R.id.work_description_layout);
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
        udisjj_layout = (LinearLayout) findViewById(R.id.work_udisjj_layout);
        udyxj = (TextView) findViewById(R.id.work_udyxj);
        udisaq = (CheckBox) findViewById(R.id.work_udisaq);
        udisbx = (CheckBox) findViewById(R.id.work_udisbx);
        udiscb = (CheckBox) findViewById(R.id.work_udiscb);
        udprojapprnum = (TextView) findViewById(R.id.work_udprojapprnum);
        udprojapprnum_layout = (LinearLayout) findViewById(R.id.work_udprojapprnum_layout);
        udevnum = (TextView) findViewById(R.id.work_udevnum);
        udevnum_layout = (LinearLayout) findViewById(R.id.work_udevnum_layout);
        udbugnum = (TextView) findViewById(R.id.work_udbugnum);
        wudbugnum_text = (TextView) findViewById(R.id.work_udbugnum_text);
        udbugnum_layout = (LinearLayout) findViewById(R.id.work_udbugnum_layout);

        work_plan_info_layout = (LinearLayout) findViewById(R.id.work_plan_info);
        lead = (TextView) findViewById(R.id.work_lead);
        work_em_info_layout = (LinearLayout) findViewById(R.id.work_em_info);
        udqxbz = (TextView) findViewById(R.id.work_udqxbz);
        lead1 = (TextView) findViewById(R.id.work_lead1);
        supervisor = (TextView) findViewById(R.id.work_supervisor);
        udsupervisor2 = (TextView) findViewById(R.id.work_udsupervisor2);
        work_failure_info_layout = (LinearLayout) findViewById(R.id.work_failure_info);
        failurecode = (TextView) findViewById(R.id.work_failurecode);
        udgzlbdm = (TextView) findViewById(R.id.work_udgzlbdm);
        udworkmemo = (EditText) findViewById(R.id.work_udworkmemo);
        udisyq = (CheckBox) findViewById(R.id.work_udisyq);
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

        delete = (Button) findViewById(R.id.work_delete);
        revise = (Button) findViewById(R.id.work_revise);
        work_flow = (Button) findViewById(R.id.work_work_flow);
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
        udprojapprnum.setText(workOrder.udprojapprnum);
        udevnum.setText(workOrder.udevnum);
        udbugnum.setText(workOrder.udbugnum);
        lead.setText(workOrder.lead);
        udqxbz.setText(workOrder.udqxbz);
        lead1.setText(workOrder.lead);
        supervisor.setText(workOrder.supervisor);
        udsupervisor2.setText(workOrder.udsupervisor2);
        failurecode.setText(workOrder.failurecode);
        udgzlbdm.setText(workOrder.udgzlbdm);
        udworkmemo.setText(workOrder.udworkmemo);
        udisyq.setChecked(ischeck(workOrder.udisyq));
//        udisplayname.setText(workOrder.udisplayname);
        targstartdate.setText(workOrder.targstartdate.equals("null") ? "" : workOrder.targstartdate);
        targcompdate.setText(workOrder.targcompdate.equals("null") ? "" : workOrder.targcompdate);
        actstart.setText(workOrder.udactstart.equals("null") ? "" : workOrder.udactstart);
        actfinish.setText(workOrder.udactfinish.equals("null") ? "" : workOrder.udactfinish);
        udtjsj.setText(workOrder.udtjsj);
        udtjtime.setText(workOrder.udtjtime);
        udremark.setText(workOrder.udremark);

        assetnum.setOnClickListener(new LayoutOnClickListener(Constants.ASSETCODE));
        jpnum.setOnClickListener(new LayoutOnClickListener(Constants.JOBPLANCODE));
        reportedby.setOnClickListener(new LayoutOnClickListener(Constants.PERSONCODE));
        lead.setOnClickListener(new LayoutOnClickListener(Constants.LABORCODE));
        udqxbz.setOnClickListener(new LayoutOnClickListener(Constants.ALNDOMAINCODE));
        lead1.setOnClickListener(new LayoutOnClickListener(Constants.LABORCODE1));
        supervisor.setOnClickListener(new LayoutOnClickListener(Constants.LABORCODE2));
        udsupervisor2.setOnClickListener(new LayoutOnClickListener(Constants.LABORCODE3));
        udevnum.setOnClickListener(new LayoutOnClickListener(Constants.UDEVCODE));
        udprojapprnum.setOnClickListener(new LayoutOnClickListener(Constants.PROJAPPR));
        pmnum.setOnClickListener(new LayoutOnClickListener(Constants.PMCODE));
        failurecode.setOnClickListener(new LayoutOnClickListener(Constants.FAILURE_TYPE));
        udgzlbdm.setOnClickListener(new LayoutOnClickListener(Constants.ALNDOMAIN2CODE));
//        udqxbz.setOnClickListener(new );

        delete.setOnClickListener(deleteOnClickListener);
        revise.setOnClickListener(reviseOnClickListener);
        work_flow.setOnClickListener(work_flowOnClickListener);

        setLayout();
    }

    //按照工单类型修改布局
    private void setLayout() {
        switch (workOrder.worktype) {
            case "CM"://故障工单
                break;
            case "EM"://抢修工单
                udisjj_layout.setVisibility(View.GONE);
                description_layout.setVisibility(View.GONE);
                work_plan_details_layout.setVisibility(View.GONE);
                work_plan_info_layout.setVisibility(View.GONE);
                work_em_info_layout.setVisibility(View.VISIBLE);
                work_failure_info_layout.setVisibility(View.VISIBLE);
                work_udremark_layout.setVisibility(View.GONE);
                break;
            case "EV"://事故工单
                udbugnum_layout.setVisibility(View.VISIBLE);
                wudbugnum_text.setText("事故预算");
                udevnum_layout.setVisibility(View.VISIBLE);
                break;
            case "PJ"://项目工单
                udprojapprnum_layout.setVisibility(View.VISIBLE);
                udbugnum_layout.setVisibility(View.VISIBLE);
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
                reportLinearLayout.setVisibility(View.GONE);
                break;
            case "EM"://抢修工单
                planLinearlayout.setVisibility(View.GONE);
                break;
            case "EV"://事故工单
                reportLinearLayout.setVisibility(View.GONE);
                break;
            case "PJ"://项目工单
//                planLinearlayout.setVisibility(View.GONE);
//                taskLinearLayout.setVisibility(View.GONE);
                reportLinearLayout.setVisibility(View.GONE);
                break;
            case "PM"://预防性维护工单
//                pmnum_layout.setVisibility(View.VISIBLE);
                planLinearlayout.setVisibility(View.GONE);
//                taskLinearLayout.setVisibility(View.GONE);
                reportLinearLayout.setVisibility(View.GONE);
                break;
            case "RS"://可维修备件工单
                reportLinearLayout.setVisibility(View.GONE);
                break;
            case "SR"://状态维修工单
                planLinearlayout.setVisibility(View.GONE);
//                taskLinearLayout.setVisibility(View.GONE);
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
        return string.equals("1");
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
//        taskLinearLayout = (LinearLayout) contentView.findViewById(R.id.work_task_id);
        realinfoLinearLayout = (LinearLayout) contentView.findViewById(R.id.work_realinfo_id);
        reportLinearLayout = (LinearLayout) contentView.findViewById(R.id.work_report_id);
        planLinearlayout.setOnClickListener(planOnClickListener);
//        taskLinearLayout.setOnClickListener(taskOnClickListener);
        realinfoLinearLayout.setOnClickListener(realinfoOnClickListener);
        reportLinearLayout.setOnClickListener(reportOnClickListener);
        decisionLayout();

    }

    private View.OnClickListener planOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(Work_detailsActivity.this, Woactivity_Activity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("workOrder", workOrder);
            intent.putExtras(bundle);
            startActivity(intent);
            popupWindow.dismiss();
        }
    };

//    private View.OnClickListener taskOnClickListener = new View.OnClickListener() {
//        @Override
//        public void onClick(View view) {
//            Intent intent = new Intent(Work_detailsActivity.this, AssignmentActivity.class);
//            Bundle bundle = new Bundle();
//            bundle.putSerializable("workOrder", workOrder);
//            intent.putExtras(bundle);
//            startActivity(intent);
//            popupWindow.dismiss();
//        }
//    };

    private View.OnClickListener realinfoOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(Work_detailsActivity.this, LabtransListActivity.class);
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
            if (failurecode.getText().toString().equals("")){
                popupWindow.dismiss();
                Toast.makeText(Work_detailsActivity.this,"请选选择故障子机构",Toast.LENGTH_SHORT).show();
            }else {
                Intent intent = new Intent(Work_detailsActivity.this, Work_FailurereportActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("workOrder", getWorkOrder());
                intent.putExtras(bundle);
                startActivity(intent);
                popupWindow.dismiss();
            }
        }
    };

    private View.OnClickListener deleteOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

        }
    };

    private View.OnClickListener reviseOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

        }
    };

    private View.OnClickListener work_flowOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

        }
    };


    private class LayoutOnClickListener implements View.OnClickListener {
        int requestCode;

        private LayoutOnClickListener(int requestCode) {
            this.requestCode = requestCode;
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(Work_detailsActivity.this, OptionActivity.class);
            intent.putExtra("requestCode", requestCode);
            if (requestCode == Constants.JOBPLANCODE) {
                intent.putExtra("AssetIsChoose", assetnum.getText().toString().equals(""));
            } else if ((requestCode == Constants.LABORCODE1 || requestCode == Constants.LABORCODE2
                    || requestCode == Constants.LABORCODE3) && !udqxbz.getText().toString().equals("")) {
                intent.putExtra("udqxbz", udqxbz.getText().toString());
            }
            startActivityForResult(intent, requestCode);
        }
    }

    //删除工单
    private void Delete() {

    }

    private WorkOrder getWorkOrder() {
        WorkOrder workOrder = this.workOrder;
        workOrder.description = description.getText().toString().trim();
        workOrder.worktype = worktype.getText().toString().trim();
        workOrder.assetnum = assetnum.getText().toString().trim();
        workOrder.woeq1 = woeq1.getText().toString().trim();
        workOrder.woeq2 = woeq2.getText().toString().trim();
        workOrder.woeq3 = woeq3.getText().toString().trim();
        workOrder.status = status.getText().toString().trim();
        workOrder.statusdate = statusdate.getText().toString().trim();
        workOrder.jpnum = jpnum.getText().toString().trim();
        workOrder.udisjf = udisjf.isChecked() ? "1" : "0";
        workOrder.pmnum = pmnum.getText().toString().trim();
        workOrder.udcreateby = udcreateby.getText().toString().trim();
        workOrder.reportedby = reportedby.getText().toString().trim();
        workOrder.reportdate = reportdate.getText().toString().trim();
        workOrder.udcreatedate = udcreatedate.getText().toString().trim();
        workOrder.udisjj = udisjj.isChecked() ? "1" : "0";
        workOrder.udyxj = udyxj.getText().toString().trim();
        workOrder.udisaq = udisaq.isChecked() ? "1" : "0";
        workOrder.udisbx = udisbx.isChecked() ? "1" : "0";
        workOrder.udiscb = udiscb.isChecked() ? "1" : "0";
        workOrder.udprojapprnum = udprojapprnum.getText().toString().trim();
        workOrder.udevnum = udevnum.getText().toString().trim();
        workOrder.udbugnum = udbugnum.getText().toString().trim();
        if (workOrder.worktype.equals("EM")) {
            workOrder.lead = lead1.getText().toString().trim();
        } else {
            workOrder.lead = lead.getText().toString().trim();
        }
        workOrder.udqxbz = udqxbz.getText().toString().trim();
        workOrder.supervisor = supervisor.getText().toString().trim();
        workOrder.udsupervisor2 = udsupervisor2.getText().toString().trim();
        workOrder.failurecode = failurecode.getText().toString().trim();
        workOrder.udgzlbdm = udgzlbdm.getText().toString().trim();
        workOrder.udworkmemo = udworkmemo.getText().toString().trim();
        workOrder.udisyq = udisyq.isChecked() ? "1" : "0";
        workOrder.targstartdate = targstartdate.getText().toString().trim();
        workOrder.targcompdate = targcompdate.getText().toString().trim();
        workOrder.udactstart = actstart.getText().toString().trim();
        workOrder.udactfinish = actfinish.getText().toString().trim();
        workOrder.udtjsj = udtjsj.getText().toString().trim();
        workOrder.udtjtime = udtjtime.getText().toString().trim();
        workOrder.udremark = udremark.getText().toString().trim();
        return workOrder;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Option option;
        switch (resultCode) {
            case Constants.ASSETCODE:
                option = (Option) data.getSerializableExtra("option");
                assetnum.setText(option.getName());
                break;
            case Constants.JOBPLANCODE:
                option = (Option) data.getSerializableExtra("option");
                jpnum.setText(option.getName());
                break;
            case Constants.PERSONCODE:
                option = (Option) data.getSerializableExtra("option");
                reportedby.setText(option.getName());
                break;
            case Constants.LABORCODE:
                option = (Option) data.getSerializableExtra("option");
                lead.setText(option.getName());
                break;
            case Constants.LABORCODE1:
                option = (Option) data.getSerializableExtra("option");
                lead1.setText(option.getName());
                break;
            case Constants.LABORCODE2:
                option = (Option) data.getSerializableExtra("option");
                supervisor.setText(option.getName());
                break;
            case Constants.LABORCODE3:
                option = (Option) data.getSerializableExtra("option");
                udsupervisor2.setText(option.getName());
                break;
            case Constants.ALNDOMAINCODE:
                option = (Option) data.getSerializableExtra("option");
                udqxbz.setText(option.getName());
                break;
            case Constants.UDEVCODE:
                option = (Option) data.getSerializableExtra("option");
                udevnum.setText(option.getName());
                break;
            case Constants.PROJAPPR:
                option = (Option) data.getSerializableExtra("option");
                udprojapprnum.setText(option.getValue());
                udbugnum.setText(option.getValue2());
                break;
            case Constants.PMCODE:
                option = (Option) data.getSerializableExtra("option");
                pmnum.setText(option.getName());
                break;
            case Constants.FAILURE_TYPE:
                option = (Option) data.getSerializableExtra("option");
                failurecode.setText(option.getName());
                break;
            case Constants.ALNDOMAIN2CODE:
                option = (Option) data.getSerializableExtra("option");
                udgzlbdm.setText(option.getName());
                break;
//            case 1000:
//                woactivityList = (ArrayList<Woactivity>) data.getSerializableExtra("woactivityList");
//                wplaborList = (ArrayList<Wplabor>) data.getSerializableExtra("wplaborList");
//                wpmaterialList = (ArrayList<Wpmaterial>) data.getSerializableExtra("wpmaterialList");
//                editImageView.performClick();
//                break;
//            case 2000:
//                assignmentList = (ArrayList<Assignment>) data.getSerializableExtra("assignmentList");
//                editImageView.performClick();
//                break;
//            case 3000:
//                labtransList = (ArrayList<Labtrans>) data.getSerializableExtra("labtransList");
//                editImageView.performClick();
//                break;
//            default:
//                break;
        }
    }
}
