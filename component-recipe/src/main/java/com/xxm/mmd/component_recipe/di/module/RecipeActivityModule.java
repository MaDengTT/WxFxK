package com.xxm.mmd.component_recipe.di.module;

import com.xxm.mmd.common.di.scope.ActivityScope;
import com.xxm.mmd.component_recipe.net.RecipeNet;
import com.xxm.mmd.component_recipe.ui.recipehome.RecipeContrace;
import com.xxm.mmd.component_recipe.ui.recipehome.RecipeModule;
import com.xxm.mmd.component_recipe.utils.AdapterLoadMoreHelper;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.ElementsIntoSet;
import dagger.multibindings.IntoSet;

/**
 * Created by MaDeng on 2018/5/31.
 */
@Module
public class RecipeActivityModule {

    private RecipeContrace.View view;

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
    RecipeContrace.Model provideModel(RecipeNet net) {
        return new RecipeModule(net);
    }


}
