package cdhxqh.shekou.ui.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import cdhxqh.shekou.R;
import cdhxqh.shekou.config.Constants;
import cdhxqh.shekou.ui.activity.Work_ListActivity;

/**
 * 工单的fragment1
 */
public class WorkFragment extends Fragment {

    private LinearLayout fault_layout,prevent_layout,status_layout,project_layout,
            service_layout,accident_layout,repair_layout;

    public WorkFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_work, container,
                false);

        findByIdView(view);
        setlistener();
        return view;
    }
    /**
     * 初始化界面组件*
     */
    private void findByIdView(View view) {
        fault_layout = (LinearLayout) view.findViewById(R.id.work_linear_fault_id);
        prevent_layout = (LinearLayout) view.findViewById(R.id.work_linear_prevent_id);
        status_layout = (LinearLayout) view.findViewById(R.id.work_linear_status_id);
        project_layout = (LinearLayout) view.findViewById(R.id.work_linear_project_id);
        service_layout = (LinearLayout) view.findViewById(R.id.work_linear_service_id);
        accident_layout = (LinearLayout) view.findViewById(R.id.work_linear_accident_id);
        repair_layout = (LinearLayout) view.findViewById(R.id.work_linear_repair_id);
    }

    /**
     * 设置跳转监听
     */
    private void setlistener(){
        fault_layout.setOnClickListener(new intentOnclicklistener(Constants.FAULT));
        prevent_layout.setOnClickListener(new intentOnclicklistener(Constants.PREVENT));
        status_layout.setOnClickListener(new intentOnclicklistener(Constants.STATUS));
        project_layout.setOnClickListener(new intentOnclicklistener(Constants.PROJECT));
        service_layout.setOnClickListener(new intentOnclicklistener(Constants.SERVICE));
        accident_layout.setOnClickListener(new intentOnclicklistener(Constants.ACCIDENT));
        repair_layout.setOnClickListener(new intentOnclicklistener(Constants.REPAIR));
    }

    private class intentOnclicklistener implements View.OnClickListener{
        private String type;
        private intentOnclicklistener(String type){
            this.type = type;
        }
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(getActivity(), Work_ListActivity.class);
            intent.putExtra("worktype",type);
            startActivity(intent);
        }
    }
}
