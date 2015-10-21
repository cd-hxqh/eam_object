package cdhxqh.shekou.ui.activity;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.widget.ListView;

import cdhxqh.shekou.R;

/**
 * Created by think on 2015/10/20.
 */
public class MainActivity extends BaseActivity {

    public static final String TAG = "Activity_MainHome";
    private ListView mLvLeftMenu;
    private DrawerLayout mDrawerLayout;
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
//        LayoutInflater inflater = LayoutInflater.from(this);
//        View view = inflater.inflate(R.layout.header_username, mLvLeftMenu, false);
//        nameview = (TextView) view.findViewById(R.id.id_link);
//        nameview.setText(Constants.UserName);
//        dateview = (TextView) view.findViewById(R.id.id_date);
//        dateview.setText(new SimpleDateFormat("yyyy.MM.dd").format(new Date()));
//        mLvLeftMenu.addHeaderView(view);
//        mLvLeftMenu.setAdapter(new MenuItemAdapter(this));
    }
}
