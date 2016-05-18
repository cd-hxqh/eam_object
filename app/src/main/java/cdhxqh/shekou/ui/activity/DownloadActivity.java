package cdhxqh.shekou.ui.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

import cdhxqh.shekou.Dao.AlndomainDao;
import cdhxqh.shekou.Dao.AssetDao;
import cdhxqh.shekou.Dao.JobPlanDao;
import cdhxqh.shekou.Dao.LaborDao;
import cdhxqh.shekou.Dao.PersonDao;
import cdhxqh.shekou.R;
import cdhxqh.shekou.api.HttpManager;
import cdhxqh.shekou.api.HttpRequestHandler;
import cdhxqh.shekou.api.JsonUtils;
import cdhxqh.shekou.bean.Results;
import cdhxqh.shekou.config.Constants;
import cdhxqh.shekou.model.Alndomain;
import cdhxqh.shekou.model.Assets;
import cdhxqh.shekou.model.JobPlan;
import cdhxqh.shekou.model.Labor;
import cdhxqh.shekou.model.Person;

/**
 * Created by think on 2015/12/25.
 * 基础数据下载页面
 */
public class DownloadActivity extends BaseActivity {

    private static final String TAG = "DownloadActivity";
    private static final int START = 0;
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

    private ProgressDialog mProgressDialog;
    private Button DownloadAll;
    private int count = 0;

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case START:
                    if (count < 10) {
                        mProgressDialog = ProgressDialog.show(DownloadActivity.this, null,
                                getString(R.string.downloading1) + childArray.get(0).get(count), true, true);
                        mProgressDialog.setCanceledOnTouchOutside(false);
                        mProgressDialog.setCancelable(false);
                        if (count == 0) {//设备
                            downloaddata(HttpManager.getAssetUrl("CCT"), childArray.get(0).get(count));
                        }
// else if (count == 1) {//资产
//                            downloaddata(HttpManager.getUrl(Constants.ASSET_APPID, Constants.ASSET_NAME), childArray.get(0).get(count));
//                        } else if (count == 2) {//故障类
//                            downloaddata(HttpManager.getUrl(Constants.UDWOCM_APPID, Constants.FAILURECODE_NAME), childArray.get(0).get(count));
//                        } else if (count == 3) {//问题代码
//                            downloaddata(HttpManager.getUrl(Constants.UDWOCM_APPID, Constants.FAILURELIST_NAME), childArray.get(0).get(count));
//                        } else if (count == 4) {//作业计划
//                            downloaddata(HttpManager.getUrl(Constants.UDWOCM_APPID, Constants.JOBPLAN_NAME), childArray.get(0).get(count));
//                        } else if (count == 5) {//人员
//                            downloaddata(HttpManager.getUrl(Constants.PERSON_APPID, Constants.PERSON_NAME), childArray.get(0).get(count));
//                        } else if (count == 6) {//员工
//                            downloaddata(HttpManager.getUrl(Constants.LABOR_APPID, Constants.LABOR_NAME), childArray.get(0).get(count));
//                        } else if (count == 7) {//工种
//                            downloaddata(HttpManager.getUrl(Constants.CRAFTRATE_APPID, Constants.CRAFTRATE_NAME), childArray.get(0).get(count));
//                        } else if (count == 8) {//项目
//                            downloaddata(HttpManager.getUrl(Constants.ITEM_APPID, Constants.ITEM_NAME), childArray.get(0).get(count));
//                        } else if (count == 9) {//员工工种
//                            downloaddata(HttpManager.getUrl(Constants.LABORCRAFTRATE_APPID, Constants.LABORCRAFTRATE_NAME), childArray.get(0).get(count));
//                        }
                        count++;
                    } else if (count == 10) {//全部下载完成
                        mProgressDialog.dismiss();
                        DownloadAll.setText(getResources().getString(R.string.downloaded));
                    }
                    break;
//                case F:
//                    mProgressDialog.dismiss();
//                    break;
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
        groupArray.add("巡检");
        List<String> tempArray01 = new ArrayList<String>();
        tempArray01.add("设备");
        tempArray01.add("作业计划");
        tempArray01.add("人员");
        tempArray01.add("员工");
        tempArray01.add("抢修班组");
//        tempArray01.add("人员");
//        tempArray01.add("员工");
//        tempArray01.add("工种");
//        tempArray01.add("项目");
//        tempArray01.add("员工工种");

        List<String> tempArray02 = new ArrayList<String>();
//        tempArray02.add("巡检单类型");
//        tempArray02.add("设备");

        childArray.add(tempArray01);
        childArray.add(tempArray02);
        expandableListView.setAdapter(new MyExpandableListViewAdapter(this));

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
                downloaddata(HttpManager.getAssetUrl("CCT"), buttonText, button);
            } else if (buttonText.equals(childArray.get(0).get(1))) {//作业计划
                downloaddata(HttpManager.getJpNumUrl(""), buttonText, button);
            } else if (buttonText.equals(childArray.get(0).get(2))) {//人员
                downloaddata(HttpManager.getPersonUrl(""), buttonText, button);
            } else if (buttonText.equals(childArray.get(0).get(3))) {//员工
                downloaddata(HttpManager.getLaborUrl(""), buttonText, button);
            } else if (buttonText.equals(childArray.get(0).get(4))) {//抢修班组
                downloaddata(HttpManager.getAlndomainUrl("CCT"), buttonText, button);
            }
// else if (buttonText.equals(childArray.get(0).get(5))) {//人员
//                downloaddata(HttpManager.getUrl(Constants.PERSON_APPID, Constants.PERSON_NAME), buttonText, button);
//            } else if (buttonText.equals(childArray.get(0).get(6))) {//员工
//                downloaddata(HttpManager.getUrl(Constants.LABOR_APPID, Constants.LABOR_NAME), buttonText, button);
//            } else if (buttonText.equals(childArray.get(0).get(7))) {//工种
//                downloaddata(HttpManager.getUrl(Constants.CRAFTRATE_APPID, Constants.CRAFTRATE_NAME), buttonText, button);
//            } else if (buttonText.equals(childArray.get(0).get(8))) {//项目
//                downloaddata(HttpManager.getUrl(Constants.ITEM_APPID, Constants.ITEM_NAME), buttonText, button);
//            } else if (buttonText.equals(childArray.get(0).get(9))) {//员工工种
//                downloaddata(HttpManager.getUrl(Constants.LABORCRAFTRATE_APPID, Constants.LABORCRAFTRATE_NAME), buttonText, button);
//            }
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
                        }
// else if (buttonText.equals(childArray.get(0).get(5))) {//人员
//                            List<Person> jobplans = Ig_Json_Model.parsingPerson(data);
//                            new PersonDao(DownloadActivity.this).create(jobplans);
//                        } else if (buttonText.equals(childArray.get(0).get(6))) {//员工
//                            List<Labor> jobplans = Ig_Json_Model.parsingLabor(data);
//                            new LaborDao(DownloadActivity.this).create(jobplans);
//                        } else if (buttonText.equals(childArray.get(0).get(7))) {//工种
//                            List<Craftrate> craftrates = Ig_Json_Model.parsingCraftrate(data);
//                            new CraftrateDao(DownloadActivity.this).create(craftrates);
//                        } else if (buttonText.equals(childArray.get(0).get(8))) {//项目
//                            List<Item> craftrates = Ig_Json_Model.parsingItem(data);
//                            new ItemDao(DownloadActivity.this).create(craftrates);
//                        } else if (buttonText.equals(childArray.get(0).get(9))) {//员工工种
//                            List<Laborcraftrate> craftrates = Ig_Json_Model.parsingLaborcraftrate(data);
//                            new LaborcraftrateDao(DownloadActivity.this).create(craftrates);
//                        }
                    mProgressDialog.dismiss();
                    button.setText(getResources().getString(R.string.downloaded));
                } else {
                    Toast.makeText(DownloadActivity.this, "下载数据出现问题", Toast.LENGTH_SHORT).show();
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

    private void downloaddata(String url, final String buttonText) {
        HttpManager.getData(DownloadActivity.this, url, new HttpRequestHandler<Results>() {
            @Override
            public void onSuccess(Results data) {
                if (data != null) {
                    if (buttonText.equals(childArray.get(0).get(0))) {//位置
//                            List<Assets> locations = Ig_Json_Model.parsingLocation(data);
//                            new AssetDao(DownloadActivity.this).create(locations);
                    }
//                        else if (buttonText.equals(childArray.get(0).get(1))) {//资产
//                            List<Assets> assets = Ig_Json_Model.parsingAsset(data);
//                            new AssetDao(DownloadActivity.this).create(assets);
//                        } else if (buttonText.equals(childArray.get(0).get(2))) {//故障类
//                            List<Failurecode> failurecodes = Ig_Json_Model.parsingFailurecode(data);
//                            new FailurecodeDao(DownloadActivity.this).create(failurecodes);
//                        } else if (buttonText.equals(childArray.get(0).get(3))) {//问题代码
//                            List<Failurelist> failurelists = Ig_Json_Model.parsingFailurelist(data);
//                            new FailurelistDao(DownloadActivity.this).create(failurelists);
//                        } else if (buttonText.equals(childArray.get(0).get(4))) {//作业计划
//                            List<Jobplan> jobplans = Ig_Json_Model.parsingJobplan(data);
//                            new JobplanDao(DownloadActivity.this).create(jobplans);
//                        } else if (buttonText.equals(childArray.get(0).get(5))) {//人员
//                            List<Person> jobplans = Ig_Json_Model.parsingPerson(data);
//                            new PersonDao(DownloadActivity.this).create(jobplans);
//                        } else if (buttonText.equals(childArray.get(0).get(6))) {//员工
//                            List<Labor> jobplans = Ig_Json_Model.parsingLabor(data);
//                            new LaborDao(DownloadActivity.this).create(jobplans);
//                        } else if (buttonText.equals(childArray.get(0).get(7))) {//工种
//                            List<Craftrate> craftrates = Ig_Json_Model.parsingCraftrate(data);
//                            new CraftrateDao(DownloadActivity.this).create(craftrates);
//                        } else if (buttonText.equals(childArray.get(0).get(8))) {//项目
//                            List<Item> craftrates = Ig_Json_Model.parsingItem(data);
//                            new ItemDao(DownloadActivity.this).create(craftrates);
//                        } else if (buttonText.equals(childArray.get(0).get(9))) {//员工工种
//                            List<Laborcraftrate> craftrates = Ig_Json_Model.parsingLaborcraftrate(data);
//                            new LaborcraftrateDao(DownloadActivity.this).create(craftrates);
//                        }
                    mHandler.sendEmptyMessage(START);
                    mProgressDialog.dismiss();
                } else {
                    Toast.makeText(DownloadActivity.this, "下载数据出现问题", Toast.LENGTH_SHORT).show();
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

    private class downloadAll implements View.OnClickListener {
        int group;
        Button button;

        private downloadAll(int group, Button button) {
            this.group = group;
            this.button = button;
        }

        @Override
        public void onClick(View view) {
            if (group == 0) {
                mHandler.sendEmptyMessage(START);
                DownloadAll = button;
            }
        }
    }

}
