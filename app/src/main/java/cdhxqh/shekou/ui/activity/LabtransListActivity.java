package cdhxqh.shekou.ui.activity;

import android.content.Intent;
import android.os.AsyncTask;
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
import cdhxqh.shekou.model.Labtrans;
import cdhxqh.shekou.model.Woactivity;
import cdhxqh.shekou.model.WorkOrder;
import cdhxqh.shekou.model.WorkResult;
import cdhxqh.shekou.ui.adapter.LabtransAdapter;
import cdhxqh.shekou.ui.adapter.WoactivityAdapter;
import cdhxqh.shekou.ui.widget.SwipeRefreshLayout;
import cdhxqh.shekou.utils.AccountUtils;
import cdhxqh.shekou.utils.MessageUtils;
import cdhxqh.shekou.webserviceclient.AndroidClientService;

/**
 * Created by think on 2016/5/10.
 * 实际员工
 */
public class LabtransListActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, SwipeRefreshLayout.OnLoadListener {
    private String TAG = "LabtransListActivity";
    private ImageView backImageView;
    private TextView titleTextView;
    private ImageView menuImageView;

    LinearLayoutManager layoutManager;
    public RecyclerView recyclerView;
    private LinearLayout nodatalayout;
    private LabtransAdapter labtransAdapter;
    private SwipeRefreshLayout refresh_layout = null;
    private int page = 1;
    /**
     * 工单主表
     **/
    public WorkOrder workOrder;
    private BaseAnimatorSet mBasIn;
    private BaseAnimatorSet mBasOut;
    private LinearLayout confirmlayout;
    /**
     * 确定按钮
     **/
    private Button confirmBtn;

    public ArrayList<Woactivity> woactivityList = new ArrayList<>();
    public ArrayList<Labtrans> labtransList = new ArrayList<>();


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
        labtransList = (ArrayList<Labtrans>) getIntent().getSerializableExtra("labtransList");
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
        titleTextView.setText(getResources().getString(R.string.title_activity_worklabtrans));
        menuImageView.setImageResource(R.drawable.add);
        menuImageView.setVisibility(View.VISIBLE);
        menuImageView.setOnClickListener(menuImageViewOnClickListener);
        confirmlayout.setVisibility(View.GONE);
        confirmBtn.setOnClickListener(confirmBtnOnClickListener);
        layoutManager = new LinearLayoutManager(LabtransListActivity.this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        labtransAdapter = new LabtransAdapter(LabtransListActivity.this);
        recyclerView.setAdapter(labtransAdapter);

        refresh_layout.setColor(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        refresh_layout.setOnRefreshListener(this);
        refresh_layout.setOnLoadListener(this);

        mBasIn = new BounceTopEnter();
        mBasOut = new SlideBottomExit();

        if (workOrder.status != null && (workOrder.status.equals(Constants.STATUS7)
                || workOrder.status.equals(Constants.STATUS18) || workOrder.status.equals(Constants.STATUS10)) || workOrder.status.equals(Constants.STATUS9)) {
            menuImageView.setVisibility(View.VISIBLE);
        } else {
            menuImageView.setVisibility(View.GONE);
        }

        if (!workOrder.isnew && (labtransList == null || labtransList.size() == 0)) {
            refresh_layout.setRefreshing(true);
            getdata();
            refresh_layout.setRefreshing(true);
        } else {
            if (labtransList != null && labtransList.size() != 0) {
                labtransAdapter.update(labtransList, true);
            } else {
                nodatalayout.setVisibility(View.VISIBLE);
            }
        }
    }

    private void getdata() {
        if (workOrder.wonum != null && !workOrder.wonum.equals("")) {
            HttpManager.getDataPagingInfo(LabtransListActivity.this, HttpManager.getlabtransUrl(workOrder.worktype, workOrder.wonum, page, 20), new HttpRequestHandler<Results>() {
                @Override
                public void onSuccess(Results results) {
                    Log.i(TAG, "data=" + results);
                }

                @Override
                public void onSuccess(Results results, int currentPage, int showcount) {
                    ArrayList<Labtrans> labtranses = null;
                    if (currentPage == page) {
                        labtranses = JsonUtils.parsingLabtrans(LabtransListActivity.this, results.getResultlist(), workOrder.wonum);
                    }
                    addListData(labtranses);
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

    private void addListData(ArrayList<Labtrans> list) {
        if (nodatalayout.getVisibility() == View.VISIBLE) {
            nodatalayout.setVisibility(View.GONE);
        }
        if (page == 1 && labtransAdapter.getItemCount() != 0) {
            labtransAdapter = new LabtransAdapter(LabtransListActivity.this);
            recyclerView.setAdapter(labtransAdapter);
        }
        if ((list == null || list.size() == 0) && page == 1) {
            nodatalayout.setVisibility(View.VISIBLE);
        } else {
            labtransAdapter.adddate(list);
        }
    }

    private View.OnClickListener menuImageViewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent;
            intent = new Intent(LabtransListActivity.this, AddLabtransActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("workOrder", workOrder);
            bundle.putSerializable("woactivityList", woactivityList);
            intent.putExtras(bundle);
            startActivityForResult(intent, 1);
        }
    };

    private View.OnClickListener backOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (confirmlayout.getVisibility() == View.VISIBLE) {
                final NormalDialog dialog = new NormalDialog(LabtransListActivity.this);
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
                                LabtransListActivity.this.finish();
//                            dialog.dismiss();
                            }
                        });
            } else {
                LabtransListActivity.this.finish();
            }
        }
    };

    private void setNodataLayout() {
        if (labtransAdapter.getItemCount() == 0) {
            nodatalayout.setVisibility(View.VISIBLE);
        } else {
            nodatalayout.setVisibility(View.GONE);
        }
    }

    private View.OnClickListener confirmBtnOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
//            Intent intent = getIntent();
//            intent.putExtra("labtransList", labtransAdapter.getList());
//            LabtransListActivity.this.setResult(2000, intent);
//            LabtransListActivity.this.finish();
            showProgressDialog("数据提交中...");
            startAsyncTask();


        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode) {
            case 1://新增
                if (data != null) {
                    Labtrans labtrans = (Labtrans) data.getSerializableExtra("labtrans");
                    labtransAdapter.adddate(labtrans);
                    Log.i(TAG, "optiontype=" + labtrans.getOptiontype());
                    nodatalayout.setVisibility(View.GONE);
                }
                confirmlayout.setVisibility(View.VISIBLE);
                setNodataLayout();
                break;
            case 2://修改
                if (data != null) {
                    Labtrans labtrans = (Labtrans) data.getSerializableExtra("labtrans");
                    int position = data.getIntExtra("position", 0);
                    labtransAdapter.labtransList.set(position, labtrans);
                    labtransAdapter.notifyDataSetChanged();
                }
                confirmlayout.setVisibility(View.VISIBLE);
                setNodataLayout();
                break;
            case 3://本地任务删除
                if (data != null) {
                    int position = data.getIntExtra("position", 0);
                    labtransAdapter.labtransList.remove(position);
                    labtransAdapter.notifyDataSetChanged();
                }
                confirmlayout.setVisibility(View.VISIBLE);
                setNodataLayout();
                break;
            case 4://服务器任务删除操作
                if (data != null) {
                    Labtrans labtrans = (Labtrans) data.getSerializableExtra("labtrans");
                    int position = data.getIntExtra("position", 0);
                    labtransAdapter.deleteList.add(labtrans);
                    labtransAdapter.labtransList.remove(position);
                    labtransAdapter.notifyDataSetChanged();
                }
                confirmlayout.setVisibility(View.VISIBLE);
                setNodataLayout();
                break;
        }
    }

    @Override
    public void onRefresh() {
        if (!workOrder.isnew && (labtransList == null || labtransList.size() == 0)) {
            refresh_layout.setRefreshing(true);
            getdata();
        } else {
            if (labtransList != null && labtransList.size() != 0) {
                labtransAdapter.update(labtransList, true);
            }
        }
    }

    @Override
    public void onLoad() {
        if (!workOrder.isnew) {
            page++;
            getdata();
        }
    }


    /**
     * 提交数据*
     */
    private void startAsyncTask() {
        String updataInfo = null;
        updataInfo = JsonUtils.WorkToJson(workOrder, null, labtransAdapter.getList(), null);
        Log.i(TAG, "updataInfo=" + updataInfo);
        final String finalUpdataInfo = updataInfo;
        new AsyncTask<String, String, WorkResult>() {
            @Override
            protected WorkResult doInBackground(String... strings) {
                WorkResult reviseresult = AndroidClientService.UpdateWO(finalUpdataInfo, AccountUtils.getpersonId(LabtransListActivity.this), AccountUtils.getIpAddress(LabtransListActivity.this) + Constants.WORK_URL);
                return reviseresult;
            }

            @Override
            protected void onPostExecute(WorkResult workResult) {
                super.onPostExecute(workResult);
                if (workResult == null) {
                    MessageUtils.showMiddleToast(LabtransListActivity.this, "保存失败");
                } else if (workResult.errorMsg.equals("成功!")) {
                    MessageUtils.showMiddleToast(LabtransListActivity.this, "保存成功");
                } else {
                    MessageUtils.showMiddleToast(LabtransListActivity.this, workResult.errorMsg);
                }
                closeProgressDialog();
            }
        }.execute();

    }


}
