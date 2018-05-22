package com.xxm.mmd.common.base;

import android.app.Application;

import com.xxm.mmd.common.di.component.AppComponent;

/**
 * Created by MaDeng on 2018/5/22.
 */
public abstract class BaseApplication extends Application {
    protected abstract AppComponent AppComponent();
}
