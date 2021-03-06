package cdhxqh.shekou.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.util.Log;
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
import cdhxqh.shekou.model.InvoiceLine;
import cdhxqh.shekou.model.Invuse;
import cdhxqh.shekou.ui.adapter.InvoicelineAdapter;
import cdhxqh.shekou.ui.widget.SwipeRefreshLayout;

/**
 *  外协服务付款申请行
 */
public class InvoiceLineActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, SwipeRefreshLayout.OnLoadListener {
    private static final String TAG = "InvoiceLineActivity";

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

    InvoicelineAdapter invoicelineAdapter;



    private String invoicenum; //外协服务付款申请编号

    private int page = 1;

    private ArrayList<InvoiceLine> items = new ArrayList<InvoiceLine>();


    /**
     * 搜索值*
     */
    private String vlaue = "";

    private Invuse invuse;



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

        invoicenum = getIntent().getExtras().getString("invoicenum");

    }

    @Override
    protected void findViewById() {

        backImageView = (ImageView) findViewById(R.id.title_back_id);
        titleTextView = (TextView) findViewById(R.id.title_name);


        mRecyclerView = (RecyclerView) findViewById(R.id.list_topics);
        mLayoutManager = new LinearLayoutManager(InvoiceLineActivity.this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        invoicelineAdapter = new InvoicelineAdapter(InvoiceLineActivity.this);
        mRecyclerView.setAdapter(invoicelineAdapter);
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
        titleTextView.setText(R.string.fksqh_text);
        searchEditText.setVisibility(View.GONE);
        mSwipeLayout.setRefreshing(true);
        getItemList(invoicenum);


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
     * 新增按钮*
     */
    private View.OnClickListener addImageViewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = getIntent();
            intent.setClass(InvoiceLineActivity.this, AddInvuseDetailActivity.class);

            startActivityForResult(intent, 100);
        }
    };

    /**
     * 获取物质清单
     * --分页
     */

    private void getItemList(String itemnum) {
        HttpManager.getDataPagingInfo(InvoiceLineActivity.this, HttpManager.getInVoiceLineurl(page,20,itemnum), new HttpRequestHandler<Results>() {
            @Override
            public void onSuccess(Results results) {
                Log.i(TAG, "data=" + results);
            }

            @Override
            public void onSuccess(Results results, int totalPages, int currentPage) {
                ArrayList<InvoiceLine> item = JsonUtils.parsingInvoiceLine(InvoiceLineActivity.this, results.getResultlist());

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
                    invoicelineAdapter.update(items, true);
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
        getItemList(invoicenum);
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
                                InvoiceLineActivity.this.getCurrentFocus()
                                        .getWindowToken(),
                                InputMethodManager.HIDE_NOT_ALWAYS);
                vlaue = searchEditText.getText().toString();
                invoicelineAdapter.removeAllData();
                notLinearLayout.setVisibility(View.GONE);
                mSwipeLayout.setRefreshing(true);
                page = 1;
                getItemList(invoicenum);
                return true;
            }
            return false;
        }


    };









}
