package cdhxqh.shekou.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import cdhxqh.shekou.R;
import cdhxqh.shekou.model.Woactivity;

/**
 * Created by think on 2015/11/6.
 * 任务详情页面
 */
public class WoactivityDetailsActivity extends BaseActivity {
    /**
     * 返回按钮
     */
    private ImageView backImageView;
    /**
     * 标题
     */
    private TextView titleTextView;

    private Woactivity woactivity = new Woactivity();
    private TextView taskid;//任务
    private TextView wojo1;//编号
    private TextView description;//描述
    private CheckBox wojo2;//需要安检
    private CheckBox udisdo;//是否完成
    private CheckBox udisyq;//是否延期
    private EditText udyqyy;//延期原因
    private EditText udremark;//备注
    private Button confirm;//确定

//    private TextView targstartdate;//计划开始时间
//    private TextView targcompdate;//计划完成时间
//    private TextView actstart;//时间开始时间
//    private TextView actfinish;///实际完成时间
//    private TextView estdur;//持续时间

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_woactivity_details);

        geiIntentData();
        findViewById();
        initView();
    }

    private void geiIntentData() {
        woactivity = (Woactivity) getIntent().getSerializableExtra("woactivity");
    }
    @Override
    protected void findViewById() {
        backImageView = (ImageView) findViewById(R.id.title_back_id);
        titleTextView = (TextView) findViewById(R.id.title_name);

        taskid = (TextView) findViewById(R.id.work_woactivity_taskid);
        wojo1 = (TextView) findViewById(R.id.work_woactivity_wojo1);
        description = (TextView) findViewById(R.id.work_woactivity_description);
        wojo2 = (CheckBox) findViewById(R.id.work_woactivity_wojo2);
        udisdo = (CheckBox) findViewById(R.id.work_woactivity_udisdo);
        udisyq = (CheckBox) findViewById(R.id.work_woactivity_udisyq);
        udyqyy = (EditText) findViewById(R.id.work_woactivity_udyqyy);
        udremark = (EditText) findViewById(R.id.work_woactivity_udremark);
        confirm = (Button) findViewById(R.id.confirm);
//        targstartdate = (TextView) findViewById(R.id.work_woactivity_targstartdate);
//        targcompdate = (TextView) findViewById(R.id.work_woactivity_targcompdate);
//        actstart = (TextView) findViewById(R.id.work_woactivity_actstart);
//        actfinish = (TextView) findViewById(R.id.work_woactivity_actfinish);
//        estdur = (TextView) findViewById(R.id.work_woactivity_estdur);
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

        taskid.setText(woactivity.taskid);
        wojo1.setText(woactivity.wojo1);
        description.setText(woactivity.description);
        wojo2.setChecked(!woactivity.wojo2.equals("N"));
        udisdo.setChecked(!woactivity.udisdo.equals("N"));
        udisyq.setChecked(!woactivity.udisyq.equals("N"));
        udyqyy.setText(woactivity.udyqyy);
        udremark.setText(woactivity.udremark);

//        targstartdate.setText(woactivity.targstartdate);
//        targcompdate.setText(woactivity.targcompdate);
//        actstart.setText(woactivity.actstart);
//        actfinish.setText(woactivity.actfinish);
//        estdur.setText(woactivity.estdur);
    }
}
