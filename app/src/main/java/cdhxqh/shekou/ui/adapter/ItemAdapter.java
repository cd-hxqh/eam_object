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
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import cdhxqh.shekou.R;
import cdhxqh.shekou.model.Invbalances;
import cdhxqh.shekou.model.Invuse;
import cdhxqh.shekou.model.Item;
import cdhxqh.shekou.model.Option;
import cdhxqh.shekou.ui.activity.InvuseDetailsActivity;
import cdhxqh.shekou.ui.activity.ItemChooseActivity;
import cdhxqh.shekou.ui.activity.OptionActivity;

/**
 * Created by apple on 15/10/26
 * 领料单适配器
 */
public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    ItemChooseActivity activity;
    ArrayList<Invbalances> list = new ArrayList<Invbalances>();

    public ItemAdapter(ItemChooseActivity activity) {
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.itemNumTitle.setText(activity.getString(R.string.wuliao_text));
        holder.itemDescTitle.setText(activity.getString(R.string.wfassig_desction_text));
        holder.itemNum.setText(list.get(position).itemnum);
        holder.itemDesc.setText(list.get(position).itemdescription);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.responseData(list.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        public RelativeLayout relativeLayout;
        /**
         * CardView*
         */
        public CardView cardView;
        /**
         * 编号名称*
         */
        public TextView itemNumTitle;
        /**
         * 描述名称*
         */
        public TextView itemDescTitle;
        /**
         * 编号*
         */
        public TextView itemNum;
        /**
         * 描述*
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

    public void update(ArrayList<Invbalances> data, boolean merge) {
        if (merge && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                Invbalances item = list.get(i);
                boolean exist = false;
                for (int j = 0; j < data.size(); j++) {
                    if (data.get(j) == item) {
                        exist = true;
                        break;
                    }
                }
                if (exist) continue;
                data.add(item);
            }
        }
        list = data;
        notifyDataSetChanged();
    }

    //
    public void adddate(ArrayList<Invbalances> data) {
        if (data.size() > 0) {
            for (int i = 0; i < data.size(); i++) {
                if (!list.contains(data.get(i))) {
                    list.add(data.get(i));
                }
            }
        }
        notifyDataSetChanged();
    }


    public void removeAllData() {
        if (list.size() > 0) {
            list.removeAll(list);
        }
    }
}
