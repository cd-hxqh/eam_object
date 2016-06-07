package cdhxqh.shekou.ui.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.flyco.animation.BaseAnimatorSet;
import com.flyco.animation.BounceEnter.BounceTopEnter;
import com.flyco.animation.SlideExit.SlideBottomExit;
import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.widget.MaterialDialog;

import cdhxqh.shekou.R;
import cdhxqh.shekou.model.Invuse;
import cdhxqh.shekou.model.Matusetrans;
import cdhxqh.shekou.webserviceclient.AndroidClientService;

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
     * 领料单号
     */
    private TextView invusenumText;
    /**
     * 描述
     */
    private TextView descriptionText;

    /**
     * 库房
     */
    private TextView fromstorelocText;
    /**
     * 库房名称*
     */
    private TextView loc_descriptionText;
    /**
     * 工单号*
     */
    private TextView wonumText;
    /**
     * 工单描述*
     */
    private TextView workorder_descriptionText;
    /**
     * 领料人(中文名称)
     */
    private TextView llr_displaynameText;
    /**
     * 部门(中文名称)*
     */
    private TextView uddept_descriptionText;
    /**
     * 物管员经办人*
     */
    private TextView udjbr_displaynameText;
    /**
     * 工单设备*
     */
    private TextView wonum_asset_assetnumText;
    /**
     * 设备管理组*
     */
    private TextView eq1Text;
    /**
     * 设备管理室*
     */
    private TextView eq2Text;
    /**
     * 设备管理班组*
     */
    private TextView eq3Text;
    /**
     * 是否紧急*
     */
    private CheckBox udisjjText;

    /**
     * 状态
     */
    private TextView statusText;
    /**
     * 地点
     */
    private TextView siteidText;
    /**
     * 总价
     */
    private TextView totalcost_vText;

    /**
     * 申请人*
     */
    private TextView sq_displaynameText;


    /**
     * 申请日期*
     */
    private TextView createdateText;
    /**
     * 批准人*
     */
    private TextView pz_displaynameText;
    /**
     * 批准日期*
     */
    private TextView changedateText;


    /**
     * 审批工作流
     */
    private Button worlflowBtn;

    /**
     * 物料清单*
     */

    private Button materialBtn;

    /**
     * matusetrans*
     */
    private Invuse invuse;

    private ProgressDialog mProgressDialog;
    private BaseAnimatorSet mBasIn;
    private BaseAnimatorSet mBasOut;


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
        loc_descriptionText = (TextView) findViewById(R.id.location_name_text_id);
        wonumText = (TextView) findViewById(R.id.invuse_wonum_text_id);
        workorder_descriptionText = (TextView) findViewById(R.id.workorder_description_text_id);
        llr_displaynameText = (TextView) findViewById(R.id.invuse_udissueto_text_id);
        uddept_descriptionText = (TextView) findViewById(R.id.uddept_text_id);
        udjbr_displaynameText = (TextView) findViewById(R.id.udjbr_text_id);
        wonum_asset_assetnumText = (TextView) findViewById(R.id.wonum_asset_assetnum_text_id);
        eq1Text = (TextView) findViewById(R.id.eq1_text_id);
        eq2Text = (TextView) findViewById(R.id.eq2_text_id);
        eq3Text = (TextView) findViewById(R.id.eq3_text_id);
        udisjjText = (CheckBox) findViewById(R.id.udisjj_text_id);
        statusText = (TextView) findViewById(R.id.invuse_status_text_id);
        siteidText = (TextView) findViewById(R.id.siteid_text_id);
        totalcost_vText = (TextView) findViewById(R.id.totalcost_v_text_id);
        sq_displaynameText = (TextView) findViewById(R.id.sq_displayname_text_id);
        createdateText = (TextView) findViewById(R.id.createdate_text_id);
        pz_displaynameText = (TextView) findViewById(R.id.pz_displayname_text_id);
        changedateText = (TextView) findViewById(R.id.changedate_text_id);


        worlflowBtn = (Button) findViewById(R.id.approval_button_id);

        materialBtn = (Button) findViewById(R.id.invuseline_button_id);

        if (invuse != null) {
            invusenumText.setText(invuse.invusenum == null ? "暂无数据" : invuse.invusenum);
            descriptionText.setText(invuse.description == null ? "暂无数据" : invuse.description);
            fromstorelocText.setText(invuse.fromstoreloc == null ? "暂无数据" : invuse.fromstoreloc);
            loc_descriptionText.setText(invuse.loc_description == null ? "暂无数据" : invuse.loc_description);
            wonumText.setText(invuse.wonum == null ? "暂无数据" : invuse.wonum);
            workorder_descriptionText.setText(invuse.workorder_description == null ? "暂无数据" : invuse.workorder_description);
            llr_displaynameText.setText(invuse.llr_displayname == null ? "暂无数据" : invuse.llr_displayname);
            uddept_descriptionText.setText(invuse.uddept_description == null ? "暂无数据" : invuse.uddept_description);
            udjbr_displaynameText.setText(invuse.udjbr_displayname == null ? "暂无数据" : invuse.udjbr_displayname);
            wonum_asset_assetnumText.setText(invuse.wonum_asset_assetnum == null ? "暂无数据" : invuse.wonum_asset_assetnum);
            eq1Text.setText(invuse.eq1 == null ? "暂无数据" : invuse.eq1);
            eq2Text.setText(invuse.eq2 == null ? "暂无数据" : invuse.eq2);
            eq3Text.setText(invuse.eq3 == null ? "暂无数据" : invuse.eq3);
            if (invuse.udisjj.equals("0")) {
                udisjjText.setChecked(false);
            } else {
                udisjjText.setChecked(true);
            }
            statusText.setText(invuse.status == null ? "暂无数据" : invuse.status);
            siteidText.setText(invuse.siteid == null ? "暂无数据" : invuse.siteid);
            totalcost_vText.setText(invuse.totalcost_v == null ? "暂无数据" : invuse.totalcost_v);
            sq_displaynameText.setText(invuse.sq_displayname == null ? "暂无数据" : invuse.sq_displayname);
            createdateText.setText(invuse.createdate == null ? "暂无数据" : invuse.createdate);
            pz_displaynameText.setText(invuse.pz_displayname == null ? "暂无数据" : invuse.pz_displayname);
            changedateText.setText(invuse.changedate == null ? "暂无数据" : invuse.changedate);


        }
    }

    @Override
    protected void initView() {
        backImageView.setOnClickListener(backImageViewOnClickListener);
        titleTextView.setText(getString(R.string.invuse_details_title));
        materialBtn.setOnClickListener(materialBtnOnClickListener);
        worlflowBtn.setOnClickListener(worlflowBtnBtnOnClickListener);

        mBasIn = new BounceTopEnter();
        mBasOut = new SlideBottomExit();

    }

    private View.OnClickListener backImageViewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };

    /**
     * 备件明细*
     */
    private View.OnClickListener materialBtnOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(InvuseDetailsActivity.this, InvuselineActivity.class);
            intent.putExtra("invusenum", invuse.invusenum);
            startActivityForResult(intent, 0);

        }
    };

    /**
     * 启动工作流*
     */

    private View.OnClickListener worlflowBtnBtnOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            MaterialDialogOneBtn1();

        }
    };

    private void MaterialDialogOneBtn1() {//启动工作流
        final MaterialDialog dialog = new MaterialDialog(InvuseDetailsActivity.this);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.isTitleShow(false)//
                .btnNum(2)
                .content("是否启动工作流")//
                .btnText("是", "否")//
                .showAnim(mBasIn)//
                .dismissAnim(mBasOut)
                .show();

        dialog.setOnBtnClickL(
                new OnBtnClickL() {//是
                    @Override
                    public void onBtnClick() {
                        startWork(invuse.invuseid);
                        dialog.dismiss();
                    }
                },
                new OnBtnClickL() {//否
                    @Override
                    public void onBtnClick() {
                        dialog.dismiss();
                    }
                }
        );
    }


    /**
     * 启动工作流工作流
     *
     * @param id
     */
    private void startWork(final String id) {
        Log.i(TAG, "id=" + id);
        mProgressDialog = ProgressDialog.show(InvuseDetailsActivity.this, null,
                getString(R.string.inputing), true, true);
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.setCancelable(false);
        new AsyncTask<String, String, String>() {
            @Override
            protected String doInBackground(String... strings) {
                String result = AndroidClientService.startwf(InvuseDetailsActivity.this, "UDINVUSE", "INVUSE", id, "INVUSEID");

                Log.i(TAG, "result=" + result);
                return result;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                if (s == null || s.equals("")) {
                    Toast.makeText(InvuseDetailsActivity.this, "失败", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(InvuseDetailsActivity.this, "成功", Toast.LENGTH_SHORT).show();
                }
                mProgressDialog.dismiss();
            }
        }.execute();
    }


}
