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
import cdhxqh.shekou.model.WorkOrder;
import cdhxqh.shekou.model.Wplabor;
import cdhxqh.shekou.ui.adapter.WplaborAdapter;
import cdhxqh.shekou.ui.widget.SwipeRefreshLayout;

/**
 * 工单员工的fragment
 */
@SuppressLint("ValidFragment")
public class WplaborFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener,SwipeRefreshLayout.OnLoadListener{
    private static String TAG = "WplaborFragment";

    LinearLayoutManager layoutManager;
    public RecyclerView recyclerView;
    private LinearLayout nodatalayout;
    private WplaborAdapter wplaborAdapter;
    private SwipeRefreshLayout refresh_layout = null;
    private int page = 1;
    private WorkOrder workOrder;
    public WplaborFragment() {
    }
    public WplaborFragment(WorkOrder workOrder) {
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

    private void initView(){
        layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        wplaborAdapter = new WplaborAdapter(getActivity());
        recyclerView.setAdapter(wplaborAdapter);

        refresh_layout.setColor(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        refresh_layout.setRefreshing(true);
        refresh_layout.setOnRefreshListener(this);
        refresh_layout.setOnLoadListener(this);

        getdata();
    }

    private void getdata(){
        HttpManager.getDataPagingInfo(getActivity(), HttpManager.getwplaborUrl(workOrder.worktype, page, 20), new HttpRequestHandler<Results>() {
            @Override
            public void onSuccess(Results results) {
                Log.i(TAG, "data=" + results);
            }

            @Override
            public void onSuccess(Results results, int totalPages, int currentPage) {
                ArrayList<Wplabor> wplabors = JsonUtils.parsingWplabor(getActivity(), results.getResultlist());
                addListData(wplabors);
                refresh_layout.setRefreshing(false);
                refresh_layout.setLoading(false);
            }

            @Override
            public void onFailure(String error) {
                refresh_layout.setRefreshing(false);
                nodatalayout.setVisibility(View.VISIBLE);
            }
        });
    }

    private void addListData(ArrayList<Wplabor> list){
        if(nodatalayout.getVisibility()==View.VISIBLE){
            nodatalayout.setVisibility(View.GONE);
        }
        if(page==1&&wplaborAdapter.getItemCount()!=0){
            wplaborAdapter = new WplaborAdapter(getActivity());
            recyclerView.setAdapter(wplaborAdapter);
        }
        if(list==null||list.size()==0){
            nodatalayout.setVisibility(View.VISIBLE);
        }else {
            wplaborAdapter.update(list,true);
        }
    }

    //下拉刷新触发事件
    @Override
    public void onRefresh() {
        page = 1;
        getdata();
    }

    @Override
    public void onLoad(){
        page++;
        getdata();
    }
}
