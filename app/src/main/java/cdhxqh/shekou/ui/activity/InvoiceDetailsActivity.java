package cdhxqh.shekou.ui.activity;

import android.app.ProgressDialog;
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
import cdhxqh.shekou.model.Invoice;
import cdhxqh.shekou.utils.AccountUtils;
import cdhxqh.shekou.utils.MessageUtils;
import cdhxqh.shekou.webserviceclient.AndroidClientService;

/**
 * 外协服务付款申请
 */
public class InvoiceDetailsActivity extends BaseActivity {
    private static String TAG = "InvoiceDetailsActivity";

    /**
     * 返回按钮
     */
    private ImageView backImageView;
    /**
     * 标题
     */
    private TextView titleTextView;

    private ImageView menuImageView; //菜单

    private LinearLayout invoicelinelayout; //付款申请行
    private LinearLayout splayout; //审批

    private PopupWindow popupWindow;


    /**界面信息**/
    private TextView invoicenumText; //付款单号
    private TextView udfincpText;//财务公司
    private TextView udjsfsText;//付款方式
    private TextView vendorinvoicenumText;//发票号
    private TextView documenttypeText;//类型
    private TextView udhszjText;//含税总价
    private TextView statusText;//状态
    private TextView enterbyText;//输入人
    private TextView udremarkText;//申请理由
    private TextView ponumText;//订单号
    private TextView receiptsText;//接收
    private TextView totalcostText;//成本总计
    private TextView pretaxtotalforuiText;//税前总计
    private TextView totaltax1foruiText;//税款总计
    private TextView currencycodeText;//货币
    private TextView invoicedateText;//发票日期
    private TextView duedateText;//到期日
    private TextView paiddateText;//付款日期
    private TextView vendorText;//供应商
    private TextView vendorNameText;//供应商名称
    private TextView contactText;//联系人
    private TextView phoneText;//电话
    private TextView paymenttermsText;//支付条款


    private String ownerid; //ownerid
    private String ownertable; //ownertable
    private String processname; //processname


    private ProgressDialog mProgressDialog;

    private Invoice invoice;


    private BaseAnimatorSet mBasIn;
    private BaseAnimatorSet mBasOut;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice_details);
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

        invoicenumText = (TextView) findViewById(R.id.invoicenum_text_id);
        udfincpText = (TextView) findViewById(R.id.udfincp_text_id);
        udjsfsText = (TextView) findViewById(R.id.udjsfs_text_id);
        vendorinvoicenumText = (TextView) findViewById(R.id.vendorinvoicenum_text_id);
        documenttypeText = (TextView) findViewById(R.id.documenttype_text_id);
        udhszjText = (TextView) findViewById(R.id.udhszj_text_id);
        statusText = (TextView) findViewById(R.id.status_text_id);
        enterbyText = (TextView) findViewById(R.id.enterby_text_id);
        udremarkText = (TextView) findViewById(R.id.udremark_text_id);
        ponumText = (TextView) findViewById(R.id.ponum_text_id);
        receiptsText = (TextView) findViewById(R.id.receipts_text_id);
        totalcostText = (TextView) findViewById(R.id.totalcost_text_id);
        pretaxtotalforuiText = (TextView) findViewById(R.id.pretaxtotalforui_text_id);
        totaltax1foruiText = (TextView) findViewById(R.id.totaltax1forui_text_id);
        currencycodeText = (TextView) findViewById(R.id.currencycode_text_id);
        invoicedateText = (TextView) findViewById(R.id.invoicedate_text_id);
        duedateText = (TextView) findViewById(R.id.duedate_text_id);
        paiddateText = (TextView) findViewById(R.id.paiddate_text_id);
        vendorText = (TextView) findViewById(R.id.vendor_text_id);
        vendorNameText = (TextView) findViewById(R.id.vendor_name_text_id);
        contactText = (TextView) findViewById(R.id.contact_text_id);
        phoneText = (TextView) findViewById(R.id.phone_text_id);
        paymenttermsText = (TextView) findViewById(R.id.paymentterms_text_id);

        mBasIn = new BounceTopEnter();
        mBasOut = new SlideBottomExit();
    }

    @Override
    protected void initView() {
        backImageView.setOnClickListener(backImageViewOnClickListener);
        titleTextView.setText(R.string.wxfufksq_text);
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
        mProgressDialog = ProgressDialog.show(InvoiceDetailsActivity.this, null,
                getString(R.string.data_load_ing), true, true);

        HttpManager.getDataPagingInfo(InvoiceDetailsActivity.this, HttpManager.getInvoiceurl(ownerid), new HttpRequestHandler<Results>() {
            @Override
            public void onSuccess(Results results) {
                Log.i(TAG, "data=" + results);
            }

            @Override
            public void onSuccess(Results results, int totalPages, int currentPage) {
                mProgressDialog.dismiss();
                ArrayList<Invoice> item = JsonUtils.parsingInvoice(InvoiceDetailsActivity.this, results.getResultlist());
               if(item!=null||item.size()!=0) {
                   invoice = item.get(0);
                   if(invoice!=null){
                        showData(invoice);
                   }

               }

            }

            @Override
            public void onFailure(String error) {
                mProgressDialog.dismiss();
                MessageUtils.showMiddleToast(InvoiceDetailsActivity.this,"数据加载失败");
            }
        });


    }

    //显示信息
    private void showData(Invoice invoice) {
        invoicenumText.setText(invoice.getINVOICENUM());
        udfincpText.setText(invoice.getUDFINCP());
        udjsfsText.setText(invoice.getUDJSFS());
        vendorinvoicenumText.setText(invoice.getVENDORINVOICENUM());
        documenttypeText.setText(invoice.getDOCUMENTTYPE());
        udhszjText.setText(invoice.getUDHSZJ());
        statusText.setText(invoice.getSTATUS());
        enterbyText.setText(invoice.getSRR());
        udremarkText.setText(invoice.getUDREMARK());
        ponumText.setText(invoice.getPONUM());
        receiptsText.setText(invoice.getJS());
        totalcostText.setText(invoice.getPOCOST());
        pretaxtotalforuiText.setText(invoice.getPRETAXTOTALFORUI());
        totaltax1foruiText.setText(invoice.getTOTALTAX1FORUI());
        currencycodeText.setText(invoice.getCURRENCYCODE());
        invoicedateText.setText(invoice.getINVOICEDATE());
        duedateText.setText(invoice.getDUEDATE());
        paiddateText.setText(invoice.getPAIDDATE());
        vendorText.setText(invoice.getVENDOR());
        vendorNameText.setText(invoice.getGYSMC());
        contactText.setText(invoice.getCONTACT());
        phoneText.setText(invoice.getPHONE());
        paymenttermsText.setText(invoice.getPAYMENTTERMS());

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

        View contentView = LayoutInflater.from(InvoiceDetailsActivity.this).inflate(
                R.layout.invoice_popup_window, null);


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

        invoicelinelayout = (LinearLayout) contentView.findViewById(R.id.invoiceline_linearlayout_id);

        splayout = (LinearLayout) contentView.findViewById(R.id.sp_linearlayout_id);

        splayout.setOnClickListener(spOnClickListener);

    }


    private View.OnClickListener spOnClickListener =new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            MaterialDialogOneBtn1();
            popupWindow.dismiss();
        }
    };


    private void MaterialDialogOneBtn1() {//审批工作流
        final NormalEditTextDialog dialog = new NormalEditTextDialog(InvoiceDetailsActivity.this);
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
        mProgressDialog = ProgressDialog.show(InvoiceDetailsActivity.this, null,
                getString(R.string.inputing), true, true);
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.setCancelable(false);
        new AsyncTask<String, String, String>() {
            @Override
            protected String doInBackground(String... strings) {
                String result = AndroidClientService.approve(InvoiceDetailsActivity.this, processname, ownertable, id, invoice.getINVOICEID(), AccountUtils.getpersonId(InvoiceDetailsActivity.this), zx, desc);

                Log.i(TAG, "result=" + result);
                return result;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                if (s == null || s.equals("")) {
                    MessageUtils.showMiddleToast(InvoiceDetailsActivity.this, "审批失败");
                } else {
                    MessageUtils.showMiddleToast(InvoiceDetailsActivity.this, "审批成功");

                }
                mProgressDialog.dismiss();
                finish();
            }
        }.execute();
    }

}
