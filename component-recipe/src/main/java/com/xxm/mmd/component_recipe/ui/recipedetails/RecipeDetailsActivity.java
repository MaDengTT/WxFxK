package com.xxm.mmd.component_recipe.ui.recipedetails;


import android.arch.lifecycle.ViewModelProvider;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.xxm.mmd.component_recipe.R;

import dagger.Provides;


public class RecipeDetailsActivity extends AppCompatActivity {

    private RecipeDetailsViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_activity_recipe_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.recipe_back);
        setSupportActionBar(toolbar);

//        ViewModelProviders.of(this)


        viewModel.getmRecipeBean(1).observe(this, recipeBean -> {

        });
    }
}
