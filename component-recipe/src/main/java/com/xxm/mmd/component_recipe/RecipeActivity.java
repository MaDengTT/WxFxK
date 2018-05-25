package com.xxm.mmd.component_recipe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import com.leochuan.CarouselLayoutManager;
import com.leochuan.CenterSnapHelper;
import com.xxm.mmd.common.utils.SizeUtils;
import com.xxm.mmd.component_recipe.adapter.ItemAdapter;
import com.xxm.mmd.component_recipe.adapter.RecipeAdapter;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class RecipeActivity extends AppCompatActivity {

    @BindView(R.id.rv_item)
    RecyclerView rvItem;
    @BindView(R.id.rl_top)
    LinearLayout rlTop;
    @BindView(R.id.rv_recipe)
    RecyclerView rvRecipe;
    private CarouselPopUpWindow window;
    String[] strings = new String[]{"one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_activity_recipe);
        ButterKnife.bind(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvItem.setLayoutManager(linearLayoutManager);
        rvItem.setAdapter(new ItemAdapter(Arrays.asList(strings)));
        Animation translateAnimation = AnimationUtils.loadAnimation(this, R.anim.recipe_translate);
        rlTop.startAnimation(translateAnimation);

        CarouselLayoutManager layoutManager = getLayoutManager();

        rvRecipe.setLayoutManager(layoutManager);
        rvRecipe.setAdapter(new RecipeAdapter(Arrays.asList(strings)));
        CenterSnapHelper centerSnapHelper = new CenterSnapHelper();
        centerSnapHelper.attachToRecyclerView(rvRecipe);
        window = new CarouselPopUpWindow(this, layoutManager, rvRecipe);
    }


    CarouselLayoutManager getLayoutManager() {
        CarouselLayoutManager.Builder builder = new CarouselLayoutManager.Builder(this, SizeUtils.dp2px(100, this))
                .setDistanceToBottom(345)   //间距
                .setMinScale(0.97f)  //自小缩放比
                .setMoveSpeed(1.0f);//滚动速度
        return new CarouselLayoutManager(builder);
    }

    @OnClick(R.id.but_serach)
    public void onViewClicked() {
        window.showAtLocation(rvRecipe, Gravity.CENTER,0,0);
    }
}
