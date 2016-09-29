package cdhxqh.shekou.ui.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
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

import com.flyco.animation.BaseAnimatorSet;
import com.flyco.animation.BounceEnter.BounceTopEnter;
import com.flyco.animation.SlideExit.SlideBottomExit;
import com.flyco.dialog.listener.OnBtnEditClickL;
import com.flyco.dialog.widget.NormalEditTextDialog;

import java.util.ArrayList;
import java.util.List;

import cdhxqh.shekou.R;
import cdhxqh.shekou.api.HttpManager;
import cdhxqh.shekou.api.HttpRequestHandler;
import cdhxqh.shekou.api.JsonUtils;
import cdhxqh.shekou.bean.Results;
import cdhxqh.shekou.config.Constants;
import cdhxqh.shekou.model.Wfassignment;
import cdhxqh.shekou.ui.activity.InvoiceDetailsActivity;
import cdhxqh.shekou.ui.activity.PoDetailsActivity;
import cdhxqh.shekou.ui.activity.WaiXiePoDetailsActivity;
import cdhxqh.shekou.ui.activity.Work_detailsActivity;
import cdhxqh.shekou.ui.activity.WuZiPRDetailsActivity;
import cdhxqh.shekou.ui.adapter.BaseQuickAdapter;
import cdhxqh.shekou.ui.adapter.WfassigAdapter;
import cdhxqh.shekou.ui.widget.SwipeRefreshLayout;
import cdhxqh.shekou.utils.AccountUtils;
import cdhxqh.shekou.utils.MessageUtils;
import cdhxqh.shekou.webserviceclient.AndroidClientService;


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


    private BaseAnimatorSet mBasIn;
    private BaseAnimatorSet mBasOut;

    private ProgressDialog mProgressDialog;


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

        mBasIn = new BounceTopEnter();
        mBasOut = new SlideBottomExit();
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
        wfassigAdapter = new WfassigAdapter(getActivity(), R.layout.sp_list_item, list);
        mRecyclerView.setAdapter(wfassigAdapter);
        wfassigAdapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                Wfassignment wfassignment =list.get(position);
                    String ownerid = wfassignment.ownerid;
                    String  ownertable = wfassignment.ownertable;
                    String  processname = wfassignment.processname;

                  if(ownertable.equals(Constants.INVOICE_NAME)){ //外协付款申请
                      Intent intent = new Intent(getActivity(), InvoiceDetailsActivity.class);
                      Bundle bundle = new Bundle();
                      bundle.putString("ownerid",ownerid);
                      bundle.putString("ownertable",ownertable);
                      bundle.putString("processname",processname);
                      intent.putExtras(bundle);
                      getActivity().startActivity(intent);
                  }

               else if(ownertable.equals(Constants.PO_NAME)&&processname.equals("UDPO")){  //非年度采购订单
                    Intent intent = new Intent(getActivity(), PoDetailsActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("ownerid",ownerid);
                    bundle.putString("ownertable",ownertable);
                    bundle.putString("processname",processname);
                    intent.putExtras(bundle);
                    getActivity().startActivity(intent);
                }
               else if(ownertable.equals(Constants.PO_NAME)&&processname.equals("UDOSPO")){  //外协服务采购订单
                    Intent intent = new Intent(getActivity(), WaiXiePoDetailsActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("ownerid",ownerid);
                    bundle.putString("ownertable",ownertable);
                    bundle.putString("processname",processname);
                    intent.putExtras(bundle);
                    getActivity().startActivity(intent);
                }
               else if(ownertable.equals(Constants.PR_NAME)&&processname.equals("UDEGPR")){  //物资采购申请
                    Intent intent = new Intent(getActivity(), WuZiPRDetailsActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("ownerid",ownerid);
                    bundle.putString("ownertable",ownertable);
                    bundle.putString("processname",processname);
                    intent.putExtras(bundle);
                    getActivity().startActivity(intent);
                }
               else if(ownertable.equals(Constants.WORKORDER_NAME)){  //工单管理
                      Intent intent = new Intent(getActivity(), Work_detailsActivity.class);
                      Bundle bundle = new Bundle();
                      if(processname.equals("UDWO03")){ //预防性维护工单
                          bundle.putString("worktype","PM");
                      }else if (processname.equals("UDWO02")){ //故障工单
                          bundle.putString("worktype","CM");
                      }else if (processname.equals("UDWO04")){ //状态维修工单
                          bundle.putString("worktype","SR");
                      }else if (processname.equals("UDWO05")){ //项目工单
                          bundle.putString("worktype","PJ");
                      }else if (processname.equals("UDWO06")){ //可维护备件工单
                          bundle.putString("worktype","RS");
                      }else if (processname.equals("UDWO07")){ //事故工单
                          bundle.putString("worktype","EV");
                      }else if (processname.equals("UDWO08")){ //抢修工单
                          bundle.putString("worktype","EM");
                      }

                    bundle.putString("ownerid",ownerid);
                    bundle.putString("ownertable",ownertable);
                    bundle.putString("processname",processname);
                    bundle.putInt("mark",1);


                    intent.putExtras(bundle);
                    getActivity().startActivity(intent);
                }else{
                      MessageUtils.showMiddleToast(getActivity(),"该类型任务尚在开发中...");
                  }


                wfassigAdapter.setOnClickListener(new WfassigAdapter.OnClickListener() {
                    @Override
                    public void cOnClickListener(Wfassignment wfassignment) {
                        Log.i(TAG,"点击一下"+wfassignment.wfassignmentid);
                        MaterialDialogOneBtn1(wfassignment);
                    }
                });



//                Intent intent = new Intent(getActivity(), Wfassig_DetailsActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putParcelable("wfassignment", list.get(position));
//                intent.putExtras(bundle);
//                getActivity().startActivity(intent);
            }
        });
    }



    private void MaterialDialogOneBtn1(final Wfassignment wfassignment) {//审批工作流
        final NormalEditTextDialog dialog = new NormalEditTextDialog(getActivity());
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialog.isTitleShow(true);//
        dialog.title("审批工作流");
        dialog.btnNum(3)
                .content("通过")//
                .btnText("取消", "通过", "不通过")//
                .showAnim(mBasIn)//
                .dismissAnim(mBasOut)
                .show();

        dialog.setOnBtnClickL(
                new OnBtnEditClickL() {//取消
                    @Override
                    public void onBtnClick(String text) {

                        dialog.dismiss();
                    }
                },
                new OnBtnEditClickL() {//通过
                    @Override
                    public void onBtnClick(String text) {
                        wfgoon(wfassignment, "1", text);
                        dialog.dismiss();
                    }
                },
                new OnBtnEditClickL() {//不通过
                    @Override
                    public void onBtnClick(String text) {
                        wfgoon(wfassignment, "0", text);
                        dialog.dismiss();
                    }
                }
        );
    }


    /**
     * 审批工作流
     *
     * @param zx
     */
    private void wfgoon(final Wfassignment wfassignment, final String zx, final String desc) {
        mProgressDialog = ProgressDialog.show(getActivity(), null,
                getString(R.string.inputing), true, true);
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.setCancelable(false);
        new AsyncTask<String, String, String>() {
            @Override
            protected String doInBackground(String... strings) {
                String result = AndroidClientService.approve(getActivity(), wfassignment.processname, wfassignment.ownertable, wfassignment.ownerid, wfassignment.ownertable + "ID", AccountUtils.getpersonId(getActivity()), zx, desc);

                Log.i(TAG, "result=" + result);
                return result;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                if (s == null || s.equals("")) {
                    MessageUtils.showMiddleToast(getActivity(), "审批失败");
                } else {
                    MessageUtils.showMiddleToast(getActivity(), "审批成功");

                }
                mProgressDialog.dismiss();
            }
        }.execute();
    }













}
