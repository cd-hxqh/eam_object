package cdhxqh.shekou.ui.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import cdhxqh.shekou.R;
import cdhxqh.shekou.model.PoLine;

/**
 * 非年度采购单行
 */
public class PoLineDetailsActivity extends BaseActivity {
    private static String TAG = "PoLineDetailsActivity";

    /**
     * 返回按钮
     */
    private ImageView backImageView;
    /**
     * 标题
     */
    private TextView titleTextView;


    /**
     * 界面信息
     **/
    private TextView polinenumText; //序号
    private TextView descriptionText;//采购项
    private TextView orderqtyText;//数量
    private TextView orderunitText;//单位
    private TextView udtaxunitcostText;//含税单价
    private TextView currencycodeText;//含税总价


    private LinearLayout polineLinearlayout;
    //非年度采购订单
    private TextView sccgdText;//上次采购单
    private TextView sccgdjText;//上次采购单价
    private TextView udghzqText;//货期（天）
    private TextView prnumText;//请购单
    private TextView sqrText;//请购人
    private TextView qgbmText;//请购部门
    private TextView sqpzrText;//申请批准

    private LinearLayout waixieLinearLayout;
    //外协服务采购订单
    private TextView tax1codeText;//税率
    private TextView qgdText;//请购单
    private TextView qgrText;//请购人
    private TextView sqpzText;//申请批准
    private TextView qgyyText;//请购原因


    private PoLine poline;

    private int mark;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poline_details);
        geiIntentData();
        findViewById();
        initView();
    }

    private void geiIntentData() {
        poline = (PoLine) getIntent().getSerializableExtra("poline");
        mark = getIntent().getExtras().getInt("mark");
        Log.i(TAG,"mark="+mark);
    }

    @Override
    protected void findViewById() {
        backImageView = (ImageView) findViewById(R.id.title_back_id);
        titleTextView = (TextView) findViewById(R.id.title_name);

        polinenumText = (TextView) findViewById(R.id.polinenum_text_id);
        descriptionText = (TextView) findViewById(R.id.description_text_id);
        orderqtyText = (TextView) findViewById(R.id.invuse_quantity_text_id);
        orderunitText = (TextView) findViewById(R.id.orderunit_text_id);
        udtaxunitcostText = (TextView) findViewById(R.id.udtaxunitcost_text_id);
        currencycodeText = (TextView) findViewById(R.id.udtotalcost_text_id);

        polineLinearlayout = (LinearLayout) findViewById(R.id.activity_poline_item_details_id);
        sccgdText = (TextView) findViewById(R.id.sccgd_text_id);
        sccgdjText = (TextView) findViewById(R.id.sccgdj_text_);
        udghzqText = (TextView) findViewById(R.id.udghzq_text_id);
        prnumText = (TextView) findViewById(R.id.prnum_text);
        sqrText = (TextView) findViewById(R.id.sqr_text_id);
        qgbmText = (TextView) findViewById(R.id.qgbm_text_id);
        sqpzrText = (TextView) findViewById(R.id.sqpzr_text_id);

        waixieLinearLayout = (LinearLayout) findViewById(R.id.activity_waixiepoline_item_details_id);
        tax1codeText = (TextView) findViewById(R.id.udtax_text_id);
        qgdText = (TextView) findViewById(R.id.prnum_text_id);
        qgrText = (TextView) findViewById(R.id.qgr_text_id);
        sqpzText = (TextView) findViewById(R.id.sqpz_text_id);
        qgyyText = (TextView) findViewById(R.id.qgyy_text_id);

    }

    @Override
    protected void initView() {
        backImageView.setOnClickListener(backImageViewOnClickListener);
        titleTextView.setText(R.string.poline_details_title);
        if (mark == 0) {
            polineLinearlayout.setVisibility(View.VISIBLE);
        } else {
            waixieLinearLayout.setVisibility(View.VISIBLE);
        }
        showData(poline);


    }


    private View.OnClickListener backImageViewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };


    //显示信息
    private void showData(PoLine poline) {
        polinenumText.setText(poline.getPOLINENUM());
        descriptionText.setText(poline.getDESCRIPTION());
        orderqtyText.setText(poline.getORDERQTY());
        orderunitText.setText(poline.getORDERUNIT());
        udtaxunitcostText.setText(poline.getUDTAXUNITCOST());
        currencycodeText.setText(poline.getUDTOTALCOST());


        sccgdText.setText(poline.getSCCGD());
        sccgdjText.setText(poline.getSCCGDJ());
        udghzqText.setText(poline.getUDBZQ());
        prnumText.setText(poline.getPRNUM());
        sqrText.setText(poline.getSQR());
        qgbmText.setText(poline.getQGBM());
        sqpzrText.setText(poline.getSQPZR());


        tax1codeText.setText(poline.TAX1CODE);
        qgdText.setText(poline.getQGD());
        qgrText.setText(poline.getQGR());
        sqpzText.setText(poline.getSQPZ());
        qgyyText.setText(poline.getQGYY());

    }


}
