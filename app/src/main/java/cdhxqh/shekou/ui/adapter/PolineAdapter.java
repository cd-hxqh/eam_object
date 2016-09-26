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
import cdhxqh.shekou.model.PoLine;
import cdhxqh.shekou.ui.activity.PoLineDetailsActivity;

/**
 * Created by apple on 15/10/26
 * 采购单行
 */
public class PolineAdapter extends RecyclerView.Adapter<PolineAdapter.ViewHolder> {

    private static final String TAG = "PolineAdapter";
    Context mContext;
    ArrayList<PoLine> mItems = new ArrayList<PoLine>();

    public PolineAdapter(Context context) {
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        final PoLine item = mItems.get(i);

        viewHolder.itemNumTitle.setText(mContext.getString(R.string.polinenum_text));
        viewHolder.itemDescTitle.setText(mContext.getString(R.string.inventory_desc_name_text));
        viewHolder.itemNum.setText(item.getPOLINENUM());
        viewHolder.itemDesc.setText(item.getDESCRIPTION());

        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, PoLineDetailsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("poline",item);
                intent.putExtras(bundle);
                mContext.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void update(ArrayList<PoLine> data, boolean merge) {
        if (merge && mItems.size() > 0) {
            for (int i = 0; i < mItems.size(); i++) {
                Log.i(TAG, "mItems=" + mItems.get(i).getPOLINENUM());
                PoLine obj = mItems.get(i);
                boolean exist = false;
                for (int j = 0; j < data.size(); j++) {
                    if (data.get(j).getPOLINENUM() == obj.getPOLINENUM()) {
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
         * 货柜标题
         */
        public TextView itemNumTitle;
        /**
         * 当前余量
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
