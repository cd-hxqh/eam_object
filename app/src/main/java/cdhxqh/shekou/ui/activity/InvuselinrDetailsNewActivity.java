package cdhxqh.shekou.ui.activity;

import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cdhxqh.shekou.R;


public class InvuselinrDetailsNewActivity extends BaseActivity {

    private static final String TAG = "InvuselinrDetailsNewActivity";

    /**
     * 标题*
     */
    private TextView titleTextView;
    /**
     * 返回按钮
     */
    private ImageView backImageView;
    /**
     * ViewPager*
     */
    private ViewPager viewPager;
    /**
     * TextView*
     */
    private TextView hxText, slText, gdText, qtText;

    private List<View> views;// Tab页面列表
    private int offset = 0;// 动画图片偏移量
    private int currIndex = 0;// 当前页卡编号
    private int bmpW;// 动画图片宽度
    private View view1, view2, view3, view4;//各个页卡

    private ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invuseline_new_details);
        findViewById();
        initView();
        InitImageView();
        InitTextView();
        InitViewPager();

    }

    @Override
    protected void findViewById() {
        backImageView = (ImageView) findViewById(R.id.title_back_id);
        titleTextView = (TextView) findViewById(R.id.title_name);

        hxText = (TextView) findViewById(R.id.hx_text);
        slText = (TextView) findViewById(R.id.sl_text);
        gdText = (TextView) findViewById(R.id.gd_text);
        qtText = (TextView) findViewById(R.id.qt_text);


        viewPager = (ViewPager) this.findViewById(R.id.pager);
    }

    @Override
    protected void initView() {
        backImageView.setOnClickListener(backImageViewOnClickListener);
        titleTextView.setText(getString(R.string.invuseline_details_title));

    }


    private View.OnClickListener backImageViewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };

    private void InitViewPager() {
        views = new ArrayList<View>();
        LayoutInflater inflater = getLayoutInflater();
        view1 = inflater.inflate(R.layout.activity_sl_details, null);
        view2 = inflater.inflate(R.layout.tab_top2, null);
        view3 = inflater.inflate(R.layout.tab_top2, null);
        view4 = inflater.inflate(R.layout.tab_top2, null);
        views.add(view1);
        views.add(view2);
        views.add(view3);
        views.add(view4);
        viewPager.setAdapter(new MyViewPagerAdapter(views));
        viewPager.setCurrentItem(0);
        setBackground(0);
        viewPager.setOnPageChangeListener(new MyOnPageChangeListener());
    }

    /**
     * 初始化头标
     */

    private void InitTextView() {


        hxText.setOnClickListener(new MyOnClickListener(0));
        slText.setOnClickListener(new MyOnClickListener(1));
        gdText.setOnClickListener(new MyOnClickListener(2));
        qtText.setOnClickListener(new MyOnClickListener(3));
    }

    /**
     * 2      * 初始化动画，这个就是页卡滑动时，下面的横线也滑动的效果，在这里需要计算一些数据
     * 3
     */

    private void InitImageView() {
        imageView = (ImageView) findViewById(R.id.cursor);
        bmpW = BitmapFactory.decodeResource(getResources(), R.drawable.line).getWidth();// 获取图片宽度
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenW = dm.widthPixels;// 获取分辨率宽度
        offset = (screenW / 3 - bmpW) / 2;// 计算偏移量
        Matrix matrix = new Matrix();
        matrix.postTranslate(offset, 0);
        imageView.setImageMatrix(matrix);// 设置动画初始位置

    }

    private class MyOnClickListener implements View.OnClickListener {
        private int index = 0;

        public MyOnClickListener(int i) {
            index = i;
        }

        public void onClick(View v) {
            setBackground(index);
            viewPager.setCurrentItem(index);
        }

    }

    public class MyViewPagerAdapter extends PagerAdapter {
        private List<View> mListViews;

        public MyViewPagerAdapter(List<View> mListViews) {
            this.mListViews = mListViews;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(mListViews.get(position));
        }


        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mListViews.get(position), 0);
            return mListViews.get(position);
        }

        @Override
        public int getCount() {
            return mListViews.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }
    }

    public class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {

        int one = offset * 2 + bmpW;// 页卡1 -> 页卡2 偏移量
        int two = one * 2;// 页卡1 -> 页卡3 偏移量

        public void onPageScrollStateChanged(int arg0) {


        }

        public void onPageScrolled(int arg0, float arg1, int arg2) {


        }

        public void onPageSelected(int arg0) {
            setBackground(arg0);

        }

    }


    /**
     * 设置背景颜色*
     */
    private void setBackground(int index) {
        if (index == 0) {
            hxText.setBackgroundResource(R.color.nomal_button_color);
            slText.setBackgroundResource(R.color.light_gray);
            gdText.setBackgroundResource(R.color.light_gray);
            qtText.setBackgroundResource(R.color.light_gray);
        } else if (index == 1) {
            hxText.setBackgroundResource(R.color.light_gray);
            slText.setBackgroundResource(R.color.nomal_button_color);
            gdText.setBackgroundResource(R.color.light_gray);
            qtText.setBackgroundResource(R.color.light_gray);
        } else if (index == 2) {
            hxText.setBackgroundResource(R.color.light_gray);
            slText.setBackgroundResource(R.color.light_gray);
            gdText.setBackgroundResource(R.color.nomal_button_color);
            qtText.setBackgroundResource(R.color.light_gray);
        } else if (index == 3) {
            hxText.setBackgroundResource(R.color.light_gray);
            slText.setBackgroundResource(R.color.light_gray);
            gdText.setBackgroundResource(R.color.light_gray);
            qtText.setBackgroundResource(R.color.nomal_button_color);
        }
    }
}