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
import android.widget.Button;
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
import cdhxqh.shekou.config.Constants;
import cdhxqh.shekou.model.Assignment;
import cdhxqh.shekou.model.Invuseline;
import cdhxqh.shekou.model.Option;
import cdhxqh.shekou.ui.adapter.InvuselineAdapter;
import cdhxqh.shekou.ui.widget.SwipeRefreshLayout;

/**
 * 物质清单
 */
public class AddInvuselineActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, SwipeRefreshLayout.OnLoadListener {
    private static final String TAG = "AddInvuselineActivity";

    /**
     * 返回按钮
     */
    private ImageView backImageView;
    /**
     * 标题
     */
    private TextView titleTextView;

    /**
     * 新建明细*
     */
    private ImageView addImage;

    /**
     * 搜索按钮*
     */
    private EditText searchEditText;


    /**
     * 确定按钮*
     */
    private LinearLayout addLinearLayout;
    private Button addButton;

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

    InvuselineAdapter invuselineAdapter;

    private String invusenum;

    private int page = 1;

    private ArrayList<Invuseline> items = new ArrayList<Invuseline>();


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
        invusenum = getIntent().getExtras().getString("invusenum");
    }

    @Override
    protected void findViewById() {

        backImageView = (ImageView) findViewById(R.id.title_back_id);
        titleTextView = (TextView) findViewById(R.id.title_name);
        addImage = (ImageView) findViewById(R.id.title_add);


        mRecyclerView = (RecyclerView) findViewById(R.id.list_topics);
        mLayoutManager = new LinearLayoutManager(AddInvuselineActivity.this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        invuselineAdapter = new InvuselineAdapter(AddInvuselineActivity.this);
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

        addLinearLayout = (LinearLayout) findViewById(R.id.boottom_id);
        addButton = (Button) findViewById(R.id.add_button_id);

    }

    @Override
    protected void initView() {
        backImageView.setOnClickListener(backImageViewOnClickListener);
        titleTextView.setText(getString(R.string.invuseline_title));

        addImage.setVisibility(View.VISIBLE);
        searchEditText.setVisibility(View.GONE);

        addImage.setOnClickListener(addImageOnClickListener);

        if (items == null || items.size() == 0) {
            notLinearLayout.setVisibility(View.VISIBLE);
        }

        addButton.setOnClickListener(addButtonOnClickListener);
    }


    private View.OnClickListener backImageViewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            setResult(1);
            finish();
        }
    };

    private View.OnClickListener addImageOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = getIntent();

            intent.setClass(AddInvuselineActivity.this, AddInvuseDetailActivity.class);
            startActivityForResult(intent, 1);
        }
    };
    private View.OnClickListener addButtonOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = getIntent();
            intent.putParcelableArrayListExtra("invuselines", items);
            setResult(0, intent);
            finish();

        }
    };


    @Override
    public void onLoad() {

    }

    @Override
    public void onRefresh() {
        mSwipeLayout.setRefreshing(false);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i(TAG, "resultCode=" + resultCode);

        switch (resultCode) {
            case 0:
                Invuseline invuseline = (Invuseline) data.getParcelableExtra("invuseline");

                items.add(invuseline);
                if (items != null || items.size() != 0) {
                    notLinearLayout.setVisibility(View.GONE);
                    addLinearLayout.setVisibility(View.VISIBLE);
                    setAdapter();
                } else {
                    notLinearLayout.setVisibility(View.VISIBLE);
                }

                break;

            default:
                break;

        }
    }


    /**
     * 设置适配器**
     */

    private void setAdapter() {
        invuselineAdapter.update(items, true);
        invuselineAdapter.notifyDataSetChanged();
    }
}
