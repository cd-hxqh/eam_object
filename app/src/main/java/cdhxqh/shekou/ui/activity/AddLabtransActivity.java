package cdhxqh.shekou.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import cdhxqh.shekou.R;
import cdhxqh.shekou.model.Labtrans;
import cdhxqh.shekou.model.Woactivity;

/**
 * Created by think on 2016/5/10.
 * 添加实际员工
 */
public class AddLabtransActivity extends BaseActivity {
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
    private TextView finishtime; //结束时间
    private TextView regularhrs;//常规时数
    private TextView payrate;//费率
    private TextView linecost;//行成本
    private TextView assetnum;//资产
    private TextView transtype;//类型
    private Button confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_labtrans_details);

        findViewById();
        initView();
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
        finishtime = (TextView) findViewById(R.id.work_labtrans_finishtime);
        regularhrs = (TextView) findViewById(R.id.work_labtrans_regularhrs);
        payrate = (TextView) findViewById(R.id.work_labtrans_payrate);
        linecost = (TextView) findViewById(R.id.work_labtrans_linecost);
        assetnum = (TextView) findViewById(R.id.work_labtrans_assetnum);
        transtype = (TextView) findViewById(R.id.work_labtrans_transtype);
        confirm = (Button) findViewById(R.id.confirm);
    }

    @Override
    protected void initView() {
        backImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        titleTextView.setText(getResources().getString(R.string.title_activity_worklabtrans_add));
    }
}