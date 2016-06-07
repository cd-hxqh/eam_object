package cdhxqh.shekou.ui.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;

import cdhxqh.shekou.R;
import cdhxqh.shekou.api.HttpManager;
import cdhxqh.shekou.api.HttpRequestHandler;
import cdhxqh.shekou.api.JsonUtils;
import cdhxqh.shekou.bean.Results;
import cdhxqh.shekou.model.Labtrans;
import cdhxqh.shekou.model.WorkOrder;
import cdhxqh.shekou.model.Wplabor;
import cdhxqh.shekou.ui.adapter.LabtransAdapter;
import cdhxqh.shekou.ui.adapter.WplaborAdapter;
import cdhxqh.shekou.ui.widget.SwipeRefreshLayout;

/**
 * 工单员工的fragment
 */
@SuppressLint("ValidFragment")
public class LabtransFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, SwipeRefreshLayout.OnLoadListener {
    private static String TAG = "WplaborFragment";

    LinearLayoutManager layoutManager;
    public RecyclerView recyclerView;
    private LinearLayout nodatalayout;
    private LabtransAdapter labtransAdapter;
    private SwipeRefreshLayout refresh_layout = null;
    private int page = 1;
    private WorkOrder workOrder;

    public LabtransFragment() {
    }

    public LabtransFragment(WorkOrder workOrder) {
        this.workOrder = workOrder;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_task, container,
                false);

        findByIdView(view);
        initView();
        return view;
    }

    /**
     * 初始化界面组件*
     */
    private void findByIdView(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView_id);
        refresh_layout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_container);
        nodatalayout = (LinearLayout) view.findViewById(R.id.have_not_data_id);
    }

    private void initView() {
        layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        labtransAdapter = new LabtransAdapter(getActivity());
        recyclerView.setAdapter(labtransAdapter);

        refresh_layout.setColor(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        refresh_layout.setRefreshing(true);
        refresh_layout.setOnRefreshListener(this);
        refresh_layout.setOnLoadListener(this);

        getdata();
    }

    private void getdata() {
        HttpManager.getDataPagingInfo(getActivity(), HttpManager.getlabtransUrl(workOrder.worktype,workOrder.wonum, page, 20), new HttpRequestHandler<Results>() {
            @Override
            public void onSuccess(Results results) {
                Log.i(TAG, "data=" + results);
            }

            @Override
            public void onSuccess(Results results, int currentPage, int showcount) {
                ArrayList<Labtrans> labtranses = null;
                if (currentPage == page) {
                    labtranses = JsonUtils.parsingLabtrans(getActivity(), results.getResultlist(),workOrder.wonum);
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
    }

    private void addListData(ArrayList<Labtrans> list) {
        if (nodatalayout.getVisibility() == View.VISIBLE) {
            nodatalayout.setVisibility(View.GONE);
        }
        if (page == 1 && labtransAdapter.getItemCount() != 0) {
//            labtransAdapter = new LabtransAdapter(getActivity());
            recyclerView.setAdapter(labtransAdapter);
        }
        if ((list == null || list.size() == 0) && page == 1) {
            nodatalayout.setVisibility(View.VISIBLE);
        } else {
            labtransAdapter.adddate(list);
        }
    }

    //下拉刷新触发事件
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
