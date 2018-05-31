package com.xxm.mmd.component_recipe.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xxm.mmd.component_recipe.R;
import com.xxm.mmd.component_recipe.bean.RecipeBean;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by MaDeng on 2018/5/25.
 */
public class RecipeAdapter extends BaseQuickAdapter<RecipeBean,BaseViewHolder> {

    @Inject
    public RecipeAdapter(@Nullable List<RecipeBean> data) {
        super(R.layout.recipe_item_big,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, RecipeBean item) {

    }
}
