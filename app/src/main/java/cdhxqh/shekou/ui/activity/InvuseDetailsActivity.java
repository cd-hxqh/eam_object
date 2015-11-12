package cdhxqh.shekou.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import cdhxqh.shekou.R;
import cdhxqh.shekou.model.Invuse;
import cdhxqh.shekou.model.Matusetrans;

/**
 * 领料单详情
 */
public class InvuseDetailsActivity extends BaseActivity {
    private static String TAG = "InvuseDetailsActivity";

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
     *领料单号
     */
    private TextView invusenumText;
    /**
     * 描述
     */
    private TextView descriptionText;

    /**
     *库房
     */
    private TextView fromstorelocText;
    /**
     *领料人
     */
    private TextView udissuetoText;
    /**
     *工单
     */
    private TextView wonumText;
    /**
     * 状态
     */
    private TextView statusText;
    /**
     * 状态日期
     */
    private TextView statusdateText;
    /**
     * 位置
     */
    private TextView siteidText;

    /**
     * 审批工作流
     */
    private Button worlflowBtn;

    /**物料清单**/

    private Button materialBtn;

    /**
     * matusetrans*
     */
    private Invuse invuse;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invuse_details);
        geiIntentData();
        findViewById();
        initView();
    }

    private void geiIntentData() {
        invuse = (Invuse) getIntent().getParcelableExtra("invuse");
    }

    @Override
    protected void findViewById() {
        backImageView = (ImageView) findViewById(R.id.title_back_id);
        titleTextView = (TextView) findViewById(R.id.title_name);

        invusenumText = (TextView) findViewById(R.id.invuse_invusenum_text_id);
        descriptionText = (TextView) findViewById(R.id.invuse_description_text_id);
        fromstorelocText = (TextView) findViewById(R.id.invuse_location_text_id);
        udissuetoText = (TextView) findViewById(R.id.invuse_udissueto_text_id);
        wonumText = (TextView) findViewById(R.id.invuse_wonum_text_id);
        statusText = (TextView) findViewById(R.id.invuse_status_text_id);
        statusdateText = (TextView) findViewById(R.id.invuse_statusdate_text_id);
        siteidText = (TextView) findViewById(R.id.invuse_siteid_text_id);


        worlflowBtn=(Button)findViewById(R.id.invuse_workflow_btn_id);

        materialBtn=(Button)findViewById(R.id.invuse_material_btn_id);

        if (invuse != null) {
            invusenumText.setText(invuse.invusenum==null?"暂无数据":invuse.invusenum);
            descriptionText.setText(invuse.description==null?"暂无数据":invuse.description);
            fromstorelocText.setText(invuse.fromstoreloc==null?"暂无数据":invuse.fromstoreloc);
            udissuetoText.setText(invuse.udissueto==null?"暂无数据":invuse.udissueto);
            wonumText.setText(invuse.wonum==null?"暂无数据":invuse.wonum);
            statusText.setText(invuse.status==null?"暂无数据":invuse.status);
            statusdateText.setText(invuse.statusdate==null?"暂无数据":invuse.statusdate);
            siteidText.setText(invuse.siteid==null?"暂无数据":invuse.siteid);
        }
    }

    @Override
    protected void initView() {
        backImageView.setOnClickListener(backImageViewOnClickListener);
        titleTextView.setText(getString(R.string.invuse_details_title));
        materialBtn.setOnClickListener(materialBtnOnClickListener);

    }

    private View.OnClickListener backImageViewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };


    private View.OnClickListener materialBtnOnClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent=new Intent();
            intent.setClass(InvuseDetailsActivity.this,InvuselineActivity.class);
            intent.putExtra("invusenum", invuse.invusenum);
            startActivityForResult(intent,0);

        }
    };

}
