package com.xxm.mmd.component_recipe.utils;

import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.loadmore.LoadMoreView;
import com.chad.library.adapter.base.loadmore.SimpleLoadMoreView;

import java.util.List;

/**
 * Created by MaDeng on 2018/6/5.
 */
public class AdapterLoadMoreHelper<T> {
    BaseQuickAdapter adapter;
    RecyclerView view;

    LoadMoreView moreView;

    public int pageSize = 15;
    public int pageNo = 0;

    OnLoadMoreListener listener;

    public  interface OnLoadMoreListener{
        void onLoadMoreRequested(int pageNo);
    }

    public AdapterLoadMoreHelper(BaseQuickAdapter adapter, RecyclerView view) {
        this.adapter = adapter;
        this.view = view;
        init();
    }
    public AdapterLoadMoreHelper(BaseQuickAdapter adapter, RecyclerView view,LoadMoreView moreView) {
        this.adapter = adapter;
        this.view = view;
        this.moreView = moreView;
        init();
    }

    public void setListener(OnLoadMoreListener listener) {
        this.listener = listener;
    }

    private void init() {
        adapter.setLoadMoreView(moreView == null?new SimpleLoadMoreView():moreView);
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                if (listener != null) {
                    listener.onLoadMoreRequested(pageNo);
                }
            }
        },view);
        view.setAdapter(adapter);
        pageNo = 0;
    }


    public void setNewDataToAdapter(List<T> data) {
        pageNo = 0;
        adapter.setNewData(data);

        if (data.size() == pageSize) {
            adapter.loadMoreComplete();
        }else {
            adapter.loadMoreEnd();
        }
        pageNo++;
    }

    public void addDataToAdapter(List<T> data) {
        if (data == null) {
            adapter.loadMoreFail();
        }else {
            adapter.addData(data);
            pageNo++;
            if (data.size() == pageSize) {
                adapter.loadMoreComplete();
            }else {
                adapter.loadMoreEnd();
            }
        }

    }
}
