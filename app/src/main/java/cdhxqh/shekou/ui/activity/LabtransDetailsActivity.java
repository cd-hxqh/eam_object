package cdhxqh.shekou.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.flyco.animation.BaseAnimatorSet;
import com.flyco.animation.BounceEnter.BounceTopEnter;
import com.flyco.animation.SlideExit.SlideBottomExit;
import com.flyco.dialog.entity.DialogMenuItem;
import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.NormalListDialog;

import java.util.ArrayList;

import cdhxqh.shekou.R;
import cdhxqh.shekou.config.Constants;
import cdhxqh.shekou.model.Labtrans;
import cdhxqh.shekou.model.Option;
import cdhxqh.shekou.model.Woactivity;
import cdhxqh.shekou.model.Wplabor;
import cdhxqh.shekou.utils.DateSelect;
import cdhxqh.shekou.utils.DateTimeSelect;
import cdhxqh.shekou.utils.MessageUtils;

/**
 * Created by think on 2015/11/6.
 * 实际员工详情页面
 */
public class LabtransDetailsActivity extends BaseActivity {
    private String TAG = "LabtransDetailsActivity";
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
    private TextView laborcode; //员工
    private TextView startdate; //开始日期
    private TextView starttime; //开始时间
    private TextView finishtime; //结束时间
    private EditText regularhrs;//常规时数
    private EditText payrate;//费率
    private TextView linecost;//行成本

    private int position;
    private ArrayList<Woactivity> woactivityList = new ArrayList<>();
    private String status;
    private BaseAnimatorSet mBasIn;
    private BaseAnimatorSet mBasOut;
    private ArrayList<DialogMenuItem> mMenuItems = new ArrayList<>();

    private LinearLayout buttonlayout;
    private Button confirm;//确定
    private Button delete;//删除

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_labtrans_details);

        geiIntentData();
        findViewById();
        initView();
        mBasIn = new BounceTopEnter();
        mBasOut = new SlideBottomExit();
        addtaskidData();
    }

    private void geiIntentData() {
        labtrans = (Labtrans) getIntent().getSerializableExtra("labtrans");
        woactivityList = (ArrayList<Woactivity>) getIntent().getSerializableExtra("woactivityList");
        position = getIntent().getIntExtra("position", 0);
        status = getIntent().getStringExtra("status");
    }

    @Override
    protected void findViewById() {
        backImageView = (ImageView) findViewById(R.id.title_back_id);
        titleTextView = (TextView) findViewById(R.id.title_name);

        actualstaskid = (TextView) findViewById(R.id.work_labtrans_actualstaskid);
        laborcode = (TextView) findViewById(R.id.work_labtrans_laborcode);
        startdate = (TextView) findViewById(R.id.work_labtrans_startdate);
        starttime = (TextView) findViewById(R.id.work_labtrans_starttime);
        finishtime = (TextView) findViewById(R.id.work_labtrans_finishtime);
        regularhrs = (EditText) findViewById(R.id.work_labtrans_regularhrs);
        payrate = (EditText) findViewById(R.id.work_labtrans_payrate);
        linecost = (TextView) findViewById(R.id.work_labtrans_linecost);

        buttonlayout = (LinearLayout) findViewById(R.id.button_layout);
        confirm = (Button) findViewById(R.id.confirm);
        delete = (Button) findViewById(R.id.work_delete);
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

        actualstaskid.setText(null == labtrans.actualstaskid ? "暂无数据" : labtrans.actualstaskid);
        laborcode.setText(labtrans.laborcode);
        startdate.setText(labtrans.startdate);
        starttime.setText(labtrans.starttime.equals("null") ? "暂无数据" : labtrans.starttime);
        finishtime.setText(labtrans.finishtime);
        regularhrs.setText(labtrans.regularhrs);
        payrate.setText(labtrans.payrate);
        linecost.setText(labtrans.linecost);

        if (status != null && (status.equals(Constants.STATUS7)
                || status.equals(Constants.STATUS18) || status.equals(Constants.STATUS10))) {
            buttonlayout.setVisibility(View.VISIBLE);
        } else {
            buttonlayout.setVisibility(View.GONE);
        }

        actualstaskid.setOnClickListener(actualstaskidOnClickListener);
        laborcode.setOnClickListener(new LayoutOnClickListener(Constants.LABORCRAFTRATECODE));
        startdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DateSelect(LabtransDetailsActivity.this, startdate).showDialog();
            }
        });
        starttime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DateTimeSelect(LabtransDetailsActivity.this, starttime).showDialog();
            }
        });
        finishtime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DateTimeSelect(LabtransDetailsActivity.this, finishtime).showDialog();
            }
        });

        confirm.setOnClickListener(confirmOnClickListener);
        delete.setOnClickListener(deleteOnClickListener);

        setEnabled(false);
    }

    private View.OnClickListener actualstaskidOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (woactivityList == null || woactivityList.size() == 0) {
                Toast.makeText(LabtransDetailsActivity.this, "无任务数据", Toast.LENGTH_SHORT).show();
            } else {
                NormalListDialog();
            }
        }
    };

    private void NormalListDialog() {
        final NormalListDialog dialog = new NormalListDialog(LabtransDetailsActivity.this, mMenuItems);
        dialog.title("请选择")//
                .showAnim(mBasIn)//
                .dismissAnim(mBasOut)//
                .show();
        dialog.setOnOperItemClickL(new OnOperItemClickL() {
            @Override
            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {

                actualstaskid.setText(mMenuItems.get(position).mOperName);

                dialog.dismiss();
            }
        });
    }

    /**
     * 添加数据*
     */
    private void addtaskidData() {

        for (int i = 0; i < woactivityList.size(); i++)
            mMenuItems.add(new DialogMenuItem(woactivityList.get(i).taskid, 0));


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

    private Labtrans getLabtrans() {
        Labtrans labtrans = this.labtrans;
        labtrans.actualstaskid = actualstaskid.getText().toString();
        labtrans.laborcode = laborcode.getText().toString();
        labtrans.startdate = startdate.getText().toString();
        labtrans.starttime = starttime.getText().toString();
        labtrans.finishtime = finishtime.getText().toString();
        labtrans.regularhrs = regularhrs.getText().toString();
        labtrans.payrate = payrate.getText().toString();
        labtrans.linecost = linecost.getText().toString();
        return labtrans;
    }

    private View.OnClickListener confirmOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = getIntent();
            if (labtrans.actualstaskid.equals(actualstaskid.getText().toString())
                    && labtrans.laborcode.equals(laborcode.getText().toString())
                    && labtrans.startdate.equals(startdate.getText().toString())
                    && labtrans.starttime.equals(starttime.getText().toString())
                    && labtrans.finishtime.equals(finishtime.getText().toString())
                    && labtrans.regularhrs.equals(regularhrs.getText().toString())
                    && labtrans.payrate.equals(payrate.getText().toString())
                    && labtrans.linecost.equals(linecost.getText().toString())
                    ) {//如果内容没有修改
                intent.putExtra("labtrans", labtrans);
            } else {
                Labtrans labtrans = getLabtrans();
                if (labtrans.optiontype == null || !labtrans.optiontype.equals("add")) {
                    labtrans.optiontype = "update";
                }
                intent.putExtra("labtrans", labtrans);
                MessageUtils.showMiddleToast(LabtransDetailsActivity.this, "实际员工本地修改成功");
            }
            intent.putExtra("position", position);
            LabtransDetailsActivity.this.setResult(2, intent);
            finish();
        }
    };

    private View.OnClickListener deleteOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = getIntent();
            intent.putExtra("position", position);
            if (labtrans.labtransid == null || labtrans.labtransid.equals("")) {
                LabtransDetailsActivity.this.setResult(3, intent);
            } else {
                Labtrans labtrans = getLabtrans();
                labtrans.optiontype = "delete";
                intent.putExtra("labtrans", labtrans);
                LabtransDetailsActivity.this.setResult(4, intent);
            }
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


    /**
     * 设置状态为不可编辑状态
     **/
    private void setEnabled(boolean enabled) {
        actualstaskid.setEnabled(enabled);
        laborcode.setEnabled(enabled);
        startdate.setEnabled(enabled);
        starttime.setEnabled(enabled);
        finishtime.setEnabled(enabled);
        regularhrs.setEnabled(enabled);
        payrate.setEnabled(enabled);
        linecost.setEnabled(enabled);
    }

}
