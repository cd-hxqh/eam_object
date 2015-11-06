package cdhxqh.shekou.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import cdhxqh.shekou.R;
import cdhxqh.shekou.model.Wplabor;

/**
 * Created by think on 2015/11/6.
 */
public class WplaborDetailsActivity extends BaseActivity {
    /**
     * 返回按钮
     */
    private ImageView backImageView;
    /**
     * 标题
     */
    private TextView titleTextView;

    private Wplabor wplabor = new Wplabor();
    private TextView laborcode;//员工
    private TextView laborhrs;//常规时数
    private TextView craft;//工种
    private TextView skilllevel;//技能级别
    private TextView vendor;//供应商
    private TextView contractnum;//员工合同
    private TextView quantity;//数量
    private TextView orgid;//组织
    private TextView siteid;//地点

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wplabor_details);

        geiIntentData();
        findViewById();
        initView();
    }

    private void geiIntentData() {
        wplabor = (Wplabor) getIntent().getParcelableExtra("wplabor");
    }
    @Override
    protected void findViewById() {
        backImageView = (ImageView) findViewById(R.id.title_back_id);
        titleTextView = (TextView) findViewById(R.id.title_name);

        laborcode = (TextView) findViewById(R.id.work_wplabor_laborcode);
        laborhrs = (TextView) findViewById(R.id.work_wplabor_laborhrs);
        craft = (TextView) findViewById(R.id.work_wplabor_craft);
        skilllevel = (TextView) findViewById(R.id.work_wplabor_skilllevel);
        vendor = (TextView) findViewById(R.id.work_wplabor_vendor);
        contractnum = (TextView) findViewById(R.id.work_wplabor_contractnum);
        quantity = (TextView) findViewById(R.id.work_wplabor_quantity);
        orgid = (TextView) findViewById(R.id.work_wplabor_orgid);
        siteid = (TextView) findViewById(R.id.work_wplabor_siteid);
    }

    @Override
    protected void initView() {
        backImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        titleTextView.setText(getResources().getString(R.string.title_activity_workwplabordetails));

        laborcode.setText(wplabor.laborcode);
        laborhrs.setText(wplabor.laborhrs);
        craft.setText(wplabor.craft);
        skilllevel.setText(wplabor.skilllevel);
        vendor.setText(wplabor.vendor);
        contractnum.setText(wplabor.contractnum);
        quantity.setText(wplabor.quantity);
        orgid.setText(wplabor.orgid);
        siteid.setText(wplabor.siteid);
    }
}
