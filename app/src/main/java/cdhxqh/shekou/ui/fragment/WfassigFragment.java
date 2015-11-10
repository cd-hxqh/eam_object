package cdhxqh.shekou.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;
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
import cdhxqh.shekou.model.Wfassignment;
import cdhxqh.shekou.ui.adapter.WfassigAdapter;
import cdhxqh.shekou.ui.widget.SwipeRefreshLayout;


/**
 * 待办事项列表*
 */
public class WfassigFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, SwipeRefreshLayout.OnLoadListener {
    private static final String TAG = "WfassigFragment";
    private static final int RESULT_ADD_TOPIC = 100;
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

    WfassigAdapter wfassigAdapter;

    private int page = 1;

    ArrayList<Wfassignment> items = new ArrayList<Wfassignment>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item, container,
                false);

        findByIdView(view);


        return view;
    }

    /**
     * 初始化界面组件*
     */
    private void findByIdView(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.list_topics);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        wfassigAdapter = new WfassigAdapter(getActivity());
        mRecyclerView.setAdapter(wfassigAdapter);
        mSwipeLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_container);
        mSwipeLayout.setColor(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);


        notLinearLayout = (LinearLayout) view.findViewById(R.id.have_not_data_id);

        mSwipeLayout.setOnRefreshListener(this);
        mSwipeLayout.setOnLoadListener(this);
        mSwipeLayout.setRefreshing(false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle args = getArguments();
        mSwipeLayout.setRefreshing(true);
        getItemList();
    }


    /**
     * 获取待办事项信息*
     * --分页
     */

    private void getItemList() {
        HttpManager.getDataPagingInfo(getActivity(), HttpManager.getwfassignmentUrl(page, 20), new HttpRequestHandler<Results>() {
            @Override
            public void onSuccess(Results results) {
                Log.i(TAG, "data=" + results);
            }

            @Override
            public void onSuccess(Results results, int totalPages, int currentPage) {
                ArrayList<Wfassignment> item = JsonUtils.parsingWfassignment(getActivity(), results.getResultlist());
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
                    wfassigAdapter.update(items, true);
                    wfassigAdapter.notifyDataSetChanged();
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
        getItemList();
    }

    @Override
    public void onRefresh() {
        mSwipeLayout.setRefreshing(false);
    }
}
