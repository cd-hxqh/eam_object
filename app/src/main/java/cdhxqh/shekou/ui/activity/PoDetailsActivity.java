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
import cdhxqh.shekou.model.Po;
import cdhxqh.shekou.utils.AccountUtils;
import cdhxqh.shekou.utils.MessageUtils;
import cdhxqh.shekou.webserviceclient.AndroidClientService;

/**
 * 非年度采购订单
 */
public class PoDetailsActivity extends BaseActivity {
    private static String TAG = "PoDetailsActivity";

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
    private LinearLayout splayout; //审批

    private PopupWindow popupWindow;


    /**界面信息**/
    private TextView ponumText; //采购订单
    private TextView desctionText;//描述
    private TextView udmsText;//订单描述
    private TextView cwgsText;//财务公司
    private TextView statusText;//状态
    private TextView statusdateText;//状态日期
    private TextView currencycodeText;//货币
    private TextView udhszjText;//含税总价
    private TextView udtaxText;//税率
    private TextView jygysText;//公司
    private TextView fyfsText;//发运方式
    private TextView jgjzText;//价格基准
    private TextView fkfsText;//付款方式
    private TextView jbrText;//制单人
    private TextView cbbmText;//呈报部门
    private TextView cbrqText;//呈报日期
    private TextView udremarksText;//备注


    private String ownerid; //ownerid
    private String ownertable; //ownertable
    private String processname; //processname


    private ProgressDialog mProgressDialog;

    private Po po;


    private BaseAnimatorSet mBasIn;
    private BaseAnimatorSet mBasOut;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_po_details);
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

        ponumText = (TextView) findViewById(R.id.ponum_text_id);
        desctionText = (TextView) findViewById(R.id.desction_text_id);
        udmsText = (TextView) findViewById(R.id.udms_text_id);
        cwgsText = (TextView) findViewById(R.id.cwgs_text_id);
        statusText = (TextView) findViewById(R.id.status_text_id);
        statusdateText = (TextView) findViewById(R.id.statusdate_text_id);
        currencycodeText = (TextView) findViewById(R.id.currencycode_text_id);
        udhszjText = (TextView) findViewById(R.id.udhszj_text_id);
        udtaxText = (TextView) findViewById(R.id.udtax_text_id);
        jygysText = (TextView) findViewById(R.id.jygys_text_id);
        fyfsText = (TextView) findViewById(R.id.fyfs_text_id);
        jgjzText = (TextView) findViewById(R.id.jgjz_text_id);
        fkfsText = (TextView) findViewById(R.id.fkfs_text_id);
        jbrText = (TextView) findViewById(R.id.jbr_text_id);
        cbbmText = (TextView) findViewById(R.id.cbbm_text_id);
        cbrqText = (TextView) findViewById(R.id.cbrq_text_id);
        udremarksText = (TextView) findViewById(R.id.udremarks_text_id);

        mBasIn = new BounceTopEnter();
        mBasOut = new SlideBottomExit();
    }

    @Override
    protected void initView() {
        backImageView.setOnClickListener(backImageViewOnClickListener);
        titleTextView.setText(R.string.fndcgdd_text);
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
        mProgressDialog = ProgressDialog.show(PoDetailsActivity.this, null,
                getString(R.string.data_load_ing), true, true);

        HttpManager.getDataPagingInfo(PoDetailsActivity.this, HttpManager.getPourl(ownerid), new HttpRequestHandler<Results>() {
            @Override
            public void onSuccess(Results results) {
                Log.i(TAG, "data=" + results);
            }

            @Override
            public void onSuccess(Results results, int totalPages, int currentPage) {
                mProgressDialog.dismiss();
                ArrayList<Po> item = JsonUtils.parsingPo(PoDetailsActivity.this, results.getResultlist());
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
                MessageUtils.showMiddleToast(PoDetailsActivity.this,"数据加载失败");
            }
        });


    }

    //显示信息
    private void showData(Po po) {
        ponumText.setText(po.getPONUM());
        desctionText.setText(po.getDESCRIPTION());
        udmsText.setText(po.getUDMS());
        cwgsText.setText(po.getCWGS());
        statusText.setText(po.getSTATUS());
        statusdateText.setText(po.getSTATUSDATE());
        currencycodeText.setText(po.getCURRENCYCODE());
        udhszjText.setText(po.getUDHSZJ());
        udtaxText.setText(po.getUDTAX());
        jygysText.setText(po.getJYGYS());
        fyfsText.setText(po.getFYFS());
        jgjzText.setText(po.getJGJZ());
        fkfsText.setText(po.getFKFS());
        jbrText.setText(po.getJBR());
        cbbmText.setText(po.getCBBM());
        cbrqText.setText(po.getCBRQ());
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

        View contentView = LayoutInflater.from(PoDetailsActivity.this).inflate(
                R.layout.po_popup_window, null);


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

        splayout = (LinearLayout) contentView.findViewById(R.id.sp_linearlayout_id);

        polayout.setOnClickListener(polayoutOnClickListener);
        splayout.setOnClickListener(spOnClickListener);

    }


    private View.OnClickListener polayoutOnClickListener =new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent =new Intent(PoDetailsActivity.this,PolineActivity.class);
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
        final NormalEditTextDialog dialog = new NormalEditTextDialog(PoDetailsActivity.this);
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
        mProgressDialog = ProgressDialog.show(PoDetailsActivity.this, null,
                getString(R.string.inputing), true, true);
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.setCancelable(false);
        new AsyncTask<String, String, String>() {
            @Override
            protected String doInBackground(String... strings) {
                String result = AndroidClientService.approve(PoDetailsActivity.this, processname, ownertable, id, po.getPOID(), AccountUtils.getpersonId(PoDetailsActivity.this), zx, desc);

                Log.i(TAG, "result=" + result);
                return result;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                if (s == null || s.equals("")) {
                    MessageUtils.showMiddleToast(PoDetailsActivity.this, "审批失败");
                } else {
                    MessageUtils.showMiddleToast(PoDetailsActivity.this, "审批成功");

                }
                mProgressDialog.dismiss();
                finish();
            }
        }.execute();
    }

}
