package cdhxqh.shekou.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import cdhxqh.shekou.R;
import cdhxqh.shekou.model.Inventory;
import cdhxqh.shekou.utils.MessageUtils;

/**
 * 库存详情
 */
public class InventoryActivity extends BaseActivity {
    private static String TAG = "InventoryActivity";

    /**
     * 返回按钮
     */
    private ImageView backImageView;
    /**
     * 标题
     */
    private TextView titleTextView;

    /**
     * 菜单
     */
    private ImageView menuImageView;


    /**界面信息**/
    /**
     * 库存备件
     */
    private TextView itemnumText;
    /**
     * 备件名称
     */
    private TextView descText;

    /**
     * 库房
     */
    private TextView locationText;

    /**
     * 单位
     */
    private TextView issueunitText;
    /**
     * 财务公司
     */
    private TextView udfincpText;
    /**
     * 状态
     */
    private TextView statusText;
    /**
     * 默认存放位置*
     */
    private TextView binnumText;
    /**地点**/
    private TextView siteidText;
    /**接收时检查**/
    private CheckBox jsjc;
    /**是否退还旧件**/
    private CheckBox isthjj;
    /**可用量**/
    private TextView  avblbalanceText;

    /**
     * 当前余量*
     */
    private TextView curbaltotalText;
    /**
     * 上次发放日期*
     */
    private TextView lastissuedateText;

    /**
     * inventory*
     */
    private Inventory inventory;

    private PopupWindow popupWindow;

    /**
     * 库存成本
     */
    private LinearLayout costLinearlayout;
    /**
     * 库存余量
     */
    private LinearLayout levelsLinearLayout;


    /**库存交易**/
    private LinearLayout matrectransLinearLayout;
    /**
     * 入库
     */
    private LinearLayout storageLinearLayout;
    /**
     * 出库
     */
    private LinearLayout outboundLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory_details);
        geiIntentData();
        findViewById();
        initView();
    }

    private void geiIntentData() {
        inventory = (Inventory) getIntent().getParcelableExtra("inventory");
    }

    @Override
    protected void findViewById() {
        backImageView = (ImageView) findViewById(R.id.title_back_id);
        titleTextView = (TextView) findViewById(R.id.title_name);
        menuImageView = (ImageView) findViewById(R.id.title_add);

        itemnumText = (TextView) findViewById(R.id.inventory_itemnum_text_id);
        descText = (TextView) findViewById(R.id.inventory_desc_text_id);
        locationText = (TextView) findViewById(R.id.inventory_location_text_id);
        issueunitText = (TextView) findViewById(R.id.issueunit_text_id);
        udfincpText = (TextView) findViewById(R.id.udfincp_text_id);
        statusText = (TextView) findViewById(R.id.status_text_id);
        binnumText = (TextView) findViewById(R.id.binnum_text_id);

        siteidText = (TextView) findViewById(R.id.siteid_text_id);
        jsjc = (CheckBox) findViewById(R.id.jsjx_text_id);
        isthjj = (CheckBox) findViewById(R.id.is_jj_text_id);
        avblbalanceText = (TextView) findViewById(R.id.avblbalance_text_id);


        curbaltotalText = (TextView) findViewById(R.id.curbaltotal_text_id);
        lastissuedateText = (TextView) findViewById(R.id.lastissuedate_text_id);

        if (inventory != null) {
            itemnumText.setText(inventory.itemnum);
            descText.setText(inventory.item_description);
            locationText.setText(inventory.locations_description);
            issueunitText.setText(inventory.issueunit);
            udfincpText.setText(inventory.udfincp_name);
            statusText.setText(inventory.status);
            binnumText.setText(inventory.binnum);
            siteidText.setText(inventory.siteid);
            if(inventory.item_inspectionrequired.equals("Y")){
                jsjc.setChecked(true);
            }else{
                jsjc.setChecked(false);
            }
            if(inventory.item_udisreturn.equals("Y")){
                isthjj.setChecked(true);
            }else{
                isthjj.setChecked(false);
            }
            avblbalanceText.setText(inventory.avblbalance);
            curbaltotalText.setText(inventory.curbaltotal);
            lastissuedateText.setText(inventory.lastissuedate);
        }
    }

    @Override
    protected void initView() {
        backImageView.setOnClickListener(backImageViewOnClickListener);
        titleTextView.setText(getString(R.string.inventory_detail_text));
        menuImageView.setImageResource(R.drawable.ic_drawer);
        menuImageView.setVisibility(View.VISIBLE);

        menuImageView.setOnClickListener(menuImageViewOnClickListener);
    }

    private View.OnClickListener backImageViewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };

    private View.OnClickListener menuImageViewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            showPopupWindow(menuImageView);
        }
    };


    private void showPopupWindow(View view) {

        View contentView = LayoutInflater.from(InventoryActivity.this).inflate(
                R.layout.popup_window, null);


        popupWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setTouchable(true);
        popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setTouchInterceptor(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {


                return false;
            }
        });

        popupWindow.setBackgroundDrawable(getResources().getDrawable(
                R.drawable.popup_background_mtrl_mult));

        popupWindow.showAsDropDown(view);

        costLinearlayout = (LinearLayout) contentView.findViewById(R.id.inventory_cost_id);
        levelsLinearLayout = (LinearLayout) contentView.findViewById(R.id.inventory_levels_id);
        costLinearlayout.setOnClickListener(costOnClickListener);
        levelsLinearLayout.setOnClickListener(levelsOnClickListener);

    }

    private View.OnClickListener costOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(InventoryActivity.this, InvCostActivity.class);
            intent.putExtra("itemnum", inventory.itemnum);
            intent.putExtra("location", inventory.location);
            intent.putExtra("siteid", inventory.siteid);
            startActivityForResult(intent, 0);
        }
    };
    private View.OnClickListener levelsOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(InventoryActivity.this, InvbalancesActivity.class);
            intent.putExtra("itemnum", inventory.itemnum);
            intent.putExtra("location", inventory.location);
            intent.putExtra("siteid", inventory.siteid);
            startActivityForResult(intent, 0);
        }
    };


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 0:
                if (popupWindow != null) {
                    popupWindow.dismiss();
                }
                break;
        }
    }
}
