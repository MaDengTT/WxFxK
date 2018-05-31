package com.xxm.mmd.component_recipe.di.module;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.leochuan.ScaleLayoutManager;
import com.xxm.mmd.common.di.scope.ActivityScope;
import com.xxm.mmd.common.utils.SizeUtils;
import com.xxm.mmd.component_recipe.bean.RecipeBean;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import dagger.Component;
import dagger.Module;
import dagger.Provides;

/**
 * Created by MaDeng on 2018/5/31.
 */
@Module
public class RecipeModule {

    Context context;

    public RecipeModule(Context context) {
        this.context = context;
    }

    @Provides
    @ActivityScope
    @Named("recipe")
    RecyclerView.LayoutManager provideViewPageLayoutManager() {
        ScaleLayoutManager.Builder builder = new ScaleLayoutManager.Builder(context, SizeUtils.dp2px(0,context));
        return new ScaleLayoutManager(builder);
    }

    @Provides
    @ActivityScope
    List<RecipeBean> provideDefaultData() {
        return new ArrayList<>();
    }
}
