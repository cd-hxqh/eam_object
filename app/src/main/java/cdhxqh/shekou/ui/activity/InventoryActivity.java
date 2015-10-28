package cdhxqh.shekou.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import cdhxqh.shekou.R;
import cdhxqh.shekou.model.Inventory;
import cdhxqh.shekou.utils.MessageUtils;

/**
 * ����������� *
 */
public class InventoryActivity extends BaseActivity {
    private static String TAG = "InventoryActivity";

    /**
     * ���ذ�ť*
     */
    private ImageView backImageView;
    /**
     * ����*
     */
    private TextView titleTextView;

    /**
     * �˵���ť*
     */
    private ImageView menuImageView;


    /**��������**/
    /**
     * ��汸��
     */
    private TextView itemnumText;
    /**
     * ��������
     */
    private TextView descText;

    /**
     * �ⷿ
     */
    private TextView locationText;
    /**
     * �ⷿ����
     */
    private TextView locationDescText;
    /**
     * ���ŵ�λ
     */
    private TextView issueunitText;
    /**
     * �ص�
     */
    private TextView siteidText;
    /**
     * �������
     */
    private TextView curbaltotalText;

    /**
     * inventory*
     */
    private Inventory inventory;

    private PopupWindow popupWindow;

    /**
     * ���ɱ�*
     */
    private LinearLayout costLinearlayout;
    /**
     * �������*
     */
    private LinearLayout levelsLinearLayout;
    /**
     * ���*
     */
    private LinearLayout storageLinearLayout;
    /**
     * ����*
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

    /**
     * ��ȡ����*
     */
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
        locationDescText = (TextView) findViewById(R.id.inventory_location_name_text_id);
        issueunitText = (TextView) findViewById(R.id.inventory_issueunit_text_id);
        siteidText = (TextView) findViewById(R.id.inventory_siteid_text_id);
        curbaltotalText = (TextView) findViewById(R.id.inventory_curbaltotal_text_id);

        if (inventory != null) {
            itemnumText.setText(inventory.itemnum);
            locationText.setText(inventory.location);
            issueunitText.setText(inventory.issueunit);
            siteidText.setText(inventory.siteid);
            curbaltotalText.setText(inventory.curbal);
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


    /**
     * ��ʼ��showPopupWindow*
     */
    private void showPopupWindow(View view) {

        // һ���Զ���Ĳ��֣���Ϊ��ʾ������
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
                // �����������true�Ļ���touch�¼���������
                // ���غ� PopupWindow��onTouchEvent�������ã���������ⲿ�����޷�dismiss
            }
        });

        // ���������PopupWindow�ı����������ǵ���ⲿ������Back�����޷�dismiss����
        // �Ҿ���������API��һ��bug
        popupWindow.setBackgroundDrawable(getResources().getDrawable(
                R.drawable.popup_background_mtrl_mult));

        // ���úò���֮����show
        popupWindow.showAsDropDown(view);

        costLinearlayout = (LinearLayout) contentView.findViewById(R.id.inventory_cost_id);
        levelsLinearLayout = (LinearLayout) contentView.findViewById(R.id.inventory_levels_id);
        storageLinearLayout = (LinearLayout) contentView.findViewById(R.id.inventory_storage_id);
        outboundLinearLayout = (LinearLayout) contentView.findViewById(R.id.inventory_outbound_id);
        costLinearlayout.setOnClickListener(costOnClickListener);
        levelsLinearLayout.setOnClickListener(levelsOnClickListener);
        storageLinearLayout.setOnClickListener(storageOnClickListener);
        outboundLinearLayout.setOnClickListener(outboundOnClickListener);

    }

    /**
     * ���ɱ�*
     */
    private View.OnClickListener costOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(InventoryActivity.this, InvCostActivity.class);
            intent.putExtra("itemnum", inventory.itemnum);
            startActivityForResult(intent, 0);
        }
    };
    private View.OnClickListener levelsOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            MessageUtils.showErrorMessage(InventoryActivity.this, "2");
        }
    };
    private View.OnClickListener storageOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            MessageUtils.showErrorMessage(InventoryActivity.this, "3");
        }
    };
    private View.OnClickListener outboundOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            MessageUtils.showErrorMessage(InventoryActivity.this, "4");
        }
    };


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 0:
                if(popupWindow!=null){
                    popupWindow.dismiss();
                }
                break;
        }
    }
}
