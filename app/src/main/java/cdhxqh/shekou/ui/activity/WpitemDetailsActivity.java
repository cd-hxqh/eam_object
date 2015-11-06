package cdhxqh.shekou.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import cdhxqh.shekou.R;
import cdhxqh.shekou.model.Wpitem;

/**
 * Created by think on 2015/11/6.
 * 物料详情页面
 */
public class WpitemDetailsActivity extends BaseActivity {
    /**
     * 返回按钮
     */
    private ImageView backImageView;
    /**
     * 标题
     */
    private TextView titleTextView;
    private Wpitem wpitem = new Wpitem();
    private TextView itemnum; //项目
    private TextView itemqty; //项目数量
    private TextView orderunit; //订购单位
    private TextView unitcost; //单位成本
    private TextView linecost; //行成本
    private TextView location; //库房
    private TextView storelocsite; //库房地点
    private TextView requestnum; //请求
    private TextView requiredate; //要求的日期
    private TextView orgid; //组织标识
    private TextView siteid; //地点

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wpitem_details);

        geiIntentData();
        findViewById();
        initView();
    }

    private void geiIntentData() {
        wpitem = (Wpitem) getIntent().getParcelableExtra("wpitem");
    }
    @Override
    protected void findViewById() {
        backImageView = (ImageView) findViewById(R.id.title_back_id);
        titleTextView = (TextView) findViewById(R.id.title_name);

        itemnum = (TextView) findViewById(R.id.work_wpitem_itemnum);
        itemqty = (TextView) findViewById(R.id.work_wpitem_itemqty);
        orderunit = (TextView) findViewById(R.id.work_wpitem_orderunit);
        unitcost = (TextView) findViewById(R.id.work_wpitem_unitcost);
        linecost = (TextView) findViewById(R.id.work_wpitem_linecost);
        location = (TextView) findViewById(R.id.work_wpitem_location);
        storelocsite = (TextView) findViewById(R.id.work_wpitem_storelocsite);
        requestnum = (TextView) findViewById(R.id.work_wpitem_requestnum);
        requiredate = (TextView) findViewById(R.id.work_wpitem_requiredate);
        orgid = (TextView) findViewById(R.id.work_wpitem_orgid);
        siteid = (TextView) findViewById(R.id.work_wpitem_siteid);
    }

    @Override
    protected void initView() {
        backImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        titleTextView.setText(getResources().getString(R.string.title_activity_workwpitemdetails));

        itemnum.setText(wpitem.itemnum);
        itemqty.setText(wpitem.itemqty);
        orderunit.setText(wpitem.orderunit);
        unitcost.setText(wpitem.unitcost);
        linecost.setText(wpitem.linecost);
        location.setText(wpitem.location);
        storelocsite.setText(wpitem.storelocsite);
        requestnum.setText(wpitem.requestnum);
        requiredate.setText(wpitem.requiredate);
        orgid.setText(wpitem.orgid);
        siteid.setText(wpitem.siteid);
    }
}
