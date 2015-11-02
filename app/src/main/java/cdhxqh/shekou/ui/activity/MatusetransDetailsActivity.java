package cdhxqh.shekou.ui.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import cdhxqh.shekou.R;
import cdhxqh.shekou.model.Matusetrans;

/**
 * 出库详情
 */
public class MatusetransDetailsActivity extends BaseActivity {
    private static String TAG = "MatusetransDetailsActivity";

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
     * 交易类型
     */
    private TextView issuetypeText;
    /**
     * 实际日期
     */
    private TextView actualdateText;

    /**
     * 交易日期
     */
    private TextView transdateText;
    /**
     * 数量
     */
    private TextView quantityText;
    /**
     * 单位成本
     */
    private TextView unitcostText;
    /**
     * 行成本
     */
    private TextView linecostText;
    /**
     * 工单
     */
    private TextView refwoText;
    /**
     * 位置
     */
    private TextView locationText;
    /**
     * 资产
     */
    private TextView assetnumText;
    /**
     * 输入人
     */
    private TextView enterbyText;

    /**
     * matusetrans*
     */
    private Matusetrans matusetrans;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matrectrans_details);
        geiIntentData();
        findViewById();
        initView();
    }

    private void geiIntentData() {
        matusetrans = (Matusetrans) getIntent().getParcelableExtra("matusetrans");
        Log.i(TAG,"matusetrans"+matusetrans);
    }

    @Override
    protected void findViewById() {
        backImageView = (ImageView) findViewById(R.id.title_back_id);
        titleTextView = (TextView) findViewById(R.id.title_name);

        issuetypeText = (TextView) findViewById(R.id.matrectrans_issuetype_text_id);
        actualdateText = (TextView) findViewById(R.id.matrectrans_actualdate_text_id);
        transdateText = (TextView) findViewById(R.id.matusetrans_transdate_text_id);
        quantityText = (TextView) findViewById(R.id.matusetrans_quantity_text_id);
        unitcostText = (TextView) findViewById(R.id.matusetrans_unitcost_text_id);
        linecostText = (TextView) findViewById(R.id.matusetrans_linecost_text_id);
        refwoText = (TextView) findViewById(R.id.matusetrans_refwo_text_id);
        locationText = (TextView) findViewById(R.id.matusetrans_location_text_id);
        assetnumText = (TextView) findViewById(R.id.matusetrans_assetnum_text_id);
        enterbyText = (TextView) findViewById(R.id.matusetrans_enterby_text_id);

        if (matusetrans != null) {

            issuetypeText.setText(matusetrans.issuetype==null?"暂无数据":matusetrans.issuetype);
            actualdateText.setText(matusetrans.actualdate==null?"暂无数据":matusetrans.actualdate);
            transdateText.setText(matusetrans.transdate==null?"暂无数据":matusetrans.transdate);
            quantityText.setText(matusetrans.quantity==null?"暂无数据":matusetrans.quantity);
            unitcostText.setText(matusetrans.unitcost==null?"暂无数据":matusetrans.unitcost);
            linecostText.setText(matusetrans.linecost==null?"暂无数据":matusetrans.linecost);
            refwoText.setText(matusetrans.refwo==null?"暂无数据":matusetrans.refwo);
            locationText.setText(matusetrans.location==null?"暂无数据":matusetrans.location);
            assetnumText.setText(matusetrans.assetnum==null?"暂无数据":matusetrans.assetnum);
            enterbyText.setText(matusetrans.enterby==null?"暂无数据":matusetrans.enterby);
        }
    }

    @Override
    protected void initView() {
        backImageView.setOnClickListener(backImageViewOnClickListener);
        titleTextView.setText(getResources().getString(R.string.matusetrans_detail_title));

    }

    private View.OnClickListener backImageViewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };




}
