package cdhxqh.shekou.ui.activity;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import cdhxqh.shekou.R;
import cdhxqh.shekou.manager.AppManager;
import cdhxqh.shekou.ui.fragment.NavigationDrawerFragment;
import cdhxqh.shekou.ui.fragment.WorkFragment;


public class MainActivity extends BaseActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {
    private static final String TAG = "MainActivity";
    private NavigationDrawerFragment mNavigationDrawerFragment;
    private DrawerLayout mDrawerLayout;
    private View mActionbarCustom;

    private WorkFragment mWorkFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    private String[] mMainTitles;

    private ActionBar actionBar; //ActionBar


    int mSelectPos = 0;
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

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.left_drawer);
        mTitle = getTitle();
        mMainTitles = getResources().getStringArray(R.array.drawer_tab_titles);

        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_drawer);
        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.left_drawer,
                mDrawerLayout);
    }

    @Override
    protected void initView() {

    }



    @Override
    public void onNavigationDrawerItemSelected(final int position) {
        mSelectPos = position;

        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        switch (position) {
            case 0:
                Log.i(TAG,"2123");
                if (mWorkFragment == null) {
                    mWorkFragment = new WorkFragment();
                    Bundle bundle = new Bundle();
                    mWorkFragment.setArguments(bundle);
                }
                fragmentTransaction.replace(R.id.container, mWorkFragment).commit();
                break;
            case 1:
                Log.i(TAG,"工单管理");
                if (mWorkFragment == null) {
                    mWorkFragment = new WorkFragment();
                    Bundle bundle = new Bundle();
                    mWorkFragment.setArguments(bundle);
                }
                fragmentTransaction.replace(R.id.container, mWorkFragment).commit();
                break;
            case 2:
                Log.i(TAG,"库存查询");
                if (mWorkFragment == null) {
                    mWorkFragment = new WorkFragment();
                    Bundle bundle = new Bundle();
                    mWorkFragment.setArguments(bundle);
                }
                fragmentTransaction.replace(R.id.container, mWorkFragment).commit();

                break;
            case 3:
                if (mWorkFragment == null) {
                    mWorkFragment = new WorkFragment();
                    Bundle bundle = new Bundle();
                    mWorkFragment.setArguments(bundle);
                }
                fragmentTransaction.replace(R.id.container, mWorkFragment).commit();
                break;
            case 4:
                if (mWorkFragment == null) {
                    mWorkFragment = new WorkFragment();
                    Bundle bundle = new Bundle();
                    mWorkFragment.setArguments(bundle);
                }
                fragmentTransaction.replace(R.id.container, mWorkFragment).commit();
                break;
        }
    }

    /**
     * 设置标题*
     */
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
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        return super.onContextItemSelected(item);
    }

    private long exitTime = 0;

    @Override
    public void onBackPressed() {
        if (mNavigationDrawerFragment.isDrawerOpen()) {
            mNavigationDrawerFragment.closeDrawer();
            return;
        }

        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(this, getString(R.string.exit_text), Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            AppManager.AppExit(MainActivity.this);
        }
    }


}
