package cdhxqh.shekou.ui.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import cdhxqh.shekou.R;
import cdhxqh.shekou.api.HttpManager;
import cdhxqh.shekou.api.HttpRequestHandler;
import cdhxqh.shekou.api.JsonUtils;
import cdhxqh.shekou.bean.Results;
import cdhxqh.shekou.model.Assignment;
import cdhxqh.shekou.model.WorkOrder;
import cdhxqh.shekou.ui.adapter.AssignmentAdapter;
import cdhxqh.shekou.ui.widget.SwipeRefreshLayout;

/**
 * Created by think on 2015/11/4.
 * 任务分配界面
 */
public class AssignmentActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener,SwipeRefreshLayout.OnLoadListener{

    private TextView titlename;
    private ImageView menuImageView;
    private RelativeLayout backlayout;
    LinearLayoutManager layoutManager;
    public RecyclerView recyclerView;
    private AssignmentAdapter assignmentAdapter;
    private LinearLayout nodatalayout;
    private SwipeRefreshLayout refresh_layout = null;
    private int page = 1;

    private WorkOrder workOrder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment);

        geiIntentData();
        findViewById();
        initView();
    }

    private void geiIntentData() {
        workOrder = getIntent().getParcelableExtra("workOrder");
    }
    @Override
    protected void findViewById() {
        titlename = (TextView) findViewById(R.id.title_name);
        menuImageView = (ImageView) findViewById(R.id.title_add);
        backlayout = (RelativeLayout) findViewById(R.id.title_back);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView_id);
        refresh_layout = (SwipeRefreshLayout) this.findViewById(R.id.swipe_container);
        nodatalayout = (LinearLayout) findViewById(R.id.have_not_data_id);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    protected void initView() {
        titlename.setText(getResources().getString(R.string.work_task));
        backlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        menuImageView.setImageResource(R.drawable.add);
        menuImageView.setVisibility(View.VISIBLE);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        assignmentAdapter = new AssignmentAdapter(this);
        recyclerView.setAdapter(assignmentAdapter);

        refresh_layout.setColor(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
//        refresh_layout.setProgressViewOffset(false, 0,
//                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources().getDisplayMetrics()));
        refresh_layout.setRefreshing(true);

        refresh_layout.setOnRefreshListener(this);
        refresh_layout.setOnLoadListener(this);

        getData();
    }

    private void getData(){
        HttpManager.getDataPagingInfo(this, HttpManager.getassignmentUrl(workOrder.worktype, page, 20), new HttpRequestHandler<Results>() {
            @Override
            public void onSuccess(Results results) {
                Log.i(TAG, "data=" + results);
            }

            @Override
            public void onSuccess(Results results, int totalPages, int currentPage) {
                if (nodatalayout.getVisibility() == View.VISIBLE) {
                    nodatalayout.setVisibility(View.GONE);
                }
                ArrayList<Assignment> items = JsonUtils.parsingAssignment(AssignmentActivity.this, results.getResultlist());
                refresh_layout.setRefreshing(false);
                refresh_layout.setLoading(false);
                if (items == null || items.isEmpty()) {
                    nodatalayout.setVisibility(View.VISIBLE);
                } else {
                    if (page == 1) {
                        assignmentAdapter = new AssignmentAdapter(AssignmentActivity.this);
                        recyclerView.setAdapter(assignmentAdapter);
                    }
                    assignmentAdapter.adddate(items);
                }
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
    }
    //下拉刷新触发事件
    @Override
    public void onRefresh() {
        page = 1;
        getData();
    }

    @Override
    public void onLoad(){
        page++;
        getData();
    }
}
