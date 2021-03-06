package cdhxqh.shekou.ui.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.flyco.animation.BaseAnimatorSet;
import com.flyco.animation.BounceEnter.BounceTopEnter;
import com.flyco.animation.SlideExit.SlideBottomExit;
import com.flyco.dialog.entity.DialogMenuItem;
import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.listener.OnBtnEditClickL;
import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.MaterialDialog;
import com.flyco.dialog.widget.NormalDialog;
import com.flyco.dialog.widget.NormalEditTextDialog;
import com.flyco.dialog.widget.NormalListDialog;

import java.util.ArrayList;

import cdhxqh.shekou.R;
import cdhxqh.shekou.api.HttpManager;
import cdhxqh.shekou.api.HttpRequestHandler;
import cdhxqh.shekou.api.JsonUtils;
import cdhxqh.shekou.bean.Results;
import cdhxqh.shekou.config.Constants;
import cdhxqh.shekou.model.Failurereport;
import cdhxqh.shekou.model.Labtrans;
import cdhxqh.shekou.model.Option;
import cdhxqh.shekou.model.Woactivity;
import cdhxqh.shekou.model.WorkOrder;
import cdhxqh.shekou.model.WorkResult;
import cdhxqh.shekou.utils.AccountUtils;
import cdhxqh.shekou.utils.DateTimeSelect;
import cdhxqh.shekou.utils.MessageUtils;
import cdhxqh.shekou.utils.WorkTitle;
import cdhxqh.shekou.webserviceclient.AndroidClientService;

/**
 * 工单详情
 */
public class Work_detailsActivity extends BaseActivity {
    private static final String TAG = "Work_detailsActivity";
    private TextView titlename;
    private ImageView menuImageView;
    private RelativeLayout backlayout;
    private PopupWindow popupWindow;

    /**
     * 工单任务*
     */
    private LinearLayout planLinearlayout;
    /**
     * 实际情况
     */
    private LinearLayout realinfoLinearLayout;
    /**
     * 物料
     **/
    private LinearLayout meterialLinearLayout;
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
    private TextView assetname;//设备名称
    private TextView woeq1;//管理组
    private TextView woeq2;//管理室
    private TextView woeq3;//管理班组

    /**
     * 抢修工单*
     */
    private LinearLayout qxgdnumLinearLayout;
    private View qxgdView;
    private TextView qxgdText;
    /**
     * 描述*
     */
    private LinearLayout qxgddescLinearLayout;
    private View qxgdmsView;
    private TextView qxgdmsText;


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
    private TextView targstartdate;//计划开始时间
    private TextView targcompdate;//计划完成时间
    private LinearLayout work_real_info_layout;
    private TextView actstart;//实际开始时间
    private TextView actfinish;//实际完成时间
    private EditText udtjsj;//实际维修时间
    private EditText udtjtime;//停机时间
    private LinearLayout work_udremark_layout;
    private EditText udremark;//备注

    /**
     * 故障工单*
     */
    private LinearLayout gzgdLinearLayout;
    private View gzgdView;
    private TextView gzgdText;
    /**
     * 工单描述*
     */
    private LinearLayout gdmsLinearLayout;
    private View gdmsView;
    private TextView gdmsText;


    /**
     * scrollview*
     */
    private ScrollView scrollview;

    /**
     * 操作界面
     **/
    private LinearLayout operationLinearLayout;

    /**
     * 删除
     **/
    private Button delete;
    /**
     * 修改
     **/
    private Button revise;
    /**
     * 工作流
     **/
    private Button work_flow;


    /**
     * 以生成的领料单*
     */
    private LinearLayout invuseLinearLayout;


    private ArrayList<Woactivity> woactivityList = new ArrayList<>();
    private ArrayList<Labtrans> labtransList = new ArrayList<>();
    private ArrayList<Failurereport> failurereportList = new ArrayList<>();

    private BaseAnimatorSet mBasIn;
    private BaseAnimatorSet mBasOut;
    private ArrayList<DialogMenuItem> mMenuItems = new ArrayList<>();
    private ProgressDialog mProgressDialog;


    /**
     * 流出审批过程名*
     */
    private String processname;

    /**
     * 入口
     **/
    private int entrn;

    private int mark = 0;


    private String ownerid; //ownerid
    private String ownertable; //ownertable
    private String workordertype;//worktype


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workdetails);
        geiIntentData();
        findViewById();
        initView();

        mBasIn = new BounceTopEnter();
        mBasOut = new SlideBottomExit();
        addudyxjData();
    }

    /**
     * 获取数据*
     */
    private void geiIntentData() {
        mark = getIntent().getExtras().getInt("mark");
        if (mark == 0) {
            workOrder = (WorkOrder) getIntent().getSerializableExtra("workOrder");

            entrn = getIntent().getExtras().getInt("entrn");
        } else if (mark == 1) {

            ownerid = getIntent().getStringExtra("ownerid");
            ownertable = getIntent().getStringExtra("ownertable");
            workordertype = getIntent().getStringExtra("worktype");
            Log.i(TAG, "ownerid=" + ownerid + "ownertable=" + ownertable + "workordertype=" + workordertype);
        }

    }

    @Override
    protected void findViewById() {
        titlename = (TextView) findViewById(R.id.title_name);
        menuImageView = (ImageView) findViewById(R.id.title_add);
        backlayout = (RelativeLayout) findViewById(R.id.title_back);


        scrollview = (ScrollView) findViewById(R.id.scrollView_id);

        work_numlayout = (LinearLayout) findViewById(R.id.work_numlayout);
        wonum = (TextView) findViewById(R.id.work_wonum);
        description = (EditText) findViewById(R.id.work_description);
        description_layout = (LinearLayout) findViewById(R.id.work_description_layout);
        worktype = (TextView) findViewById(R.id.work_worktype);
        assetnum = (TextView) findViewById(R.id.work_assetnum);
        assetname = (TextView) findViewById(R.id.work_assetnum_name);
        woeq1 = (TextView) findViewById(R.id.work_glz);
        woeq2 = (TextView) findViewById(R.id.work_gls);
        woeq3 = (TextView) findViewById(R.id.work_glbz);

        qxgdnumLinearLayout = (LinearLayout) findViewById(R.id.qxgd_linearlayout_id);
        qxgdView = (View) findViewById(R.id.qxgd_view_id);
        qxgdText = (TextView) findViewById(R.id.qxgd_text_id);
        qxgddescLinearLayout = (LinearLayout) findViewById(R.id.qxgdms_linearLayout_id);
        qxgdmsView = (View) findViewById(R.id.qxgdms_view_id);
        qxgdmsText = (TextView) findViewById(R.id.qxgdms_id);


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
        targstartdate = (TextView) findViewById(R.id.work_targstartdate);
        targcompdate = (TextView) findViewById(R.id.work_targcompdate);
        work_real_info_layout = (LinearLayout) findViewById(R.id.work_real_info);
        actstart = (TextView) findViewById(R.id.work_actstart);
        actfinish = (TextView) findViewById(R.id.work_actfinish);
        udtjsj = (EditText) findViewById(R.id.work_udtjsj);
        udtjtime = (EditText) findViewById(R.id.work_udtjtime);
        work_udremark_layout = (LinearLayout) findViewById(R.id.work_udremark_layout);
        udremark = (EditText) findViewById(R.id.work_udremark);

        gzgdLinearLayout = (LinearLayout) findViewById(R.id.gzgd_linearlayout_id);
        gzgdView = (View) findViewById(R.id.gzgd_view_id);
        gzgdText = (TextView) findViewById(R.id.gzgd_num_text);
        gdmsLinearLayout = (LinearLayout) findViewById(R.id.gd_text_id);
        gdmsView = (View) findViewById(R.id.gdms_view_id);
        gdmsText = (TextView) findViewById(R.id.gdms_text_id);


        operationLinearLayout = (LinearLayout) findViewById(R.id.operation_id);
        delete = (Button) findViewById(R.id.work_delete);
        revise = (Button) findViewById(R.id.work_revise);
        work_flow = (Button) findViewById(R.id.work_work_flow);
    }

    @Override
    protected void initView() {

        backlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        menuImageView.setImageResource(R.drawable.ic_drawer);
        menuImageView.setVisibility(View.VISIBLE);
        menuImageView.setOnClickListener(menuImageViewOnClickListener);


        targstartdate.setOnClickListener(new TimeOnClickListener(targstartdate));
        targcompdate.setOnClickListener(new TimeOnClickListener(targcompdate));
        actstart.setOnClickListener(new TimeOnClickListener(actstart));
        actfinish.setOnClickListener(new TimeOnClickListener(actfinish));
        reportdate.setOnClickListener(new TimeOnClickListener(reportdate));
        udyxj.setOnClickListener(udyxjOnClickListener);

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

        delete.setOnClickListener(deleteOnClickListener);
        revise.setOnClickListener(reviseOnClickListener);
        work_flow.setOnClickListener(approvalBtnOnClickListener);
        if (mark == 0) {
            titlename.setText(WorkTitle.getTitle(workOrder.worktype));
            showData(workOrder);
            setLayout(workOrder.worktype);
        } else if (mark == 1) {
            titlename.setText(WorkTitle.getTitle(workordertype));
            getData(ownerid);
            setLayout(workordertype);
        }


        if (entrn == 0) {
            setEditor(true);
            setSpace(200);

        } else if (entrn == 1) {
            setEditor(false);
            setSpace(0);
            operationLinearLayout.setVisibility(View.GONE);
        }
    }


    private void showData(WorkOrder workOrder) {
        if (workOrder.worktype.equals("CM")) {//故障工单
            qxgdnumLinearLayout.setVisibility(View.VISIBLE);
            qxgdView.setVisibility(View.VISIBLE);
            qxgddescLinearLayout.setVisibility(View.VISIBLE);
            qxgdmsView.setVisibility(View.VISIBLE);
            qxgdText.setText(workOrder.qxgdwonum);
            qxgdmsText.setText(workOrder.qxgddescription);
        }
        if (workOrder.worktype.equals("EM")) {//抢修工单
            gzgdLinearLayout.setVisibility(View.VISIBLE);
            gzgdView.setVisibility(View.VISIBLE);
            gdmsLinearLayout.setVisibility(View.VISIBLE);
            gdmsView.setVisibility(View.VISIBLE);
            gzgdText.setText(workOrder.gzgdwonum);
            gdmsText.setText(workOrder.gzgddescription);
            udtjtime.setEnabled(false);
        }


        workOrder.isnew = false;

        wonum.setText(workOrder.wonum);
        description.setText(workOrder.description);
        worktype.setText(workOrder.wtypedesc);
        assetnum.setText(workOrder.assetnum);
        assetname.setText(workOrder.assetdescription);
        woeq1.setText(workOrder.woeq1);
        woeq2.setText(workOrder.woeq2);
        woeq3.setText(workOrder.woeq3);
        status.setText(workOrder.status);
        statusdate.setText(workOrder.statusdate);

        jpnum.setText(workOrder.jpnum);
        udisjf.setChecked(ischeck(workOrder.udisjf));
        pmnum.setText(workOrder.pmnum);
        udcreateby.setText(workOrder.udcreatebyname);
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
        targstartdate.setText(workOrder.targstartdate.equals("null") ? "" : workOrder.targstartdate);
        targcompdate.setText(workOrder.targcompdate.equals("null") ? "" : workOrder.targcompdate);
        actstart.setText(workOrder.udactstart.equals("null") ? "" : workOrder.udactstart);
        actfinish.setText(workOrder.udactfinish.equals("null") ? "" : workOrder.udactfinish);
        udtjsj.setText(workOrder.udtjsj);
        udtjtime.setText(workOrder.udtjtime);
        udremark.setText(workOrder.udremark);
    }


    private View.OnClickListener udyxjOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            NormalListDialog();
        }
    };

    private void NormalListDialog() {
        final NormalListDialog dialog = new NormalListDialog(Work_detailsActivity.this, mMenuItems);
        dialog.title("请选择")
                .showAnim(mBasIn)
                .dismissAnim(mBasOut)
                .show();
        dialog.setOnOperItemClickL(new OnOperItemClickL() {
            @Override
            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {

                udyxj.setText(mMenuItems.get(position).mOperName);

                dialog.dismiss();
            }
        });
    }

    /**
     * 添加数据*
     */
    private void addudyxjData() {
        String[] lctypes = getResources().getStringArray(R.array.udyxj_tab_titles);

        for (int i = 0; i < lctypes.length; i++)
            mMenuItems.add(new DialogMenuItem(lctypes[i], 0));


    }

    //时间选择监听
    private class TimeOnClickListener implements View.OnClickListener {
        TextView textView;

        private TimeOnClickListener(TextView textView) {
            this.textView = textView;
        }

        @Override
        public void onClick(View view) {
            new DateTimeSelect(Work_detailsActivity.this, textView).showDialog();
        }
    }

    //按照工单类型修改布局
    private void setLayout(String worktype) {
        switch (worktype) {
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
                reportLinearLayout.setVisibility(View.GONE);
                break;
            case "PM"://预防性维护工单
                reportLinearLayout.setVisibility(View.GONE);
                break;
            case "RS"://可维修备件工单
                reportLinearLayout.setVisibility(View.GONE);
                break;
            case "SR"://状态维修工单
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
        if (string == null) {
            return false;
        }
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
            }
        });

        popupWindow.setBackgroundDrawable(getResources().getDrawable(
                R.drawable.popup_background_mtrl_mult));

        popupWindow.showAsDropDown(view);

        planLinearlayout = (LinearLayout) contentView.findViewById(R.id.work_plan_id);

        realinfoLinearLayout = (LinearLayout) contentView.findViewById(R.id.work_realinfo_id);

        meterialLinearLayout = (LinearLayout) contentView.findViewById(R.id.meterial_id);

        reportLinearLayout = (LinearLayout) contentView.findViewById(R.id.work_report_id);


        invuseLinearLayout = (LinearLayout) contentView.findViewById(R.id.work_invuse_id);

        invuseLinearLayout.setVisibility(View.VISIBLE);
        planLinearlayout.setOnClickListener(planOnClickListener);

        realinfoLinearLayout.setOnClickListener(realinfoOnClickListener);

        meterialLinearLayout.setOnClickListener(meterialOnClickListener);

        reportLinearLayout.setOnClickListener(reportOnClickListener);

        invuseLinearLayout.setOnClickListener(invuseOnClickListener);
        decisionLayout();

    }

    private View.OnClickListener planOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = getIntent();
            intent.setClass(Work_detailsActivity.this, Woactivity_Activity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("workOrder", workOrder);
            bundle.putSerializable("woactivityList", woactivityList);
            intent.putExtras(bundle);
            startActivityForResult(intent, 1000);
            popupWindow.dismiss();
        }
    };

    private View.OnClickListener meterialOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = getIntent();
            intent.setClass(Work_detailsActivity.this, MatusetransActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("refwo", workOrder.wonum);
            intent.putExtras(bundle);
            startActivity(intent);
            popupWindow.dismiss();
        }
    };

    private View.OnClickListener realinfoOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = getIntent();
            intent.setClass(Work_detailsActivity.this, LabtransListActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("workOrder", workOrder);
            bundle.putSerializable("woactivityList", woactivityList);
            bundle.putSerializable("labtransList", labtransList);
            intent.putExtras(bundle);
            startActivityForResult(intent, 2000);
            popupWindow.dismiss();
        }
    };

    private View.OnClickListener reportOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (failurecode.getText().toString().equals("")) {
                popupWindow.dismiss();
                MessageUtils.showMiddleToast(Work_detailsActivity.this, "请选选择故障子机构");
            } else {
                Intent intent = getIntent();
                intent.setClass(Work_detailsActivity.this, Work_FailurereportActivity.class);

                Bundle bundle = new Bundle();
                bundle.putSerializable("workOrder", getWorkOrder());
                bundle.putSerializable("failurereportList", failurereportList);
                intent.putExtras(bundle);
                startActivityForResult(intent, 3000);
                popupWindow.dismiss();
            }
        }
    };


    /**
     * 领料单*
     */
    private View.OnClickListener invuseOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = getIntent();
            intent.setClass(Work_detailsActivity.this, InvuseActivity.class);
            Bundle bundle = new Bundle();
            bundle.putInt("entrance", 1);
            bundle.putString("udapptype", "USE");
            bundle.putString("wonum", workOrder.wonum);
            intent.putExtras(bundle);
            startActivityForResult(intent, 3000);
            popupWindow.dismiss();
        }
    };


    private View.OnClickListener deleteOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            deleteDataInfo();
        }
    };

    private View.OnClickListener reviseOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            if (workOrder.status.equals(Constants.STATUS25)) {
                submitDataInfo();
            } else {
                MessageUtils.showMiddleToast(Work_detailsActivity.this, "该状态无法修改");
            }
        }
    };

    /**
     * 提交数据*
     */
    private void submitDataInfo() {
        final NormalDialog dialog = new NormalDialog(Work_detailsActivity.this);
        dialog.content("确定修改工单吗?")
                .showAnim(mBasIn)
                .dismissAnim(mBasOut)
                .show();
        dialog.setOnBtnClickL(
                new OnBtnClickL() {
                    @Override
                    public void onBtnClick() {
                        dialog.dismiss();
                    }
                },
                new OnBtnClickL() {
                    @Override
                    public void onBtnClick() {
                        showProgressDialog("数据提交中...");
                        startAsyncTask();
                        dialog.dismiss();
                    }
                });
    }


    /**
     * 提交数据*
     */
    private void startAsyncTask() {
        String updataInfo = null;
        updataInfo = JsonUtils.WorkToJson(getWorkOrder(), woactivityList, labtransList, failurereportList);
        final String finalUpdataInfo = updataInfo;
        new AsyncTask<String, String, WorkResult>() {
            @Override
            protected WorkResult doInBackground(String... strings) {
                WorkResult reviseresult = AndroidClientService.UpdateWO(finalUpdataInfo, AccountUtils.getpersonId(Work_detailsActivity.this), AccountUtils.getIpAddress(Work_detailsActivity.this) + Constants.WORK_URL);
                return reviseresult;
            }

            @Override
            protected void onPostExecute(WorkResult workResult) {
                super.onPostExecute(workResult);
                if (workResult == null) {
                    MessageUtils.showMiddleToast(Work_detailsActivity.this, "修改工单失败");
                } else if (workResult.errorMsg.equals("成功!")) {
                    MessageUtils.showMiddleToast(Work_detailsActivity.this, "修改工单成功");
                } else {
                    MessageUtils.showMiddleToast(Work_detailsActivity.this, workResult.errorMsg);
                }
                closeProgressDialog();
            }
        }.execute();

    }


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
            } else if (requestCode == Constants.ASSETCODE) {
                intent.putExtra("type", workOrder.worktype);
            }
            startActivityForResult(intent, requestCode);
        }
    }

    /**
     * 提交数据*
     */
    private void deleteDataInfo() {
        final NormalDialog dialog = new NormalDialog(Work_detailsActivity.this);
        dialog.content("确定删除工单吗?")
                .showAnim(mBasIn)
                .dismissAnim(mBasOut)
                .show();
        dialog.setOnBtnClickL(
                new OnBtnClickL() {
                    @Override
                    public void onBtnClick() {
                        dialog.dismiss();
                    }
                },
                new OnBtnClickL() {
                    @Override
                    public void onBtnClick() {
                        showProgressDialog("数据提交中...");
                        deleteAsyncTask();
                        dialog.dismiss();
                    }
                });
    }

    /**
     * 提交数据*
     */
    private void deleteAsyncTask() {
        new AsyncTask<String, String, WorkResult>() {
            @Override
            protected WorkResult doInBackground(String... strings) {
                WorkResult reviseresult = AndroidClientService.DeleteWO(workOrder.wonum, AccountUtils.getpersonId(Work_detailsActivity.this), AccountUtils.getIpAddress(Work_detailsActivity.this) + Constants.WORK_URL);
                return reviseresult;
            }

            @Override
            protected void onPostExecute(WorkResult workResult) {
                super.onPostExecute(workResult);
                if (workResult == null) {
                    MessageUtils.showMiddleToast(Work_detailsActivity.this, "删除失败");
                } else if (workResult.errorMsg.equals("操作成功！") && workResult.errorNo.equals("0")) {
                    MessageUtils.showMiddleToast(Work_detailsActivity.this, workResult.errorMsg);
                    Work_detailsActivity.this.finish();
                } else {
                    MessageUtils.showMiddleToast(Work_detailsActivity.this, workResult.errorMsg);
                }
                closeProgressDialog();
            }
        }.execute();

    }

    //工作流审批
    private View.OnClickListener approvalBtnOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (workOrder.status.equals(Constants.STATUS25)) { //开始工作流
                MaterialDialogOneBtn();
            } else { //审批工作流
                MaterialDialogOneBtn1();
            }

        }
    };


    private void MaterialDialogOneBtn1() {//审批工作流
        final NormalEditTextDialog dialog = new NormalEditTextDialog(Work_detailsActivity.this);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialog.isTitleShow(true);//
        dialog.title("审批工作流");
        dialog.btnNum(3)
                .content("通过")//
                .btnText("取消", "通过", "不通过")//
                .showAnim(mBasIn)//
                .dismissAnim(mBasOut)
                .show();

        dialog.setOnBtnClickL(
                new OnBtnEditClickL() {//取消
                    @Override
                    public void onBtnClick(String text) {

                        dialog.dismiss();
                    }
                },
                new OnBtnEditClickL() {//通过
                    @Override
                    public void onBtnClick(String text) {
                        wfgoon(workOrder.workorderid, "1", text);
                        dialog.dismiss();
                    }
                },
                new OnBtnEditClickL() {//不通过
                    @Override
                    public void onBtnClick(String text) {
                        wfgoon(workOrder.workorderid, "0", text);
                        dialog.dismiss();
                    }
                }
        );
    }


    /**
     * 获取工单工程名*
     */
    private String getprocessname(String worktype) {
        String processname = "";
        switch (worktype) {
            case "CM":
                processname = "UDWO02";
                break;
            case "PM":
                processname = "UDWO03";
                break;
            case "SR":
                processname = "UDWO04";
                break;
            case "PJ":
                processname = "UDWO05";
                break;
            case "RS":
                processname = "UDWO06";
                break;
            case "EV":
                processname = "UDWO07";
                break;
            case "EM":
                processname = "UDWO08";
                break;
        }
        return processname;
    }


    private WorkOrder getWorkOrder() {
        WorkOrder workOrder = this.workOrder;
        workOrder.description = description.getText().toString().trim();
        workOrder.assetnum = assetnum.getText().toString().trim();
        workOrder.woeq1 = woeq1.getText().toString().trim();
        workOrder.woeq2 = woeq2.getText().toString().trim();
        workOrder.woeq3 = woeq3.getText().toString().trim();
        workOrder.status = status.getText().toString().trim();
        workOrder.statusdate = statusdate.getText().toString().trim();
        workOrder.jpnum = jpnum.getText().toString().trim();
        workOrder.udisjf = udisjf.isChecked() ? "1" : "0";
        workOrder.pmnum = pmnum.getText().toString().trim();
//        workOrder.udcreateby = workOrder.udcreateby;
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
                assetname.setText(option.getDescription());
                workOrder.udassetbz = option.getValue();
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
            case 1000:
                woactivityList = (ArrayList<Woactivity>) data.getSerializableExtra("woactivityList");
                break;
            case 2000:
                labtransList = (ArrayList<Labtrans>) data.getSerializableExtra("labtransList");
                break;
            case 3000:  //故障报告
                failurereportList = (ArrayList<Failurereport>) data.getSerializableExtra("failurereportList");
                break;
            default:
                break;
        }
    }


    /**
     * 是否编辑
     **/
    private void setEditor(boolean isEnabled) {
        //工单描述
        description.setEnabled(isEnabled);
        //设备
        assetnum.setEnabled(isEnabled);
        //是否紧急维修
        udisjj.setEnabled(isEnabled);
        //作业计划
        jpnum.setEnabled(isEnabled);
        //是否按项目计费
        udisjf.setEnabled(isEnabled);
        //是否抄表
        udiscb.setEnabled(isEnabled);
        //报告人
        reportedby.setEnabled(isEnabled);
        //报告日期
        reportdate.setEnabled(isEnabled);
        //优先级
        udyxj.setEnabled(isEnabled);
        //是否安全
        udisaq.setEnabled(isEnabled);
        //是否保修
        udisbx.setEnabled(isEnabled);
        //工作负责人
        lead.setEnabled(isEnabled);
        //计划开始时间
        targstartdate.setEnabled(isEnabled);
        //计划完成时间
        targcompdate.setEnabled(isEnabled);
        //实际开始时间
        actstart.setEnabled(isEnabled);
        //实际完成时间
        actfinish.setEnabled(isEnabled);
        //实际维修时间
        udtjsj.setEnabled(isEnabled);
        //停机时间
        udtjtime.setEnabled(isEnabled);

        /**备注**/
        udremark.setEnabled(isEnabled);


    }


    /**
     * 设置scrollview的距离*
     */
    private void setSpace(int space) {
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) scrollview.getLayoutParams();
        layoutParams.bottomMargin = space;//将默认的距离底部20dp，改为0，这样底部区域全被scrollview填满。
        scrollview.setLayoutParams(layoutParams);
    }


    private void MaterialDialogOneBtn() {//开始工作流
        final MaterialDialog dialog = new MaterialDialog(Work_detailsActivity.this);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(true);
        dialog.isTitleShow(false)
                .btnNum(2)
                .content("是否启动工作流")
                .btnText("是", "否")
                .showAnim(mBasIn)
                .dismissAnim(mBasOut)
                .show();

        dialog.setOnBtnClickL(
                new OnBtnClickL() {//是
                    @Override
                    public void onBtnClick() {
                        startWF();
                        dialog.dismiss();
                    }
                },
                new OnBtnClickL() {//否
                    @Override
                    public void onBtnClick() {
                        dialog.dismiss();
                    }
                }
        );
    }


    /**
     * 开始工作流
     */
    private void startWF() {
        mProgressDialog = ProgressDialog.show(Work_detailsActivity.this, null,
                getString(R.string.start), true, true);
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.setCancelable(false);
        new AsyncTask<String, String, String>() {
            @Override
            protected String doInBackground(String... strings) {
                String result = AndroidClientService.startwf(Work_detailsActivity.this, "UDWO", "WORKORDER", workOrder.wonum, "WONUM", AccountUtils.getpersonId(Work_detailsActivity.this));

                Log.i(TAG, "result=" + result);
                return result;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                if (s == null || s.equals("")) {
                    MessageUtils.showMiddleToast(Work_detailsActivity.this, "启动失败");
                } else {
                    MessageUtils.showMiddleToast(Work_detailsActivity.this, "启动成功");
                    finish();
                }
                mProgressDialog.dismiss();
            }
        }.execute();
    }


    /**
     * 审批工作流
     *
     * @param id
     * @param zx
     */
    private void wfgoon(final String id, final String zx, final String desc) {
        mProgressDialog = ProgressDialog.show(Work_detailsActivity.this, null,
                getString(R.string.inputing), true, true);
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.setCancelable(false);
        new AsyncTask<String, String, String>() {
            @Override
            protected String doInBackground(String... strings) {


                String result = AndroidClientService.approve(Work_detailsActivity.this, getprocessname(workOrder.worktype), "WORKORDER", id, "WORKORDERID", AccountUtils.getpersonId(Work_detailsActivity.this), zx, desc);
                Log.i(TAG, "result=" + result);
                return result;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                if (s == null || s.equals("")) {
                    MessageUtils.showMiddleToast(Work_detailsActivity.this, "审批失败");
                } else {
                    MessageUtils.showMiddleToast(Work_detailsActivity.this, "审批成功");
                    finish();
                }
                mProgressDialog.dismiss();
            }
        }.execute();
    }


    //获取数据方法
    private void getData(String ownerid) {
        mProgressDialog = ProgressDialog.show(Work_detailsActivity.this, null,
                getString(R.string.data_load_ing), true, true);

        HttpManager.getDataPagingInfo(Work_detailsActivity.this, HttpManager.getWorkOrderByIdurl(workordertype, ownerid), new HttpRequestHandler<Results>() {
            @Override
            public void onSuccess(Results results) {
                Log.i(TAG, "data=" + results);
            }

            @Override
            public void onSuccess(Results results, int totalPages, int currentPage) {
                mProgressDialog.dismiss();
                ArrayList<WorkOrder> item = JsonUtils.parsingWorkOrder(Work_detailsActivity.this, results.getResultlist(), workordertype);
                if (item != null || item.size() != 0) {
                    workOrder = item.get(0);
                    if (workOrder != null) {
                        showData(workOrder);
                    }

                }

            }

            @Override
            public void onFailure(String error) {
                mProgressDialog.dismiss();
                MessageUtils.showMiddleToast(Work_detailsActivity.this, "数据加载失败");
            }
        });


    }


}
