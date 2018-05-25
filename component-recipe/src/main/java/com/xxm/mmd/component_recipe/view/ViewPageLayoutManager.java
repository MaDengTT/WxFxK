package com.xxm.mmd.component_recipe.view;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * Created by MaDeng on 2018/5/25.
 */
public class ViewPageLayoutManager extends RecyclerView.LayoutManager {
    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        super.onLayoutChildren(recycler, state);
        removeAllViews();
        detachAndScrapAttachedViews(recycler);

        int itemCount = getItemCount();



    }


}
