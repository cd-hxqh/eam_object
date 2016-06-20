package cdhxqh.shekou.ui.adapter;

import android.animation.Animator;
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
import java.util.List;

import cdhxqh.shekou.R;
import cdhxqh.shekou.model.Inventory;
import cdhxqh.shekou.model.Wfassignment;
import cdhxqh.shekou.ui.activity.Wfassig_DetailsActivity;
import cdhxqh.shekou.ui.widget.BaseViewHolder;

/**
 * Created by apple on 15/10/26
 */
public class WfassigAdapter extends BaseQuickAdapter<Wfassignment> {
    public WfassigAdapter(Context context, int layoutResId, List data) {
        super(context, layoutResId, data);
    }

    @Override
    protected void startAnim(Animator anim, int index) {
        super.startAnim(anim, index);
        if (index < 5)
            anim.setStartDelay(index * 150);
    }

    @Override
    protected void convert(BaseViewHolder helper, Wfassignment item) {
        CardView cardView = helper.getView(R.id.card_container);
        helper.setText(R.id.item_num_title, mContext.getString(R.string.inventory_itemnum_text));
        helper.setText(R.id.item_desc_title, mContext.getString(R.string.inventory_location_text));
        helper.setText(R.id.item_num_text, item.wfassignmentid + "");
        helper.setText(R.id.item_desc_text, item.description);
    }
}
