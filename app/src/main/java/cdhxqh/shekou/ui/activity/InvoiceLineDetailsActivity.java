package cdhxqh.shekou.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import cdhxqh.shekou.R;
import cdhxqh.shekou.model.InvoiceLine;

/**
 * 付款申请行详情
 */
public class InvoiceLineDetailsActivity extends BaseActivity {
    private static String TAG = "InvoiceLineDetailsActivity";

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
    private TextView invoicelinenumText; //行号
    private TextView ponumText;//订单号
    private TextView podesText;//订单描述
    private TextView polinenumText;//订单行
    private TextView invwonumText;//申请单号
    private TextView indmsText;//申请描述
    private TextView descriptionText;//付款明细描述
    private TextView udtaxunitcostText;//含税单价
    private TextView udtotalcostText;//含税总价


    private InvoiceLine invoiceLine;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoiceline_details);
        geiIntentData();
        findViewById();
        initView();
    }

    private void geiIntentData() {
        invoiceLine = (InvoiceLine) getIntent().getSerializableExtra("invoiceLine");
    }

    @Override
    protected void findViewById() {
        backImageView = (ImageView) findViewById(R.id.title_back_id);
        titleTextView = (TextView) findViewById(R.id.title_name);

        invoicelinenumText = (TextView) findViewById(R.id.invoicelinenum_text_id);
        ponumText = (TextView) findViewById(R.id.ponum_text_id);
        podesText = (TextView) findViewById(R.id.udms_text_id);
        polinenumText = (TextView) findViewById(R.id.ddh_text_id);
        invwonumText = (TextView) findViewById(R.id.sq_wonum_text_id);
        indmsText = (TextView) findViewById(R.id.poms_text_id);
        descriptionText = (TextView) findViewById(R.id.description_text_id);
        udtaxunitcostText = (TextView) findViewById(R.id.udtaxunitcost_text_id);
        udtotalcostText = (TextView) findViewById(R.id.udtotalcost_text_id);

    }

    @Override
    protected void initView() {
        backImageView.setOnClickListener(backImageViewOnClickListener);
        titleTextView.setText(R.string.fksqh_text);

        showData(invoiceLine);


    }


    private View.OnClickListener backImageViewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };


    //显示信息
    private void showData(InvoiceLine invoiceLine) {
        invoicelinenumText.setText(invoiceLine.getINVOICELINENUM());
        ponumText.setText(invoiceLine.getPONUM());
        podesText.setText(invoiceLine.getPODES());
        polinenumText.setText(invoiceLine.getPOLINENUM());
        invwonumText.setText(invoiceLine.getINVOICENUM());
        indmsText.setText(invoiceLine.getINDMS());
        descriptionText.setText(invoiceLine.getDESCRIPTION());
        udtaxunitcostText.setText(invoiceLine.getUDTAXUNITCOST());
        udtotalcostText.setText(invoiceLine.getUDTOTALCOST());

    }


}
