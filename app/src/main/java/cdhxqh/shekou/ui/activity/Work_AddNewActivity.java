package cdhxqh.shekou.ui.activity;

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
import android.widget.TextView;
import android.widget.Toast;

import com.flyco.animation.BaseAnimatorSet;
import com.flyco.animation.BounceEnter.BounceTopEnter;
import com.flyco.animation.SlideExit.SlideBottomExit;
import com.flyco.dialog.entity.DialogMenuItem;
import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.NormalDialog;
import com.flyco.dialog.widget.NormalListDialog;

import java.util.ArrayList;

import cdhxqh.shekou.R;
import cdhxqh.shekou.api.JsonUtils;
import cdhxqh.shekou.config.Constants;
import cdhxqh.shekou.model.Failurereport;
import cdhxqh.shekou.model.Labtrans;
import cdhxqh.shekou.model.Option;
import cdhxqh.shekou.model.Woactivity;
import cdhxqh.shekou.model.WorkOrder;
import cdhxqh.shekou.model.WorkResult;
import cdhxqh.shekou.utils.AccountUtils;
import cdhxqh.shekou.utils.DateTimeSelect;
import cdhxqh.shekou.utils.GetDateAndTime;
import cdhxqh.shekou.utils.MessageUtils;
import cdhxqh.shekou.utils.WorkTitle;
import cdhxqh.shekou.webserviceclient.AndroidClientService;

/**
 * Created by think on 2015/10/29.
 * 新建工单
 */
public class Work_AddNewActivity extends BaseActivity {
    private static final String TAG = "Work_AddNewActivity";

    private TextView titlename;
    private ImageView menuImageView;
    private RelativeLayout backlayout;
    private PopupWindow popupWindow;

    /**
     * 工作计划*
     */
    private LinearLayout planLinearlayout;

    /**
     * 实际情况
     */
    private LinearLayout realinfoLinearLayout;

    /**
     * 物料*
     */
    private LinearLayout matusetransLinearLayout;
    /**
     * 故障汇报*
     */
    private WorkOrder workOrder = new WorkOrder();
    private LinearLayout reportLinearLayout;
    private LinearLayout work_numlayout;
    private LinearLayout wonum_layout;
    private TextView wonum;//工单号
    private EditText description;//工单描述
    private LinearLayout description_layout;
    private TextView worktype;//工作类型
    private TextView assetnum;//设备
    private TextView assetname;//设备名称
    private LinearLayout gl_layout;
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
    private TextView actstart;//实际开始时间
    private TextView actfinish;//实际完成时间

    private LinearLayout udtjsj_layout;
    private View udtjsj_view;
    private EditText udtjsj;//实际维修时间

    private EditText udtjtime;//停机时间
    private LinearLayout work_udremark_layout;
    private EditText udremark;//备注

    private Button insert;//新增
    private Button work_flow;//工作流


    /**
     * 设备类型*
     */
    private String udassettype = "";

    private ArrayList<Woactivity> woactivityList = new ArrayList<>();
    private ArrayList<Labtrans> labtransList = new ArrayList<>();
    private ArrayList<Failurereport> failurereportList = new ArrayList<>();

    private BaseAnimatorSet mBasIn;
    private BaseAnimatorSet mBasOut;
    private ArrayList<DialogMenuItem> mMenuItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_work);
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
        workOrder.worktype = getIntent().getExtras().getString("worktype");
    }

    @Override
    protected void findViewById() {
        titlename = (TextView) findViewById(R.id.title_name);
        menuImageView = (ImageView) findViewById(R.id.title_add);
        backlayout = (RelativeLayout) findViewById(R.id.title_back);

        work_numlayout = (LinearLayout) findViewById(R.id.work_numlayout);
        wonum_layout = (LinearLayout) findViewById(R.id.wonum_layout);
        wonum = (TextView) findViewById(R.id.work_wonum);
        description = (EditText) findViewById(R.id.work_description);
        description_layout = (LinearLayout) findViewById(R.id.work_description_layout);
        worktype = (TextView) findViewById(R.id.work_worktype);
        assetnum = (TextView) findViewById(R.id.work_assetnum);
        assetname = (TextView) findViewById(R.id.work_assetnum_name);
        gl_layout = (LinearLayout) findViewById(R.id.gl_layout);
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

        actstart = (TextView) findViewById(R.id.work_actstart);
        actfinish = (TextView) findViewById(R.id.work_actfinish);

        udtjsj_layout = (LinearLayout) findViewById(R.id.udtjsj_linearlayout_id);
        udtjsj_view = (View) findViewById(R.id.udtjsj_view_id);
        udtjsj = (EditText) findViewById(R.id.work_udtjsj);

        udtjtime = (EditText) findViewById(R.id.work_udtjtime);
        work_udremark_layout = (LinearLayout) findViewById(R.id.work_udremark_layout);
        udremark = (EditText) findViewById(R.id.work_udremark);

        insert = (Button) findViewById(R.id.work_insert);
        work_flow = (Button) findViewById(R.id.work_work_flow);
    }

    @Override
    protected void initView() {
        titlename.setText(WorkTitle.getTitleadd(workOrder.worktype) + "新建");
        backlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        menuImageView.setImageResource(R.drawable.ic_drawer);
        menuImageView.setVisibility(View.VISIBLE);
        menuImageView.setOnClickListener(menuImageViewOnClickListener);
        wonum_layout.setVisibility(View.GONE);
        gl_layout.setVisibility(View.GONE);
        work_flow.setVisibility(View.GONE);
        udtjsj_layout.setVisibility(View.GONE);
        udtjsj_view.setVisibility(View.GONE);

        workOrder.isnew = true;

        worktype.setText(WorkTitle.getTitleadd(workOrder.worktype));
        status.setText(getString(R.string.addstatus_text));
        targstartdate.setOnClickListener(new TimeOnClickListener(targstartdate));
        targcompdate.setOnClickListener(new TimeOnClickListener(targcompdate));
        actstart.setOnClickListener(new TimeOnClickListener(actstart));
        actfinish.setOnClickListener(new TimeOnClickListener(actfinish));
        reportdate.setOnClickListener(new TimeOnClickListener(reportdate));
        udyxj.setOnClickListener(udyxjOnClickListener);

        statusdate.setText(GetDateAndTime.GetDateTime());
        udcreateby.setText(AccountUtils.getpersonId(Work_AddNewActivity.this));
        udcreatedate.setText(GetDateAndTime.GetDateTime());
        reportedby.setText(AccountUtils.getpersonId(Work_AddNewActivity.this));
        reportdate.setText(GetDateAndTime.GetDateTime());
        udiscb.setChecked(true);

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

        insert.setOnClickListener(insertOnClickListener);
        setLayout();
    }

    private View.OnClickListener udyxjOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            NormalListDialog();
        }
    };

    private void NormalListDialog() {
        final NormalListDialog dialog = new NormalListDialog(Work_AddNewActivity.this, mMenuItems);
        dialog.title("请选择")//
                .showAnim(mBasIn)//
                .dismissAnim(mBasOut)//
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

    private View.OnClickListener insertOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (assetnum.getText().toString().equals("")) {
                MessageUtils.showErrorMessage(Work_AddNewActivity.this, "设备必填项");
            } else {
                final NormalDialog dialog = new NormalDialog(Work_AddNewActivity.this);
                dialog.content("确定新增工单吗?")//
                        .showAnim(mBasIn)//
                        .dismissAnim(mBasOut)//
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
        }
    };

    /**
     * 提交数据*
     */
    private void startAsyncTask() {
        final String updataInfo = JsonUtils.WorkToJson(getWorkOrder(), woactivityList, labtransList, failurereportList);
        new AsyncTask<String, String, WorkResult>() {
            @Override
            protected WorkResult doInBackground(String... strings) {
                WorkResult addresult = AndroidClientService.InsertWO(updataInfo, AccountUtils.getpersonId(Work_AddNewActivity.this), AccountUtils.getIpAddress(Work_AddNewActivity.this) + Constants.WORK_URL);
                return addresult;
            }

            @Override
            protected void onPostExecute(WorkResult workResult) {
                super.onPostExecute(workResult);
                if (workResult == null) {
                    MessageUtils.showMiddleToast(Work_AddNewActivity.this, "新增工单失败");
                } else if (!workResult.errorMsg.equals("成功!")) {
                    MessageUtils.showMiddleToast(Work_AddNewActivity.this, workResult.errorMsg);
                    finish();
                } else if (workResult.errorMsg.equals("成功!")) {
                    MessageUtils.showMiddleToast(Work_AddNewActivity.this, "工单" + workResult.wonum + "新增成功");
                    wonum.setText(workResult.wonum);
                    finish();
                }
                closeProgressDialog();
            }
        }.execute();
    }

    //时间选择监听
    private class TimeOnClickListener implements View.OnClickListener {
        TextView textView;

        private TimeOnClickListener(TextView textView) {
            this.textView = textView;
        }

        @Override
        public void onClick(View view) {
            new DateTimeSelect(Work_AddNewActivity.this, textView).showDialog();
        }
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
                udbugnum_layout.setVisibility(View.GONE);
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
                realinfoLinearLayout.setVisibility(View.GONE);
                break;
            case "EM"://抢修工单
                planLinearlayout.setVisibility(View.GONE);
                realinfoLinearLayout.setVisibility(View.GONE);
                break;
            case "EV"://事故工单
                reportLinearLayout.setVisibility(View.GONE);
                realinfoLinearLayout.setVisibility(View.GONE);
                break;
            case "PJ"://项目工单
                reportLinearLayout.setVisibility(View.GONE);
                realinfoLinearLayout.setVisibility(View.GONE);
                break;
            case "PM"://预防性维护工单
                reportLinearLayout.setVisibility(View.GONE);
                realinfoLinearLayout.setVisibility(View.GONE);
                break;
            case "RS"://可维修备件工单
                reportLinearLayout.setVisibility(View.GONE);
                realinfoLinearLayout.setVisibility(View.GONE);
                break;
            case "SR"://状态维修工单
                realinfoLinearLayout.setVisibility(View.GONE);
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
        realinfoLinearLayout = (LinearLayout) contentView.findViewById(R.id.work_realinfo_id);
        matusetransLinearLayout = (LinearLayout) contentView.findViewById(R.id.meterial_id);
        reportLinearLayout = (LinearLayout) contentView.findViewById(R.id.work_report_id);

        matusetransLinearLayout.setVisibility(View.GONE);
        planLinearlayout.setOnClickListener(planOnClickListener);
        realinfoLinearLayout.setOnClickListener(realinfoOnClickListener);
        reportLinearLayout.setOnClickListener(reportOnClickListener);
        decisionLayout();
    }

    private class LayoutOnClickListener implements View.OnClickListener {
        int requestCode;

        private LayoutOnClickListener(int requestCode) {
            this.requestCode = requestCode;
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(Work_AddNewActivity.this, OptionActivity.class);
            intent.putExtra("requestCode", requestCode);
            if (requestCode == Constants.JOBPLANCODE) {
                intent.putExtra("udassettype", udassettype);
            } else if ((requestCode == Constants.LABORCODE1 || requestCode == Constants.LABORCODE2
                    || requestCode == Constants.LABORCODE3) && !udqxbz.getText().toString().equals("")) {
                intent.putExtra("udqxbz", udqxbz.getText().toString());
            }
            startActivityForResult(intent, requestCode);
        }
    }

    private View.OnClickListener planOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(Work_AddNewActivity.this, Woactivity_Activity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("workOrder", workOrder);
            bundle.putSerializable("woactivityList", woactivityList);
            intent.putExtras(bundle);
            startActivityForResult(intent, 1000);
            popupWindow.dismiss();
        }
    };


    private View.OnClickListener realinfoOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(Work_AddNewActivity.this, LabtransListActivity.class);
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
                MessageUtils.showMiddleToast(Work_AddNewActivity.this, "请选选择故障子机构");
            } else {
                Intent intent = new Intent(Work_AddNewActivity.this, Work_FailurereportActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("workOrder", getWorkOrder());
                bundle.putSerializable("failurereportList", failurereportList);
                intent.putExtras(bundle);
                startActivityForResult(intent, 3000);
                popupWindow.dismiss();
            }
        }
    };

    private WorkOrder getWorkOrder() {
        WorkOrder workOrder = this.workOrder;
        workOrder.wonum = "";
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
                udassettype = option.getValue2();
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
            case 3000:
                failurereportList = (ArrayList<Failurereport>) data.getSerializableExtra("failurereportList");
                break;
            default:
                break;
        }
    }
}
