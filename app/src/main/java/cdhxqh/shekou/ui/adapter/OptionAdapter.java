package cdhxqh.shekou.ui.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;


import java.util.ArrayList;

import cdhxqh.shekou.R;
import cdhxqh.shekou.model.Option;
import cdhxqh.shekou.ui.activity.OptionActivity;

/**
 * Created by think on 2016/5/16.
 */
public class OptionAdapter extends RecyclerView.Adapter<OptionAdapter.ViewHolder> {
    OptionActivity activity;
    ArrayList<Option> list=new ArrayList<Option>();
    public OptionAdapter(OptionActivity activity){
        this.activity = activity;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.itemNumTitle.setText("名称");
        holder.itemDescTitle.setText("描述");
        holder.itemNum.setText(list.get(position).getName());
        holder.itemDesc.setText(list.get(position).getDescription());
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

            itemNumTitle=(TextView) view.findViewById(R.id.item_num_title);
            itemDescTitle=(TextView) view.findViewById(R.id.item_desc_title);


            itemNum = (TextView) view.findViewById(R.id.item_num_text);
            itemDesc = (TextView) view.findViewById(R.id.item_desc_text);
        }
    }

    public void update(ArrayList<Option> data, boolean merge) {
        if (merge && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                Option workOrder = list.get(i);
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
        list = data;
        notifyDataSetChanged();
    }
    //
    public void adddate(ArrayList<Option> data){
        if(data.size()>0){
            for(int i = 0;i < data.size();i++){
                if(!list.contains(data.get(i))){
                    list.add(data.get(i));
                }
            }
        }
        notifyDataSetChanged();
    }
}
