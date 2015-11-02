package cdhxqh.shekou.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import cdhxqh.shekou.R;
import cdhxqh.shekou.model.Invbalances;
import cdhxqh.shekou.model.Matrectrans;

/**
 * 入库详情
 */
public class MatrectransDetailsActivity extends BaseActivity {
    private static String TAG = "MatrectransDetailsActivity";

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
     * 原地点
     */
    private TextView fromsiteidText;
    /**
     * 原位置
     */
    private TextView fromstorelocText;
    /**
     * 目标位置
     */
    private TextView tostorelocText;

    /**
     * matrectrans*
     */
    private Matrectrans matrectrans;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matusetrans_details);
        geiIntentData();
        findViewById();
        initView();
    }

    private void geiIntentData() {
        matrectrans = (Matrectrans) getIntent().getParcelableExtra("matrectrans");
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
        fromsiteidText = (TextView) findViewById(R.id.matusetrans_fromsiteid_text_id);
        fromstorelocText = (TextView) findViewById(R.id.matusetrans_fromstoreloc_text_id);
        tostorelocText = (TextView) findViewById(R.id.matusetrans_tostoreloc_text_id);

        if (matrectrans != null) {

            issuetypeText.setText(matrectrans.issuetype==null?"暂无数据":matrectrans.issuetype);
            actualdateText.setText(matrectrans.actualdate==null?"暂无数据":matrectrans.actualdate);
            transdateText.setText(matrectrans.transdate==null?"暂无数据":matrectrans.transdate);
            quantityText.setText(matrectrans.quantity==null?"暂无数据":matrectrans.quantity);
            unitcostText.setText(matrectrans.unitcost==null?"暂无数据":matrectrans.unitcost);
            linecostText.setText(matrectrans.loadedcost==null?"暂无数据":matrectrans.loadedcost);
            fromsiteidText.setText(matrectrans.fromsiteid==null?"暂无数据":matrectrans.fromsiteid);
            fromstorelocText.setText(matrectrans.fromstoreloc==null?"暂无数据":matrectrans.fromstoreloc);
            tostorelocText.setText(matrectrans.tostoreloc==null?"暂无数据":matrectrans.tostoreloc);
        }
    }

    @Override
    protected void initView() {
        backImageView.setOnClickListener(backImageViewOnClickListener);
        titleTextView.setText(getString(R.string.matrectrans_detail_title));

    }

    private View.OnClickListener backImageViewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };




}
