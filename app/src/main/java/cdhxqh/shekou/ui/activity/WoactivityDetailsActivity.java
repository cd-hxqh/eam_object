package cdhxqh.shekou.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
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
    private TextView wojo1;//编号
    private TextView description;//描述
    private CheckBox wojo2;//需要安检
    private TextView targstartdate;//计划开始时间
    private TextView targcompdate;//计划完成时间
    private TextView actstart;//时间开始时间
    private TextView actfinish;///实际完成时间
    private TextView estdur;//持续时间

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_woactivity_details);

        geiIntentData();
        findViewById();
        initView();
    }

    private void geiIntentData() {
        woactivity = (Woactivity) getIntent().getParcelableExtra("woactivity");
    }
    @Override
    protected void findViewById() {
        backImageView = (ImageView) findViewById(R.id.title_back_id);
        titleTextView = (TextView) findViewById(R.id.title_name);

        wojo1 = (TextView) findViewById(R.id.work_woactivity_wojo1);
        description = (TextView) findViewById(R.id.work_woactivity_description);
        wojo2 = (CheckBox) findViewById(R.id.work_woactivity_wojo2);
        targstartdate = (TextView) findViewById(R.id.work_woactivity_targstartdate);
        targcompdate = (TextView) findViewById(R.id.work_woactivity_targcompdate);
        actstart = (TextView) findViewById(R.id.work_woactivity_actstart);
        actfinish = (TextView) findViewById(R.id.work_woactivity_actfinish);
        estdur = (TextView) findViewById(R.id.work_woactivity_estdur);
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

        wojo1.setText(woactivity.wojo1);
        description.setText(woactivity.description);
        wojo2.setChecked(woactivity.wojo2.equals("0") ? false : true);
        targstartdate.setText(woactivity.targstartdate);
        targcompdate.setText(woactivity.targcompdate);
        actstart.setText(woactivity.actstart);
        actfinish.setText(woactivity.actfinish);
        estdur.setText(woactivity.estdur);
    }
}
