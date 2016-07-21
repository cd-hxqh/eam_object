package cdhxqh.shekou.ui.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import cdhxqh.shekou.R;
import cdhxqh.shekou.model.Matusetrans;

/**
 * 物料详情
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
     * 任务
     */
    private TextView actualstaskidText;
    //物料编号
    private TextView itemnumText;
    //物料描述
    private TextView descriptionText;
    //行类型
    private TextView linetypeText;
    //单位成本
    private TextView unitcostText;
    //行成本
    private TextView linecostText;
    //输入人
    private TextView enterbyText;
    //实际日期
    private TextView actualdateText;
    //库房
    private TextView storelocText;
    //数量
    private TextView quantityText;
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
        Log.i(TAG, "matusetrans" + matusetrans);
    }

    @Override
    protected void findViewById() {
        backImageView = (ImageView) findViewById(R.id.title_back_id);
        titleTextView = (TextView) findViewById(R.id.title_name);

        actualstaskidText = (TextView) findViewById(R.id.actualstaskid_text_id);
        itemnumText = (TextView) findViewById(R.id.itemnum_text_id);
        descriptionText = (TextView) findViewById(R.id.desction_text_id);
        linetypeText = (TextView) findViewById(R.id.linetype_text_id);
        unitcostText = (TextView) findViewById(R.id.matusetrans_unitcost_text_id);
        linecostText = (TextView) findViewById(R.id.matusetrans_linecost_text_id);

        enterbyText = (TextView) findViewById(R.id.work_labtrans_enterby_id);
        actualdateText = (TextView) findViewById(R.id.matrectrans_actualdate_text_id);
        storelocText = (TextView) findViewById(R.id.inventory_location_text_id);
        quantityText = (TextView) findViewById(R.id.invuse_quantity_text_id);

        if (matusetrans != null) {

            actualstaskidText.setText(matusetrans.actualstaskid == null ? "暂无数据" : matusetrans.actualstaskid);
            itemnumText.setText(matusetrans.itemnum == null ? "暂无数据" : matusetrans.itemnum);
            descriptionText.setText(matusetrans.description == null ? "暂无数据" : matusetrans.description);
            linetypeText.setText(matusetrans.linetype == null ? "暂无数据" : matusetrans.linetype);
            unitcostText.setText(matusetrans.unitcost == null ? "暂无数据" : matusetrans.unitcost);
            linecostText.setText(matusetrans.linecost == null ? "暂无数据" : matusetrans.linecost);
            enterbyText.setText(matusetrans.enterby == null ? "暂无数据" : matusetrans.enterby);
            actualdateText.setText(matusetrans.actualdate == null ? "暂无数据" : matusetrans.actualdate);
            storelocText.setText(matusetrans.storeloc == null ? "暂无数据" : matusetrans.storeloc);
            quantityText.setText(matusetrans.quantity == null ? "暂无数据" : matusetrans.quantity);
        }
    }

    @Override
    protected void initView() {
        backImageView.setOnClickListener(backImageViewOnClickListener);
        titleTextView.setText(R.string.wuliao_detail_text);

    }

    private View.OnClickListener backImageViewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };


}
