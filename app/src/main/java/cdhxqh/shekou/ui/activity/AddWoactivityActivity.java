package cdhxqh.shekou.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import cdhxqh.shekou.R;
import cdhxqh.shekou.model.Woactivity;
import cdhxqh.shekou.model.Wplabor;

/**
 * Created by think on 2016/5/10.
 * 添加工单任务列表
 */
public class AddWoactivityActivity extends BaseActivity {
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
    private Button delete;//删除

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_woactivity);

        findViewById();
        initView();
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
        titleTextView.setText(getResources().getString(R.string.title_activity_workwoactivity_add));

        taskid.setText(getIntent().getIntExtra("taskid", 0) + "");

        confirm.setOnClickListener(confirmOnClickListener);
        delete.setOnClickListener(deleteOnClickListener);
    }

    private Woactivity getWoactivity() {
        Woactivity woactivity = new Woactivity();
        woactivity.taskid = taskid.getText().toString().trim();
        woactivity.wojo1 = wojo1.getText().toString().trim();
        woactivity.description = description.getText().toString().trim();
        woactivity.wojo2 = wojo2.isChecked() ? "Y" : "N";
        woactivity.udisdo = udisdo.isChecked() ? "Y" : "N";
        woactivity.udisyq = udisyq.isChecked() ? "Y" : "N";
        woactivity.udyqyy = udyqyy.getText().toString().trim();
        woactivity.udremark = udremark.getText().toString().trim();
        woactivity.optiontype = "add";
        return woactivity;
    }

    private View.OnClickListener confirmOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = getIntent();
            intent.putExtra("woactivity",getWoactivity());
            AddWoactivityActivity.this.setResult(1, intent);
            Toast.makeText(AddWoactivityActivity.this, "任务本地新增成功", Toast.LENGTH_SHORT).show();
            finish();
        }
    };

    private View.OnClickListener deleteOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };
}
