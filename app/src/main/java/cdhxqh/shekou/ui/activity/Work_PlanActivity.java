package cdhxqh.shekou.ui.activity;

import android.annotation.TargetApi;
import android.app.FragmentTransaction;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cdhxqh.shekou.R;
import cdhxqh.shekou.ui.fragment.TaskFragment;

/**
 * Created by think on 2015/11/2.
 * 工单计划页面
 */
public class Work_PlanActivity extends BaseActivity {

    private TextView titlename;
    private ImageView menuImageView;
    private RelativeLayout backlayout;
    private Button task;
    private Button worker;
    private Button meterial;
    private FragmentTransaction fragmentTransaction;
    private TaskFragment taskFragment = new TaskFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workplan);

        findViewById();
        initView();
    }

    @Override
    protected void findViewById() {
        titlename = (TextView) findViewById(R.id.title_name);
        menuImageView = (ImageView) findViewById(R.id.title_add);
        backlayout = (RelativeLayout) findViewById(R.id.title_back);
        task = (Button) findViewById(R.id.work_plan_task);
        worker = (Button) findViewById(R.id.work_plan_worker);
        meterial = (Button) findViewById(R.id.work_plan_meterial);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void initView() {
        titlename.setText(getResources().getString(R.string.work_plan));
        backlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        menuImageView.setImageResource(R.drawable.add);
        menuImageView.setVisibility(View.VISIBLE);
//        menuImageView.setOnClickListener(menuImageViewOnClickListener);
        task.setBackground(getResources().getDrawable(R.drawable.button_true_shape));
        task.setOnClickListener(new Buttonlistener());
        worker.setOnClickListener(new Buttonlistener());
        meterial.setOnClickListener(new Buttonlistener());

        fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.replace(R.id.container, taskFragment);
        fragmentTransaction.commit();
    }

    public class Buttonlistener implements View.OnClickListener {
        public Buttonlistener(){

        }
        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public void onClick(View view) {
            task.setBackground(getResources().getDrawable(R.drawable.button_false_shape));
            worker.setBackground(getResources().getDrawable(R.drawable.button_false_shape));
            meterial.setBackground(getResources().getDrawable(R.drawable.button_false_shape));
            if(view.getId()==task.getId()){
                view.setBackground(getResources().getDrawable(R.drawable.button_true_shape));
            }else if(view.getId()==worker.getId()){
                view.setBackground(getResources().getDrawable(R.drawable.button_true_shape));
            }else if(view.getId()==meterial.getId()){
                view.setBackground(getResources().getDrawable(R.drawable.button_true_shape));
            }
        }
    }
}
