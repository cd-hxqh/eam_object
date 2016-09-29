package cdhxqh.shekou.ui.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.flyco.animation.BaseAnimatorSet;
import com.flyco.animation.BounceEnter.BounceTopEnter;
import com.flyco.animation.SlideExit.SlideBottomExit;
import com.flyco.dialog.listener.OnBtnEditClickL;
import com.flyco.dialog.widget.NormalEditTextDialog;

import java.util.ArrayList;

import cdhxqh.shekou.R;
import cdhxqh.shekou.api.HttpManager;
import cdhxqh.shekou.api.HttpRequestHandler;
import cdhxqh.shekou.api.JsonUtils;
import cdhxqh.shekou.bean.Results;
import cdhxqh.shekou.model.WaiXiePo;
import cdhxqh.shekou.utils.AccountUtils;
import cdhxqh.shekou.utils.MessageUtils;
import cdhxqh.shekou.webserviceclient.AndroidClientService;

/**
 * 外协服务采购订单
 */
public class WaiXiePoDetailsActivity extends BaseActivity {
    private static String TAG = "WaiXiePoDetailsActivity";

    /**
     * 返回按钮
     */
    private ImageView backImageView;
    /**
     * 标题
     */
    private TextView titleTextView;

    private ImageView menuImageView; //菜单

    private LinearLayout polayout; //采购明细行
    private LinearLayout rfqvendorlayout; //询价单
    private LinearLayout servrectranslayout; //服务接收验收
    private LinearLayout splayout; //审批

    private PopupWindow popupWindow;


    /**界面信息**/
    private TextView udassetnumText; //设备编码
    private TextView adescriptionText;//设备描述
    private TextView ponumText;//订单编号
    private TextView descriptionText;//订单描述
    private TextView vendorText;//供应商
    private TextView vendornameText;//供应商名称
    private TextView contractrefnumText;//合同编号
    private TextView cdescriptionText;//合同描述
    private TextView udhszjText;//含税总价
    private TextView statusText;//状态
    private TextView statusdateText;//状态日期
    private TextView jbrText;//经办人
    private TextView udcreatedateText;//制单日期
    private TextView pzrnameText;//批准人
    private TextView udappdateText;//批准日期
    private TextView udremarksText;//备注


    private String ownerid; //ownerid
    private String ownertable; //ownertable
    private String processname; //processname


    private ProgressDialog mProgressDialog;

    private WaiXiePo po;


    private BaseAnimatorSet mBasIn;
    private BaseAnimatorSet mBasOut;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waixiepo_details);
        geiIntentData();
        findViewById();
        initView();
    }

    private void geiIntentData() {
        ownerid=getIntent().getStringExtra("ownerid");
        ownertable=getIntent().getStringExtra("ownertable");
        processname=getIntent().getStringExtra("processname");
    }

    @Override
    protected void findViewById() {
        backImageView = (ImageView) findViewById(R.id.title_back_id);
        titleTextView = (TextView) findViewById(R.id.title_name);
        menuImageView = (ImageView) findViewById(R.id.title_add);

        udassetnumText = (TextView) findViewById(R.id.assetnum_text_id);
        adescriptionText = (TextView) findViewById(R.id.adescription_text_id);
        ponumText = (TextView) findViewById(R.id.ponum_text_id);
        descriptionText = (TextView) findViewById(R.id.udms_text_id);
        vendorText = (TextView) findViewById(R.id.vendor_text_id);
        vendornameText = (TextView) findViewById(R.id.vendor_name_text_id);
        contractrefnumText = (TextView) findViewById(R.id.contractrefnum_text_id);
        cdescriptionText = (TextView) findViewById(R.id.cdescription_text_id);
        udhszjText = (TextView) findViewById(R.id.udhszj_text_id);
        statusText = (TextView) findViewById(R.id.status_text_id);
        statusdateText = (TextView) findViewById(R.id.invuse_statusdate_text_id);
        jbrText = (TextView) findViewById(R.id.jbr_text_id);
        udcreatedateText = (TextView) findViewById(R.id.udcreatedate_text_id);
        pzrnameText = (TextView) findViewById(R.id.pz_displayname_text_id);
        udappdateText = (TextView) findViewById(R.id.changedate_text_id);
        udremarksText = (TextView) findViewById(R.id.udremarks_text_id);

        mBasIn = new BounceTopEnter();
        mBasOut = new SlideBottomExit();
    }

    @Override
    protected void initView() {
        backImageView.setOnClickListener(backImageViewOnClickListener);
        titleTextView.setText(R.string.wxfwcgdd_text);
        menuImageView.setImageResource(R.drawable.ic_drawer);
        menuImageView.setVisibility(View.VISIBLE);
        menuImageView.setOnClickListener(menuImageViewOnClickListener);


        getData(ownerid);

    }



    private View.OnClickListener backImageViewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };


    //获取数据方法
    private void getData(String ownerid) {
        mProgressDialog = ProgressDialog.show(WaiXiePoDetailsActivity.this, null,
                getString(R.string.data_load_ing), true, true);

        HttpManager.getDataPagingInfo(WaiXiePoDetailsActivity.this, HttpManager.getWaiXiePourl(ownerid), new HttpRequestHandler<Results>() {
            @Override
            public void onSuccess(Results results) {
                Log.i(TAG, "data=" + results);
            }

            @Override
            public void onSuccess(Results results, int totalPages, int currentPage) {
                mProgressDialog.dismiss();
                ArrayList<WaiXiePo> item = JsonUtils.parsingWaiXiePo(WaiXiePoDetailsActivity.this, results.getResultlist());
               if(item!=null||item.size()!=0) {
                   po = item.get(0);
                   if(po!=null){
                        showData(po);
                   }

               }

            }

            @Override
            public void onFailure(String error) {
                mProgressDialog.dismiss();
                MessageUtils.showMiddleToast(WaiXiePoDetailsActivity.this,"数据加载失败");
            }
        });


    }

    //显示信息
    private void showData(WaiXiePo po) {
        udassetnumText.setText(po.getUDASSETNUM());
        adescriptionText.setText(po.getADESCRIPTION());
        ponumText.setText(po.getPONUM());
        descriptionText.setText(po.getDESCRIPTION());
        vendorText.setText(po.getVENDOR());
        vendornameText.setText(po.getVENDORNAME());
        contractrefnumText.setText(po.getCONTRACTREFNUM());
        cdescriptionText.setText(po.getCDESCRIPTION());
        udhszjText.setText(po.getUDHSZJ());
        statusText.setText(po.getSTATUS());
        statusdateText.setText(po.getSTATUSDATE());
        jbrText.setText(po.getJBR());
        udcreatedateText.setText(po.getUDCREATEDATE());
        pzrnameText.setText(po.getPZRNAME());
        udappdateText.setText(po.getUDAPPDATE());
        udremarksText.setText(po.getUDREMARKS());

    }



    private View.OnClickListener menuImageViewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            showPopupWindow(menuImageView);
        }
    };


    /**
     * 初始化showPopupWindow*
     */
    private void showPopupWindow(View view) {

        View contentView = LayoutInflater.from(WaiXiePoDetailsActivity.this).inflate(
                R.layout.waixiepo_popup_window, null);


        popupWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setTouchable(true);
        popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setTouchInterceptor(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {


                return false;
            }
        });

        popupWindow.setBackgroundDrawable(getResources().getDrawable(
                R.drawable.popup_background_mtrl_mult));

        popupWindow.showAsDropDown(view);

        polayout = (LinearLayout) contentView.findViewById(R.id.poline_linearlayout_id);
        servrectranslayout = (LinearLayout) contentView.findViewById(R.id.servrectrans_linearlayout_id);

        splayout = (LinearLayout) contentView.findViewById(R.id.sp_linearlayout_id);

        polayout.setOnClickListener(polayoutOnClickListener);
        servrectranslayout.setOnClickListener(servrectranslayoutOnClickListener);
        splayout.setOnClickListener(spOnClickListener);

    }


    private View.OnClickListener polayoutOnClickListener =new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent =new Intent(WaiXiePoDetailsActivity.this,PolineActivity.class);
            intent.putExtra("ponum",po.getPONUM());
            intent.putExtra("mark",1);
            startActivityForResult(intent,0);
            popupWindow.dismiss();
        }
    };
    private View.OnClickListener servrectranslayoutOnClickListener =new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent =new Intent(WaiXiePoDetailsActivity.this,ServrectransActivity.class);
            intent.putExtra("ponum",po.getPONUM());
            startActivityForResult(intent,0);
            popupWindow.dismiss();
        }
    };
    private View.OnClickListener spOnClickListener =new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            MaterialDialogOneBtn1();
            popupWindow.dismiss();
        }
    };


    private void MaterialDialogOneBtn1() {//审批工作流
        final NormalEditTextDialog dialog = new NormalEditTextDialog(WaiXiePoDetailsActivity.this);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialog.isTitleShow(true);//
        dialog.title("审批工作流");
        dialog.btnNum(3)
                .content("通过")//
                .btnText("取消", "通过", "不通过")//
                .showAnim(mBasIn)//
                .dismissAnim(mBasOut)
                .show();

        dialog.setOnBtnClickL(
                new OnBtnEditClickL() {//取消
                    @Override
                    public void onBtnClick(String text) {

                        dialog.dismiss();
                    }
                },
                new OnBtnEditClickL() {//通过
                    @Override
                    public void onBtnClick(String text) {
                        wfgoon(ownerid, "1", text);
                        dialog.dismiss();
                    }
                },
                new OnBtnEditClickL() {//不通过
                    @Override
                    public void onBtnClick(String text) {
                        wfgoon(ownerid, "0", text);
                        dialog.dismiss();
                    }
                }
        );
    }


    /**
     * 审批工作流
     *
     * @param id
     * @param zx
     */
    private void wfgoon(final String id, final String zx, final String desc) {
        mProgressDialog = ProgressDialog.show(WaiXiePoDetailsActivity.this, null,
                getString(R.string.inputing), true, true);
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.setCancelable(false);
        new AsyncTask<String, String, String>() {
            @Override
            protected String doInBackground(String... strings) {
                String result = AndroidClientService.approve(WaiXiePoDetailsActivity.this, processname, ownertable, id, po.getPOID(), AccountUtils.getpersonId(WaiXiePoDetailsActivity.this), zx, desc);

                Log.i(TAG, "result=" + result);
                return result;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                if (s == null || s.equals("")) {
                    MessageUtils.showMiddleToast(WaiXiePoDetailsActivity.this, "审批失败");
                } else {
                    MessageUtils.showMiddleToast(WaiXiePoDetailsActivity.this, "审批成功");

                }
                mProgressDialog.dismiss();
                finish();
            }
        }.execute();
    }

}
