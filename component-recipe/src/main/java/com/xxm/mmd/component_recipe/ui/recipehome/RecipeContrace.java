package com.xxm.mmd.component_recipe.ui.recipehome;

import com.xxm.mmd.common.mvp.BasePresenter;
import com.xxm.mmd.common.mvp.IModel;
import com.xxm.mmd.common.mvp.IPresenter;
import com.xxm.mmd.common.mvp.IView;
import com.xxm.mmd.component_recipe.bean.RecipeBean;

import java.util.List;

/**
 * Created by MaDeng on 2018/5/26.
 */
public interface RecipeContrace {
    interface View extends IView {
        void setDataToRecipeAdapter(List<RecipeBean> data);
        void addDataToRecipeAdapter(List<RecipeBean> data);
    }

    abstract class Presenter extends BasePresenter {
        abstract void loadMoreData(int pageSize,int pageNo);
    }

}
