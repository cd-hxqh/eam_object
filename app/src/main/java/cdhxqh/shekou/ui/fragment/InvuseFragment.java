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
import cdhxqh.shekou.ui.activity.InvuseActivity;
import cdhxqh.shekou.ui.activity.Work_ListActivity;

/**
 * 领料单的fragment
 */
public class InvuseFragment extends Fragment {

    private LinearLayout work_layout, notwork_layout;

    public InvuseFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_invuse, container,
                false);

        findByIdView(view);
        setlistener();
        return view;
    }

    /**
     * 初始化界面组件*
     */
    private void findByIdView(View view) {
        work_layout = (LinearLayout) view.findViewById(R.id.work_linear_invuse_id);
        notwork_layout = (LinearLayout) view.findViewById(R.id.work_linear_notinvuse_id);
    }

    /**
     * 设置跳转监听
     */
    private void setlistener() {
        work_layout.setOnClickListener(workOnClickListener);
        notwork_layout.setOnClickListener(notWorkOnClickListener);
    }


    private View.OnClickListener workOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(getActivity(), InvuseActivity.class);
            intent.putExtra("udapptype", "USE");
            startActivityForResult(intent, 0);
        }
    };


    private View.OnClickListener notWorkOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(getActivity(), InvuseActivity.class);
            intent.putExtra("udapptype", "UNWOUSE");
            startActivityForResult(intent, 0);
        }
    };

}
