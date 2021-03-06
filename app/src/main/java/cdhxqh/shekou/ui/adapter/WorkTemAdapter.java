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
import cdhxqh.shekou.model.WorkOrder;
import cdhxqh.shekou.model.WorkOrderTem;
import cdhxqh.shekou.ui.activity.Work_detailsActivity;


/**
 * Created by think on 2015/8/17.
 */
public class WorkTemAdapter extends RecyclerView.Adapter<WorkTemAdapter.ViewHolder> {
    Context mContext;
    List<WorkOrderTem> workOrderList = new ArrayList<>();

    /**
     * 点击事件*
     */
    public OnClickListener onClickListener;


    public WorkTemAdapter(Context context) {
        this.mContext = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final WorkOrderTem workOrder = workOrderList.get(position);
        holder.itemNumTitle.setText(mContext.getString(R.string.work_number));
        holder.itemDescTitle.setText(mContext.getString(R.string.work_describe));
        holder.itemNum.setText(workOrder.wonum);
        holder.itemDesc.setText(workOrder.description);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickListener.cOnClickListener(workOrder);
            }
        });
    }

    @Override
    public int getItemCount() {
        return workOrderList.size();
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

    public void update(ArrayList<WorkOrderTem> data, boolean merge) {
        if (merge && workOrderList.size() > 0) {
            for (int i = 0; i < workOrderList.size(); i++) {
                WorkOrderTem workOrder = workOrderList.get(i);
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
        workOrderList = data;
        notifyDataSetChanged();
    }

    //
    public void adddate(ArrayList<WorkOrderTem> data) {
        if (data.size() > 0) {
            for (int i = 0; i < data.size(); i++) {
                if (!workOrderList.contains(data.get(i))) {
                    workOrderList.add(data.get(i));
                }
            }
        }
        notifyDataSetChanged();
    }


    public interface OnClickListener {
        public void cOnClickListener(WorkOrderTem workOrderTem);
    }

    public OnClickListener getOnClickListener() {
        return onClickListener;
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }


}
