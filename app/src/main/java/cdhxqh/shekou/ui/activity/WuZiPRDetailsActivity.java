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
import cdhxqh.shekou.model.PR;
import cdhxqh.shekou.utils.AccountUtils;
import cdhxqh.shekou.utils.MessageUtils;
import cdhxqh.shekou.webserviceclient.AndroidClientService;

/**
 * 物资采购申请
 */
public class WuZiPRDetailsActivity extends BaseActivity {
    private static String TAG = "WuZiPRDetailsActivity";

    /**
     * 返回按钮
     */
    private ImageView backImageView;
    /**
     * 标题
     */
    private TextView titleTextView;

    private ImageView menuImageView; //菜单

    private LinearLayout qglinelayout; //请购明细行
    private LinearLayout jhfplayout; //计划分配
    private LinearLayout splayout; //审批

    private PopupWindow popupWindow;


    /**界面信息**/
    private TextView prnumText; //申请单号
    private TextView udfincpText;//财务公司
    private TextView descriptionText;//申购描述
    private TextView requestedbyText;//申请人
    private TextView sqrnameText;//申请人姓名
    private TextView sqbmText;//申请部门
    private TextView sqglsText;//申请管理室
    private TextView requireddateText;//要求到货日期
    private TextView issuedateText;//申请日期
    private TextView pretaxtotalText;//金额总计
    private TextView statusText;//状态
    private TextView statusdateText;//状态日期
    private TextView zdrText;//制单人
    private TextView udcreatedateText;//制单日期
    private TextView zdbmText;//制单部门


    private String ownerid; //ownerid
    private String ownertable; //ownertable
    private String processname; //processname


    private ProgressDialog mProgressDialog;

    private PR pr;


    private BaseAnimatorSet mBasIn;
    private BaseAnimatorSet mBasOut;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wuzipr_details);
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

        prnumText = (TextView) findViewById(R.id.prnum_text_id);
        udfincpText = (TextView) findViewById(R.id.udfincp_text_id);
        descriptionText = (TextView) findViewById(R.id.description_text_id);
        requestedbyText = (TextView) findViewById(R.id.invuse_displayname_text_id);
        sqrnameText = (TextView) findViewById(R.id.sqrname_text_id);
        sqbmText = (TextView) findViewById(R.id.sqbm_text_id);
        sqglsText = (TextView) findViewById(R.id.sqgls_text_id);
        requireddateText = (TextView) findViewById(R.id.requireddate_text_id);
        issuedateText = (TextView) findViewById(R.id.createdate_text_id);
        pretaxtotalText = (TextView) findViewById(R.id.pretaxtotal_text_id);
        statusText = (TextView) findViewById(R.id.status_text_id);
        statusdateText = (TextView) findViewById(R.id.statusdate_id);
        zdrText = (TextView) findViewById(R.id.jbr_text_id);
        udcreatedateText = (TextView) findViewById(R.id.udcreatedate_text_id);
        zdbmText = (TextView) findViewById(R.id.zdbm_text_id);

        mBasIn = new BounceTopEnter();
        mBasOut = new SlideBottomExit();
    }

    @Override
    protected void initView() {
        backImageView.setOnClickListener(backImageViewOnClickListener);
        titleTextView.setText(R.string.wzcgsq_text);
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
        mProgressDialog = ProgressDialog.show(WuZiPRDetailsActivity.this, null,
                getString(R.string.data_load_ing), true, true);

        HttpManager.getDataPagingInfo(WuZiPRDetailsActivity.this, HttpManager.getPr(ownerid), new HttpRequestHandler<Results>() {
            @Override
            public void onSuccess(Results results) {
                Log.i(TAG, "data=" + results);
            }

            @Override
            public void onSuccess(Results results, int totalPages, int currentPage) {
                mProgressDialog.dismiss();
                ArrayList<PR> item = JsonUtils.parsingPr(WuZiPRDetailsActivity.this, results.getResultlist());
               if(item!=null||item.size()!=0) {
                   pr = item.get(0);
                   if(pr!=null){
                        showData(pr);
                   }

               }

            }

            @Override
            public void onFailure(String error) {
                mProgressDialog.dismiss();
                MessageUtils.showMiddleToast(WuZiPRDetailsActivity.this,"数据加载失败");
            }
        });


    }

    //显示信息
    private void showData(PR pr) {
        prnumText.setText(pr.getPRNUM());
        udfincpText.setText(pr.getUDFINCP());
        descriptionText.setText(pr.getDESCRIPTION());
        requestedbyText.setText(pr.getREQUESTEDBY());
        sqrnameText.setText(pr.getSQRNAME());
        sqbmText.setText(pr.getSQBM());
        sqglsText.setText(pr.getSQGLS());
        requireddateText.setText(pr.getREQUIREDDATE());
        issuedateText.setText(pr.getISSUEDATE());
        pretaxtotalText.setText(pr.getPRETAXTOTAL());
        statusText.setText(pr.getSTATUS());
        statusdateText.setText(pr.getSTATUSDATE());
        zdrText.setText(pr.getZDR());
        udcreatedateText.setText(pr.getUDCREATEDATE());
        zdbmText.setText(pr.getZDBM());


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

        View contentView = LayoutInflater.from(WuZiPRDetailsActivity.this).inflate(
                R.layout.wuzipr_popup_window, null);


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

        qglinelayout = (LinearLayout) contentView.findViewById(R.id.prline_linearlayout_id);
        jhfplayout = (LinearLayout) contentView.findViewById(R.id.jhfp_linearlayout_id);

        splayout = (LinearLayout) contentView.findViewById(R.id.sp_linearlayout_id);

        qglinelayout.setOnClickListener(qglinelayoutOnClickListener);
        jhfplayout.setOnClickListener(jhfplayoutOnClickListener);
        splayout.setOnClickListener(spOnClickListener);

    }


    private View.OnClickListener qglinelayoutOnClickListener =new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent =new Intent(WuZiPRDetailsActivity.this,PrlineActivity.class);
            intent.putExtra("prnum",pr.getPRNUM());
            intent.putExtra("mark",0);
            startActivityForResult(intent,0);
            popupWindow.dismiss();
        }
    };
    private View.OnClickListener jhfplayoutOnClickListener =new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent =new Intent(WuZiPRDetailsActivity.this,PrlineActivity.class);
            intent.putExtra("prnum",pr.getPRNUM());
            intent.putExtra("mark",1);
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
        final NormalEditTextDialog dialog = new NormalEditTextDialog(WuZiPRDetailsActivity.this);
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
        mProgressDialog = ProgressDialog.show(WuZiPRDetailsActivity.this, null,
                getString(R.string.inputing), true, true);
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.setCancelable(false);
        new AsyncTask<String, String, String>() {
            @Override
            protected String doInBackground(String... strings) {
                String result = AndroidClientService.approve(WuZiPRDetailsActivity.this, processname, ownertable, id, pr.getPRID(), AccountUtils.getpersonId(WuZiPRDetailsActivity.this), zx, desc);

                Log.i(TAG, "result=" + result);
                return result;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                if (s == null || s.equals("")) {
                    MessageUtils.showMiddleToast(WuZiPRDetailsActivity.this, "审批失败");
                } else {
                    MessageUtils.showMiddleToast(WuZiPRDetailsActivity.this, "审批成功");

                }
                mProgressDialog.dismiss();
                finish();
            }
        }.execute();
    }

}
