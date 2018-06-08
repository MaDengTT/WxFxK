package com.xxm.mmd.common.di.module;

import android.content.Context;

import com.google.gson.Gson;
import com.xxm.mmd.common.net.RetrofitClient;
import com.xxm.mmd.common.utils.NetWorkUtils;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by MaDeng on 2018/5/7.
 */
@Module
public class ClientModule {

    @Provides
    public OkHttpClient provideOkHttpClient(final Context context) {
        File httpCacheDirectory = new File(context.getCacheDir().getAbsolutePath(), "Cache");

        Cache cache;
        cache = new Cache(httpCacheDirectory, 100 * 1024 * 1024);

        Interceptor mRewriteCacheControlInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                if (!NetWorkUtils.isNetConnected(context)) {
                    request = request.newBuilder()
                            .cacheControl(CacheControl.FORCE_CACHE)
                            .build();
                }
                Response originalResponse = chain.proceed(request);
                if (NetWorkUtils.isNetConnected(context)) {
                    //有网的时候读接口上的@Headers里的配置，你可以在这里进行统一的设置
                    String cacheControl = request.cacheControl().toString();
                    return originalResponse.newBuilder()
                            .header("Cache-Control", cacheControl)
                            .removeHeader("Pragma")
                            .build();
                } else {
                    return originalResponse.newBuilder()
                            .header("Cache-Control", "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 2)
                            .removeHeader("Pragma")
                            .build();
                }
            }
        };
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        Interceptor headerInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request build = chain.request().newBuilder()
                        .addHeader("Content-Type","application/json")
                        .build();
                return chain.proceed(build);
            }
        };

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(7676, TimeUnit.MILLISECONDS)
                .connectTimeout(7676,TimeUnit.MILLISECONDS)
                .addInterceptor(mRewriteCacheControlInterceptor)
                .addNetworkInterceptor(mRewriteCacheControlInterceptor)
                .addInterceptor(loggingInterceptor)
                .addInterceptor(headerInterceptor)
                .cache(cache)
                .build();
        return okHttpClient;
    }

    @Provides
    public Gson provideGson() {
        return new Gson();
    }

    @Provides
    public Retrofit provideRetrofit(OkHttpClient okHttpClient,Retrofit.Builder retrofitBuilder) {
        return retrofitBuilder.client(okHttpClient)
                .build();
    }


    @Provides
    Retrofit.Builder provideRetrofitBuilder(){
        return new Retrofit.Builder()
//                .baseUrl()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create());
    }


}
