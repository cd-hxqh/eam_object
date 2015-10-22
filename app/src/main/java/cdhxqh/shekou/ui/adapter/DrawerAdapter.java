package cdhxqh.shekou.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import cdhxqh.shekou.R;


/**
 * Created by yugy on 14-3-15.
 */
public class DrawerAdapter extends BaseAdapter {

    private Context mContext;
    private String[] mTitles;
    private final int mIcons[] = new int[]{
            R.drawable.ic_task,
            R.drawable.ic_workorder,
            R.drawable.ic_inventory,
            R.drawable.ic_item

    };

    public DrawerAdapter(Context context) {
        mContext = context;
        mTitles = context.getResources().getStringArray(R.array.drawer_tab_titles);
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }

    @Override
    public String getItem(int position) {
        return mTitles[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public int getIconId(int position) {
        return mIcons[position];
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView item = (TextView) convertView;
        if (item == null) {
            item = (TextView) LayoutInflater.from(mContext).inflate(R.layout.view_drawer_item, null);
        }
        item.setText(getItem(position));
        item.setCompoundDrawablesWithIntrinsicBounds(getIconId(position), 0, 0, 0);
        return item;
    }
}
