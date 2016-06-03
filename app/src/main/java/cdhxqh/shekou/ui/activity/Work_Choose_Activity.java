package cdhxqh.shekou.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
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
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import cdhxqh.shekou.R;
import cdhxqh.shekou.api.HttpManager;
import cdhxqh.shekou.api.HttpRequestHandler;
import cdhxqh.shekou.api.JsonUtils;
import cdhxqh.shekou.bean.Results;
import cdhxqh.shekou.config.Constants;
import cdhxqh.shekou.model.WorkOrder;
import cdhxqh.shekou.model.WorkOrderTem;
import cdhxqh.shekou.ui.adapter.WorkListAdapter;
import cdhxqh.shekou.ui.adapter.WorkTemAdapter;
import cdhxqh.shekou.ui.widget.SwipeRefreshLayout;

/**
 * 工单选择界面
 */
public class Work_Choose_Activity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, SwipeRefreshLayout.OnLoadListener {
    private static String TAG = "Work_Choose_Activity";

    /**
     * 标题*
     */
    private TextView titlename;
    /**
     * 返回按钮*
     */
    private ImageView backImage;

    LinearLayoutManager layoutManager;

    public RecyclerView recyclerView;
    private LinearLayout nodatalayout;

    private WorkTemAdapter workListAdapter;
    private SwipeRefreshLayout refresh_layout = null;
    private EditText search;
    private String searchText = "";
    private int page = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worklist);

        getIntentData();
        findViewById();
        initView();
    }

    private void getIntentData() {
    }

    @Override
    protected void findViewById() {
        titlename = (TextView) findViewById(R.id.title_name);
        backImage = (ImageView) findViewById(R.id.title_back_id);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView_id);
        refresh_layout = (SwipeRefreshLayout) this.findViewById(R.id.swipe_container);
        nodatalayout = (LinearLayout) findViewById(R.id.have_not_data_id);
        search = (EditText) findViewById(R.id.search_edit);
    }

    @Override
    protected void initView() {
        setSearchEdit();
        titlename.setText(R.string.work_list_title);
        backImage.setOnClickListener(backImageOnClickListener);

        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        workListAdapter = new WorkTemAdapter(this);
        recyclerView.setAdapter(workListAdapter);
        refresh_layout.setColor(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        refresh_layout.setRefreshing(true);
        getData(searchText);

        refresh_layout.setOnRefreshListener(this);
        refresh_layout.setOnLoadListener(this);


    }

    private View.OnClickListener backImageOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            finish();
        }
    };


    private void getData(String search) {
        HttpManager.getDataPagingInfo(this, HttpManager.getChooseWorkOrderUrl(search, page, 20), new HttpRequestHandler<Results>() {
            @Override
            public void onSuccess(Results results) {
                Log.i(TAG, "data=" + results);
            }

            @Override
            public void onSuccess(Results results, int totalPages, int currentPage) {
                ArrayList<WorkOrderTem> items = JsonUtils.parsingWorkOrderTem(results.getResultlist());
                refresh_layout.setRefreshing(false);
                refresh_layout.setLoading(false);
                if (items == null || items.isEmpty()) {
                    nodatalayout.setVisibility(View.VISIBLE);
                } else {
                    if (page == 1) {
                        workListAdapter = new WorkTemAdapter(Work_Choose_Activity.this);
                        recyclerView.setAdapter(workListAdapter);
                    }
                    if (totalPages == page) {
                        workListAdapter.adddate(items);
                    }


                    workListAdapter.setOnClickListener(new WorkTemAdapter.OnClickListener() {
                        @Override
                        public void cOnClickListener(WorkOrderTem workOrderTem) {
                            Intent intent = getIntent();
                            intent.putExtra("wonum", workOrderTem.wonum);
                            intent.putExtra("description", workOrderTem.description);
                            setResult(Constants.WORKORDERCODE, intent);
                            finish();
                        }
                    });
                }


            }

            @Override
            public void onFailure(String error) {
                refresh_layout.setRefreshing(false);
                nodatalayout.setVisibility(View.VISIBLE);
            }
        });
    }

    private void setSearchEdit() {
        SpannableString msp = new SpannableString("XX搜索");
        Drawable drawable = getResources().getDrawable(R.drawable.ic_search);
        msp.setSpan(new ImageSpan(drawable), 0, 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        search.setHint(msp);
        search.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    // 先隐藏键盘
                    ((InputMethodManager) search.getContext().getSystemService(Context.INPUT_METHOD_SERVICE))
                            .hideSoftInputFromWindow(
                                    Work_Choose_Activity.this.getCurrentFocus()
                                            .getWindowToken(),
                                    InputMethodManager.HIDE_NOT_ALWAYS);
                    searchText = search.getText().toString().trim();
                    workListAdapter = new WorkTemAdapter(Work_Choose_Activity.this);
                    recyclerView.setAdapter(workListAdapter);
                    getData(searchText);
                    return true;
                }
                return false;
            }
        });
    }

    //下拉刷新触发事件
    @Override
    public void onRefresh() {
        page = 1;
        getData(search.getText().toString());
    }

    @Override
    public void onLoad() {
        page++;
        getData(searchText);
    }
}
