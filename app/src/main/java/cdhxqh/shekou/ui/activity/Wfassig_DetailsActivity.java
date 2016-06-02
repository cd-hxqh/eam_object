package cdhxqh.shekou.ui.activity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.flyco.animation.BaseAnimatorSet;
import com.flyco.animation.BounceEnter.BounceTopEnter;
import com.flyco.animation.SlideExit.SlideBottomExit;
import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.listener.OnBtnEditClickL;
import com.flyco.dialog.widget.MaterialDialog;
import com.flyco.dialog.widget.NormalEditTextDialog;

import cdhxqh.shekou.R;
import cdhxqh.shekou.model.Wfassignment;
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
        assignstatusText = (TextView) findViewById(R.id.wfassig_assignstatus_text_id);
        startdateText = (TextView) findViewById(R.id.wfassig_startdate_text_id);

        if (wfassignment != null) {
            descriptionText.setText(wfassignment.description);
            processnameText.setText(wfassignment.processname);
            assigncodedescText.setText(wfassignment.assigncodedesc);
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
        final MaterialDialog dialog = new MaterialDialog(Wfassig_DetailsActivity.this);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.isTitleShow(false)//
                .btnNum(2)
                .content("是否填写输入意见")//
                .btnText("是", "否，直接提交")//
                .showAnim(mBasIn)//
                .dismissAnim(mBasOut)
                .show();

        dialog.setOnBtnClickL(
                new OnBtnClickL() {//是
                    @Override
                    public void onBtnClick() {
                        EditDialog(true);
                        dialog.dismiss();
                    }
                },
                new OnBtnClickL() {//否
                    @Override
                    public void onBtnClick() {
                        wfgoon(wfassignment.ownerid, "1", "");
                        dialog.dismiss();
                    }
                }
        );
    }


    private void EditDialog(final boolean isok) {//输入审核意见
        final NormalEditTextDialog dialog = new NormalEditTextDialog(Wfassig_DetailsActivity.this);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.isTitleShow(false)//
                .btnNum(2)
                .content(isok ? "通过" : "不通过")//
                .btnText("提交", "取消")//
                .showAnim(mBasIn)//
                .dismissAnim(mBasOut)
                .show();

        dialog.setOnBtnClickL(
                new OnBtnEditClickL() {
                    @Override
                    public void onBtnClick(String text) {
                        wfgoon(wfassignment.ownerid, "1", text);

                        dialog.dismiss();
                    }
                },
                new OnBtnEditClickL() {
                    @Override
                    public void onBtnClick(String text) {

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
                String result = AndroidClientService.approve(Wfassig_DetailsActivity.this, wfassignment.processname, wfassignment.ownertable, id, wfassignment.ownertable + "ID", zx, desc);

                Log.i(TAG, "result=" + result);
                return result;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                if (s == null || s.equals("")) {
                    Toast.makeText(Wfassig_DetailsActivity.this, "审批失败", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Wfassig_DetailsActivity.this, "审批成功", Toast.LENGTH_SHORT).show();
                }
                mProgressDialog.dismiss();
            }
        }.execute();
    }


}
