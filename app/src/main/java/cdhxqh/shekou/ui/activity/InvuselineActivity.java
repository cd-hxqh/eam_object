package cdhxqh.shekou.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import cdhxqh.shekou.R;
import cdhxqh.shekou.api.HttpManager;
import cdhxqh.shekou.api.HttpRequestHandler;
import cdhxqh.shekou.api.JsonUtils;
import cdhxqh.shekou.bean.InvuseResult;
import cdhxqh.shekou.bean.Results;
import cdhxqh.shekou.config.Constants;
import cdhxqh.shekou.model.Invuse;
import cdhxqh.shekou.model.Invuseline;
import cdhxqh.shekou.ui.adapter.InvuselineAdapter;
import cdhxqh.shekou.ui.widget.SwipeRefreshLayout;
import cdhxqh.shekou.utils.AccountUtils;
import cdhxqh.shekou.webserviceclient.AndroidClientService;

/**
 * 物质清单
 */
public class InvuselineActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, SwipeRefreshLayout.OnLoadListener {
    private static final String TAG = "InvuselineActivity";

    /**
     * 返回按钮
     */
    private ImageView backImageView;
    /**
     * 标题
     */
    private TextView titleTextView;
    /**
     * 新建*
     */
    private ImageView addImageView;

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
     * 确定按钮*
     */
    private LinearLayout addLinearLayout;
    private Button addButton;


    /**
     * 暂无数据*
     */
    LinearLayout notLinearLayout;

    InvuselineAdapter invuselineAdapter;

    private String invusenum;

    private int page = 1;

    private ArrayList<Invuseline> items = new ArrayList<Invuseline>();


    /**
     * 搜索值*
     */
    private String vlaue = "";

    private Invuse invuse;

    /**
     * 新增的领料单行*
     */
    private ArrayList<Invuseline> newInvuseline = new ArrayList<Invuseline>();

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

        invusenum = getIntent().getExtras().getString("invusenum");
        invuse = (Invuse) getIntent().getParcelableExtra("invuse");
    }

    @Override
    protected void findViewById() {

        backImageView = (ImageView) findViewById(R.id.title_back_id);
        titleTextView = (TextView) findViewById(R.id.title_name);


        mRecyclerView = (RecyclerView) findViewById(R.id.list_topics);
        mLayoutManager = new LinearLayoutManager(InvuselineActivity.this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        invuselineAdapter = new InvuselineAdapter(InvuselineActivity.this);
        mRecyclerView.setAdapter(invuselineAdapter);
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
        addImageView = (ImageView) findViewById(R.id.title_add);

        addLinearLayout = (LinearLayout) findViewById(R.id.boottom_id);
        addButton = (Button) findViewById(R.id.add_button_id);
    }

    @Override
    protected void initView() {
        backImageView.setOnClickListener(backImageViewOnClickListener);
        titleTextView.setText(getString(R.string.invuseline_title_text));
        addImageView.setVisibility(View.VISIBLE);

        mSwipeLayout.setRefreshing(true);
        getItemList(vlaue, page, invusenum);


        SpannableString msp = new SpannableString("XX搜索");
        Drawable drawable = getResources().getDrawable(R.drawable.ic_search);
        msp.setSpan(new ImageSpan(drawable), 0, 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        searchEditText.setHint(msp);

        searchEditText.setOnEditorActionListener(searchEditTextOnEditorActionListener);
        addImageView.setOnClickListener(addImageViewOnClickListener);
        addButton.setOnClickListener(updateButtonOnClickListener);
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
            intent.setClass(InvuselineActivity.this, AddInvuseDetailActivity.class);

            startActivityForResult(intent, 100);
        }
    };

    /**
     * 获取物质清单
     * --分页
     */

    private void getItemList(String value, int page, String itemnum) {
        HttpManager.getDataPagingInfo(InvuselineActivity.this, HttpManager.getInvuselineurl(value, page, 20, itemnum), new HttpRequestHandler<Results>() {
            @Override
            public void onSuccess(Results results) {
                Log.i(TAG, "data=" + results);
            }

            @Override
            public void onSuccess(Results results, int totalPages, int currentPage) {
                ArrayList<Invuseline> item = JsonUtils.parsingInvuseline(InvuselineActivity.this, results.getResultlist());

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
                    invuselineAdapter.update(items, true);
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
        getItemList(vlaue, page, invusenum);
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
                                InvuselineActivity.this.getCurrentFocus()
                                        .getWindowToken(),
                                InputMethodManager.HIDE_NOT_ALWAYS);
                vlaue = searchEditText.getText().toString();
                invuselineAdapter.removeAllData();
                notLinearLayout.setVisibility(View.GONE);
                mSwipeLayout.setRefreshing(true);
                page = 1;
                getItemList(vlaue, page, invusenum);
                return true;
            }
            return false;
        }


    };


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (resultCode) {
            case 0:
                Invuseline invuseline = (Invuseline) data.getParcelableExtra("invuseline");
                newInvuseline.add(invuseline);
                items.add(invuseline);
                if (items != null || items.size() != 0) {
                    notLinearLayout.setVisibility(View.GONE);
                    addLinearLayout.setVisibility(View.VISIBLE);
                    invuselineAdapter.update(items, true);
                } else {
                    notLinearLayout.setVisibility(View.VISIBLE);
                }

                break;

            default:
                break;

        }
    }


    /**
     * 修改信息*
     */
    private View.OnClickListener updateButtonOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            showProgressDialog("数据更新中...");
            startAsyncTask();


        }
    };


    /**
     * 提交数据*
     */
    private void startAsyncTask() {

        final String updataInfo = JsonUtils.InvuseToJson(invuse, items);

        Log.i(TAG,"updataInfo="+updataInfo);
        new AsyncTask<String, String, InvuseResult>() {
            @Override
            protected InvuseResult doInBackground(String... strings) {
                InvuseResult addresult = AndroidClientService.UpdateInvuse(updataInfo, AccountUtils.getpersonId(InvuselineActivity.this), AccountUtils.getIpAddress(InvuselineActivity.this) + Constants.INVUSE_URL);

                return addresult;
            }

            @Override
            protected void onPostExecute(InvuseResult invuseResult) {
                super.onPostExecute(invuseResult);


                if (invuseResult == null) {
                    Toast.makeText(InvuselineActivity.this, "更新失败", Toast.LENGTH_SHORT).show();
                } else if (!invuseResult.errorMsg.equals("成功!")) {

                    Toast.makeText(InvuselineActivity.this, invuseResult.errorMsg, Toast.LENGTH_SHORT).show();
                    finish();
                } else if (invuseResult.errorMsg.equals("成功!")) {
                    Toast.makeText(InvuselineActivity.this, "领料单" + invuseResult.invusenum + "更新成功", Toast.LENGTH_SHORT).show();
                    finish();
                }
                closeProgressDialog();
            }
        }.execute();
    }


}
