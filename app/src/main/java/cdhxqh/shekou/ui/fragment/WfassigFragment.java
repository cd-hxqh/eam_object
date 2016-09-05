package cdhxqh.shekou.ui.fragment;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
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
import cdhxqh.shekou.model.Inventory;
import cdhxqh.shekou.model.Wfassignment;
import cdhxqh.shekou.ui.activity.InventoryActivity;
import cdhxqh.shekou.ui.activity.Wfassig_DetailsActivity;
import cdhxqh.shekou.ui.adapter.BaseQuickAdapter;
import cdhxqh.shekou.ui.adapter.InventoryAdapter;
import cdhxqh.shekou.ui.adapter.WfassigAdapter;
import cdhxqh.shekou.ui.widget.SwipeRefreshLayout;
import cdhxqh.shekou.utils.AccountUtils;


/**
 * 待办任务*
 */
public class WfassigFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, SwipeRefreshLayout.OnLoadListener {
    private static final String TAG = "WfassigFragment";
    private static final int RESULT_ADD_TOPIC = 100;


    /**
     * 搜索按钮*
     */
    private EditText searchEditText;

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


    /**
     * 搜索值*
     */
    private String vlaue = "";

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
        ;
        mSwipeLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_container);
        mSwipeLayout.setColor(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);


        notLinearLayout = (LinearLayout) view.findViewById(R.id.have_not_data_id);

        mSwipeLayout.setOnRefreshListener(this);
        mSwipeLayout.setOnLoadListener(this);
        mSwipeLayout.setRefreshing(false);


        searchEditText = (EditText) view.findViewById(R.id.search_edit);
    }


    /**
     * 设置事件监听*
     */


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle args = getArguments();
        mSwipeLayout.setRefreshing(true);
        getItemList(vlaue, page);
        initView();
    }


    /**
     * 初始化界面组件*
     */
    private void initView() {

        SpannableString msp = new SpannableString("XX搜索");
        Drawable drawable = getResources().getDrawable(R.drawable.ic_search);
        msp.setSpan(new ImageSpan(drawable), 0, 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        searchEditText.setHint(msp);

        searchEditText.setOnEditorActionListener(searchEditTextOnEditorActionListener);
    }


    /**
     * 获取库存查询信息*
     * --分页
     */

    private void getItemList(String value, int page) {
        HttpManager.getDataPagingInfo(getActivity(), HttpManager.getwfassignmentUrl(AccountUtils.getpersonId(getActivity()), value, page, 20), new HttpRequestHandler<Results>() {
            @Override
            public void onSuccess(Results results) {
                Log.i(TAG, "data=" + results);
            }

            @Override
            public void onSuccess(Results results, int totalPages, int currentPage) {
                List<Wfassignment> item = JsonUtils.parsingWfassignment(getActivity(), results.getResultlist());
                if (item != null || item.size() != 0) {
                    for (int i = 0; i < item.size(); i++) {
                        items.add(item.get(i));
                    }
                }

                mSwipeLayout.setLoading(false);
                mSwipeLayout.setRefreshing(false);
                if (items == null || items.size() == 0) {
                    notLinearLayout.setVisibility(View.VISIBLE);
                } else {
                    initAdapter(item);
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
        getItemList(vlaue, page);
    }


    @Override
    public void onRefresh() {
        page = 1;
        getItemList(vlaue,page);
    }


    private TextView.OnEditorActionListener searchEditTextOnEditorActionListener = new TextView.OnEditorActionListener() {
        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                // 先隐藏键盘
                ((InputMethodManager) searchEditText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE))
                        .hideSoftInputFromWindow(
                                getActivity().getCurrentFocus()
                                        .getWindowToken(),
                                InputMethodManager.HIDE_NOT_ALWAYS);
                vlaue = searchEditText.getText().toString();
                wfassigAdapter.removeAll(items);
                items = new ArrayList<Wfassignment>();
                notLinearLayout.setVisibility(View.GONE);
                mSwipeLayout.setRefreshing(true);
                page = 1;
                getItemList(vlaue, page);
                return true;
            }
            return false;
        }


    };


    /**
     * 获取数据*
     */
    private void initAdapter(final List<Wfassignment> list) {
        wfassigAdapter = new WfassigAdapter(getActivity(), R.layout.list_item, list);
        mRecyclerView.setAdapter(wfassigAdapter);
        wfassigAdapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getActivity(), Wfassig_DetailsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable("wfassignment", list.get(position));
                intent.putExtras(bundle);
                getActivity().startActivity(intent);
            }
        });
    }

}
