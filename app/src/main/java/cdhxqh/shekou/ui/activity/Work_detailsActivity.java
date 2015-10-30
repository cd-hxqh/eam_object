package cdhxqh.shekou.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cdhxqh.shekou.R;
import cdhxqh.shekou.model.WorkOrder;

/**
 * Created by think on 2015/10/29.
 */
public class Work_detailsActivity extends BaseActivity {

    private TextView titlename;
    private ImageView menuImageView;
    private RelativeLayout backlayout;
    private WorkOrder workOrder;
    private TextView wonum;
    private TextView description;
    private TextView glz;
    private TextView gls;
    private TextView glbz;
    private TextView status;
    private TextView statusdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workdetails);
        geiIntentData();
        findViewById();
        initView();
    }

    /**
     * 获取数据*
     */
    private void geiIntentData() {
        workOrder = (WorkOrder) getIntent().getParcelableExtra("workOrder");
    }
    @Override
    protected void findViewById() {
        titlename = (TextView) findViewById(R.id.title_name);
        menuImageView = (ImageView) findViewById(R.id.title_add);
        backlayout = (RelativeLayout) findViewById(R.id.title_back);

        wonum = (TextView) findViewById(R.id.work_wonum);
        description = (TextView) findViewById(R.id.work_description);
        glz = (TextView) findViewById(R.id.work_glz);
        gls = (TextView) findViewById(R.id.work_gls);
        glbz = (TextView) findViewById(R.id.work_glbz);
        status = (TextView) findViewById(R.id.work_status);
        statusdate = (TextView) findViewById(R.id.work_statusdate);
    }

    @Override
    protected void initView() {
        titlename.setText(getResources().getString(R.string.work_details));
        backlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        menuImageView.setImageResource(R.drawable.ic_drawer);
        menuImageView.setVisibility(View.VISIBLE);

        wonum.setText(workOrder.wonum);
        description.setText(workOrder.description);
        glz.setText(workOrder.glz);
        gls.setText(workOrder.gls);
        glbz.setText(workOrder.glbz);
        status.setText(workOrder.status);
        statusdate.setText(workOrder.statusdate);
    }
}
