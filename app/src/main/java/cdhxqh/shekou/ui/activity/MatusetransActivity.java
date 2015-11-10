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
import cdhxqh.shekou.model.Matrectrans;
import cdhxqh.shekou.model.Matusetrans;
import cdhxqh.shekou.ui.adapter.MatrectransAdapter;
import cdhxqh.shekou.ui.adapter.MatusetransAdapter;
import cdhxqh.shekou.ui.widget.SwipeRefreshLayout;

/**
 * 出库
 */
public class MatusetransActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, SwipeRefreshLayout.OnLoadListener{
    private static final String TAG = "MatusetransActivity";

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

    MatusetransAdapter matusetransAdapter;

    private String itemnum;


    private int page = 1;

    private ArrayList<Matusetrans> items=new ArrayList<Matusetrans>();

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
        itemnum=getIntent().getExtras().getString("itemnum");
        Log.i(TAG,"itemnum="+itemnum);
    }

    @Override
    protected void findViewById() {

        backImageView = (ImageView) findViewById(R.id.title_back_id);
        titleTextView = (TextView) findViewById(R.id.title_name);


        mRecyclerView = (RecyclerView) findViewById(R.id.list_topics);
        mLayoutManager = new LinearLayoutManager(MatusetransActivity.this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        matusetransAdapter = new MatusetransAdapter(MatusetransActivity.this);
        mRecyclerView.setAdapter(matusetransAdapter);
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
        titleTextView.setText(getResources().getText(R.string.inventory_outbound_text));

        mSwipeLayout.setRefreshing(true);
        getItemList(itemnum);
    }



    private View.OnClickListener backImageViewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };

    /**
     * 获取出库出库
     * --分页
     *
     */

    private void getItemList(String itemnum) {
        HttpManager.getDataPagingInfo(MatusetransActivity.this, HttpManager.getMatusetransurl(1, 20, itemnum), new HttpRequestHandler<Results>() {
            @Override
            public void onSuccess(Results results) {
                Log.i(TAG, "data=" + results);
            }

            @Override
            public void onSuccess(Results results, int totalPages, int currentPage) {
                ArrayList<Matusetrans> item = JsonUtils.parsingMatusetrans(MatusetransActivity.this, results.getResultlist());

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
                    matusetransAdapter.update(items, true);
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
        getItemList(itemnum);
    }

    @Override
    public void onRefresh() {
        mSwipeLayout.setRefreshing(false);
    }
}
