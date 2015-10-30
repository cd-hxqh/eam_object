package cdhxqh.shekou.ui.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import cdhxqh.shekou.R;
import cdhxqh.shekou.model.Invcost;

/**
 * Created by apple on 15/10/26
 * 库存成本
 */
public class InvcostAdapter extends RecyclerView.Adapter<InvcostAdapter.ViewHolder> {

    private static final String TAG = "InvcostAdapter";
    Context mContext;
    ArrayList<Invcost> mItems = new ArrayList<Invcost>();

    public InvcostAdapter(Context context) {
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.invcost_list_item, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        final Invcost item = mItems.get(i);

        viewHolder.itemNum.setText(item.itemnum);
        viewHolder.avgcostText.setText(item.avgcost);
        viewHolder.lastcostText.setText(item.lastcost);


    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void update(ArrayList<Invcost> data, boolean merge) {
        if (merge && mItems.size() > 0) {
            for (int i = 0; i < mItems.size(); i++) {
                Log.i(TAG, "mItems=" + mItems.get(i).itemnum);
                Invcost obj = mItems.get(i);
                boolean exist = false;
                for (int j = 0; j < data.size(); j++) {
                    if (data.get(j).itemnum == obj.itemnum) {
                        exist = true;
                        break;
                    }
                }
                if (exist) continue;
                data.add(obj);
            }
        }
        mItems = data;

        notifyDataSetChanged();
    }


    public void removeAllData() {
        if (mItems.size() > 0) {
            mItems.removeAll(mItems);
        }
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        /**
         * CardView*
         */
        public CardView cardView;

        /**
         * 编号*
         */
        public TextView itemNum;
        /**
         * 平均成本
         */
        public TextView avgcostText;
        /**
         * 上次接收成本*
         */
        public TextView lastcostText;

        public ViewHolder(View view) {
            super(view);
            cardView = (CardView) view.findViewById(R.id.card_container);

            itemNum = (TextView) view.findViewById(R.id.invcost_num_text);
            avgcostText = (TextView) view.findViewById(R.id.invcost_avgcost_text_id);


            lastcostText = (TextView) view.findViewById(R.id.invcost_lastcost_id);
        }
    }
}
