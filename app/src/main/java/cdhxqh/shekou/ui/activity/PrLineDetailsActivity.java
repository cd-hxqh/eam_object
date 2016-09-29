package cdhxqh.shekou.ui.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import cdhxqh.shekou.R;
import cdhxqh.shekou.model.PrLine;

/**
 * 非年度采购单行
 */
public class PrLineDetailsActivity extends BaseActivity {
    private static String TAG = "PrLineDetailsActivity";

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
    //请购申请行表
    private LinearLayout sgprlineLinearlayout;
    private TextView prlinenumText; //序号
    private TextView itemnumText;//设备编码
    private TextView descriptionText;//描述
    private TextView orderqtyText;//数量
    private TextView orderunitText;//单位
    private TextView unitcostText;//单价
    private TextView linecostText;//总价
    private TextView udjedxText;//大写金额
    private TextView udyyText;//申购原因
    private TextView remarkText;//备注
    private TextView requestedbyText;//申请人
    private TextView reqdeliverydateText;//要求的日期
    private CheckBox udisxyText;//协议采购
    private CheckBox iscsxText;//超限？
    private CheckBox udcancleText;//取消？


    //计划分配
    private LinearLayout jhprlineLinearLayout;
    private TextView udmanufacturerText;//品牌/制造商
    private TextView ppmsText;//品牌描述
    private TextView channumText;//渠道编码
    private TextView qdmsText;//渠道描述
    private TextView fpcgyText;//分配采购员
    private CheckBox udisfpText;//是否已分配？
    private TextView udcontractnumText;//年度合同编号
    private CheckBox udisscText;//是否首次采购?
    private TextView rfqnumText;//询价单号
    private TextView rfqunitcostText;//单价
    private TextView rfqoderqtyText;//数量
    private TextView rfqlinecostText;//行成本
    private TextView ponumText;//采购单号
    private TextView poorderqtyText;//数量
    private TextView pounitcostText;//单位成本
    private TextView polinecostText;//行成本


    private PrLine prline;

    private int mark;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prline_details);
        geiIntentData();
        findViewById();
        initView();
    }

    private void geiIntentData() {
        prline = (PrLine) getIntent().getSerializableExtra("prline");
        mark = getIntent().getExtras().getInt("mark");
        Log.i(TAG, "mark=" + mark);
    }

    @Override
    protected void findViewById() {
        backImageView = (ImageView) findViewById(R.id.title_back_id);
        titleTextView = (TextView) findViewById(R.id.title_name);

        sgprlineLinearlayout = (LinearLayout) findViewById(R.id.qg_prline_details_id);
        prlinenumText = (TextView) findViewById(R.id.prlinenum_text_id);
        itemnumText = (TextView) findViewById(R.id.itemnum_text_id);
        descriptionText = (TextView) findViewById(R.id.description_text_id);


        orderqtyText = (TextView) findViewById(R.id.orderqty_text_id);
        orderunitText = (TextView) findViewById(R.id.issueunit_text_id);
        unitcostText = (TextView) findViewById(R.id.unitcost_text_id);
        linecostText = (TextView) findViewById(R.id.totalcost_v_text_id);
        udjedxText = (TextView) findViewById(R.id.udjedx_text_id);
        udyyText = (TextView) findViewById(R.id.udyy_text_id);
        remarkText = (TextView) findViewById(R.id.udremark_id);
        requestedbyText = (TextView) findViewById(R.id.requestedby_text_id);
        reqdeliverydateText = (TextView) findViewById(R.id.reqdeliverydate_text_id);
        udisxyText = (CheckBox) findViewById(R.id.udisxy_text_id);
        iscsxText = (CheckBox) findViewById(R.id.iscsx_text_id);
        udcancleText = (CheckBox) findViewById(R.id.udcancle_text_id);


        jhprlineLinearLayout = (LinearLayout) findViewById(R.id.fp_prline_details_id);
        udmanufacturerText = (TextView) findViewById(R.id.udmanufacturer_text_id);
        ppmsText = (TextView) findViewById(R.id.ppms_text_id);
        channumText = (TextView) findViewById(R.id.channum_text_id);
        qdmsText = (TextView) findViewById(R.id.qdms_text_id);
        fpcgyText = (TextView) findViewById(R.id.fpcgy_text_id);
        udisfpText = (CheckBox) findViewById(R.id.udisfp_text_id);
        udcontractnumText = (TextView) findViewById(R.id.udcontractnum_text_id);
        udisscText = (CheckBox) findViewById(R.id.udissc_text_id);
        rfqnumText = (TextView) findViewById(R.id.rfqnum_text_id);
        rfqunitcostText = (TextView) findViewById(R.id.unitcost_text_id);
        rfqoderqtyText = (TextView) findViewById(R.id.quantity_text_id);
        rfqlinecostText = (TextView) findViewById(R.id.displaylinecost_text_id);
        ponumText = (TextView) findViewById(R.id.ponum_text_id);
        poorderqtyText = (TextView) findViewById(R.id.rfqoderqty_text_id);
        pounitcostText = (TextView) findViewById(R.id.displayunitcost_text_id);
        polinecostText = (TextView) findViewById(R.id.polinecost_text_id);

    }

    @Override
    protected void initView() {
        backImageView.setOnClickListener(backImageViewOnClickListener);

        if (mark == 0) {
            titleTextView.setText(R.string.qgmxh_text);
            sgprlineLinearlayout.setVisibility(View.VISIBLE);
        } else {
            titleTextView.setText(R.string.jhfp_text);
            jhprlineLinearLayout.setVisibility(View.VISIBLE);
        }
        showData(prline);


    }


    private View.OnClickListener backImageViewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };


    //显示信息
    private void showData(PrLine prline) {
        prlinenumText.setText(prline.getPRLINENUM());
        itemnumText.setText(prline.getITEMNUM());
        descriptionText.setText(prline.getDESCRIPTION());


        orderqtyText.setText(prline.getORDERQTY());
        orderunitText.setText(prline.getORDERUNIT());
        unitcostText.setText(prline.getUNITCOST());
        linecostText.setText(prline.getLINECOST());
        udjedxText.setText(prline.getUDJEDX());
        udyyText.setText(prline.getUDYY());
        remarkText.setText(prline.getREMARK());
        requestedbyText.setText(prline.getREQUESTEDBY());
        reqdeliverydateText.setText(prline.getREQDELIVERYDATE());

        if (prline.getUDISXY() != null && prline.getUDISXY().equals("Y")) {

            udisxyText.setChecked(true);
        }
        if (prline.getISCSX() != null && prline.getISCSX().equals("Y")) {
            iscsxText.setChecked(true);
        }

        if (prline.getUDCANCLE() != null && prline.getUDCANCLE().equals("Y")) {
            udcancleText.setChecked(true);
        }


        udmanufacturerText.setText(prline.getUDMANUFACTURER());
        ppmsText.setText(prline.getPPMS());
        channumText.setText(prline.getCHANNUM());
        qdmsText.setText(prline.getQDMS());
        fpcgyText.setText(prline.getFPCGY());
        if (prline.getUDISFP() != null && prline.getUDISFP().equals("Y")) {
            udisfpText.setChecked(true);
        }


        udcontractnumText.setText(prline.getUDCONTRACTNUM());
        if (prline.getUDISSC() != null && prline.getUDISSC().equals("Y")) {
            udisscText.setChecked(true);
        }

        rfqnumText.setText(prline.getRFQNUM());
        rfqunitcostText.setText(prline.getRFQUNITCOST());
        rfqoderqtyText.setText(prline.getRFQODERQTY());
        rfqlinecostText.setText(prline.getRFQLINECOST());
        ponumText.setText(prline.getPONUM());
        poorderqtyText.setText(prline.getPOORDERQTY());
        pounitcostText.setText(prline.getPOUNITCOST());
        polinecostText.setText(prline.getPOLINECOST());

    }


}
