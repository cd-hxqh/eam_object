package cdhxqh.shekou.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.flyco.animation.BaseAnimatorSet;
import com.flyco.animation.BounceEnter.BounceTopEnter;
import com.flyco.animation.SlideExit.SlideBottomExit;
import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.widget.NormalDialog;

import java.util.ArrayList;

import cdhxqh.shekou.R;
import cdhxqh.shekou.api.HttpManager;
import cdhxqh.shekou.api.HttpRequestHandler;
import cdhxqh.shekou.api.JsonUtils;
import cdhxqh.shekou.bean.Results;
import cdhxqh.shekou.config.Constants;
import cdhxqh.shekou.model.Woactivity;
import cdhxqh.shekou.model.WorkOrder;
import cdhxqh.shekou.ui.adapter.WoactivityAdapter;
import cdhxqh.shekou.ui.widget.SwipeRefreshLayout;
import cdhxqh.shekou.utils.AccountUtils;

/**
 * Created by think on 2016/5/10.
 * 工单任务列表
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
    public WorkOrder workOrder;
    public ArrayList<Woactivity> woactivityList = new ArrayList<>();

    private BaseAnimatorSet mBasIn;
    private BaseAnimatorSet mBasOut;
    private LinearLayout confirmlayout;
    private Button confirmBtn;

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
        woactivityList = (ArrayList<Woactivity>) getIntent().getSerializableExtra("woactivityList");
    }

    @Override
    protected void findViewById() {
        backImageView = (ImageView) findViewById(R.id.title_back_id);
        titleTextView = (TextView) findViewById(R.id.title_name);
        menuImageView = (ImageView) findViewById(R.id.title_add);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView_id);
        refresh_layout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
        nodatalayout = (LinearLayout) findViewById(R.id.have_not_data_id);
        confirmlayout = (LinearLayout) findViewById(R.id.confirm_layout);
        confirmBtn = (Button) findViewById(R.id.ok);
    }

    @Override
    protected void initView() {
        backImageView.setOnClickListener(backOnClickListener);
        titleTextView.setText(getResources().getString(R.string.title_activity_workwoactivity));
        menuImageView.setImageResource(R.drawable.add);
        menuImageView.setVisibility(View.VISIBLE);
        menuImageView.setOnClickListener(menuImageViewOnClickListener);
        confirmlayout.setVisibility(View.GONE);
        confirmBtn.setOnClickListener(confirmBtnOnClickListener);
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

        refresh_layout.setOnRefreshListener(this);
        refresh_layout.setOnLoadListener(this);

        mBasIn = new BounceTopEnter();
        mBasOut = new SlideBottomExit();

        if ((workOrder.status != null && workOrder.status.equals(Constants.STATUS25))||workOrder.isnew) {
            menuImageView.setVisibility(View.VISIBLE);
        }else {
            menuImageView.setVisibility(View.GONE);
        }

        if (!workOrder.isnew && (woactivityList == null || woactivityList.size() == 0)) {
            refresh_layout.setRefreshing(true);
            getdata();
        } else {
            if (woactivityList != null && woactivityList.size() != 0) {
                woactivityAdapter.update(woactivityList, true);
            }else {
                nodatalayout.setVisibility(View.VISIBLE);
            }
        }
    }

    private void getdata() {
        if (workOrder.wonum != null && !workOrder.wonum.equals("")) {
            HttpManager.getDataPagingInfo(Woactivity_Activity.this, HttpManager.getwoactivityUrl(workOrder.worktype, workOrder.wonum, AccountUtils.getinsertSite(Woactivity_Activity.this), page, 20), new HttpRequestHandler<Results>() {
                @Override
                public void onSuccess(Results results) {
                    Log.i(TAG, "data=" + results);
                }

                @Override
                public void onSuccess(Results results, int currentPage, int showcount) {
                    ArrayList<Woactivity> woactivities = null;
                    if (currentPage == page) {
                        woactivities = JsonUtils.parsingWoactivity(Woactivity_Activity.this, results.getResultlist(),workOrder.wonum);
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
        } else {
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

    private View.OnClickListener backOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (confirmlayout.getVisibility() == View.VISIBLE) {
                final NormalDialog dialog = new NormalDialog(Woactivity_Activity.this);
                dialog.content("确定放弃修改吗?")//
                        .showAnim(mBasIn)//
                        .dismissAnim(mBasOut)//
                        .show();

                dialog.setOnBtnClickL(
                        new OnBtnClickL() {
                            @Override
                            public void onBtnClick() {
                                dialog.dismiss();
                            }
                        },
                        new OnBtnClickL() {
                            @Override
                            public void onBtnClick() {
                                Woactivity_Activity.this.finish();
//                            dialog.dismiss();
                            }
                        });
            } else {
                Woactivity_Activity.this.finish();
            }
        }
    };

    private View.OnClickListener menuImageViewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent;
            intent = new Intent(Woactivity_Activity.this, AddWoactivityActivity.class);
            intent.putExtra("taskid", (woactivityAdapter.woactivityList.size() + 1) * 10);
            startActivityForResult(intent, 1);
        }
    };

    private void setNodataLayout() {
        if (woactivityAdapter.getItemCount() == 0) {
            nodatalayout.setVisibility(View.VISIBLE);
        } else {
            nodatalayout.setVisibility(View.GONE);
        }
    }

    private View.OnClickListener confirmBtnOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = getIntent();
            intent.putExtra("woactivityList",woactivityAdapter.getList());
            Woactivity_Activity.this.setResult(1000, intent);
            Woactivity_Activity.this.finish();
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode) {
            case 1://新增
                if (data != null) {
                    Woactivity woactivity = (Woactivity) data.getSerializableExtra("woactivity");
                    woactivityAdapter.adddate(woactivity);
                    nodatalayout.setVisibility(View.GONE);
                }
                confirmlayout.setVisibility(View.VISIBLE);
                setNodataLayout();
                break;
            case 2://修改
                if (data != null) {
                    Woactivity woactivity = (Woactivity) data.getSerializableExtra("woactivity");
                    int position = data.getIntExtra("position", 0);
                    woactivityAdapter.woactivityList.set(position, woactivity);
                    woactivityAdapter.notifyDataSetChanged();
                }
                confirmlayout.setVisibility(View.VISIBLE);
                setNodataLayout();
                break;
            case 3://本地任务删除
                if (data != null) {
                    int position = data.getIntExtra("position", 0);
                    woactivityAdapter.woactivityList.remove(position);
                    woactivityAdapter.notifyDataSetChanged();
                }
                confirmlayout.setVisibility(View.VISIBLE);
                setNodataLayout();
                break;
            case 4://服务器任务删除操作
                if (data != null) {
                    Woactivity woactivity = (Woactivity) data.getSerializableExtra("woactivity");
                    int position = data.getIntExtra("position", 0);
                    woactivityAdapter.deleteList.add(woactivity);
                    woactivityAdapter.woactivityList.remove(position);
                    woactivityAdapter.notifyDataSetChanged();
                }
                confirmlayout.setVisibility(View.VISIBLE);
                setNodataLayout();
                break;
        }
    }

    @Override
    public void onRefresh() {
        if (!workOrder.isnew&& (woactivityList == null || woactivityList.size() == 0)) {
            page = 1;
            getdata();
        }else {
            refresh_layout.setRefreshing(false);
        }
    }

    @Override
    public void onLoad() {
        if (!workOrder.isnew) {
            page++;
            getdata();
        }
    }
}
