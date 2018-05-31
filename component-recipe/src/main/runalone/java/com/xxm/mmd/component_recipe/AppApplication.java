package com.xxm.mmd.component_recipe;

import com.xxm.mmd.common.base.BaseApplication;
import com.xxm.mmd.common.di.component.AppComponent;
import com.xxm.mmd.common.di.component.DaggerAppComponent;

/**
 * Created by MaDeng on 2018/5/31.
 */
public class AppApplication extends BaseApplication {

    private AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerAppComponent.builder().application(this).build();
    }

    @Override
    public AppComponent AppComponent() {
        return component;
    }
}
