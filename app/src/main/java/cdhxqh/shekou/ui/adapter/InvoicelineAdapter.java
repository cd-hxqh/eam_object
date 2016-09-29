package cdhxqh.shekou.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import cdhxqh.shekou.R;
import cdhxqh.shekou.model.InvoiceLine;
import cdhxqh.shekou.ui.activity.InvoiceLineDetailsActivity;

/**
 * Created by apple on 15/10/26
 * 付款申请行
 */
public class InvoicelineAdapter extends RecyclerView.Adapter<InvoicelineAdapter.ViewHolder> {

    private static final String TAG = "InvoicelineAdapter";
    Context mContext;
    ArrayList<InvoiceLine> mItems = new ArrayList<InvoiceLine>();

    public InvoicelineAdapter(Context context) {
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        final InvoiceLine item = mItems.get(i);

        viewHolder.itemNumTitle.setText(R.string.line_text);
        viewHolder.itemDescTitle.setText(mContext.getString(R.string.udms_text));
        viewHolder.itemNum.setText(item.getINVOICELINENUM());
        viewHolder.itemDesc.setText(item.getPODES());

        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, InvoiceLineDetailsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("invoiceLine", item);
                intent.putExtras(bundle);
                mContext.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void update(ArrayList<InvoiceLine> data, boolean merge) {
        if (merge && mItems.size() > 0) {
            for (int i = 0; i < mItems.size(); i++) {
                InvoiceLine obj = mItems.get(i);
                boolean exist = false;
                for (int j = 0; j < data.size(); j++) {
                    if (data.get(j).getINVOICELINENUM() == obj.getINVOICELINENUM()) {
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
