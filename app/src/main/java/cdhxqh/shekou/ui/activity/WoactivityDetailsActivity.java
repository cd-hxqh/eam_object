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
    private int position;

    private TextView taskid;//任务
    private TextView wojo1;//编号
    private TextView description;//描述
    private CheckBox wojo2;//需要安检
    private CheckBox udisdo;//是否完成
    private CheckBox udisyq;//是否延期
    private EditText udyqyy;//延期原因
    private EditText udremark;//备注
    private Button confirm;//确定


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
        position = getIntent().getIntExtra("position",0);
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

        confirm.setOnClickListener(confirmOnClickListener);
    }

    private Woactivity getWoactivity() {
        Woactivity woactivity = this.woactivity;
        woactivity.taskid = taskid.getText().toString().trim();
        woactivity.wojo1 = wojo1.getText().toString().trim();
        woactivity.description = description.getText().toString().trim();
        woactivity.wojo2 = wojo2.isChecked() ? "Y" : "N";
        woactivity.udisdo = udisdo.isChecked() ? "Y" : "N";
        woactivity.udisyq = udisyq.isChecked() ? "Y" : "N";
        woactivity.udyqyy = udyqyy.getText().toString().trim();
        woactivity.udremark = udremark.getText().toString().trim();
        return woactivity;
    }

    private View.OnClickListener confirmOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

        }
    };
}
