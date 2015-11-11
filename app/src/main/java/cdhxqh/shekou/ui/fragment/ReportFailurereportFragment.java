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
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cdhxqh.shekou.R;
import cdhxqh.shekou.api.HttpManager;
import cdhxqh.shekou.api.HttpRequestHandler;
import cdhxqh.shekou.api.JsonUtils;
import cdhxqh.shekou.bean.Results;
import cdhxqh.shekou.model.Failurereport;
import cdhxqh.shekou.model.Woactivity;
import cdhxqh.shekou.model.WorkOrder;
import cdhxqh.shekou.ui.adapter.WoactivityAdapter;
import cdhxqh.shekou.ui.widget.SwipeRefreshLayout;

/**
 * 故障汇报的fragment
 */
@SuppressLint("ValidFragment")
public class ReportFailurereportFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private static String TAG = "ReportFailurereportFragment";

    private TextView udgzzjgdm;
    private TextView udgzlbdm;
    private TextView failurecode;
    private TextView faildate;
    private EditText bz;
    private EditText bzdate;
    private SwipeRefreshLayout refresh_layout = null;
    private WorkOrder workOrder;

    public ReportFailurereportFragment() {
    }

    public ReportFailurereportFragment(WorkOrder workOrder) {
        this.workOrder = workOrder;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_failurereport, container,
                false);

        findByIdView(view);
        initView();
        return view;
    }

    /**
     * 初始化界面组件*
     */
    private void findByIdView(View view) {
        udgzzjgdm = (TextView) view.findViewById(R.id.work_failurereport_udgzzjgdm);
        udgzlbdm = (TextView) view.findViewById(R.id.work_failurereport_udgzlbdm);
        failurecode = (TextView) view.findViewById(R.id.work_failurereport_failurecode);
        faildate = (TextView) view.findViewById(R.id.work_failurereport_faildate);
        bz = (EditText) view.findViewById(R.id.work_failurereport_bz);
        bzdate = (EditText) view.findViewById(R.id.work_failurereport_bzdate);
        refresh_layout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_container);
    }

    private void initView() {

        refresh_layout.setColor(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        refresh_layout.setRefreshing(true);
        refresh_layout.setOnRefreshListener(this);

        getdata();
    }

    private void getdata() {
        HttpManager.getData(getActivity(), HttpManager.getfailurereportUrl(workOrder.worktype, workOrder.wonum), new HttpRequestHandler<Results>() {
            @Override
            public void onSuccess(Results results) {
                Log.i(TAG, "data=" + results);
            }

            @Override
            public void onSuccess(Results results, int currentPage, int showcount) {
                ArrayList<Failurereport> failurereports = JsonUtils.parsingFailurereport(getActivity(), results.getResultlist());
                addListData(failurereports);
                refresh_layout.setRefreshing(false);
                refresh_layout.setLoading(false);
            }

            @Override
            public void onFailure(String error) {
                refresh_layout.setRefreshing(false);
                refresh_layout.setLoading(false);
            }
        });
    }

    private void addListData(ArrayList<Failurereport> list) {
        if (list.size() > 0) {

        }
    }

    //下拉刷新触发事件
    @Override
    public void onRefresh() {
        getdata();
    }
}
