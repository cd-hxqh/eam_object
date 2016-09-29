package cdhxqh.shekou.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import cdhxqh.shekou.R;
import cdhxqh.shekou.model.Servrectrans;

/**
 * 服务接收验收
 */
public class ServrectransDetailsActivity extends BaseActivity {
    private static String TAG = "ServrectransDetailsActivity";

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
    private TextView polinenumText; //订单行
    private TextView descriptionText;//描述
    private TextView qtytoreceiveText;//数量
    private TextView issuetypeText;//类型
    private TextView transdateText;//接收日期
    private TextView jsrText;//接收人
    private TextView wonumText;//申请单号
    private TextView statusText;//检查状态


    private Servrectrans servrectrans;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servrectrans_details);
        geiIntentData();
        findViewById();
        initView();
    }

    private void geiIntentData() {
        servrectrans = (Servrectrans) getIntent().getSerializableExtra("servrectrans");
    }

    @Override
    protected void findViewById() {
        backImageView = (ImageView) findViewById(R.id.title_back_id);
        titleTextView = (TextView) findViewById(R.id.title_name);

        polinenumText = (TextView) findViewById(R.id.ddh_text_id);
        descriptionText = (TextView) findViewById(R.id.description_text_id);
        qtytoreceiveText = (TextView) findViewById(R.id.invuse_quantity_text_id);
        issuetypeText = (TextView) findViewById(R.id.work_labtrans_transtype_id);
        transdateText = (TextView) findViewById(R.id.transdate_text_id);
        jsrText = (TextView) findViewById(R.id.jsr_text_id);
        wonumText = (TextView) findViewById(R.id.sq_wonum_text_id);
        statusText = (TextView) findViewById(R.id.jc_status_text_id);

    }

    @Override
    protected void initView() {
        backImageView.setOnClickListener(backImageViewOnClickListener);
        titleTextView.setText(R.string.fwjsysxq_text);

        showData(servrectrans);


    }


    private View.OnClickListener backImageViewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };


    //显示信息
    private void showData(Servrectrans servrectrans) {
        polinenumText.setText(servrectrans.getPOLINENUM());
        descriptionText.setText(servrectrans.getDESCRIPTION());
        qtytoreceiveText.setText(servrectrans.getQTYTORECEIVE());
        issuetypeText.setText(servrectrans.getISSUETYPE());
        transdateText.setText(servrectrans.getTRANSDATE());
        jsrText.setText(servrectrans.getJSR());
        wonumText.setText(servrectrans.getWONUM());
        statusText.setText(servrectrans.getSTATUS());

    }


}
