package cdhxqh.shekou.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;

import com.umeng.analytics.MobclickAgent;

/**
 * Created by yw on 2015/10/21
 */
public class BaseFragment extends Fragment  {

    protected boolean mIsLogin;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart(this.toString());
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd(this.toString());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


}
