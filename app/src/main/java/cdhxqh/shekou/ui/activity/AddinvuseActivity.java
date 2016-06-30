package cdhxqh.shekou.ui.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import cdhxqh.shekou.R;
import cdhxqh.shekou.api.JsonUtils;
import cdhxqh.shekou.bean.InvuseResult;
import cdhxqh.shekou.config.Constants;
import cdhxqh.shekou.model.Failurereport;
import cdhxqh.shekou.model.Invuse;
import cdhxqh.shekou.model.Invuseline;
import cdhxqh.shekou.model.Labtrans;
import cdhxqh.shekou.model.Option;
import cdhxqh.shekou.model.Woactivity;
import cdhxqh.shekou.model.WorkResult;
import cdhxqh.shekou.utils.AccountUtils;
import cdhxqh.shekou.utils.DateTimePickDialogUtil;
import cdhxqh.shekou.utils.GetDateAndTime;
import cdhxqh.shekou.utils.MessageUtils;
import cdhxqh.shekou.webserviceclient.AndroidClientService;

/**
 * 新增领料单*
 */

public class AddinvuseActivity extends BaseActivity {
    private static final String TAG = "AddinvuseActivity";

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
     * 描述*
     */
    private TextView descriptionText;
    /**
     * 库房*
     */
    private TextView fromstorelocText;

    /**
     * 工单*
     */

    private LinearLayout wonumLinearLayout;
    private TextView wonumText;
    private View wonumView;
    /**
     * 领料人*
     */
    private TextView udissuetoText;
    /**
     * 物管员经办人*
     */
    private TextView udjbrText;
    /**
     * 是否紧急*
     */
    private CheckBox udisjjText;

    /**
     * 领料原因*
     */
    private LinearLayout udreasonLinearLayout;
    private EditText udreasonText;
    private View udreasonView;

    /**
     * 新建*
     */
    private Button addButton;

    /**
     * 备件明细*
     */
    private Button lineButton;


    /**
     * 位置*
     */
    private String fromstoreloc;
    /**
     * 领料人*
     */
    private String udissueto;
    /**
     * 物管员经办人*
     */
    private String udjbr;

    /**
     * 是否紧急*
     */
    private int udisjj;

    /**
     * 类型*
     */
    private String udapptype;


    ArrayList<Invuseline> list = new ArrayList<Invuseline>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addinvuse);
        initData();
        findViewById();
        initView();
    }

    private void initData() {
        udapptype = getIntent().getExtras().getString("udapptype");
    }

    @Override
    protected void findViewById() {
        backImageView = (ImageView) findViewById(R.id.title_back_id);
        titleTextView = (TextView) findViewById(R.id.title_name);

        descriptionText = (TextView) findViewById(R.id.invuse_description_text_id);
        fromstorelocText = (TextView) findViewById(R.id.invuse_location_text_id);

        wonumLinearLayout = (LinearLayout) findViewById(R.id.invuse_wonum_linearLayout);
        wonumText = (TextView) findViewById(R.id.invuse_wonum_text_id);
        wonumView = (View) findViewById(R.id.wonum_view_id);


        udissuetoText = (TextView) findViewById(R.id.invuse_udissueto_text_id);
        udjbrText = (TextView) findViewById(R.id.udjbr_text_id);
        udisjjText = (CheckBox) findViewById(R.id.udisjj_text_id);

        udreasonLinearLayout = (LinearLayout) findViewById(R.id.udreason_linearlayout_id);
        udreasonText = (EditText) findViewById(R.id.udreason_text_id);
        udreasonView = (View) findViewById(R.id.udreason_view_id);


        addButton = (Button) findViewById(R.id.add_button_id);
        lineButton = (Button) findViewById(R.id.invuseline_button_id);

    }

    @Override
    protected void initView() {
        backImageView.setOnClickListener(backImageViewOnClickListener);
        titleTextView.setText(getString(R.string.add_invuse_title));
        udissuetoText.setText(AccountUtils.getdisplayName(AddinvuseActivity.this));
        udissueto = AccountUtils.getpersonId(AddinvuseActivity.this);
        fromstorelocText.setOnClickListener(fromstorelocTextOnClickListener);
        wonumText.setOnClickListener(wonumTextOnClickListener);
        udissuetoText.setOnClickListener(udissuetoTextOnClickListener);
        udjbrText.setOnClickListener(udjbrTextOnClickListener);

        udisjjText.setOnCheckedChangeListener(udisjjTextOnCheckedChangeListener);

        addButton.setOnClickListener(addButtonOnClickListener);
        lineButton.setOnClickListener(lineButtonOnClickListener);

        if (udapptype.equals("USE")) {
            wonumLinearLayout.setVisibility(View.VISIBLE);
            wonumView.setVisibility(View.VISIBLE);
        } else if (udapptype.equals("UNWOUSE")) {
            udreasonLinearLayout.setVisibility(View.VISIBLE);
            udreasonView.setVisibility(View.VISIBLE);
        }


    }

    private View.OnClickListener backImageViewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };

    /**
     * 库房*
     */
    private View.OnClickListener fromstorelocTextOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(AddinvuseActivity.this, OptionActivity.class);
            intent.putExtra("requestCode", Constants.LOCATIONCODE);
            startActivityForResult(intent, Constants.LOCATIONCODE);
        }
    };
    /**
     * 工单
     */
    private View.OnClickListener wonumTextOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(AddinvuseActivity.this, Work_Choose_Activity.class);
            startActivityForResult(intent, Constants.WORKORDERCODE);
        }
    };
    /**
     * 领料人*
     */
    private View.OnClickListener udissuetoTextOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(AddinvuseActivity.this, OptionActivity.class);
            intent.putExtra("requestCode", Constants.PERSONCODE);
            startActivityForResult(intent, Constants.PERSONCODE);
        }
    };
    /**
     * 物管员经办人*
     */
    private View.OnClickListener udjbrTextOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(AddinvuseActivity.this, OptionActivity.class);
            intent.putExtra("requestCode", Constants.PERSONCODE1);
            startActivityForResult(intent, Constants.PERSONCODE1);
        }
    };

    /**
     * 是否紧急*
     */
    private CompoundButton.OnCheckedChangeListener udisjjTextOnCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            if (b) {
                udisjj = 1;
            } else {
                udisjj = 0;
            }
        }
    };


    /**
     * 新建*
     */
    private View.OnClickListener addButtonOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            showProgressDialog("数据提交中...");
            startAsyncTask();
        }
    };


    /**
     * 新建*
     */
    private View.OnClickListener lineButtonOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            if (isCheck()) {
                Intent intent = new Intent(AddinvuseActivity.this, AddInvuselineActivity.class);
                intent.putExtra("invusenum", "");
                startActivityForResult(intent, 0);
            }
        }
    };


    /**
     * 判断描述，库房，工单是否为空*
     */
    private boolean isCheck() {

        if (descriptionText.getText().toString().equals("")) {
            MessageUtils.showErrorMessage(AddinvuseActivity.this, "描述不能为空");
            return false;
        }
        if (fromstorelocText.getText().toString().equals("")) {
            MessageUtils.showErrorMessage(AddinvuseActivity.this, "库房不能为空");
            return false;
        }
        if (udapptype.equals("USE")) {
            if (wonumText.getText().toString().equals("")) {
                MessageUtils.showErrorMessage(AddinvuseActivity.this, "工单不能为空");
                return false;

            }
        }

        return true;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Option option;
        Log.i(TAG, "resultCode=" + resultCode);
        switch (resultCode) {
            case Constants.LOCATIONCODE:
                Log.i(TAG, "库房");
                option = (Option) data.getSerializableExtra("option");
                if (option != null) {
                    fromstoreloc = option.getName();
                    fromstorelocText.setText(option.getDescription());
                }
                break;
            case Constants.WORKORDERCODE:
                String wonum = data.getStringExtra("wonum");
                String description = data.getStringExtra("description");
                wonumText.setText(wonum);
                break;
            case Constants.PERSONCODE:
                option = (Option) data.getSerializableExtra("option");
                udissueto = option.getName();
                udissuetoText.setText(option.getDescription());
                break;
            case Constants.PERSONCODE1:
                option = (Option) data.getSerializableExtra("option");
                udjbr = option.getName();
                udjbrText.setText(option.getDescription());
                break;
            case 0:
                list = data.getParcelableArrayListExtra("invuselines");
                Log.i(TAG, "list=" + list);
                break;
            default:
                break;

        }


    }


    /**
     * 提交数据*
     */
    private void startAsyncTask() {

        final String updataInfo = JsonUtils.InvuseToJson(encapsulationInvuse(), list);
        Log.i(TAG, "updataInfo=" + updataInfo);
        new AsyncTask<String, String, InvuseResult>() {
            @Override
            protected InvuseResult doInBackground(String... strings) {
                InvuseResult addresult = AndroidClientService.InsertInvuse(updataInfo, AccountUtils.getpersonId(AddinvuseActivity.this), AccountUtils.getIpAddress(AddinvuseActivity.this) + Constants.INVUSE_URL);

                return addresult;
            }

            @Override
            protected void onPostExecute(InvuseResult invuseResult) {
                super.onPostExecute(invuseResult);


                if (invuseResult == null) {
                    Toast.makeText(AddinvuseActivity.this, "新增工单失败", Toast.LENGTH_SHORT).show();
                } else if (!invuseResult.errorMsg.equals("成功!")) {

                    Toast.makeText(AddinvuseActivity.this, invuseResult.errorMsg, Toast.LENGTH_SHORT).show();
                    finish();
                } else if (invuseResult.errorMsg.equals("成功!")) {
                    Toast.makeText(AddinvuseActivity.this, "工单" + invuseResult.invusenum + "新增成功", Toast.LENGTH_SHORT).show();

                }
                closeProgressDialog();
            }
        }.execute();
    }


    /**
     * 封装提交的对象*
     */
    private Invuse encapsulationInvuse() {

        String description = descriptionText.getText().toString(); //描述


        String wonum = wonumText.getText().toString(); //工单


        String udreason = udreasonText.getText().toString(); //领料原因


        Invuse invuse = new Invuse();
        invuse.invusenum = "";
        invuse.description = description;
        invuse.fromstoreloc = fromstoreloc;
        invuse.wonum = wonum;
        invuse.udissueto = udissueto;
        invuse.uddept = AccountUtils.getDepartment(AddinvuseActivity.this);
        invuse.udisjj = udisjj + "";
        invuse.udjbr = udjbr;
        invuse.status = "申请建立";
        invuse.udapptype = udapptype;
        invuse.udreason = udreason;
        invuse.statusdate = GetDateAndTime.GetDateTime();
        invuse.createdate = GetDateAndTime.GetDateTime();
        return invuse;
    }


    /**
     * 验证必填字段*
     */
    private void isMust() {
        String description = descriptionText.getText().toString(); //描述
        if (description.isEmpty()) {
            descriptionText.setError("描述信息必填");
        }
    }
}
