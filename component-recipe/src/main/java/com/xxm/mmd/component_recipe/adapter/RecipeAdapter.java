package com.xxm.mmd.component_recipe.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xxm.mmd.component_recipe.R;

import java.util.List;

/**
 * Created by MaDeng on 2018/5/25.
 */
public class RecipeAdapter extends BaseQuickAdapter<String,BaseViewHolder> {


    public RecipeAdapter(@Nullable List<String> data) {
        super(R.layout.recipe_item_big,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

    }
}
