package cdhxqh.shekou.ui.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import cdhxqh.shekou.R;
import cdhxqh.shekou.api.HttpManager;
import cdhxqh.shekou.api.HttpRequestHandler;
import cdhxqh.shekou.manager.AppManager;
import cdhxqh.shekou.utils.AccountUtils;
import cdhxqh.shekou.utils.MessageUtils;


/**
 * 登录界面
 */
public class LoginActivity extends BaseActivity  implements View.OnClickListener{


    private static final String TAG = "Activity_Login";
    private EditText mUsername;
    private EditText mPassword;
    private Button mLogin;
    private ProgressDialog mProgressDialog;
    //    private MemberModel mProfile;
    private CheckBox checkBox; //记住密码

    private boolean isRemember; //是否记住密码


    String userName; //用户名

    String userPassWorld; //密码

    String imei; //imei

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        imei = ((TelephonyManager) getSystemService(TELEPHONY_SERVICE))
                .getDeviceId();

        findViewById();
        initView();
    }
    @Override
    protected void findViewById() {
        mUsername = (EditText) findViewById(R.id.user_login_id);
        mPassword = (EditText) findViewById(R.id.user_login_password);
        checkBox = (CheckBox) findViewById(R.id.isremenber_password);
        mLogin = (Button) findViewById(R.id.user_login);
    }

    @Override
    protected void initView() {

        boolean isChecked = AccountUtils.getIsChecked(LoginActivity.this);
        if (isChecked) {
            mUsername.setText(AccountUtils.getUserName(LoginActivity.this));
            mPassword.setText(AccountUtils.getUserPassword(LoginActivity.this));
        }
        checkBox.setOnCheckedChangeListener(cheBoxOnCheckedChangListener);
        mLogin.setOnClickListener(this);
    }

    private CompoundButton.OnCheckedChangeListener cheBoxOnCheckedChangListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            isRemember = isChecked;
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.user_login:
                if (mUsername.getText().length() == 0) {
                    mUsername.setError(getString(R.string.login_error_empty_user));
                    mUsername.requestFocus();
                } else if (mPassword.getText().length() == 0) {
                    mPassword.setError(getString(R.string.login_error_empty_passwd));
                    mPassword.requestFocus();
                } else {
                    login();
                }
                break;

        }
    }



    /**
     * 登陆*
     */
    private void login() {
        mProgressDialog = ProgressDialog.show(LoginActivity.this, null,
                getString(R.string.login_loging), true, true);

        HttpManager.loginWithUsername(LoginActivity.this,
                mUsername.getText().toString(),
                mPassword.getText().toString(), imei,
                new HttpRequestHandler<String>() {
                    @Override
                    public void onSuccess(String data) {

                        MessageUtils.showMiddleToast(LoginActivity.this, data);
                        mProgressDialog.dismiss();
                        if (isRemember) {
                            AccountUtils.setChecked(LoginActivity.this, isRemember);
                            //记住密码
                            AccountUtils.setUserNameAndPassWord(LoginActivity.this, mUsername.getText().toString(), mPassword.getText().toString());
                        }
                        startIntent();

                    }

                    @Override
                    public void onSuccess(String data, int totalPages, int currentPage) {
                        MessageUtils.showMiddleToast(LoginActivity.this, getString(R.string.login_successful_hint));

                        startIntent();
                    }

                    @Override
                    public void onFailure(String error) {
                        MessageUtils.showErrorMessage(LoginActivity.this, error);
                        mProgressDialog.dismiss();
                    }
                });
    }


    private void startIntent() {
        Intent inetnt = new Intent();
        inetnt.setClass(this, MainActivity.class);
        startActivity(inetnt);
    }


    private long exitTime = 0;

    @Override
    public void onBackPressed() {


        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(this, getString(R.string.exit_text), Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            AppManager.AppExit(LoginActivity.this);
        }
    }



}
