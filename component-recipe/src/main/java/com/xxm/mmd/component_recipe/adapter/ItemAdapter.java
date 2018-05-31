package com.xxm.mmd.component_recipe.adapter;

import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xxm.mmd.component_recipe.R;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by MaDeng on 2018/5/25.
 */
public class ItemAdapter extends BaseQuickAdapter<String,BaseViewHolder> {
    @Inject
    public ItemAdapter(@Nullable List<String> data) {
        super(R.layout.recipe_item_item,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_item, item);
        if (helper.getAdapterPosition() == 3) {
            CardView view = helper.getView(R.id.card_item);
            view.setCardBackgroundColor(ContextCompat.getColor(mContext,R.color.recipe_e92d2e));
        }
    }
}
