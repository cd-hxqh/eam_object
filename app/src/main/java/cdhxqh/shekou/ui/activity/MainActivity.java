package cdhxqh.shekou.ui.activity;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import cdhxqh.shekou.R;
import cdhxqh.shekou.manager.AppManager;
import cdhxqh.shekou.ui.fragment.InventoryFragment;
import cdhxqh.shekou.ui.fragment.InvuseFragment;
import cdhxqh.shekou.ui.fragment.NavigationDrawerFragment;
import cdhxqh.shekou.ui.fragment.Setting_Fragment;
import cdhxqh.shekou.ui.fragment.WfassigFragment;
import cdhxqh.shekou.ui.fragment.WorkFragment;


public class MainActivity extends BaseActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    private static final String TAG = "MainActivity";
    //    private SpinnerAdapter mSpinnerAdapter;
    private NavigationDrawerFragment mNavigationDrawerFragment;
    private ViewGroup mDrawerLayout;
    private View mActionbarCustom;

    /**
     * 待办事项*
     */
    private WfassigFragment mNewWfassigFragment;
    /**
     * 工单管理*
     */
    private WorkFragment mNewWorkFragment;
    /**
     * 库存查询*
     */
    private InventoryFragment mNewInventoryFragment;
    /**
     * 领料管理*
     */
    private InvuseFragment mNewInvuseFragment;
    /**
     *
     */
    private Setting_Fragment settingFragment;
    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    private String[] mFavoriteTabTitles;
    private String[] mFavoriteTabPaths;
    private String[] mMainTitles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById();


    }

    @Override
    protected void findViewById() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDrawerLayout = (ViewGroup) findViewById(R.id.drawer_layout);
        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.left_drawer);
        mTitle = getTitle();

        mMainTitles = getResources().getStringArray(R.array.drawer_tab_titles);


        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.left_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    @Override
    protected void initView() {

    }

    int mSelectPos = 0;

    @Override
    public void onNavigationDrawerItemSelected(final int position) {
        mSelectPos = position;

        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        switch (position) {
            case 0: //待办任务
                if (mNewWfassigFragment == null) {
                    mNewWfassigFragment = new WfassigFragment();
                    Bundle bundle = new Bundle();
                    mNewWfassigFragment.setArguments(bundle);
                }
                fragmentTransaction.replace(R.id.container, mNewWfassigFragment).commit();
                break;
            case 1: //工单管理
                if (mNewWorkFragment == null) {
                    mNewWorkFragment = new WorkFragment();
                    Bundle bundle = new Bundle();
                    mNewWorkFragment.setArguments(bundle);
                }
                fragmentTransaction.replace(R.id.container, mNewWorkFragment).commit();
                break;
            case 2://库存查询
                if (mNewInventoryFragment == null) {
                    mNewInventoryFragment = new InventoryFragment();
                    Bundle bundle = new Bundle();
                    mNewInventoryFragment.setArguments(bundle);
                }
                fragmentTransaction.replace(R.id.container, mNewInventoryFragment).commit();
                break;
            case 3://领料管理
                if (mNewInvuseFragment == null) {
                    mNewInvuseFragment = new InvuseFragment();
                    Bundle bundle = new Bundle();
                    mNewInvuseFragment.setArguments(bundle);
                }
                fragmentTransaction.replace(R.id.container, mNewInvuseFragment).commit();

                break;
            case 4://设置
                if (settingFragment == null) {
                    settingFragment = new Setting_Fragment();
                    Bundle bundle = new Bundle();
                    settingFragment.setArguments(bundle);
                }
                fragmentTransaction.replace(R.id.container, settingFragment).commit();

                break;
        }

    }


    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        mTitle = mMainTitles[mSelectPos];
        actionBar.setDisplayShowCustomEnabled(false);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            if (mSelectPos == 3) {
                getMenuInflater().inflate(R.menu.menu_main, menu);
            }
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (mSelectPos == 3&&item.getItemId() == R.id.action_add) {
            Intent intent=new Intent();
            intent.setClass(MainActivity.this,AddinvuseActivity.class);
            startActivityForResult(intent,0);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }




    private long exitTime = 0;

    @Override
    public void onBackPressed() {
        if (mNavigationDrawerFragment.isDrawerOpen()) {
            mNavigationDrawerFragment.closeDrawer();
            return;
        }

        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(this, getResources().getString(R.string.exit_text), Toast.LENGTH_LONG).show();
            exitTime = System.currentTimeMillis();
        } else {
            AppManager.AppExit(MainActivity.this);
        }
    }

}
