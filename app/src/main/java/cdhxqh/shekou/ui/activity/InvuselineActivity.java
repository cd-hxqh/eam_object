package cdhxqh.shekou.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import cdhxqh.shekou.R;
import cdhxqh.shekou.api.HttpManager;
import cdhxqh.shekou.api.HttpRequestHandler;
import cdhxqh.shekou.api.JsonUtils;
import cdhxqh.shekou.bean.Results;
import cdhxqh.shekou.model.Invuse;
import cdhxqh.shekou.model.Invuseline;
import cdhxqh.shekou.model.Matusetrans;
import cdhxqh.shekou.ui.adapter.InvuselineAdapter;
import cdhxqh.shekou.ui.adapter.MatusetransAdapter;
import cdhxqh.shekou.ui.widget.SwipeRefreshLayout;

/**
 * 物质清单
 */
public class InvuselineActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, SwipeRefreshLayout.OnLoadListener{
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
     * RecyclerView*
     */
    RecyclerView mRecyclerView;

    RecyclerView.LayoutManager mLayoutManager;

    SwipeRefreshLayout mSwipeLayout;


    /**
     *暂无数据*
     */
    LinearLayout notLinearLayout;

    InvuselineAdapter invuselineAdapter;

    private String invusenum;

    private int page = 1;

    private  ArrayList<Invuseline> items=new ArrayList<Invuseline>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_invcost);
        getInitData();
        findViewById();
        initView();
    }

    /**获取上个界面的数据**/
    private void getInitData() {
        invusenum=getIntent().getExtras().getString("invusenum");
        Log.i(TAG,"invusenum="+invusenum);
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
        mSwipeLayout = (SwipeRefreshLayout)findViewById(R.id.swipe_container);
        mSwipeLayout.setColor(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);


        notLinearLayout = (LinearLayout) findViewById(R.id.have_not_data_id);

        mSwipeLayout.setOnRefreshListener(this);
        mSwipeLayout.setOnLoadListener(this);
        mSwipeLayout.setRefreshing(false);
    }

    @Override
    protected void initView() {
        backImageView.setOnClickListener(backImageViewOnClickListener);
        titleTextView.setText(getString(R.string.invuseline_title_text));

        mSwipeLayout.setRefreshing(true);
        getItemList(invusenum);
    }



    private View.OnClickListener backImageViewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };

    /**
     * 获取物质清单
     * --分页
     *
     */

    private void getItemList(String itemnum) {
        HttpManager.getDataPagingInfo(InvuselineActivity.this, HttpManager.getInvuselineurl(page, 20, itemnum),new HttpRequestHandler<Results>() {
            @Override
            public void onSuccess(Results results) {
                Log.i(TAG, "data=" + results);
            }

            @Override
            public void onSuccess(Results results, int totalPages, int currentPage) {
                ArrayList<Invuseline> item = JsonUtils.parsingInvuseline(InvuselineActivity.this, results.getResultlist());

                if(item!=null||item.size()!=0){
                    for (int i=0;i<item.size();i++) {
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
        getItemList(invusenum);
    }

    @Override
    public void onRefresh() {
        mSwipeLayout.setRefreshing(false);
    }
}
