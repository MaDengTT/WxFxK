package com.xxm.mmd.component_recipe.ui.recipedetails;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.xxm.mmd.component_recipe.bean.RecipeBean;
import com.xxm.mmd.component_recipe.net.RecipeRepository;

/**
 * Created by MaDeng on 2018/6/26.
 */
public class RecipeDetailsViewModel extends ViewModel {

    RecipeRepository webService;

    public RecipeDetailsViewModel(RecipeRepository webService) {
        this.webService = webService;
    }

    private LiveData<RecipeBean> mRecipeBean;
    public LiveData<RecipeBean> getmRecipeBean(int id) {
        if (mRecipeBean == null) {
            webService.getRecipeData(id);
        }
        return mRecipeBean;
    }
}
