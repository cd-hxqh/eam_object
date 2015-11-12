package cdhxqh.shekou.ui.activity;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;

import cdhxqh.shekou.R;
import cdhxqh.shekou.utils.DateTimePickDialogUtil;

/**新增领料单**/

public class AddinvuseActivity extends BaseActivity {
    private static final String TAG="AddinvuseActivity";

    /**
     * 返回按钮
     */
    private ImageView backImageView;
    /**
     * 标题
     */
    private TextView titleTextView;

    /**界面信息**/
    /**描述**/
    private  TextView descriptionText;
    /**库房**/
    private TextView fromstorelocText;
    /**领料人**/
    private TextView udissuetoText;
    /**工单**/
    private TextView wonumText;
    /**状态**/
    private TextView statusText;
    /**状态日期**/
    private TextView statusdateText;
    /**位置**/
    private TextView siteidText;
    /**申请人**/
    private TextView displaynameText;

    /**申请日期**/
    private TextView createdateText;

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

        descriptionText=(TextView)findViewById(R.id.invuse_description_text_id);
        fromstorelocText=(TextView)findViewById(R.id.invuse_location_text_id);
        udissuetoText=(TextView)findViewById(R.id.invuse_udissueto_text_id);
        wonumText=(TextView)findViewById(R.id.invuse_wonum_text_id);
        statusText=(TextView)findViewById(R.id.invuse_status_text_id);
        statusdateText=(TextView)findViewById(R.id.invuse_statusdate_text_id);
        siteidText=(TextView)findViewById(R.id.invuse_siteid_text_id);
        displaynameText=(TextView)findViewById(R.id.invuse_displayname_text_id);
        createdateText=(TextView)findViewById(R.id.invuse_createdate_text_id);

    }

    @Override
    protected void initView() {
        backImageView.setOnClickListener(backImageViewOnClickListener);
        titleTextView.setText(getString(R.string.add_invuse_title));

        statusdateText.setText(getCurrenttime());
        statusdateText.setOnClickListener(statusdataTextOnClickListener);
        createdateText.setText(getCurrenttime());
    }

    private View.OnClickListener backImageViewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };


    private View.OnClickListener statusdataTextOnClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            DateTimePickDialogUtil dateTimePicKDialog = new DateTimePickDialogUtil(
                    AddinvuseActivity.this, getCurrenttime());
            dateTimePicKDialog.dateTimePicKDialog(statusdateText);
        }
    };

    /**获取系统当前时间**/
    private String getCurrenttime(){
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
        String date = sDateFormat.format(new java.util.Date());

        return date;
    }
}
