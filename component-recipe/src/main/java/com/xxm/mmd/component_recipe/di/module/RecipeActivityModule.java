package com.xxm.mmd.component_recipe.di.module;

import com.xxm.mmd.common.di.scope.ActivityScope;
import com.xxm.mmd.component_recipe.ui.recipehome.RecipeContrace;
import com.xxm.mmd.component_recipe.ui.recipehome.RecipeModule;

import dagger.Module;
import dagger.Provides;

/**
 * Created by MaDeng on 2018/5/31.
 */
@Module
public class RecipeActivityModule {

    RecipeContrace.View view;

    public RecipeActivityModule(RecipeContrace.View view) {
        this.view = view;
    }

    @Provides
    @ActivityScope
    RecipeContrace.View provideView() {
        return view;
    }

    @Provides
    @ActivityScope
    RecipeContrace.Model provideModel() {
        return new RecipeModule();
    }
}
