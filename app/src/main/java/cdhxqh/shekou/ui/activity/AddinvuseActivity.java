package cdhxqh.shekou.ui.activity;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import cdhxqh.shekou.R;
import cdhxqh.shekou.config.Constants;
import cdhxqh.shekou.model.Failurereport;
import cdhxqh.shekou.model.Labtrans;
import cdhxqh.shekou.model.Option;
import cdhxqh.shekou.model.Woactivity;
import cdhxqh.shekou.utils.DateTimePickDialogUtil;

/**
 * 新增领料单*
 */

public class AddinvuseActivity extends BaseActivity {
    private static final String TAG = "AddinvuseActivity";

    /**
     * 返回按钮
     */
    private ImageView backImageView;
    /**
     * 标题
     */
    private TextView titleTextView;

    /**界面信息**/
    /**
     * 描述*
     */
    private TextView descriptionText;
    /**
     * 库房*
     */
    private TextView fromstorelocText;

    /**
     * 工单*
     */
    private TextView wonumText;
    /**
     * 领料人*
     */
    private TextView udissuetoText;
    /**
     * 物管员经办人*
     */
    private TextView udjbrText;
    /**
     * 是否紧急*
     */
    private TextView udisjjText;


    /**
     * 位置*
     */
    private String fromstoreloc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addinvuse);
        findViewById();
        initView();
    }

    @Override
    protected void findViewById() {
        backImageView = (ImageView) findViewById(R.id.title_back_id);
        titleTextView = (TextView) findViewById(R.id.title_name);

        descriptionText = (TextView) findViewById(R.id.invuse_description_text_id);
        fromstorelocText = (TextView) findViewById(R.id.invuse_location_text_id);
        wonumText = (TextView) findViewById(R.id.invuse_wonum_text_id);
        udissuetoText = (TextView) findViewById(R.id.invuse_udissueto_text_id);
        udjbrText = (TextView) findViewById(R.id.udjbr_text_id);
        udisjjText = (CheckBox) findViewById(R.id.udisjj_text_id);

    }

    @Override
    protected void initView() {
        backImageView.setOnClickListener(backImageViewOnClickListener);
        titleTextView.setText(getString(R.string.add_invuse_title));

        fromstorelocText.setOnClickListener(fromstorelocTextOnClickListener);
        wonumText.setOnClickListener(wonumTextOnClickListener);
        udissuetoText.setOnClickListener(udissuetoTextOnClickListener);


    }

    private View.OnClickListener backImageViewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };

    /**
     * 库房*
     */
    private View.OnClickListener fromstorelocTextOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(AddinvuseActivity.this, OptionActivity.class);
            intent.putExtra("requestCode", Constants.LOCATIONCODE);
            startActivityForResult(intent, Constants.LOCATIONCODE);
        }
    };
    /**
     * 领料人*
     */
    private View.OnClickListener udissuetoTextOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(AddinvuseActivity.this, OptionActivity.class);
            intent.putExtra("requestCode", Constants.PERSONCODE);
            startActivityForResult(intent, Constants.PERSONCODE);
        }
    };
    /**
     * 工单
     */
    private View.OnClickListener wonumTextOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(AddinvuseActivity.this, Work_Choose_Activity.class);
            startActivityForResult(intent, Constants.WORKORDERCODE);
        }
    };


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Option option;
        switch (resultCode) {
            case Constants.LOCATIONCODE:
                option = (Option) data.getSerializableExtra("option");
                fromstoreloc = option.getName();
                fromstorelocText.setText(option.getDescription());
                break;
            case Constants.WORKORDERCODE:
                String wonum = data.getStringExtra("wonum");
                String description = data.getStringExtra("description");
                wonumText.setText(wonum);
                Log.i(TAG, "wonum=" + wonum + ",description=" + description);
                break;
            case Constants.PERSONCODE:
                option = (Option) data.getSerializableExtra("option");
                udissuetoText.setText(option.getName());
                break;
            default:
                break;

        }



    }
    }
