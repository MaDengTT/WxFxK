package com.xxm.mmd.component_recipe.di.component;

import android.support.v7.widget.RecyclerView;

import com.xxm.mmd.common.di.component.AppComponent;
import com.xxm.mmd.common.di.scope.ActivityScope;
import com.xxm.mmd.component_recipe.adapter.RecipeAdapter;
import com.xxm.mmd.component_recipe.bean.RecipeBean;
import com.xxm.mmd.component_recipe.di.module.RecipeActivityModule;
import com.xxm.mmd.component_recipe.di.module.RecipeModule;
import com.xxm.mmd.component_recipe.ui.recipehome.RecipeActivity;
import com.xxm.mmd.component_recipe.ui.recipehome.RecipeContrace;
import com.xxm.mmd.component_recipe.ui.recipehome.RecipePresenter;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import dagger.Component;
import dagger.Provides;

/**
 * Created by MaDeng on 2018/5/31.
 */
@ActivityScope
@Component(modules = {RecipeModule.class, RecipeActivityModule.class},dependencies = AppComponent.class)
public interface RecipeComponent {

    void Inject(RecipeActivity activity);

    List<RecipeBean> getDefaultData();

    RecipeAdapter getRecipeAdapter();

    com.xxm.mmd.component_recipe.ui.recipehome.RecipeModule getRecipeModule();

    RecipePresenter getPresenter();

    @Named("recipe")
    RecyclerView.LayoutManager getViewPageLayoutManager();
}
