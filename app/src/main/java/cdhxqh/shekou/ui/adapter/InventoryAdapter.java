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
import cdhxqh.shekou.model.Inventory;
import cdhxqh.shekou.model.Wfassignment;

/**
 * Created by apple on 15/10/26
 */
public class InventoryAdapter extends RecyclerView.Adapter<InventoryAdapter.ViewHolder> {

    private static final String TAG = "InventoryAdapter";
    Context mContext;
    ArrayList<Inventory> mItems = new ArrayList<Inventory>();

    public InventoryAdapter(Context context) {
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        final Inventory item = mItems.get(i);

        viewHolder.itemNumTitle.setText(mContext.getString(R.string.inventory_itemnum_text));
        viewHolder.itemDescTitle.setText(mContext.getString(R.string.inventory_location_text));
        viewHolder.itemNum.setText(item.itemnum);
        viewHolder.itemDesc.setText(item.location);

        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(mContext, Wfassig_DetailsActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putParcelable("wfassignment", item);
//                intent.putExtras(bundle);
//                mContext.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void update(ArrayList<Inventory> data, boolean merge) {
        if (merge && mItems.size() > 0) {
            for (int i = 0; i < mItems.size(); i++) {
                Log.i(TAG, "mItems=" + mItems.get(i).itemnum);
                Inventory obj = mItems.get(i);
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
         * ±‡∫≈*
         */
        public TextView itemNumTitle;
        /**
         * √Ë ˆ*
         */
        public TextView itemDescTitle;
        /**
         * ±‡∫≈*
         */
        public TextView itemNum;
        /**
         * √Ë ˆ*
         */
        public TextView itemDesc;

        public ViewHolder(View view) {
            super(view);
            cardView = (CardView) view.findViewById(R.id.card_container);

            itemNumTitle=(TextView) view.findViewById(R.id.item_num_title);
            itemDescTitle=(TextView) view.findViewById(R.id.item_desc_title);


            itemNum = (TextView) view.findViewById(R.id.item_num_text);
            itemDesc = (TextView) view.findViewById(R.id.item_desc_text);
        }
    }
}
