package cdhxqh.shekou.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import cdhxqh.shekou.model.Woactivity;
import cdhxqh.shekou.model.WorkOrder;
import cdhxqh.shekou.ui.adapter.WoactivityAdapter;
import cdhxqh.shekou.ui.widget.SwipeRefreshLayout;

/**
 * Created by think on 2016/5/10.
 */
public class Woactivity_Activity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, SwipeRefreshLayout.OnLoadListener {
    private ImageView backImageView;
    private TextView titleTextView;
    private ImageView menuImageView;

    LinearLayoutManager layoutManager;
    public RecyclerView recyclerView;
    private LinearLayout nodatalayout;
    private WoactivityAdapter woactivityAdapter;
    private SwipeRefreshLayout refresh_layout = null;
    private int page = 1;
    private WorkOrder workOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_woactivity);

        getData();
        findViewById();
        initView();
    }

    private void getData() {
        workOrder = (WorkOrder) getIntent().getSerializableExtra("workOrder");
    }

    @Override
    protected void findViewById() {
        backImageView = (ImageView) findViewById(R.id.title_back_id);
        titleTextView = (TextView) findViewById(R.id.title_name);
        menuImageView = (ImageView) findViewById(R.id.title_add);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView_id);
        refresh_layout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
        nodatalayout = (LinearLayout) findViewById(R.id.have_not_data_id);
    }

    @Override
    protected void initView() {
        backImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        titleTextView.setText(getResources().getString(R.string.title_activity_workwoactivity));
        menuImageView.setImageResource(R.drawable.add);
        menuImageView.setVisibility(View.VISIBLE);
        menuImageView.setOnClickListener(menuImageViewOnClickListener);
        layoutManager = new LinearLayoutManager(Woactivity_Activity.this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        woactivityAdapter = new WoactivityAdapter(Woactivity_Activity.this);
        recyclerView.setAdapter(woactivityAdapter);

        refresh_layout.setColor(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        refresh_layout.setRefreshing(true);
        refresh_layout.setOnRefreshListener(this);
        refresh_layout.setOnLoadListener(this);

        if (workOrder.wonum!=null&&!workOrder.wonum.equals("")) {
            getdata();
        }
    }

    private void getdata() {
        if (workOrder.wonum != null && !workOrder.wonum.equals("")) {
            HttpManager.getDataPagingInfo(Woactivity_Activity.this, HttpManager.getwoactivityUrl(workOrder.worktype,workOrder.wonum, page, 20), new HttpRequestHandler<Results>() {
                @Override
                public void onSuccess(Results results) {
                    Log.i(TAG, "data=" + results);
                }

                @Override
                public void onSuccess(Results results, int currentPage, int showcount) {
                    ArrayList<Woactivity> woactivities = null;
                    if (currentPage == page) {
                        woactivities = JsonUtils.parsingWoactivity(Woactivity_Activity.this, results.getResultlist());
                    }
                    addListData(woactivities);
                    refresh_layout.setRefreshing(false);
                    refresh_layout.setLoading(false);
                }

                @Override
                public void onFailure(String error) {
                    if (page == 1) {
                        nodatalayout.setVisibility(View.VISIBLE);
                    }
                    refresh_layout.setRefreshing(false);
                    refresh_layout.setLoading(false);
                }
            });
        }else {
            refresh_layout.setRefreshing(false);
            refresh_layout.setLoading(false);
        }
    }

    private void addListData(ArrayList<Woactivity> list) {
        if (nodatalayout.getVisibility() == View.VISIBLE) {
            nodatalayout.setVisibility(View.GONE);
        }
        if (page == 1 && woactivityAdapter.getItemCount() != 0) {
            woactivityAdapter = new WoactivityAdapter(Woactivity_Activity.this);
            recyclerView.setAdapter(woactivityAdapter);
        }
        if ((list == null || list.size() == 0) && page == 1) {
            nodatalayout.setVisibility(View.VISIBLE);
        } else {
            woactivityAdapter.adddate(list);
        }
    }

    private View.OnClickListener menuImageViewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent;
            intent = new Intent(Woactivity_Activity.this, AddWoactivityActivity.class);
            startActivity(intent);
        }
    };

    @Override
    public void onRefresh() {
        page = 1;
        getdata();
    }

    @Override
    public void onLoad() {
        page++;
        getdata();
    }
}
