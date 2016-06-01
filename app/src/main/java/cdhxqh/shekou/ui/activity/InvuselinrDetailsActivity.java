package cdhxqh.shekou.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import cdhxqh.shekou.R;
import cdhxqh.shekou.model.Invuse;
import cdhxqh.shekou.model.Invuseline;

/**
 * 领料单详情
 */
public class InvuselinrDetailsActivity extends BaseActivity {
    private static String TAG = "InvuselinrDetailsActivity";

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
     *备件
     */
    private TextView itemnumText;
    /**
     * 备件名称
     */
    private TextView descriptionText;

    /**
     *数量
     */
    private TextView quantityText;
    /**
     *单位成本
     */
    private TextView unitcostText;
    /**
     *发放单位
     */
    private TextView issueunitText;
    /**
     * 地点
     */
    private TextView siteidText;
    /**
     * 输入人
     */
    private TextView enterbyText;

    /**
     * invuseline*
     */
    private Invuseline invuseline;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invuseline_details);
        geiIntentData();
        findViewById();
        initView();
    }

    private void geiIntentData() {
        invuseline = (Invuseline) getIntent().getParcelableExtra("invuseline");
    }

    @Override
    protected void findViewById() {
        backImageView = (ImageView) findViewById(R.id.title_back_id);
        titleTextView = (TextView) findViewById(R.id.title_name);

//        itemnumText = (TextView) findViewById(R.id.invuseline_itemnum_text_id);
//        descriptionText = (TextView) findViewById(R.id.invuseline_description_text_id);
//        quantityText = (TextView) findViewById(R.id.invuse_quantity_text_id);
//        unitcostText = (TextView) findViewById(R.id.invuseline_linecost_text_id);
//        issueunitText = (TextView) findViewById(R.id.invuseline_issueunit_text_id);
//        siteidText = (TextView) findViewById(R.id.invuseline_siteid_text_id);
//        enterbyText = (TextView) findViewById(R.id.invuseline_enterby_text_id);



        if (invuseline != null) {

//            itemnumText.setText(invuseline.itemnum==null?"暂无数据":invuseline.itemnum);
//            descriptionText.setText(invuseline.description==null?"暂无数据":invuseline.description);
//            quantityText.setText(invuseline.quantity==null?"暂无数据":invuseline.quantity);
//            unitcostText.setText(invuseline.unitcost==null?"暂无数据":invuseline.unitcost);
//            issueunitText.setText(invuseline.issueunit==null?"暂无数据":invuseline.issueunit);
//            siteidText.setText(invuseline.siteid==null?"暂无数据":invuseline.siteid);
//            enterbyText.setText(invuseline.enterby==null?"暂无数据":invuseline.enterby);
        }
    }

    @Override
    protected void initView() {
        backImageView.setOnClickListener(backImageViewOnClickListener);
        titleTextView.setText(getString(R.string.invuseline_details_title));

    }

    private View.OnClickListener backImageViewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };




}
