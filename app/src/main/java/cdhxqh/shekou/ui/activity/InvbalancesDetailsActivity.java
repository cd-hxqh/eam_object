package cdhxqh.shekou.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import cdhxqh.shekou.R;
import cdhxqh.shekou.model.Invbalances;
import cdhxqh.shekou.utils.MessageUtils;

/**
 * 库存余量详情
 */
public class InvbalancesDetailsActivity extends BaseActivity {
    private static String TAG = "InvbalancesDetailsActivity";

    /**
     * 返回按钮
     */
    private ImageView backImageView;
    /**
     * 标题
     */
    private TextView titleTextView;



    /**界面信息**/
    /**
     * 货柜
     */
    private TextView binnumText;
    /**
     * 当前数量
     */
    private TextView curbalText;

    /**
     * 暂存余量
     */
    private TextView stagedcurbalText;
    /**
     * 实际库存量
     */
    private TextView physcntText;
    /**
     * 单位成本
     */
    private TextView unitcostText;
    /**
     * 实际盘点日期
     */
    private TextView physcntdateText;

    /**
     * inventory*
     */
    private Invbalances invbalances;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invbalances_details);
        geiIntentData();
        findViewById();
        initView();
    }

    private void geiIntentData() {
        invbalances = (Invbalances) getIntent().getParcelableExtra("invbalances");
    }

    @Override
    protected void findViewById() {
        backImageView = (ImageView) findViewById(R.id.title_back_id);
        titleTextView = (TextView) findViewById(R.id.title_name);

        binnumText = (TextView) findViewById(R.id.invbalances_binnum_text_id);
        curbalText = (TextView) findViewById(R.id.invbalances_curbal_text_id);
        stagedcurbalText = (TextView) findViewById(R.id.invbalances_stagedcurbal_text_id);
        physcntText = (TextView) findViewById(R.id.invbalances_physcnt_text_id);
        unitcostText = (TextView) findViewById(R.id.invbalance_unitcost_text_id);
        physcntdateText = (TextView) findViewById(R.id.invbalances_physcntdate_text_id);

        if (invbalances != null) {

            binnumText.setText(invbalances.binnum==null?"暂无数据":invbalances.binnum);
            curbalText.setText(invbalances.curbal==null?"暂无数据":invbalances.curbal);
            stagedcurbalText.setText(invbalances.stagedcurbal==null?"暂无数据":invbalances.stagedcurbal);
            physcntText.setText(invbalances.physcnt==null?"暂无数据":invbalances.physcnt);
            unitcostText.setText(invbalances.unitcost==null?"暂无数据":invbalances.unitcost);
            physcntdateText.setText(invbalances.physcntdate==null?"暂无数据":invbalances.physcntdate);
        }
    }

    @Override
    protected void initView() {
        backImageView.setOnClickListener(backImageViewOnClickListener);
        titleTextView.setText(getString(R.string.invbalances_detail_title));

    }

    private View.OnClickListener backImageViewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };




}
