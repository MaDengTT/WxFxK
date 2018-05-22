package com.xxm.mmd.common.di.component;

import android.app.Application;

import com.google.gson.Gson;
import com.xxm.mmd.common.di.module.AppModule;
import com.xxm.mmd.common.di.module.ClientModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Created by MaDeng on 2018/5/7.
 */
@Singleton
@Component(modules = {AppModule.class, ClientModule.class})
public interface AppComponent {

    Application application();

    OkHttpClient okHttpClient();
    Gson gson();

    Retrofit retrofit();

    @Component.Builder
    interface Builder{
        @BindsInstance
        Builder application(Application application);
        AppComponent build();
    }
}
