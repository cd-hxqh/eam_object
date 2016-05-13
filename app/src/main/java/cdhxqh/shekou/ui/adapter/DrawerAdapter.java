package cdhxqh.shekou.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import cdhxqh.shekou.R;


/**
 * Created by yugy on 14-3-15.
 */
public class DrawerAdapter extends BaseAdapter {

    private LayoutInflater mInflater;//得到一个LayoutInfalter对象用来导入布局
    private Context mContext;
    private String[] mTitles;
    private final int mIcons[] = new int[]{
            R.drawable.ic_task,
            R.drawable.ic_workorder,
            R.drawable.ic_inventory,
            R.drawable.ic_item,
            R.drawable.ic_setting

    };

    public DrawerAdapter(Context context) {
        mContext = context;
        this.mInflater = LayoutInflater.from(context);
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
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.view_drawer_item, null);
            viewHolder = new ViewHolder();
            viewHolder.iconImage = (ImageView) convertView.findViewById(R.id.drawer_title_id);
            viewHolder.textView = (TextView) convertView.findViewById(R.id.drawer_textview_id);
            convertView.setTag(viewHolder);//绑定ViewHolder对象
        } else {
            viewHolder = (ViewHolder) convertView.getTag();//取出ViewHolder对象
        }
        viewHolder.iconImage.setImageResource(mIcons[position]);
        viewHolder.textView.setText(mTitles[position]);
        return convertView;
    }


    static class ViewHolder {
        /**
         * 图标*
         */
        private ImageView iconImage;
        /**
         * 文字*
         */
        private TextView textView;
    }
}
