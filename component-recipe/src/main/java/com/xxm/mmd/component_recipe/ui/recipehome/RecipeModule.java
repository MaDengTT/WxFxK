package com.xxm.mmd.component_recipe.ui.recipehome;

import com.xxm.mmd.common.base.BaseBean;
import com.xxm.mmd.component_recipe.bean.RecipeBean;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import io.reactivex.Observable;

/**
 * Created by MaDeng on 2018/5/28.
 */
public class RecipeModule implements RecipeContrace.Model {
    @Override
    public Observable<BaseBean<List<RecipeBean>>> getRecipeData(int id, int pageSize, int pageNo){
        List<RecipeBean> datas = new ArrayList<>();
        for (int i= 0;i<10;i++) {
            RecipeBean bean = new RecipeBean();
            bean.title = "这是Title" + i;
            bean.desc = "这是简介这是简介这是简介这是简介";
            datas.add(bean);
        }
        BaseBean<List<RecipeBean>> data = new BaseBean<>();
        data.data = datas;
        return Observable.just(data);
    }

    @Override
    public void onDestroy() {

    }

}
