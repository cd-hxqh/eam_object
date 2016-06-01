package cdhxqh.shekou.ui.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.telephony.TelephonyManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import cdhxqh.shekou.application.BaseApplication;
import cdhxqh.shekou.config.Constants;
import cdhxqh.shekou.manager.AppManager;


public abstract class BaseActivity extends ActionBarActivity {

    public static final String TAG = BaseActivity.class.getSimpleName();

    protected InputMethodManager imm;
    private TelephonyManager tManager;

    private long exitTime = 0;

    protected SharedPreferences myshared;
    protected BaseApplication baseApplication;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppManager.getInstance().addActivity(this);

        tManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);

        myshared = this.getSharedPreferences(Constants.USER_INFO, Context.MODE_PRIVATE);
        baseApplication = (BaseApplication) getApplication();
    }

    /**
     * 绑定控件id
     */
    protected abstract void findViewById();

    /**
     * 初始化控件
     */
    protected abstract void initView();

    /**
     *
     */
    protected BaseApplication getBaseApplication(){
        return baseApplication;
    }

    /**
     * 通过类名启动Activity
     *
     * @param pClass
     */
    protected void openActivity(Class<?> pClass) {
        openActivity(pClass, null);
    }

    /**
     * 通过类名启动Activity，并且含有Bundle数据
     *
     * @param pClass
     * @param pBundle
     */
    protected void openActivity(Class<?> pClass, Bundle pBundle) {
        Intent intent = new Intent(this, pClass);
        if (pBundle != null) {
            intent.putExtras(pBundle);
        }
        startActivity(intent);
    }

    /**
     * 通过Action启动Activity
     *
     * @param pAction
     */
    protected void openActivity(String pAction) {
        openActivity(pAction, null);
    }

    /**
     * 通过Action启动Activity，并且含有Bundle数据
     *
     * @param pAction
     * @param pBundle
     */
    protected void openActivity(String pAction, Bundle pBundle) {
        Intent intent = new Intent(pAction);
        if (pBundle != null) {
            intent.putExtras(pBundle);
        }
        startActivity(intent);
    }


    /**
     * 加载进度条
     */
    public void showProgressDialog(String message) {

        if (progressDialog != null) {
            progressDialog.cancel();
        }
        progressDialog = new ProgressDialog(this);
//        Drawable drawable=getResources().getDrawable(R.drawable.loading_animation);
//        progressDialog.setIndeterminateDrawable(drawable);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(true);
        progressDialog.setMessage(message);
        progressDialog.show();
    }

    /**关闭进度条**/
    public void closeProgressDialog(){
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    public void DisplayToast(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

    protected void hideOrShowSoftInput(boolean isShowSoft, EditText editText) {
        if (isShowSoft) {
            imm.showSoftInput(editText, 0);
        } else {
            imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
        }
    }

    //获得当前程序版本信息
    protected String getVersionName() throws Exception {
        // 获取packagemanager的实例
        PackageManager packageManager = getPackageManager();
        // getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo = packageManager.getPackageInfo(getPackageName(), 0);
        return packInfo.versionName;
    }


    //獲得設備信息
    protected String getDeviceId() throws Exception {
        String deviceId = tManager.getDeviceId();

        return deviceId;

    }


    /**
     * 退出登陆*
     */
    public void exit(Context context) {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            AppManager.AppExit(context);
        }
    }

}
