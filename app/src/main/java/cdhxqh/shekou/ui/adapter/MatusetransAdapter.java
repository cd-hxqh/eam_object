package cdhxqh.shekou.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import cdhxqh.shekou.R;
import cdhxqh.shekou.model.Matusetrans;
import cdhxqh.shekou.ui.activity.MatusetransDetailsActivity;

/**
 * Created by apple on 15/10/26
 * 库存出库
 */
public class MatusetransAdapter extends RecyclerView.Adapter<MatusetransAdapter.ViewHolder> {

    private static final String TAG = "MatusetransAdapter";
    Context mContext;
    ArrayList<Matusetrans> mItems = new ArrayList<Matusetrans>();

    public MatusetransAdapter(Context context) {
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        final Matusetrans item = mItems.get(i);

        viewHolder.itemNumTitle.setText(mContext.getString(R.string.itemnum_text));
        viewHolder.itemDescTitle.setText(mContext.getString(R.string.item_desc_title));
        viewHolder.itemNum.setText(item.itemnum);
        viewHolder.itemDesc.setText(item.description);

        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MatusetransDetailsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable("matusetrans", item);
                intent.putExtras(bundle);
                mContext.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void update(ArrayList<Matusetrans> data, boolean merge) {
        if (merge && mItems.size() > 0) {
            for (int i = 0; i < mItems.size(); i++) {
                Log.i(TAG, "mItems=" + mItems.get(i).matusetransid);
                Matusetrans obj = mItems.get(i);
                boolean exist = false;
                for (int j = 0; j < data.size(); j++) {
                    if (data.get(j).matusetransid == obj.matusetransid) {
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

    public void adddate(ArrayList<Matusetrans> data) {
        if (data.size() > 0) {
            for (int i = 0; i < data.size(); i++) {
                mItems.add(data.get(i));
            }
        }
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
         * 交易类型
         */
        public TextView itemNumTitle;
        /**
         * 实际日期
         */
        public TextView itemDescTitle;
        /**
         * 货柜
         */
        public TextView itemNum;
        /**
         * 当前余量
         */
        public TextView itemDesc;

        public ViewHolder(View view) {
            super(view);
            cardView = (CardView) view.findViewById(R.id.card_container);

            itemNumTitle = (TextView) view.findViewById(R.id.item_num_title);
            itemDescTitle = (TextView) view.findViewById(R.id.item_desc_title);


            itemNum = (TextView) view.findViewById(R.id.item_num_text);
            itemDesc = (TextView) view.findViewById(R.id.item_desc_text);
        }
    }
}
