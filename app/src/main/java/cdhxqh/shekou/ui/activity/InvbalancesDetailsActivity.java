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
     * 货位
     */
    private TextView binnumText;
    /**
     * 批号
     */
    private TextView udtypeText;
    /**
     * 当前数量
     */
    private TextView curbalText;


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
        udtypeText = (TextView) findViewById(R.id.udtype_text_id);
        curbalText = (TextView) findViewById(R.id.invbalances_curbal_text);
        physcntdateText = (TextView) findViewById(R.id.invbalances_physcntdate_text_id);

        if (invbalances != null) {

            binnumText.setText(invbalances.binnum == null ? "暂无数据" : invbalances.binnum);
            udtypeText.setText(invbalances.udtype == null ? "暂无数据" : invbalances.udtype);
            curbalText.setText(invbalances.curbal == null ? "暂无数据" : invbalances.curbal);
            physcntdateText.setText(invbalances.physcntdate == null ? "暂无数据" : invbalances.physcntdate);
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
