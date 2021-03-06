package cdhxqh.shekou.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cdhxqh.shekou.R;
import cdhxqh.shekou.model.Wpitem;
import cdhxqh.shekou.ui.activity.WpitemDetailsActivity;


/**
 * Created by think on 2015/11/3
 * 物料.
 */
public class WpitemAdapter extends RecyclerView.Adapter<WpitemAdapter.ViewHolder> {
    Context mContext;
    List<Wpitem>wpitemList = new ArrayList<>();
    public WpitemAdapter(Context context) {
        this.mContext = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Wpitem wpitem = wpitemList.get(position);
        holder.itemNumTitle.setText(mContext.getString(R.string.work_plan_num));
        holder.itemDescTitle.setText(mContext.getString(R.string.work_plan_desc));
        holder.itemNum.setText(wpitem.itemnum);
//        holder.itemDesc.setText(workOrder.description);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, WpitemDetailsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable("wpitem", wpitem);
                intent.putExtras(bundle);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return wpitemList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        public RelativeLayout relativeLayout;
        /**
         * CardView*
         */
        public CardView cardView;
        /**
         * 物料编码*
         */
        public TextView itemNumTitle;
        /**
         * 物料名单*
         */
        public TextView itemDescTitle;
        /**
         * 编码*
         */
        public TextView itemNum;
        /**
         * 名单*
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

    public void update(ArrayList<Wpitem> data, boolean merge) {
        if (merge && wpitemList.size() > 0) {
            for (int i = 0; i < wpitemList.size(); i++) {
                Wpitem workOrder = wpitemList.get(i);
                boolean exist = false;
                for (int j = 0; j < data.size(); j++) {
                    if (data.get(j) == workOrder) {
                        exist = true;
                        break;
                    }
                }
                if (exist) continue;
                data.add(workOrder);
            }
        }
        wpitemList = data;
        notifyDataSetChanged();
    }
//
    public void adddate(ArrayList<Wpitem> data){
        if(data.size()>0){
            for(int i = 0;i < data.size();i++){
                wpitemList.add(data.get(i));
            }
        }
        notifyDataSetChanged();
    }
}
