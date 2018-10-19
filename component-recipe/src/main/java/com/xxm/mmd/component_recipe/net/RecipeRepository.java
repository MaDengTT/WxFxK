package com.xxm.mmd.component_recipe.net;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModelProvider;

import com.xxm.mmd.component_recipe.bean.RecipeBean;
import android.arch.lifecycle.ViewModelProvider;
import io.reactivex.functions.Consumer;

/**
 * Created by MaDeng on 2018/6/26.
 */
public class RecipeRepository {

    private RecipeNet api;

    public LiveData<RecipeBean> getRecipeData(int id) {
        MutableLiveData<RecipeBean> data = new MutableLiveData<>();

        api.getRecipeNet(id).subscribe(reipeBean -> {
            data.setValue(reipeBean);
        }, throwable -> {

        });
        return data;
    }
}
