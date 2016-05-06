package cdhxqh.shekou.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cdhxqh.shekou.R;
import cdhxqh.shekou.model.WorkOrder;
import cdhxqh.shekou.ui.fragment.LabtransFragment;
import cdhxqh.shekou.ui.fragment.MatusetransFragment;
import cdhxqh.shekou.ui.fragment.WoactivityFragment;
import cdhxqh.shekou.ui.fragment.WpitemFragment;

/**
 * Created by think on 2015/11/9.
 * 实际情况页面
 */
public class Work_RealinfoActivity extends BaseActivity {

    private TextView titlename;
    private ImageView menuImageView;
    private RelativeLayout backlayout;

    private LinearLayout woactivitylayout;
    private LinearLayout wplaborlayout;
    private LinearLayout wpitemlayout;
    private ImageView mTabLineIv;
    private int currentIndex = 0;
    private int screenWidth;
    private ImageView mImageView;
    private ViewPager mViewPager;    //下方的可横向拖动的控件
    private List<Fragment> fragmentlist = new ArrayList<Fragment>();
    private WoactivityFragment woactivityFragment;
    private LabtransFragment labtransFragment;
    private MatusetransFragment matusetransFragment;

    public WorkOrder workOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_realinfo);

        geiIntentData();
        findViewById();
        initView();
        initTabLineWidth();
    }

    private void geiIntentData() {
        workOrder = getIntent().getParcelableExtra("workOrder");
    }

    @Override
    protected void findViewById() {
        titlename = (TextView) findViewById(R.id.title_name);
        menuImageView = (ImageView) findViewById(R.id.title_add);
        backlayout = (RelativeLayout) findViewById(R.id.title_back);
        woactivitylayout = (LinearLayout) findViewById(R.id.work_report_woactivity);
        wplaborlayout = (LinearLayout) findViewById(R.id.work_report_wplabor);
        wpitemlayout = (LinearLayout) findViewById(R.id.work_report_wpitem);
        mTabLineIv = (ImageView) findViewById(R.id.id_tab_line_iv);
        mViewPager = (ViewPager) findViewById(R.id.pager);
    }

    @Override
    protected void initView() {
        mViewPager.setCurrentItem(currentIndex);
        mViewPager.setOffscreenPageLimit(4);
        titlename.setText(getResources().getString(R.string.work_realinfo));
        backlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        menuImageView.setImageResource(R.drawable.add);
        menuImageView.setVisibility(View.VISIBLE);
        woactivitylayout.setOnClickListener(new layoutlistener(0));
        wplaborlayout.setOnClickListener(new layoutlistener(1));
        wpitemlayout.setOnClickListener(new layoutlistener(2));
        fragmentlist = new ArrayList<Fragment>();
        labtransFragment = new LabtransFragment(workOrder);
        woactivityFragment = new WoactivityFragment(workOrder);
        matusetransFragment = new MatusetransFragment(workOrder);
        fragmentlist.add(woactivityFragment);
        fragmentlist.add(labtransFragment);
        fragmentlist.add(matusetransFragment);
        mViewPager.setAdapter(new MyFrageStatePagerAdapter(getSupportFragmentManager()));//设置ViewPager的适配器
        mViewPager.setOnPageChangeListener(new MyPagerOnPageChangeListener());
    }

    private class layoutlistener implements View.OnClickListener {
        int i;

        private layoutlistener(int i) {
            this.i = i;
        }

        @Override
        public void onClick(View view) {
            mViewPager.setCurrentItem(i);
        }
    }

    /**
     * ViewPager的适配器
     */
    class MyFrageStatePagerAdapter extends FragmentStatePagerAdapter {

        public MyFrageStatePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentlist.get(position);
        }

        @Override
        public int getCount() {
            return fragmentlist.size();
        }
    }

    /**
     * ViewPager的PageChangeListener(页面改变的监听器)
     */
    private class MyPagerOnPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }

        @Override
        public void onPageScrolled(int position, float offset, int offsetPixels) {
            LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mTabLineIv
                    .getLayoutParams();

            /**
             * 利用currentIndex(当前所在页面)和position(下一个页面)以及offset来
             * 设置mTabLineIv的左边距 滑动场景：
             * 记3个页面,
             * 从左到右分别为0,1,2
             * 0->1; 1->2; 2->1; 1->0
             */

            if (currentIndex == 0 && position == 0)// 0->1
            {
                lp.leftMargin = (int) (offset * (screenWidth * 1.0 / 3) + currentIndex
                        * (screenWidth / 3));

            } else if (currentIndex == 1 && position == 0) // 1->0
            {
                lp.leftMargin = (int) (-(1 - offset)
                        * (screenWidth * 1.0 / 3) + currentIndex
                        * (screenWidth / 3));

            } else if (currentIndex == 1 && position == 1) // 1->2
            {
                lp.leftMargin = (int) (offset * (screenWidth * 1.0 / 3) + currentIndex
                        * (screenWidth / 3));
            } else if (currentIndex == 2 && position == 1) // 2->1
            {
                lp.leftMargin = (int) (-(1 - offset)
                        * (screenWidth * 1.0 / 3) + currentIndex
                        * (screenWidth / 3));
            }
            mTabLineIv.setLayoutParams(lp);
        }

        /**
         * 滑动ViewPager的时候,让上方的HorizontalScrollView自动切换
         */
        @Override
        public void onPageSelected(int position) {
            switch (position) {
                case 0:
                    break;
                case 1:
                    break;
                case 2:
                    break;
            }
            currentIndex = position;
        }
    }

    /**
     * 设置滑动条的宽度为屏幕的1/3(根据Tab的个数而定)
     */
    private void initTabLineWidth() {
        DisplayMetrics dpMetrics = new DisplayMetrics();
        getWindow().getWindowManager().getDefaultDisplay()
                .getMetrics(dpMetrics);
        screenWidth = dpMetrics.widthPixels;
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mTabLineIv
                .getLayoutParams();
        lp.width = screenWidth / 3;
        mTabLineIv.setLayoutParams(lp);
    }

}
