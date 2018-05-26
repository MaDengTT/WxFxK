package com.xxm.mmd.component_recipe.module;

import com.xxm.mmd.common.base.BaseBean;
import com.xxm.mmd.component_recipe.bean.RecipeBean;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

/**
 * Created by MaDeng on 2018/5/26.
 */
public class RecipeDataService {

    Observable<BaseBean<List<RecipeBean>>> getRecipeListData(int id) {
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

}
