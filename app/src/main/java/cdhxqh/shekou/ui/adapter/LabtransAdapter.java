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
import cdhxqh.shekou.model.Labtrans;
import cdhxqh.shekou.model.Wplabor;
import cdhxqh.shekou.ui.activity.LabtransDetailsActivity;
import cdhxqh.shekou.ui.activity.LabtransListActivity;
import cdhxqh.shekou.ui.activity.WplaborDetailsActivity;


/**
 * Created by think on 2015/11/3.
 * 实际员工
 */
public class LabtransAdapter extends RecyclerView.Adapter<LabtransAdapter.ViewHolder> {
    LabtransListActivity mContext;
    public List<Labtrans>labtransList = new ArrayList<>();
    public ArrayList<Labtrans> deleteList = new ArrayList<>();
    public LabtransAdapter(LabtransListActivity context) {
        this.mContext = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Labtrans labtrans = labtransList.get(position);
        holder.itemNumTitle.setText(mContext.getString(R.string.work_plan_worker));
        holder.itemDescTitle.setText(mContext.getString(R.string.work_plan_laborhrs));
        holder.itemNum.setText(labtrans.laborcode);
        holder.itemDesc.setText(labtrans.regularhrs);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, LabtransDetailsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("labtrans",labtrans);
                bundle.putSerializable("woactivityList", mContext.woactivityList);
                bundle.putSerializable("position", position);
                intent.putExtras(bundle);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return labtransList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        public RelativeLayout relativeLayout;
        /**
         * CardView*
         */
        public CardView cardView;
        /**
         * 员工*
         */
        public TextView itemNumTitle;
        /**
         * 常规时数*
         */
        public TextView itemDescTitle;
        /**
         * 员工内容*
         */
        public TextView itemNum;
        /**
         * 时数内容*
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

    public void update(ArrayList<Labtrans> data, boolean merge) {
        if (merge && labtransList.size() > 0) {
            for (int i = 0; i < labtransList.size(); i++) {
                Labtrans labtrans = labtransList.get(i);
                boolean exist = false;
                for (int j = 0; j < data.size(); j++) {
                    if (data.get(j) == labtrans) {
                        exist = true;
                        break;
                    }
                }
                if (exist) continue;
                data.add(labtrans);
            }
        }
        labtransList = data;
        notifyDataSetChanged();
    }
//
    public void adddate(ArrayList<Labtrans> data){
        if(data.size()>0){
            for(int i = 0;i < data.size();i++){
                labtransList.add(data.get(i));
            }
        }
        notifyDataSetChanged();
    }

    public void adddate(Labtrans labtrans) {
        labtransList.add(labtrans);
        notifyDataSetChanged();
    }

    public ArrayList<Labtrans> getList(){
        ArrayList<Labtrans> list = new ArrayList<>();
        if(labtransList.size()!=0) {
            list.addAll(labtransList);
        }
        if(deleteList.size()!=0) {
            list.addAll(deleteList);
        }
        return list;
    }
}
