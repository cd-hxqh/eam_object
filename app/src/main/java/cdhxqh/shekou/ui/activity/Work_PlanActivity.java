package cdhxqh.shekou.ui.activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cdhxqh.shekou.R;
import cdhxqh.shekou.model.WorkOrder;
import cdhxqh.shekou.ui.fragment.WpitemFragment;
import cdhxqh.shekou.ui.fragment.WoactivityFragment;
import cdhxqh.shekou.ui.fragment.WplaborFragment;

/**
 * Created by think on 2015/11/2.
 * 工单计划页面
 */
public class Work_PlanActivity extends BaseActivity {

    private TextView titlename;
    private ImageView menuImageView;
    private RelativeLayout backlayout;
    private Button task;
    private Button worker;
    private Button meterial;
    private ViewPager mViewPager;
    private int currentIndex = 0;
    private List<Fragment> fragmentlist = new ArrayList<>();
    private WoactivityFragment woactivityFragment;
    private WplaborFragment wplaborFragment;
    private WpitemFragment wpitemFragment;

    public WorkOrder workOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workplan);

        geiIntentData();
        findViewById();
        initView();
    }

    private void geiIntentData() {
        workOrder = (WorkOrder) getIntent().getSerializableExtra("workOrder");
    }

    @Override
    protected void findViewById() {
        titlename = (TextView) findViewById(R.id.title_name);
        menuImageView = (ImageView) findViewById(R.id.title_add);
        backlayout = (RelativeLayout) findViewById(R.id.title_back);
        task = (Button) findViewById(R.id.work_plan_task);
        worker = (Button) findViewById(R.id.work_plan_worker);
        meterial = (Button) findViewById(R.id.work_plan_meterial);
        mViewPager = (ViewPager) findViewById(R.id.pager);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void initView() {
        mViewPager.setCurrentItem(currentIndex);
        mViewPager.setOffscreenPageLimit(2);
        titlename.setText(getResources().getString(R.string.work_plan));
        backlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        menuImageView.setImageResource(R.drawable.add);
        menuImageView.setVisibility(View.VISIBLE);
        menuImageView.setOnClickListener(menuImageViewOnClickListener);
        task.setBackground(getResources().getDrawable(R.drawable.button_true_shape));
        task.setOnClickListener(new Buttonlistener());
        worker.setOnClickListener(new Buttonlistener());
        meterial.setOnClickListener(new Buttonlistener());
        fragmentlist = new ArrayList<>();
        woactivityFragment = new WoactivityFragment(workOrder);
        wplaborFragment = new WplaborFragment(workOrder);
        wpitemFragment = new WpitemFragment(workOrder);
        fragmentlist.add(woactivityFragment);
        fragmentlist.add(wplaborFragment);
        fragmentlist.add(wpitemFragment);
        mViewPager.setAdapter(new MyFrageStatePagerAdapter(getSupportFragmentManager()));//设置ViewPager的适配器
        mViewPager.setOnPageChangeListener(new MyPagerOnPageChangeListener());
        task.performClick();
    }

    private View.OnClickListener menuImageViewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent;
            if (currentIndex == 0) {

            } else if (currentIndex == 1) {
                intent = new Intent(Work_PlanActivity.this,AddWplaborActivity.class);
                startActivity(intent);
            } else if (currentIndex == 2) {

            }
        }
    };

    public class Buttonlistener implements View.OnClickListener {
        public Buttonlistener() {

        }

        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public void onClick(View view) {
            resetTextView();
            if (view.getId() == task.getId()) {
                view.setBackground(getResources().getDrawable(R.drawable.button_true_shape));
                task.setTextColor(getResources().getColor(R.color.white));
                currentIndex = 0;
            } else if (view.getId() == worker.getId()) {
                view.setBackground(getResources().getDrawable(R.drawable.button_true_shape));
                worker.setTextColor(getResources().getColor(R.color.white));
                currentIndex = 1;
            } else if (view.getId() == meterial.getId()) {
                view.setBackground(getResources().getDrawable(R.drawable.button_true_shape));
                meterial.setTextColor(getResources().getColor(R.color.white));
                currentIndex = 2;
            }
            mViewPager.setCurrentItem(currentIndex);
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

        }

        /**
         * 滑动ViewPager的时候
         */
        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public void onPageSelected(int position) {
            resetTextView();
            switch (position) {
                case 0:
                    task.performClick();
                    break;
                case 1:
                    worker.performClick();
                    break;
                case 2:
                    meterial.performClick();
                    break;
            }
            currentIndex = position;
        }
    }

    /**
     * 重置颜色
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void resetTextView() {
        task.setBackground(getResources().getDrawable(R.drawable.button_false_shape));
        task.setTextColor(getResources().getColor(R.color.black));
        worker.setBackground(getResources().getDrawable(R.drawable.button_false_shape));
        worker.setTextColor(getResources().getColor(R.color.black));
        meterial.setBackground(getResources().getDrawable(R.drawable.button_false_shape));
        meterial.setTextColor(getResources().getColor(R.color.black));
    }
}
