package cdhxqh.shekou.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import cdhxqh.shekou.R;
import cdhxqh.shekou.model.Labtrans;
import cdhxqh.shekou.model.Wplabor;

/**
 * Created by think on 2015/11/6.
 * 实际员工详情页面
 */
public class LabtransDetailsActivity extends BaseActivity {
    /**
     * 返回按钮
     */
    private ImageView backImageView;
    /**
     * 标题
     */
    private TextView titleTextView;

    private Labtrans labtrans = new Labtrans();
    private TextView actualstaskid; //任务
    private TextView craft; //工种
    private TextView skilllevel; //技能级别
    private TextView laborcode; //员工
    private TextView startdate; //开始日期
    private TextView starttime; //开始时间
    private TextView finishdate; //结束日期
    private TextView finishtime; //结束时间
    private TextView regularhrs;//常规时数
    private TextView enterby;//输入人
    private TextView enterdate;//输入日期
    private TextView payrate;//费率
    private TextView linecost;//行成本
    private TextView assetnum;//资产
    private TextView transdate;//交易日期
    private TextView transtype;//类型
    private TextView orgid; //组织
    private TextView siteid; //地点

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_labtrans_details);

        geiIntentData();
        findViewById();
        initView();
    }

    private void geiIntentData() {
        labtrans = (Labtrans) getIntent().getParcelableExtra("labtrans");
    }
    @Override
    protected void findViewById() {
        backImageView = (ImageView) findViewById(R.id.title_back_id);
        titleTextView = (TextView) findViewById(R.id.title_name);

        actualstaskid = (TextView) findViewById(R.id.work_labtrans_actualstaskid);
        craft = (TextView) findViewById(R.id.work_labtrans_craft);
        skilllevel = (TextView) findViewById(R.id.work_labtrans_skilllevel);
        laborcode = (TextView) findViewById(R.id.work_labtrans_laborcode);
        startdate = (TextView) findViewById(R.id.work_labtrans_startdate);
        starttime = (TextView) findViewById(R.id.work_labtrans_starttime);
        finishdate = (TextView) findViewById(R.id.work_labtrans_finishdate);
        finishtime = (TextView) findViewById(R.id.work_labtrans_finishtime);
        regularhrs = (TextView) findViewById(R.id.work_labtrans_regularhrs);
        enterby = (TextView) findViewById(R.id.work_labtrans_enterby);
        enterdate = (TextView) findViewById(R.id.work_labtrans_enterdate);
        payrate = (TextView) findViewById(R.id.work_labtrans_payrate);
        linecost = (TextView) findViewById(R.id.work_labtrans_linecost);
        assetnum = (TextView) findViewById(R.id.work_labtrans_assetnum);
        transdate = (TextView) findViewById(R.id.work_labtrans_transdate);
        transtype = (TextView) findViewById(R.id.work_labtrans_transtype);
        orgid = (TextView) findViewById(R.id.work_labtrans_orgid);
        siteid = (TextView) findViewById(R.id.work_labtrans_siteid);
    }

    @Override
    protected void initView() {
        backImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        titleTextView.setText(getResources().getString(R.string.title_activity_labtransdetails));

        actualstaskid.setText(labtrans.actualstaskid);
        craft.setText(labtrans.craft);
        skilllevel.setText(labtrans.skilllevel);
        laborcode.setText(labtrans.laborcode);
        startdate.setText(labtrans.startdate);
        starttime.setText(labtrans.starttime);
        finishdate.setText(labtrans.finishdate);
        finishtime.setText(labtrans.finishtime);
        regularhrs.setText(labtrans.regularhrs);
        enterby.setText(labtrans.enterby);
        enterdate.setText(labtrans.enterdate);
        payrate.setText(labtrans.payrate);
        linecost.setText(labtrans.linecost);
        assetnum.setText(labtrans.assetnum);
        transdate.setText(labtrans.transdate);
        transtype.setText(labtrans.transtype);
        orgid.setText(labtrans.orgid);
        siteid.setText(labtrans.siteid);
    }
}
