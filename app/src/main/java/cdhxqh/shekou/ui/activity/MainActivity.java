package cdhxqh.shekou.ui.activity;

import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import cdhxqh.shekou.R;
import cdhxqh.shekou.manager.AppManager;
import cdhxqh.shekou.ui.fragment.NavigationDrawerFragment;
import cdhxqh.shekou.ui.fragment.WorkFragment;


public class MainActivity extends BaseActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    private NavigationDrawerFragment mNavigationDrawerFragment;
    private DrawerLayout mDrawerLayout;
    private View mActionbarCustom;

    private WorkFragment mWorkFragment;

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
//        UmengUpdateAgent.setDefault();
//        UmengUpdateAgent.update(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.left_drawer);
        mTitle = getTitle();

        mMainTitles = getResources().getStringArray(R.array.drawer_tab_titles);


        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.left_drawer,
                mDrawerLayout);





    }

    @Override
    protected void findViewById() {

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
            case 0:
                if (mWorkFragment == null) {
                    mWorkFragment = new WorkFragment();
                    Bundle bundle = new Bundle();
                    mWorkFragment.setArguments(bundle);
                }
                fragmentTransaction.replace(R.id.container, mWorkFragment).commit();
                break;
            case 1:
                if (mWorkFragment == null) {
                    mWorkFragment = new WorkFragment();
                    Bundle bundle = new Bundle();
                    mWorkFragment.setArguments(bundle);
                }
                fragmentTransaction.replace(R.id.container, mWorkFragment).commit();
                break;
            case 2:
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
     * ÉèÖÃ±êÌâ*
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
