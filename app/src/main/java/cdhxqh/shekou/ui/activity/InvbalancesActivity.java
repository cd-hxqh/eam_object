package cdhxqh.shekou.ui.activity;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import cdhxqh.shekou.R;
import cdhxqh.shekou.api.HttpManager;
import cdhxqh.shekou.api.HttpRequestHandler;
import cdhxqh.shekou.api.JsonUtils;
import cdhxqh.shekou.bean.Results;
import cdhxqh.shekou.model.Invbalances;
import cdhxqh.shekou.ui.adapter.InvbalancesAdapter;
import cdhxqh.shekou.ui.widget.SwipeRefreshLayout;

/**
 * 库存余量
 */
public class InvbalancesActivity extends BaseActivity implements cdhxqh.shekou.ui.widget.SwipeRefreshLayout.OnRefreshListener, cdhxqh.shekou.ui.widget.SwipeRefreshLayout.OnLoadListener {
    private static final String TAG = "InvbalancesActivity";

    /**
     * 返回按钮
     */
    private ImageView backImageView;
    /**
     * 标题
     */
    private TextView titleTextView;

    /**
     * 搜索按钮*
     */
    private EditText searchEditText;

    /**
     * RecyclerView*
     */
    RecyclerView mRecyclerView;

    RecyclerView.LayoutManager mLayoutManager;

    SwipeRefreshLayout mSwipeLayout;


    /**
     * 暂无数据*
     */
    LinearLayout notLinearLayout;

    InvbalancesAdapter invbalancesAdapter;
    /**
     * 编号*
     */
    private String itemnum;
    /**
     * 库房*
     */
    private String location;
    /**
     * 站点*
     */
    private String siteid;

    private int page = 1;

    private ArrayList<Invbalances> items = new ArrayList<Invbalances>();

    /**
     * 搜索值*
     */
    private String vlaue = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_invcost);
        getInitData();
        findViewById();
        initView();
    }

    /**
     * 获取上个界面的数据*
     */
    private void getInitData() {
        itemnum = getIntent().getExtras().getString("itemnum");
        location = getIntent().getExtras().getString("location");
        siteid = getIntent().getExtras().getString("siteid");
    }

    @Override
    protected void findViewById() {

        backImageView = (ImageView) findViewById(R.id.title_back_id);
        titleTextView = (TextView) findViewById(R.id.title_name);


        mRecyclerView = (RecyclerView) findViewById(R.id.list_topics);
        mLayoutManager = new LinearLayoutManager(InvbalancesActivity.this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        invbalancesAdapter = new InvbalancesAdapter(InvbalancesActivity.this);
        mRecyclerView.setAdapter(invbalancesAdapter);
        mSwipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
        mSwipeLayout.setColor(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);


        notLinearLayout = (LinearLayout) findViewById(R.id.have_not_data_id);

        mSwipeLayout.setOnRefreshListener(this);
        mSwipeLayout.setOnLoadListener(this);
        mSwipeLayout.setRefreshing(false);

        searchEditText = (EditText) findViewById(R.id.search_edit);
    }

    @Override
    protected void initView() {
        backImageView.setOnClickListener(backImageViewOnClickListener);
        titleTextView.setText(getString(R.string.invbalances_title));
        searchEditText.setVisibility(View.GONE);
        mSwipeLayout.setRefreshing(true);
        getItemList(vlaue, page, itemnum);

        SpannableString msp = new SpannableString("XX搜索");
        Drawable drawable = getResources().getDrawable(R.drawable.ic_search);
        msp.setSpan(new ImageSpan(drawable), 0, 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        searchEditText.setHint(msp);

        searchEditText.setOnEditorActionListener(searchEditTextOnEditorActionListener);
    }


    private View.OnClickListener backImageViewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };

    /**
     * 获取库存成本
     * --分页
     */

    private void getItemList(String value, int page, String itemnum) {
        HttpManager.getDataPagingInfo(InvbalancesActivity.this, HttpManager.getInvbalancesurl(value, page, 20, itemnum, location, siteid), new HttpRequestHandler<Results>() {
            @Override
            public void onSuccess(Results results) {
                Log.i(TAG, "data=" + results);
            }

            @Override
            public void onSuccess(Results results, int totalPages, int currentPage) {
                ArrayList<Invbalances> item = JsonUtils.parsingInvbalances(InvbalancesActivity.this, results.getResultlist());

                if (item != null || item.size() != 0) {
                    for (int i = 0; i < item.size(); i++) {
                        items.add(item.get(i));
                    }
                }

                mSwipeLayout.setLoading(false);
                mSwipeLayout.setRefreshing(false);
                if (items == null || items.isEmpty()) {
                    notLinearLayout.setVisibility(View.VISIBLE);
                } else {
                    invbalancesAdapter.update(items, true);
                }
            }

            @Override
            public void onFailure(String error) {
                mSwipeLayout.setRefreshing(false);
                notLinearLayout.setVisibility(View.VISIBLE);
            }
        });
    }


    @Override
    public void onLoad() {
        page++;
        getItemList(vlaue, page, itemnum);
    }

    @Override
    public void onRefresh() {
        mSwipeLayout.setRefreshing(false);
    }


    private TextView.OnEditorActionListener searchEditTextOnEditorActionListener = new TextView.OnEditorActionListener() {
        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                // 先隐藏键盘
                ((InputMethodManager) searchEditText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE))
                        .hideSoftInputFromWindow(
                                InvbalancesActivity.this.getCurrentFocus()
                                        .getWindowToken(),
                                InputMethodManager.HIDE_NOT_ALWAYS);
                vlaue = searchEditText.getText().toString();
                invbalancesAdapter.removeAllData();
                notLinearLayout.setVisibility(View.GONE);
                mSwipeLayout.setRefreshing(true);
                page = 1;
                getItemList(vlaue, page, itemnum);
                return true;
            }
            return false;
        }


    };


}
