package cdhxqh.shekou.ui.activity;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

import cdhxqh.shekou.R;
import cdhxqh.shekou.config.Constants;

/**
 * Created by think on 2015/10/20.
 */
public class Activity_MainHome extends BaseActivity {

    public static final String TAG = "Activity_MainHome";
    private ListView mLvLeftMenu;
    private DrawerLayout mDrawerLayout;
    private TextView username;
    private TextView password_change;
    private FragmentTransaction fragmentTransaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainhome);

        findViewById();
        initView();
    }

    @Override
    protected void findViewById() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.id_drawer_layout);
        mLvLeftMenu = (ListView) findViewById(R.id.left_menu);
    }

    @Override
    protected void initView() {
        setUpDrawer();
    }

    private void setUpDrawer() {
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.header_userinfo, mLvLeftMenu, false);
        username = (TextView) view.findViewById(R.id.username);
        username.setText(Constants.USERNAME);
        password_change = (TextView) view.findViewById(R.id.password_change);
        password_change.setText(new SimpleDateFormat("yyyy.MM.dd").format(new Date()));
        mLvLeftMenu.addHeaderView(view);
        mLvLeftMenu.setAdapter(new MenuItemAdapter(this));
    }
}
