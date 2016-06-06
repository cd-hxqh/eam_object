package cdhxqh.shekou.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import cdhxqh.shekou.R;
import cdhxqh.shekou.config.Constants;
import cdhxqh.shekou.model.Labtrans;
import cdhxqh.shekou.model.Option;
import cdhxqh.shekou.model.Woactivity;
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
    private TextView finishtime; //结束时间
    private TextView regularhrs;//常规时数
    private TextView payrate;//费率
    private TextView linecost;//行成本
    private TextView assetnum;//资产
    private TextView transtype;//类型

    private int position;
    private ArrayList<Woactivity> woactivityList = new ArrayList<>();

    private Button confirm;//确定

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_labtrans_details);

        geiIntentData();
        findViewById();
        initView();
    }

    private void geiIntentData() {
        labtrans = (Labtrans) getIntent().getSerializableExtra("labtrans");
        woactivityList = (ArrayList<Woactivity>) getIntent().getSerializableExtra("woactivityList");
        position = getIntent().getIntExtra("position",0);
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
        titleTextView.setText(getResources().getString(R.string.title_activity_labtransdetails));

        actualstaskid.setText(labtrans.actualstaskid);
        craft.setText(labtrans.craft);
        skilllevel.setText(labtrans.skilllevel);
        laborcode.setText(labtrans.laborcode);
        startdate.setText(labtrans.startdate);
        starttime.setText(labtrans.starttime);
        finishtime.setText(labtrans.finishtime);
        regularhrs.setText(labtrans.regularhrs);
        payrate.setText(labtrans.payrate);
        linecost.setText(labtrans.linecost);
        assetnum.setText(labtrans.assetnum);
        transtype.setText(labtrans.transtype);

        laborcode.setOnClickListener(new LayoutOnClickListener(Constants.LABORCRAFTRATECODE));

        confirm.setOnClickListener(confirmOnClickListener);
    }

    private class LayoutOnClickListener implements View.OnClickListener {
        int requestCode;

        private LayoutOnClickListener(int requestCode) {
            this.requestCode = requestCode;
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(LabtransDetailsActivity.this, OptionActivity.class);
            intent.putExtra("requestCode", requestCode);
            startActivityForResult(intent, requestCode);
        }
    }

    private Labtrans getLabtrans(){
        Labtrans labtrans = this.labtrans;
        labtrans.actualstaskid = actualstaskid.getText().toString();
        labtrans.craft = craft.getText().toString();
        labtrans.skilllevel = skilllevel.getText().toString();
        labtrans.laborcode = laborcode.getText().toString();
        labtrans.startdate = startdate.getText().toString();
        labtrans.starttime = starttime.getText().toString();
        labtrans.finishtime = finishtime.getText().toString();
        labtrans.regularhrs = regularhrs.getText().toString();
        labtrans.payrate = payrate.getText().toString();
        labtrans.linecost = linecost.getText().toString();
        labtrans.assetnum = assetnum.getText().toString();
        labtrans.transtype = transtype.getText().toString();
        return labtrans;
    }

    private View.OnClickListener confirmOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = getIntent();
            if(labtrans.actualstaskid.equals(actualstaskid.getText().toString())
                    &&labtrans.craft.equals(craft.getText().toString())
                    &&labtrans.skilllevel.equals(skilllevel.getText().toString())
                    &&labtrans.laborcode.equals(laborcode.getText().toString())
                    &&labtrans.startdate.equals(startdate.getText().toString())
                    &&labtrans.starttime.equals(starttime.getText().toString())
                    &&labtrans.finishtime.equals(finishtime.getText().toString())
                    &&labtrans.regularhrs.equals(regularhrs.getText().toString())
                    &&labtrans.payrate.equals(payrate.getText().toString())
                    &&labtrans.linecost.equals(linecost.getText().toString())
                    &&labtrans.assetnum.equals(assetnum.getText().toString())
                    &&labtrans.transtype.equals(transtype.getText().toString())) {//如果内容没有修改
                intent.putExtra("labtrans",labtrans);
            }else {
                Labtrans labtrans = getLabtrans();
                if(labtrans.optiontype==null||!labtrans.optiontype.equals("add")) {
                    labtrans.optiontype = "update";
                }
                intent.putExtra("labtrans", labtrans);
                Toast.makeText(LabtransDetailsActivity.this, "实际员工本地修改成功", Toast.LENGTH_SHORT).show();
            }
            intent.putExtra("position", position);
            LabtransDetailsActivity.this.setResult(2, intent);
            finish();
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Option option;
        switch (resultCode) {
            case Constants.LABORCRAFTRATECODE:
                option = (Option) data.getSerializableExtra("option");
                laborcode.setText(option.getName());
                break;
        }
    }
}
