package com.xxm.mmd.component_recipe.ui.recipehome;

import com.xxm.mmd.common.base.BaseBean;
import com.xxm.mmd.common.mvp.BasePresenter;
import com.xxm.mmd.component_recipe.bean.RecipeBean;
import com.xxm.mmd.component_recipe.utils.AdapterLoadMoreHelper;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;

/**
 * Created by MaDeng on 2018/5/28.
 */
public class RecipePresenter extends BasePresenter<RecipeContrace.Model,RecipeContrace.View> implements RecipeContrace.Presenter {

    @Inject
    public RecipePresenter(RecipeContrace.Model model, RecipeContrace.View rootView) {
        super(model, rootView);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    private void getRecipeData() {
        mModel.getRecipeData(0, mView.getHelper().pageSize, mView.getHelper().pageNo)
                .doOnTerminate(() -> mView.hideLoading())
                .subscribe(new Observer<BaseBean<List<RecipeBean>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mView.showLoading();
                    }

                    @Override
                    public void onNext(BaseBean<List<RecipeBean>> listBaseBean) {
                        if (listBaseBean.state()) {
                            if (mView.getHelper().pageNo == 0) {
                                mView.getHelper().setNewDataToAdapter(listBaseBean.data);
                            }else {
                                mView.getHelper().addDataToAdapter(listBaseBean.data);
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (mView.getHelper().pageNo != 0) {
                            mView.getHelper().addDataToAdapter(null);
                        }
                        mView.showMessage(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void initData() {
        getRecipeData();
        mView.getHelper().setListener(pageNo -> getRecipeData());
    }
}
