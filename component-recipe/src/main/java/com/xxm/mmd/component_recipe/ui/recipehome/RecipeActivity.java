package com.xxm.mmd.component_recipe.ui.recipehome;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.loadmore.SimpleLoadMoreView;
import com.leochuan.CarouselLayoutManager;
import com.leochuan.CenterSnapHelper;
import com.leochuan.ScaleLayoutManager;
import com.xxm.mmd.common.base.BaseActivity;
import com.xxm.mmd.common.utils.SizeUtils;
import com.xxm.mmd.component_recipe.R;
import com.xxm.mmd.component_recipe.di.component.DaggerRecipeComponent;
import com.xxm.mmd.component_recipe.di.module.RecipeActivityModule;
import com.xxm.mmd.component_recipe.ui.recipedetails.RecipeDetailsActivity;
import com.xxm.mmd.component_recipe.utils.AdapterLoadMoreHelper;
import com.xxm.mmd.component_recipe.view.ScalePopUpWindow;
import com.xxm.mmd.component_recipe.adapter.ItemAdapter;
import com.xxm.mmd.component_recipe.adapter.RecipeAdapter;
import com.xxm.mmd.component_recipe.bean.RecipeBean;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class RecipeActivity extends BaseActivity implements RecipeContrace.View{

    @BindView(R.id.rv_item)
    RecyclerView rvItem;
    @BindView(R.id.rl_top)
    LinearLayout rlTop;
    @BindView(R.id.rv_recipe)
    RecyclerView rvRecipe;
    private ScalePopUpWindow window;



    @Inject
    public RecipeAdapter recipeAdapter;

    @Named("recipe")
    @Inject
    RecyclerView.LayoutManager scalayoutManager;

    @Inject
    RecipeModule module;

    @Inject
    public RecipePresenter presenter;

    public AdapterLoadMoreHelper<RecipeBean> helper;
    private ItemAdapter itemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_activity_recipe);
        ButterKnife.bind(this);

        DaggerRecipeComponent
                .builder()
                .recipeModule(new com.xxm.mmd.component_recipe.di.module.RecipeModule(this))
                .appComponent(getAppComponent())
                .recipeActivityModule(new RecipeActivityModule(this))
                .build().Inject(this);

        getLifecycle();


        initView();

        presenter.initData();
    }

    private void initView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvItem.setLayoutManager(linearLayoutManager);
        itemAdapter = new ItemAdapter(null);
        rvItem.setAdapter(itemAdapter);

        Animation translateAnimation = AnimationUtils.loadAnimation(this, R.anim.recipe_translate);
        rlTop.startAnimation(translateAnimation);

        rvRecipe.setLayoutManager(scalayoutManager);

        rvRecipe.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(RecipeActivity.this, RecipeDetailsActivity.class);
//                intent.putExtra("img",img)

                Pair<View, String> p1 = Pair.create(view.findViewById(R.id.iv_image), "img_view_1");

                ActivityOptionsCompat optionsCompat = ActivityOptionsCompat
                        .makeSceneTransitionAnimation(RecipeActivity.this, p1);

                startActivity(intent,optionsCompat.toBundle());
            }
        });
        helper = new AdapterLoadMoreHelper<>(recipeAdapter, rvRecipe);



        CenterSnapHelper centerSnapHelper = new CenterSnapHelper();
        centerSnapHelper.attachToRecyclerView(rvRecipe);
        window = new ScalePopUpWindow(this, (ScaleLayoutManager) scalayoutManager, rvRecipe);
    }

    @OnClick(R.id.but_serach)
    public void onViewClicked() {
        window.showAtLocation(rvRecipe, Gravity.CENTER,0,0);
    }

    @Override
    public void showLoading() {

    }
    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(@NonNull String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


    @Override
    public AdapterLoadMoreHelper getHelper() {
        return helper;
    }

    @Override
    public void setItemData(List<String> data) {
        itemAdapter.setNewData(data);
    }
}
