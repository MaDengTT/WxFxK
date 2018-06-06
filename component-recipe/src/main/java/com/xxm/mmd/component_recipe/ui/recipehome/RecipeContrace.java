package com.xxm.mmd.component_recipe.ui.recipehome;

import com.xxm.mmd.common.base.BaseBean;
import com.xxm.mmd.common.mvp.BasePresenter;
import com.xxm.mmd.common.mvp.IModel;
import com.xxm.mmd.common.mvp.IPresenter;
import com.xxm.mmd.common.mvp.IView;
import com.xxm.mmd.component_recipe.bean.RecipeBean;
import com.xxm.mmd.component_recipe.utils.AdapterLoadMoreHelper;

import java.util.List;
import java.util.zip.Inflater;

import io.reactivex.Observable;

/**
 * Created by MaDeng on 2018/5/26.
 */
public interface RecipeContrace {
    interface View extends IView {
        AdapterLoadMoreHelper getHelper();

        void setItemData(List<String> data);
    }

    interface Presenter  {
        void initData();
    }

    interface Model extends IModel {
        Observable<BaseBean<List<RecipeBean>>> getRecipeData(int id,int pageSize,int pageNo);
        Observable<BaseBean<List<String>>> getRecipeItem();
    }
}
