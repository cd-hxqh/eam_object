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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cdhxqh.shekou.Dao.AlndomainDao;
import cdhxqh.shekou.Dao.AssetDao;
import cdhxqh.shekou.Dao.FailurelistDao;
import cdhxqh.shekou.Dao.JobPlanDao;
import cdhxqh.shekou.Dao.LaborDao;
import cdhxqh.shekou.Dao.LaborcraftrateDao;
import cdhxqh.shekou.Dao.PersonDao;
import cdhxqh.shekou.Dao.PmDao;
import cdhxqh.shekou.Dao.ProjapprDao;
import cdhxqh.shekou.Dao.UdevDao;
import cdhxqh.shekou.R;
import cdhxqh.shekou.config.Constants;
import cdhxqh.shekou.model.Alndomain;
import cdhxqh.shekou.model.Assets;
import cdhxqh.shekou.model.Failurelist;
import cdhxqh.shekou.model.JobPlan;
import cdhxqh.shekou.model.Labor;
import cdhxqh.shekou.model.Laborcraftrate;
import cdhxqh.shekou.model.Option;
import cdhxqh.shekou.model.Person;
import cdhxqh.shekou.model.Pm;
import cdhxqh.shekou.model.Projappr;
import cdhxqh.shekou.model.Udev;
import cdhxqh.shekou.ui.adapter.OptionAdapter;
import cdhxqh.shekou.ui.widget.SwipeRefreshLayout;

/**
 * Created by think on 2016/5/16.
 * 通用选项查询界面
 */
public class OptionActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, SwipeRefreshLayout.OnLoadListener {
    private static final String TAG = "OptionActivity";
    /**
     * 标题*
     */
    private TextView titlename;
    /**
     * 返回*
     */
    private ImageView backImage;

    LinearLayoutManager layoutManager;
    public RecyclerView recyclerView;
    /**暂无数据**/
    private LinearLayout nodatalayout;
    /**同步数据**/
    private Button synchronousbtn;
    private OptionAdapter optionAdapter;
    private EditText search;
    private String searchText = "";
    public int requestCode;
    public String parent;
    ArrayList<Option> list;

    private String CraftSearch;

    private SwipeRefreshLayout refresh_layout = null;
    private int page = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);

        getIntentData();
        findViewById();
        initView();
    }

    private void getIntentData() {
        requestCode = getIntent().getIntExtra("requestCode", 0);
//        if (requestCode == Constants.LABORCRAFTRATE) {
//            CraftSearch = getIntent().getStringExtra("craft");
//        }
    }

    @Override
    protected void findViewById() {
        titlename = (TextView) findViewById(R.id.title_name);
        backImage = (ImageView) findViewById(R.id.title_back_id);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView_id);
        nodatalayout = (LinearLayout) findViewById(R.id.have_not_data_id);
        synchronousbtn = (Button) findViewById(R.id.synchronous_btn);
        search = (EditText) findViewById(R.id.search_edit);
        refresh_layout = (SwipeRefreshLayout) this.findViewById(R.id.swipe_container);
    }

    @Override
    protected void initView() {
        titlename.setText(R.string.activity_option_title);

        backImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        optionAdapter = new OptionAdapter(this);
        recyclerView.setAdapter(optionAdapter);

        refresh_layout.setColor(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        refresh_layout.setRefreshing(false);
        refresh_layout.setOnRefreshListener(this);
        refresh_layout.setOnLoadListener(this);
        setSearchEdit();
        getData(searchText);

        synchronousbtn.setOnClickListener(synchronousbtnOnClickListener);
    }

    /**同步数据**/
    private View.OnClickListener synchronousbtnOnClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent =new Intent();
            intent.setClass(OptionActivity.this,DownloadActivity.class);
            startActivityForResult(intent,1000);
        }
    };


    @Override
    protected void onActivityResult(int requestCode1, int resultCode, Intent data) {
        super.onActivityResult(requestCode1, resultCode, data);
        switch (requestCode1) {
            case 1000:
                Log.i(TAG,"requestCode="+requestCode1);
                getData(searchText);
                break;
        }
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
                    ((InputMethodManager) search.getContext().getSystemService(Context.INPUT_METHOD_SERVICE))
                            .hideSoftInputFromWindow(
                                    OptionActivity.this.getCurrentFocus()
                                            .getWindowToken(),
                                    InputMethodManager.HIDE_NOT_ALWAYS);
                    searchText = search.getText().toString();
                    optionAdapter = new OptionAdapter(OptionActivity.this);
                    recyclerView.setAdapter(optionAdapter);
                    getData(searchText);
                    return true;
                }
                return false;
            }
        });
    }

    private void getData(String searchText) {
        list = new ArrayList<Option>();
        Option option;
        Log.i(TAG,"requestCode="+requestCode);
        switch (requestCode) {
            case Constants.ASSETCODE:
                List<Assets> assets;
                assets = new AssetDao(OptionActivity.this).queryByCount(page, searchText);
                for (int i = 0; i < assets.size(); i++) {
                    option = new Option();
                    option.setName(assets.get(i).assetnum);
                    option.setDescription(assets.get(i).description);
//                    option.setValue(assets.get(i).location);
                    list.add(option);
                }
                break;
            case Constants.JOBPLANCODE:
                List<JobPlan> jobPlans;
                if (getIntent().hasExtra("AssetIsChoose")&&getIntent().getBooleanExtra("AssetIsChoose",false)) {
                    jobPlans = new JobPlanDao(OptionActivity.this).queryByCount1(page, searchText);
                }else {
                    jobPlans = new JobPlanDao(OptionActivity.this).queryByCount(page, searchText);
                }
                for (int i = 0; i < jobPlans.size(); i++) {
                    option = new Option();
                    option.setName(jobPlans.get(i).jpnum);
                    option.setDescription(jobPlans.get(i).description);
                    list.add(option);
                }
                break;
            case Constants.PERSONCODE:
                List<Person> persons;
                persons = new PersonDao(OptionActivity.this).queryByCount(page, searchText);
                for (int i = 0; i < persons.size(); i++) {
                    option = new Option();
                    option.setName(persons.get(i).personid);
                    option.setDescription(persons.get(i).displayname);
                    list.add(option);
                }
                break;
            case Constants.LABORCODE:
                List<Labor> labors;
                labors = new LaborDao(OptionActivity.this).queryByCount(page, searchText);
                for (int i = 0; i < labors.size(); i++) {
                    option = new Option();
                    option.setName(labors.get(i).laborcode);
                    option.setDescription(labors.get(i).displayname);
                    list.add(option);
                }
                break;
            case Constants.LABORCODE1:
                List<Labor> labors1;
                if (getIntent().hasExtra("udqxbz")){
                    String udeq1 = getIntent().getStringExtra("udqxbz").substring(0,2);
                    labors1 = new LaborDao(OptionActivity.this).queryByCount1(page, searchText, udeq1);
                }else {
                    labors1 = new LaborDao(OptionActivity.this).queryByCount(page, searchText);
                }
                for (int i = 0; i < labors1.size(); i++) {
                    option = new Option();
                    option.setName(labors1.get(i).laborcode);
                    option.setDescription(labors1.get(i).displayname);
                    list.add(option);
                }
                break;
            case Constants.LABORCODE2:
                List<Labor> labors2;
                if (getIntent().hasExtra("udqxbz")){
                    String udeq1 = getIntent().getStringExtra("udqxbz").substring(0,2);
                    labors2 = new LaborDao(OptionActivity.this).queryByCount1(page, searchText, udeq1);
                }else {
                    labors2 = new LaborDao(OptionActivity.this).queryByCount(page, searchText);
                }
                for (int i = 0; i < labors2.size(); i++) {
                    option = new Option();
                    option.setName(labors2.get(i).laborcode);
                    option.setDescription(labors2.get(i).displayname);
                    list.add(option);
                }
                break;
            case Constants.LABORCODE3:
                List<Labor> labors3;
                if (getIntent().hasExtra("udqxbz")){
                    String udeq1 = getIntent().getStringExtra("udqxbz").substring(0,2);
                    labors3 = new LaborDao(OptionActivity.this).queryByCount1(page, searchText, udeq1);
                }else {
                    labors3 = new LaborDao(OptionActivity.this).queryByCount(page, searchText);
                }
                for (int i = 0; i < labors3.size(); i++) {
                    option = new Option();
                    option.setName(labors3.get(i).laborcode);
                    option.setDescription(labors3.get(i).displayname);
                    list.add(option);
                }
                break;
            case Constants.ALNDOMAINCODE:
                List<Alndomain> alndomains;
                alndomains = new AlndomainDao(OptionActivity.this).queryByCount(page, searchText);
                for (int i = 0; i < alndomains.size(); i++) {
                    option = new Option();
                    option.setName(alndomains.get(i).value);
                    option.setDescription(alndomains.get(i).description);
                    list.add(option);
                }
                break;
            case Constants.UDEVCODE:
                List<Udev> udevs;
                udevs = new UdevDao(OptionActivity.this).queryByCount(page, searchText);
                for (int i = 0; i < udevs.size(); i++) {
                    option = new Option();
                    option.setName(udevs.get(i).evnum);
                    option.setDescription(udevs.get(i).description);
                    list.add(option);
                }
                break;
            case Constants.PROJAPPR:
                List<Projappr> projapprs;
                projapprs = new ProjapprDao(OptionActivity.this).queryByCount(page, searchText);
                for (int i = 0; i < projapprs.size(); i++) {
                    option = new Option();
                    option.setName(projapprs.get(i).prjnum);
                    option.setDescription(projapprs.get(i).description);
                    option.setValue(projapprs.get(i).projapprnum);
                    option.setValue2(projapprs.get(i).bugnum);
                    list.add(option);
                }
                break;
            case Constants.PMCODE:
                List<Pm> pms;
                pms = new PmDao(OptionActivity.this).queryByCount(page, searchText);
                for (int i = 0; i < pms.size(); i++) {
                    option = new Option();
                    option.setName(pms.get(i).pmnum);
                    option.setDescription(pms.get(i).description);
                    list.add(option);
                }
                break;
            case Constants.LABORCRAFTRATECODE:
                List<Laborcraftrate> laborcraftrates;
                laborcraftrates = new LaborcraftrateDao(OptionActivity.this).queryByCount(page, searchText,"CCT");
                for (int i = 0; i < laborcraftrates.size(); i++) {
                    option = new Option();
                    option.setName(laborcraftrates.get(i).laborcode);
                    option.setDescription(laborcraftrates.get(i).displayname);
                    list.add(option);
                }
                break;
            case Constants.FAILURE_TYPE:
                List<Failurelist> failurelists1;
                failurelists1 = new FailurelistDao(OptionActivity.this).queryByCount(page, searchText,"");
                for (int i = 0; i < failurelists1.size(); i++) {
                    option = new Option();
                    option.setName(failurelists1.get(i).failurecode);
                    option.setDescription(failurelists1.get(i).description);
                    list.add(option);
                }
                break;
            case Constants.FAILURE_QUESTION:
                List<Failurelist> failurelists2;
                if (getIntent().hasExtra("failurecode")){
                    parent = new FailurelistDao(OptionActivity.this).queryForParent(getIntent().getStringExtra("failurecode"));
                }
                failurelists2 = new FailurelistDao(OptionActivity.this).queryByCount(page, searchText,parent);
                for (int i = 0; i < failurelists2.size(); i++) {
                    option = new Option();
                    option.setName(failurelists2.get(i).failurecode);
                    option.setDescription(failurelists2.get(i).description);
                    list.add(option);
                }
                break;
            case Constants.FAILURE_CAUSE:
                List<Failurelist> failurelists3;
                if (getIntent().hasExtra("failurecode")){
                    parent = new FailurelistDao(OptionActivity.this).queryForParent(getIntent().getStringExtra("failurecode"));
                }
                failurelists3 = new FailurelistDao(OptionActivity.this).queryByCount(page, searchText,parent);
                for (int i = 0; i < failurelists3.size(); i++) {
                    option = new Option();
                    option.setName(failurelists3.get(i).failurecode);
                    option.setDescription(failurelists3.get(i).description);
                    list.add(option);
                }
                break;
            case Constants.FAILURE_REMEMDY:
                List<Failurelist> failurelists4;
                if (getIntent().hasExtra("failurecode")){
                    parent = new FailurelistDao(OptionActivity.this).queryForParent(getIntent().getStringExtra("failurecode"));
                }
                failurelists4 = new FailurelistDao(OptionActivity.this).queryByCount(page, searchText,parent);
                for (int i = 0; i < failurelists4.size(); i++) {
                    option = new Option();
                    option.setName(failurelists4.get(i).failurecode);
                    option.setDescription(failurelists4.get(i).description);
                    list.add(option);
                }
                break;
//            case Constants.LABORCRAFTRATE:
//                List<Laborcraftrate> laborcraftrates;
//
//                if (searchText.equals("") && !CraftSearch.equals("")) {
//                    laborcraftrates = new LaborcraftrateDao(OptionActivity.this).queryByCraft(page, searchText, CraftSearch);
//                } else {
//                    laborcraftrates = new LaborcraftrateDao(OptionActivity.this).queryByCount(page, searchText);
//                }
//                for (int i = 0; i < laborcraftrates.size(); i++) {
//                    option = new Option();
//                    option.setName(laborcraftrates.get(i).laborcode);
//                    option.setDescription(laborcraftrates.get(i).craft);
//                    list.add(option);
//                }
//                break;
        }

        Log.i(TAG,"ssssssssssssss");
        if (page == 1) {
            optionAdapter = new OptionAdapter(OptionActivity.this);
            recyclerView.setAdapter(optionAdapter);
        }
        optionAdapter.adddate(list);
        optionAdapter.notifyDataSetChanged();
        if (optionAdapter.getItemCount() == 0) {
            nodatalayout.setVisibility(View.VISIBLE);
            synchronousbtn.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }else{
            nodatalayout.setVisibility(View.GONE);
            synchronousbtn.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }
        refresh_layout.setLoading(false);
        refresh_layout.setRefreshing(false);
    }

    public void responseData(Option option) {
        Intent intent = getIntent();
        intent.putExtra("option", option);
        OptionActivity.this.setResult(requestCode, intent);
        finish();
    }

    //下拉刷新触发事件
    @Override
    public void onRefresh() {
        page = 1;
        getData(searchText);
    }

    //上拉加载
    @Override
    public void onLoad() {
        page++;
        getData(searchText);
    }
}
