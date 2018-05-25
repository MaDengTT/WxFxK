package com.xxm.mmd.component_recipe.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xxm.mmd.component_recipe.R;

import java.util.List;

/**
 * Created by MaDeng on 2018/5/25.
 */
public class ItemAdapter extends BaseQuickAdapter<String,BaseViewHolder> {
    public ItemAdapter(@Nullable List<String> data) {
        super(R.layout.recipe_item_item,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_item, item);
    }
}
