package cdhxqh.shekou.ui.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

import cdhxqh.shekou.Dao.Alndomain2Dao;
import cdhxqh.shekou.Dao.AlndomainDao;
import cdhxqh.shekou.Dao.AssetDao;
import cdhxqh.shekou.Dao.FailurelistDao;
import cdhxqh.shekou.Dao.ItemDao;
import cdhxqh.shekou.Dao.JobPlanDao;
import cdhxqh.shekou.Dao.LaborDao;
import cdhxqh.shekou.Dao.LaborcraftrateDao;
import cdhxqh.shekou.Dao.LocationDao;
import cdhxqh.shekou.Dao.PersonDao;
import cdhxqh.shekou.Dao.PmDao;
import cdhxqh.shekou.Dao.ProjapprDao;
import cdhxqh.shekou.Dao.UdevDao;
import cdhxqh.shekou.R;
import cdhxqh.shekou.api.HttpManager;
import cdhxqh.shekou.api.HttpRequestHandler;
import cdhxqh.shekou.api.JsonUtils;
import cdhxqh.shekou.bean.Results;
import cdhxqh.shekou.config.Constants;
import cdhxqh.shekou.model.Alndomain;
import cdhxqh.shekou.model.Alndomain2;
import cdhxqh.shekou.model.Assets;
import cdhxqh.shekou.model.Failurelist;
import cdhxqh.shekou.model.Item;
import cdhxqh.shekou.model.JobPlan;
import cdhxqh.shekou.model.Labor;
import cdhxqh.shekou.model.Laborcraftrate;
import cdhxqh.shekou.model.Locations;
import cdhxqh.shekou.model.Person;
import cdhxqh.shekou.model.Pm;
import cdhxqh.shekou.model.Projappr;
import cdhxqh.shekou.model.Udev;
import cdhxqh.shekou.utils.AccountUtils;
import cdhxqh.shekou.utils.MessageUtils;

/**
 * Created by think on 2015/12/25.
 * 基础数据下载页面
 */
public class DownloadActivity extends BaseActivity {

    private static final String TAG = "DownloadActivity";
    private static final int START = 0; //工单
    private static final int START1 = 1; //领料单
    private static final int REFRESH_ITEM = 2; //刷洗子项
    /**
     * 标题*
     */
    private TextView titleView;
    /**
     * 返回按钮*
     */
    private ImageView backImageView;
    private ExpandableListView expandableListView;
    //标题
    List<String> groupArray = new ArrayList<String>();
    //子标题
    List<List<String>> childArray = new ArrayList<List<String>>();
    /**
     * 显示下载*
     */
    List<List<String>> isDownList = new ArrayList<List<String>>();

    private ProgressDialog mProgressDialog;
    private Button DownloadAll;
    private int count = 0; //工单
    private int count_1 = 1; //领料单


    /**
     * 定义List的url*
     */
    private List<String> urlList = new ArrayList<String>();

    private MyExpandableListViewAdapter myExpandableListViewAdapter;


    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case START:

                    mProgressDialog.dismiss();
                    DownloadAll.setText("已下载");
                    for (int i = 0; i < isDownList.get(0).size(); i++) {
                        isDownList.get(0).remove(i);
                        isDownList.get(0).add(i,"已下载");
                    }


                    myExpandableListViewAdapter.notifyDataSetChanged();

                    break;
                case START1:
                    mProgressDialog.dismiss();
                    DownloadAll.setText("已下载");
                    for (int i = 0; i < isDownList.get(1).size(); i++) {
                        isDownList.get(1).remove(i);
                        isDownList.get(1).add(i,"已下载");
                    }


                    myExpandableListViewAdapter.notifyDataSetChanged();

                    break;
                case REFRESH_ITEM:

                    mProgressDialog.dismiss();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);

        findViewById();
        initView();

    }

    @Override
    protected void findViewById() {
        titleView = (TextView) findViewById(R.id.title_name);
        backImageView = (ImageView) findViewById(R.id.title_back_id);
        expandableListView = (ExpandableListView) findViewById(R.id.expendlist);
    }

    @Override
    protected void initView() {
        titleView.setText(getResources().getString(R.string.data_download));
        backImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        groupArray.add("工单");
        groupArray.add("领料单");

        List<String> tempArray01 = new ArrayList<String>();
        tempArray01.add("设备");
        tempArray01.add("作业计划");
        tempArray01.add("人员");
        tempArray01.add("员工");
        tempArray01.add("抢修班组");
        tempArray01.add("事故");
        tempArray01.add("立项申报");
        tempArray01.add("预防性维护");
        tempArray01.add("员工工种");
        tempArray01.add("故障代码");
        tempArray01.add("故障类别");

        List<String> tempArray02 = new ArrayList<String>();
        tempArray02.add("库房");
        tempArray02.add("备件");
        childArray.add(tempArray01);
        childArray.add(tempArray02);
        addisDown();
        myExpandableListViewAdapter = new MyExpandableListViewAdapter(this);
        expandableListView.setAdapter(myExpandableListViewAdapter);


    }

    class MyExpandableListViewAdapter extends BaseExpandableListAdapter {

        private Context context;

        public MyExpandableListViewAdapter(Context context) {
            this.context = context;
        }

        @Override
        public int getGroupCount() {
            return groupArray.size();
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return childArray.get(groupPosition).size();
        }

        @Override
        public Object getGroup(int groupPosition) {
            return groupArray.get(groupPosition);
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return childArray.get(groupPosition).get(childPosition);
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }


        @Override
        public View getGroupView(int groupPosition, boolean isExpanded,
                                 View convertView, ViewGroup parent) {
            final int p = groupPosition;
            GroupHolder groupHolder = null;
            if (convertView == null) {
                convertView = getLayoutInflater().from(context).inflate(
                        R.layout.list_group, null);
                groupHolder = new GroupHolder();
                groupHolder.groupText = (TextView) convertView.findViewById(R.id.group_text);
                groupHolder.downAll = (Button) convertView.findViewById(R.id.download_all);
                convertView.setTag(groupHolder);
            } else {
                groupHolder = (GroupHolder) convertView.getTag();
            }
            groupHolder.groupText.setText(groupArray.get(groupPosition));
            groupHolder.downAll.setOnClickListener(new downloadAll(groupPosition, groupHolder.downAll));

            return convertView;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition,
                                 boolean isLastChild, View convertView, ViewGroup parent) {
            ItemHolder itemHolder = null;
            if (convertView == null) {
                convertView = getLayoutInflater().from(context).inflate(
                        R.layout.list_child, null);
                itemHolder = new ItemHolder();
                itemHolder.childText = (TextView) convertView.findViewById(R.id.child_text);
                itemHolder.down = (Button) convertView.findViewById(R.id.download);
                convertView.setTag(itemHolder);
            } else {
                itemHolder = (ItemHolder) convertView.getTag();
            }
            itemHolder.childText.setText(childArray.get(groupPosition).get(
                    childPosition));
            itemHolder.down.setText(isDownList.get(groupPosition).get(childPosition));
            itemHolder.down.setOnClickListener(new DownloadOnclickListener(groupPosition, childPosition, itemHolder.down));
            return convertView;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }

    }


    class GroupHolder {
        public TextView groupText;
        public Button downAll;
    }

    class ItemHolder {
        public Button down;
        public TextView childText;
    }

    private class DownloadOnclickListener implements View.OnClickListener {
        int group;
        int child;
        Button button;

        private DownloadOnclickListener(int group, int child, Button button) {
            this.group = group;
            this.child = child;
            this.button = button;
        }

        @Override
        public void onClick(View view) {
            String buttonText = childArray.get(group).get(child);
            if (buttonText.equals(childArray.get(0).get(0))) {//设备
                downloaddata(HttpManager.getAssetUrl(AccountUtils.getinsertSite(DownloadActivity.this)), buttonText, button);
            } else if (buttonText.equals(childArray.get(0).get(1))) {//作业计划
                downloaddata(HttpManager.getJpNumUrl(AccountUtils.getinsertSite(DownloadActivity.this)), buttonText, button);
            } else if (buttonText.equals(childArray.get(0).get(2))) {//人员
                downloaddata(HttpManager.getPersonUrl(AccountUtils.getinsertSite(DownloadActivity.this)), buttonText, button);
            } else if (buttonText.equals(childArray.get(0).get(3))) {//员工
                downloaddata(HttpManager.getLaborUrl(AccountUtils.getinsertSite(DownloadActivity.this)), buttonText, button);
            } else if (buttonText.equals(childArray.get(0).get(4))) {//抢修班组
                downloaddata(HttpManager.getAlndomainUrl(AccountUtils.getinsertSite(DownloadActivity.this)), buttonText, button);
            } else if (buttonText.equals(childArray.get(0).get(5))) {//事故
                downloaddata(HttpManager.getUdevUrl(AccountUtils.getinsertSite(DownloadActivity.this)), buttonText, button);
            } else if (buttonText.equals(childArray.get(0).get(6))) {//立项申报
                downloaddata(HttpManager.getProjapprUrl(AccountUtils.getinsertSite(DownloadActivity.this)), buttonText, button);
            } else if (buttonText.equals(childArray.get(0).get(7))) {//预防性维护
                downloaddata(HttpManager.getPmUrl(AccountUtils.getinsertSite(DownloadActivity.this)), buttonText, button);
            } else if (buttonText.equals(childArray.get(0).get(8))) {//员工工种
                downloaddata(HttpManager.getLaborcraftrateUrl(AccountUtils.getinsertSite(DownloadActivity.this)), buttonText, button);
            } else if (buttonText.equals(childArray.get(0).get(9))) {//故障代码
                downloaddata(HttpManager.getFailurelistUrl(), buttonText, button);
            } else if (buttonText.equals(childArray.get(0).get(10))) {//故障类别
                downloaddata(HttpManager.getAlndomain2Url(), buttonText, button);
            } else if (buttonText.equals(childArray.get(1).get(0))) {//库房
                downloaddata(HttpManager.getLocationUrl(), buttonText, button);
            } else if (buttonText.equals(childArray.get(1).get(1))) {//备件
                downloaddata(HttpManager.getItemUrl(), buttonText, button);
            }
            mProgressDialog = ProgressDialog.show(DownloadActivity.this, null,
                    getString(R.string.downloading), true, true);
            mProgressDialog.setCanceledOnTouchOutside(false);
            mProgressDialog.setCancelable(false);
        }
    }

    private void downloaddata(String url, final String buttonText, final Button button) {

        HttpManager.getData(DownloadActivity.this, url, new HttpRequestHandler<Results>() {
            @Override
            public void onSuccess(Results data) {
                if (data != null) {
                    if (buttonText.equals(childArray.get(0).get(0))) {//设备
                        List<Assets> asset = JsonUtils.parsingAsset(data.getResultlist());
                        new AssetDao(DownloadActivity.this).create(asset);
                    } else if (buttonText.equals(childArray.get(0).get(1))) {//作业计划
                        List<JobPlan> jobPlans = JsonUtils.parsingJobplan(data.getResultlist());
                        new JobPlanDao(DownloadActivity.this).create(jobPlans);
                    } else if (buttonText.equals(childArray.get(0).get(2))) {//人员
                        List<Person> persons = JsonUtils.parsingPerson(data.getResultlist());
                        new PersonDao(DownloadActivity.this).create(persons);
                    } else if (buttonText.equals(childArray.get(0).get(3))) {//员工
                        List<Labor> labors = JsonUtils.parsingLabor(data.getResultlist());
                        new LaborDao(DownloadActivity.this).create(labors);
                    } else if (buttonText.equals(childArray.get(0).get(4))) {//抢修班组
                        List<Alndomain> alndomains = JsonUtils.parsingAlndomain(data.getResultlist());
                        new AlndomainDao(DownloadActivity.this).create(alndomains);
                    } else if (buttonText.equals(childArray.get(0).get(5))) {//事故
                        List<Udev> udevs = JsonUtils.parsingUdev(data.getResultlist());
                        new UdevDao(DownloadActivity.this).create(udevs);
                    } else if (buttonText.equals(childArray.get(0).get(6))) {//立项申报
                        List<Projappr> projapprs = JsonUtils.parsingProjappr(data.getResultlist());
                        new ProjapprDao(DownloadActivity.this).create(projapprs);
                    } else if (buttonText.equals(childArray.get(0).get(7))) {//预防性维护
                        List<Pm> pms = JsonUtils.parsingPm(data.getResultlist());
                        new PmDao(DownloadActivity.this).create(pms);
                    } else if (buttonText.equals(childArray.get(0).get(8))) {//员工工种
                        List<Laborcraftrate> laborcraftrates = JsonUtils.parsingLaborcraftrate(data.getResultlist());
                        new LaborcraftrateDao(DownloadActivity.this).create(laborcraftrates);
                    } else if (buttonText.equals(childArray.get(0).get(9))) {//故障代码
                        List<Failurelist> failurelists = JsonUtils.parsingFailurelist(data.getResultlist());
                        new FailurelistDao(DownloadActivity.this).create(failurelists);
                    } else if (buttonText.equals(childArray.get(0).get(10))) {//故障类别
                        List<Alndomain2> alndomains = JsonUtils.parsingAlndomain2(data.getResultlist());
                        new Alndomain2Dao(DownloadActivity.this).create(alndomains);
                    } else if (buttonText.equals(childArray.get(1).get(0))) {//库房

                        List<Locations> locationses = JsonUtils.parsingLocations(data.getResultlist());
                        new LocationDao(DownloadActivity.this).create(locationses);
                    }else if (buttonText.equals(childArray.get(1).get(1))) {//备件

                        List<Item> items = JsonUtils.parsingItem(data.getResultlist());
                        new ItemDao(DownloadActivity.this).create(items);
                    }
                    mProgressDialog.dismiss();
                    button.setText(getResources().getString(R.string.downloaded));
                } else {
                    MessageUtils.showMiddleToast(DownloadActivity.this, "下载数据出现问题");
                    mProgressDialog.dismiss();
                }
            }

            @Override
            public void onSuccess(Results data, int totalPages, int currentPage) {

            }

            @Override
            public void onFailure(String error) {
                Toast.makeText(DownloadActivity.this, "下载失败", Toast.LENGTH_SHORT).show();
            }

        });
    }

    private void downloaddata1(String url, final String buttonText) {
        HttpManager.getData(DownloadActivity.this, url, new HttpRequestHandler<Results>() {
            @Override
            public void onSuccess(Results data) {
                mProgressDialog.setMessage(getString(R.string.downloading1) + buttonText);
                count++;
                if (data != null) {
                    if (buttonText.equals(childArray.get(0).get(0))) {//设备
                        List<Assets> asset = JsonUtils.parsingAsset(data.getResultlist());
                        new AssetDao(DownloadActivity.this).create(asset);
                    } else if (buttonText.equals(childArray.get(0).get(1))) {//作业计划
                        List<JobPlan> jobPlans = JsonUtils.parsingJobplan(data.getResultlist());
                        new JobPlanDao(DownloadActivity.this).create(jobPlans);
                    } else if (buttonText.equals(childArray.get(0).get(2))) {//人员
                        List<Person> persons = JsonUtils.parsingPerson(data.getResultlist());
                        new PersonDao(DownloadActivity.this).create(persons);
                    } else if (buttonText.equals(childArray.get(0).get(3))) {//员工
                        List<Labor> labors = JsonUtils.parsingLabor(data.getResultlist());
                        new LaborDao(DownloadActivity.this).create(labors);
                    } else if (buttonText.equals(childArray.get(0).get(4))) {//抢修班组
                        List<Alndomain> alndomains = JsonUtils.parsingAlndomain(data.getResultlist());
                        new AlndomainDao(DownloadActivity.this).create(alndomains);
                    } else if (buttonText.equals(childArray.get(0).get(5))) {//事故
                        List<Udev> udevs = JsonUtils.parsingUdev(data.getResultlist());
                        new UdevDao(DownloadActivity.this).create(udevs);
                    } else if (buttonText.equals(childArray.get(0).get(6))) {//立项申报
                        List<Projappr> projapprs = JsonUtils.parsingProjappr(data.getResultlist());
                        new ProjapprDao(DownloadActivity.this).create(projapprs);
                    } else if (buttonText.equals(childArray.get(0).get(7))) {//预防性维护
                        List<Pm> pms = JsonUtils.parsingPm(data.getResultlist());
                        new PmDao(DownloadActivity.this).create(pms);
                    } else if (buttonText.equals(childArray.get(0).get(8))) {//员工工种
                        List<Laborcraftrate> laborcraftrates = JsonUtils.parsingLaborcraftrate(data.getResultlist());
                        new LaborcraftrateDao(DownloadActivity.this).create(laborcraftrates);
                    } else if (buttonText.equals(childArray.get(0).get(9))) {//故障代码
                        List<Failurelist> failurelists = JsonUtils.parsingFailurelist(data.getResultlist());
                        new FailurelistDao(DownloadActivity.this).create(failurelists);
                    } else if (buttonText.equals(childArray.get(0).get(10))) {//故障类别
                        List<Alndomain2> alndomains = JsonUtils.parsingAlndomain2(data.getResultlist());
                        new Alndomain2Dao(DownloadActivity.this).create(alndomains);
                    }
                    if (count == childArray.get(0).size()) {

                        mHandler.sendEmptyMessage(START);
                    }
                } else {
                    MessageUtils.showMiddleToast(DownloadActivity.this, "下载数据出现问题");
                }
            }

            @Override
            public void onSuccess(Results data, int totalPages, int currentPage) {

            }

            @Override
            public void onFailure(String error) {
                MessageUtils.showMiddleToast(DownloadActivity.this, "下载失败");
            }

        });
    }
    private void downloaddata2(String url, final String buttonText) {
        Log.i(TAG, "url=" + url);
        HttpManager.getData(DownloadActivity.this, url, new HttpRequestHandler<Results>() {
            @Override
            public void onSuccess(Results data) {
                mProgressDialog.setMessage(getString(R.string.downloading1) + buttonText);
                count++;
                if (data != null) {
                    if (buttonText.equals(childArray.get(1).get(0))) {//库房

                        List<Locations> locationses = JsonUtils.parsingLocations(data.getResultlist());
                        new LocationDao(DownloadActivity.this).create(locationses);
                    }
                    if (count == childArray.get(1).size()) {

                        mHandler.sendEmptyMessage(START1);
                    }
                } else {
                    MessageUtils.showMiddleToast(DownloadActivity.this, "下载数据出现问题");
                }
            }

            @Override
            public void onSuccess(Results data, int totalPages, int currentPage) {

            }

            @Override
            public void onFailure(String error) {
                MessageUtils.showMiddleToast(DownloadActivity.this, "下载失败");
            }

        });
    }

    private class downloadAll implements View.OnClickListener {
        int group;
        Button button;

        private downloadAll(int group, Button button) {
            this.group = group;
            this.button = button;
        }

        @Override
        public void onClick(View view) {
            count=0;
            if (group == 0) {

                addUrl();
                mProgressDialog = new ProgressDialog(DownloadActivity.this);
                mProgressDialog.setMessage(getString(R.string.downloading1) + childArray.get(0).get(0));
                mProgressDialog.show();
                for (int i = 0; i < urlList.size(); i++) {

                    downloaddata1(urlList.get(i), childArray.get(0).get(i));

                }
                DownloadAll = button;


            } else if (group == 1) {
                addUrl1();
                mProgressDialog = new ProgressDialog(DownloadActivity.this);
                mProgressDialog.setMessage(getString(R.string.downloading1) + childArray.get(1).get(0));
                mProgressDialog.show();
                for (int i = 0; i < urlList.size(); i++) {

                    downloaddata2(urlList.get(i), childArray.get(0).get(i));

                }

                DownloadAll = button;
            }
        }
    }


    /**
     * 封装需要工单下载的url*
     */
    private void addUrl() {
        urlList = new ArrayList<String>();
        urlList.add(HttpManager.getAssetUrl(AccountUtils.getinsertSite(DownloadActivity.this)));//设备
        urlList.add(HttpManager.getJpNumUrl(AccountUtils.getinsertSite(DownloadActivity.this)));//作业计划
        urlList.add(HttpManager.getPersonUrl(AccountUtils.getinsertSite(DownloadActivity.this)));//人员
        urlList.add(HttpManager.getLaborUrl(AccountUtils.getinsertSite(DownloadActivity.this)));//员工
        urlList.add(HttpManager.getAlndomainUrl(AccountUtils.getinsertSite(DownloadActivity.this)));//抢修班组
        urlList.add(HttpManager.getUdevUrl(AccountUtils.getinsertSite(DownloadActivity.this)));//事故
        urlList.add(HttpManager.getProjapprUrl(AccountUtils.getinsertSite(DownloadActivity.this)));//立项申报
        urlList.add(HttpManager.getPmUrl(AccountUtils.getinsertSite(DownloadActivity.this)));//预防性维护
        urlList.add(HttpManager.getLaborcraftrateUrl(AccountUtils.getinsertSite(DownloadActivity.this)));//员工工种
        urlList.add(HttpManager.getFailurelistUrl());//故障代码
        urlList.add(HttpManager.getAlndomain2Url());//故障类别
    }
    /**
     * 封装领料单需要下载的url*
     */
    private void addUrl1() {
        urlList = new ArrayList<String>();
        urlList.add(HttpManager.getLocationUrl());//库房
        urlList.add(HttpManager.getItemUrl());//备件
    }




    /**
     * 添加子项列表数据*
     */
    private void addisDown() {
        List<String> isDowns = null;

        //添加主项
        for (int i = 0; i < childArray.size(); i++) {
            isDowns = new ArrayList<String>();

            for (int j = 0; j < childArray.get(i).size(); j++) {
                isDowns.add("下载");
            }
            isDownList.add(isDowns);
        }


    }
}
