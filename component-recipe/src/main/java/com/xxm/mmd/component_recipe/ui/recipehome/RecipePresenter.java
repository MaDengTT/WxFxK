package com.xxm.mmd.component_recipe.ui.recipehome;

import com.xxm.mmd.common.base.BaseBean;
import com.xxm.mmd.common.mvp.BasePresenter;
import com.xxm.mmd.component_recipe.bean.RecipeBean;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;

/**
 * Created by MaDeng on 2018/5/28.
 */
public class RecipePresenter extends BasePresenter<RecipeContrace.Model,RecipeContrace.View> implements RecipeContrace.Presenter {


    public RecipePresenter(RecipeContrace.Model model, RecipeContrace.View rootView) {
        super(model, rootView);
    }

    @Override
    public void onStart() {
        super.onStart();
        mModel.getRecipeData(0, mView.getPageSize(), mView.getPageNo())
                .doOnTerminate(new Action() {
                    @Override
                    public void run() throws Exception {
                        mView.hideLoading();
                    }
                })
                .subscribe(new Observer<BaseBean<List<RecipeBean>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mView.showLoading();
                    }

                    @Override
                    public void onNext(BaseBean<List<RecipeBean>> listBaseBean) {
                        if (listBaseBean.state()) {
                            mView.setDataToRecipeAdapter(listBaseBean.data);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showMessage(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void loadMoreData() {

    }
}
