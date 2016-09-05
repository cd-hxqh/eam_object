package cdhxqh.shekou.ui.activity;

import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cdhxqh.shekou.R;
import cdhxqh.shekou.model.Invuseline;


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

    private Invuseline invuseline;
    /**行项**/
    /**
     * 备件*
     */
    private TextView itemnumText;
    /**
     * 备件名称*
     */
    private TextView bjNmaeText;
    /**
     * 部位*
     */
    private TextView level4Text;
    /**
     * 部件 名称*
     */
    private TextView level5Text;

    /**
     * 领料数量*
     */
    private TextView quantityText;
    /**
     * 货柜*
     */
    private TextView frombinText;
    /**
     * 货柜可用数量*
     */
    private TextView curbalText;
    /**
     * 库存总数量*
     */
    private TextView curbaltotalText;
    /**
     * 发放单位*
     */
    private TextView issueunitText;
    /**
     * 备注*
     */
    private TextView remarkText;


    /**数量和成本**/
    /**
     * 预算编号*
     */
    private TextView udbudctrlnumText;
    /**
     * 预算描述*
     */
    private TextView budctrlText;
    /**
     * 成本科目*
     */
    private TextView udglaccountText;
    /**
     * 科目描述*
     */
    private TextView chartofaccountsText;
    /**
     * 预算总额*
     */
    private TextView udlimitText;
    /**
     * 实际金额*
     */
    private TextView actualText;
    /**
     * 剩余金额*
     */
    private TextView remainderText;
    /**
     * 单位成本*
     */
    private TextView displayunitcostText;
    /**
     * 行成本*
     */
    private TextView displaylinecostText;

    /**工单**/
    /**
     * 工单号*
     */
    private TextView wonumText;
    /**
     * 任务号*
     */
    private TextView taskidText;
    /**
     * 设备*
     */
    private TextView assetnumText;

    /**其他**/
    /**
     * 行类型*
     */
    private TextView linetypeText;
    /**
     * 目标地点*
     */
    private TextView tositeidText;
    /**
     * 输入人*
     */
    private TextView enterbyText;
    /**
     * 领料人*
     */
    private TextView issuetoText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invuseline_new_details);
        initData();
        findViewById();
        initView();
        InitImageView();
        InitTextView();
        InitViewPager();

    }


    /**
     * 初始化界面数据*
     */
    private void initData() {
        invuseline = (Invuseline) getIntent().getParcelableExtra("invuseline");
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
        view2 = inflater.inflate(R.layout.activity_slcb_details, null);
        view3 = inflater.inflate(R.layout.activity_gd_details, null);
        view4 = inflater.inflate(R.layout.activity_qt_details, null);
        views.add(view1);
        views.add(view2);
        views.add(view3);
        views.add(view4);
        viewPager.setAdapter(new MyViewPagerAdapter(views));
        viewPager.setCurrentItem(0);
        inithx(view1);
        initslcb(view2);
        initgd(view3);
        initqt(view4);
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
            showhx();
        } else if (index == 1) {
            hxText.setBackgroundResource(R.color.light_gray);
            slText.setBackgroundResource(R.color.nomal_button_color);
            gdText.setBackgroundResource(R.color.light_gray);
            qtText.setBackgroundResource(R.color.light_gray);
            showslcb();
        } else if (index == 2) {
            hxText.setBackgroundResource(R.color.light_gray);
            slText.setBackgroundResource(R.color.light_gray);
            gdText.setBackgroundResource(R.color.nomal_button_color);
            qtText.setBackgroundResource(R.color.light_gray);
            showgd();
        } else if (index == 3) {
            hxText.setBackgroundResource(R.color.light_gray);
            slText.setBackgroundResource(R.color.light_gray);
            gdText.setBackgroundResource(R.color.light_gray);
            qtText.setBackgroundResource(R.color.nomal_button_color);
            showqt();
        }
    }


    /**
     * 初始化行项*
     */
    private void inithx(View view) {
        itemnumText = (TextView) view.findViewById(R.id.invuseline_itemnum_text_id);
        bjNmaeText = (TextView) view.findViewById(R.id.invuseline_description_text_id);
        level4Text = (TextView) view.findViewById(R.id.level5_text_id);
        level5Text = (TextView) view.findViewById(R.id.level6_text_id);
        quantityText = (TextView) view.findViewById(R.id.quantity_text_id);
        frombinText = (TextView) view.findViewById(R.id.frombin_txt_id);
        curbalText = (TextView) view.findViewById(R.id.curbal_text_id);
        curbaltotalText = (TextView) view.findViewById(R.id.curbaltotal_text_id);
        issueunitText = (TextView) view.findViewById(R.id.issueunit_text_id);
        remarkText = (TextView) view.findViewById(R.id.remark_text_id);
    }

    /**
     * 初始化数量和成本*
     */
    private void initslcb(View view) {
        udbudctrlnumText = (TextView) view.findViewById(R.id.udbudctrlnum_text_id);
        budctrlText = (TextView) view.findViewById(R.id.budctrl_description_text_id);
        udglaccountText = (TextView) view.findViewById(R.id.udglaccount_text_id);
        chartofaccountsText = (TextView) view.findViewById(R.id.chartofaccounts_accountname_text_id);
        udlimitText = (TextView) view.findViewById(R.id.budctrl_udlimit_text_id);
        actualText = (TextView) view.findViewById(R.id.budctrl_actual_text_id);
        remainderText = (TextView) view.findViewById(R.id.budctrl_remainder_text_id);
        displayunitcostText = (TextView) view.findViewById(R.id.displayunitcost_text_id);
        displaylinecostText = (TextView) view.findViewById(R.id.displaylinecost_text_id);
    }

    /**
     * 初始化工单*
     */
    private void initgd(View view) {
        wonumText = (TextView) view.findViewById(R.id.wonum_text_id);
        taskidText = (TextView) view.findViewById(R.id.taskid_text_id);
        assetnumText = (TextView) view.findViewById(R.id.assetnum_text_id);
    }

    /**
     * 初始化其他*
     */
    private void initqt(View view) {
        linetypeText = (TextView) view.findViewById(R.id.linetype_text_id);
        tositeidText = (TextView) view.findViewById(R.id.tositeid_text_id);
        enterbyText = (TextView) view.findViewById(R.id.enterby_displayname_text_id);
        issuetoText = (TextView) view.findViewById(R.id.issueto_text_id);
    }

    /**
     * 显示行项信息*
     */
    private void showhx() {
        itemnumText.setText(invuseline.itemnum);
        bjNmaeText.setText(invuseline.description);
        level4Text.setText(invuseline.classstructureid);
        level5Text.setText(invuseline.classstructure_description);
        quantityText.setText(invuseline.quantity);
        frombinText.setText(invuseline.frombin);
        curbalText.setText(invuseline.invbalances_curbal);
        curbaltotalText.setText(invuseline.inventory_curbaltotal);
        issueunitText.setText(invuseline.inventory_issueunit);
        remarkText.setText(invuseline.remark);
    }

    /**
     * 显示数量和成本*
     */
    private void showslcb() {
        udbudctrlnumText.setText(invuseline.udbudctrlnum);
        budctrlText.setText(invuseline.budctrl_description);
        udglaccountText.setText(invuseline.udglaccount);
        chartofaccountsText.setText(invuseline.chartofaccounts_accountname);
        udlimitText.setText(invuseline.budctrl_udlimit);
        actualText.setText(invuseline.budctrl_actual);
        remainderText.setText(invuseline.budctrl_remainder);
        displayunitcostText.setText(invuseline.displayunitcost);
        displaylinecostText.setText(invuseline.displaylinecost);
    }


    /**
     * 显示工单信息*
     */
    private void showgd() {
        wonumText.setText(invuseline.wonum);
        taskidText.setText(invuseline.taskid);
        assetnumText.setText(invuseline.assetnum);
    }


    /**
     * 显示其他信息*
     */
    private void showqt() {
        linetypeText.setText(invuseline.linetype);
        tositeidText.setText(invuseline.tositeid);
        enterbyText.setText(invuseline.enterby_displayname);
        issuetoText.setText(invuseline.issueto_displayname);
    }
}