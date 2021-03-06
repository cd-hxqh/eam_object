package cdhxqh.shekou.ui.activity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.flyco.animation.BaseAnimatorSet;
import com.flyco.animation.BounceEnter.BounceTopEnter;
import com.flyco.animation.SlideExit.SlideBottomExit;
import com.flyco.dialog.listener.OnBtnEditClickL;
import com.flyco.dialog.widget.NormalEditTextDialog;

import cdhxqh.shekou.R;
import cdhxqh.shekou.model.Wfassignment;
import cdhxqh.shekou.utils.AccountUtils;
import cdhxqh.shekou.utils.MessageUtils;
import cdhxqh.shekou.webserviceclient.AndroidClientService;

/**
 * 待办任务详情
 */
public class Wfassig_DetailsActivity extends BaseActivity {
    private static String TAG = "Wfassig_DetailsActivity";


    private ImageView backImageView;

    private TextView titleTextView;


    private TextView descriptionText;

    private TextView processnameText;

    private TextView assigncodedescText;

    private TextView udassign02;

    private TextView assignstatusText;

    private TextView startdateText;

    /**
     * Wfassig*
     */
    private Wfassignment wfassignment;
    /**
     * 审批工作流*
     */
    private Button approvalBtn;

    private ProgressDialog mProgressDialog;

    private BaseAnimatorSet mBasIn;
    private BaseAnimatorSet mBasOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wfassig_details);
        geiIntentData();
        findViewById();
        initView();
    }


    private void geiIntentData() {
        wfassignment = (Wfassignment) getIntent().getParcelableExtra("wfassignment");
    }

    @Override
    protected void findViewById() {
        backImageView = (ImageView) findViewById(R.id.title_back_id);
        titleTextView = (TextView) findViewById(R.id.title_name);


        descriptionText = (TextView) findViewById(R.id.wfassig_desc_text_id);
        processnameText = (TextView) findViewById(R.id.wfassig_processname_text_id);
        assigncodedescText = (TextView) findViewById(R.id.wfassig_assigncodedesc_text_id);
        udassign02 = (TextView) findViewById(R.id.udassign02_text_id);
        assignstatusText = (TextView) findViewById(R.id.wfassig_assignstatus_text_id);
        startdateText = (TextView) findViewById(R.id.wfassig_startdate_text_id);

        if (wfassignment != null) {
            descriptionText.setText(wfassignment.description);
            processnameText.setText(wfassignment.udassign01);
            assigncodedescText.setText(wfassignment.assigncodedesc);
            udassign02.setText(wfassignment.udassign02);
            assignstatusText.setText(wfassignment.assignstatus);
            startdateText.setText(wfassignment.startdate);
        }


        approvalBtn = (Button) findViewById(R.id.approval_button_id);
        mBasIn = new BounceTopEnter();
        mBasOut = new SlideBottomExit();
    }

    @Override
    protected void initView() {
        backImageView.setOnClickListener(backImageViewOnClickListener);
        titleTextView.setText(getString(R.string.wfasssig_detail_text));
        approvalBtn.setOnClickListener(approvalBtnOnClickListener);
    }

    private View.OnClickListener backImageViewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };

    private View.OnClickListener approvalBtnOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            MaterialDialogOneBtn1();
        }
    };


    private void MaterialDialogOneBtn1() {//审批工作流
        final NormalEditTextDialog dialog = new NormalEditTextDialog(Wfassig_DetailsActivity.this);
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
                        wfgoon(wfassignment.ownerid, "1", text);
                        dialog.dismiss();
                    }
                },
                new OnBtnEditClickL() {//不通过
                    @Override
                    public void onBtnClick(String text) {
                        wfgoon(wfassignment.ownerid, "0", text);
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
        mProgressDialog = ProgressDialog.show(Wfassig_DetailsActivity.this, null,
                getString(R.string.inputing), true, true);
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.setCancelable(false);
        new AsyncTask<String, String, String>() {
            @Override
            protected String doInBackground(String... strings) {
                String result = AndroidClientService.approve(Wfassig_DetailsActivity.this, wfassignment.processname, wfassignment.ownertable, id, wfassignment.ownertable + "ID", AccountUtils.getpersonId(Wfassig_DetailsActivity.this), zx, desc);

                Log.i(TAG, "result=" + result);
                return result;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                if (s == null || s.equals("")) {
                    MessageUtils.showMiddleToast(Wfassig_DetailsActivity.this, "审批失败");
                } else {
                    MessageUtils.showMiddleToast(Wfassig_DetailsActivity.this, "审批成功");

                }
                mProgressDialog.dismiss();
                finish();
            }
        }.execute();
    }


}
