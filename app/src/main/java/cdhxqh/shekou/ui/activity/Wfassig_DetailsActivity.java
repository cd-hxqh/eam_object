package cdhxqh.shekou.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import cdhxqh.shekou.R;
import cdhxqh.shekou.model.Wfassignment;

/**
 * ����������� *
 */
public class Wfassig_DetailsActivity extends BaseActivity {
    private static String TAG = "Wfassig_DetailsActivity";


    private ImageView backImageView;

    private TextView titleTextView;



    private TextView descriptionText;

    private TextView processnameText;

    private TextView assigncodedescText;

    private TextView assignstatusText;

    private TextView startdateText;

    /**
     * Wfassig*
     */
    private Wfassignment wfassignment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wfassig_details);
        geiIntentData();
        findViewById();
        initView();
    }


    private void geiIntentData() {
        wfassignment = (Wfassignment) getIntent().getParcelableExtra("wfassignment");
    }

    @Override
    protected void findViewById() {
        backImageView = (ImageView) findViewById(R.id.title_back_id);
        titleTextView = (TextView) findViewById(R.id.title_name);


        descriptionText = (TextView) findViewById(R.id.wfassig_desc_text_id);
        processnameText = (TextView) findViewById(R.id.wfassig_processname_text_id);
        assigncodedescText = (TextView) findViewById(R.id.wfassig_assigncodedesc_text_id);
        assignstatusText = (TextView) findViewById(R.id.wfassig_assignstatus_text_id);
        startdateText = (TextView) findViewById(R.id.wfassig_startdate_text_id);

        if (wfassignment != null) {
            descriptionText.setText(wfassignment.description);
            processnameText.setText(wfassignment.processname);
            assigncodedescText.setText(wfassignment.assigncodedesc);
            assignstatusText.setText(wfassignment.assignstatus);
            startdateText.setText(wfassignment.startdate);
        }
    }

    @Override
    protected void initView() {
        backImageView.setOnClickListener(backImageViewOnClickListener);
        titleTextView.setText(getString(R.string.wfasssig_detail_text));
    }

    private View.OnClickListener backImageViewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };


}
