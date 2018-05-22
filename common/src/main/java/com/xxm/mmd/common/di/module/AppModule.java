package com.xxm.mmd.common.di.module;

import android.app.Application;
import android.content.Context;

import com.xxm.mmd.common.base.BaseApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by MaDeng on 2018/5/7.
 */
@Module
public class AppModule {
    Context context;

    @Provides
    @Singleton
    Context provideContext(Application application) {
        return application;
    }

}
