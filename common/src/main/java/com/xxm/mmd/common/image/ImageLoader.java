package com.xxm.mmd.common.image;

import android.content.Context;

import javax.inject.Singleton;

/**
 * Created by MaDeng on 2018/5/7.
 */
@Singleton
public final class ImageLoader {

    BaseImageLoaderStrategy mStrategy;

    /**
     * 加载图片
     *
     * @param context
     * @param config
     * @param <T>
     */
    public <T extends ImageConfig> void loadImage(Context context, T config) {
        this.mStrategy.loadImage(context,config);
    }

    /**
     * 停止加载或清理缓存
     *
     * @param context
     * @param config
     * @param <T>
     */
    public <T extends ImageConfig> void clear(Context context, T config) {
        this.mStrategy.clear(context, config);
    }

    /**
     * 可在运行时随意切换 {@link BaseImageLoaderStrategy}
     *
     * @param strategy
     */
    public void setLoadImgStrategy(BaseImageLoaderStrategy strategy) {
        this.mStrategy = strategy;
    }

    public BaseImageLoaderStrategy getLoadImgStrategy() {
        return mStrategy;
    }
}
