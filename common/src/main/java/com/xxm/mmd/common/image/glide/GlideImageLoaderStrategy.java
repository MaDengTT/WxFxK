package com.xxm.mmd.common.image.glide;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.xxm.mmd.common.image.BaseImageLoaderStrategy;

import com.xxm.mmd.common.image.ImageConfig;
import com.xxm.mmd.common.utils.Preconditions;

/**
 * Created by MaDeng on 2018/5/7.
 */
public class GlideImageLoaderStrategy implements BaseImageLoaderStrategy<ImageConfigImpl> {
    @Override
    public void loadImage(Context ctx, ImageConfigImpl config) {
        Preconditions.checkNotNull(ctx, "Context is required");
        Preconditions.checkNotNull(config, "ImageConfigImpl is required");
        if (TextUtils.isEmpty(config.getUrl())) throw new NullPointerException("Url is required");
        Preconditions.checkNotNull(config.getImageView(), "ImageView is required");

        GlideRequests requests;

        requests = GlideApp.with(ctx);

        GlideRequest<Drawable> glideRequest = requests.load(config.getUrl());

        switch (config.getCacheStrategy()) {//缓存策略
            case 0:
                glideRequest.diskCacheStrategy(DiskCacheStrategy.ALL);
                break;
            case 1:
                glideRequest.diskCacheStrategy(DiskCacheStrategy.NONE);
                break;
            case 2:
                glideRequest.diskCacheStrategy(DiskCacheStrategy.RESOURCE);
                break;
            case 3:
                glideRequest.diskCacheStrategy(DiskCacheStrategy.DATA);
                break;
            case 4:
                glideRequest.diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);
                break;
            default:
                glideRequest.diskCacheStrategy(DiskCacheStrategy.ALL);
                break;
        }

        if (config.isCrossFade()) {
            glideRequest.transition(DrawableTransitionOptions.withCrossFade());
        }

        if (config.isCenterCrop()) {
            glideRequest.centerCrop();
        }

        if (config.isCircle()) {
            glideRequest.circleCrop();
        }

        if (config.isImageRadius()) {
            glideRequest.transform(new RoundedCorners(config.getImageRadius()));
        }

        if (config.isBlurImage()) {
            glideRequest.transform(new BlurTransformation(config.getBlurValue()));
        }

        if (config.getTransformation() != null) {//glide用它来改变图形的形状
            glideRequest.transform(config.getTransformation());
        }

        if (config.getPlaceholder() != 0)//设置占位符
            glideRequest.placeholder(config.getPlaceholder());

        if (config.getErrorPic() != 0)//设置错误的图片
            glideRequest.error(config.getErrorPic());

        if (config.getFallback() != 0)//设置请求 url 为空图片
            glideRequest.fallback(config.getFallback());


        glideRequest
                .into(config.getImageView());
    }

    @Override
    public void clear(Context ctx, ImageConfigImpl config) {
        Preconditions.checkNotNull(ctx, "Context is required");
        Preconditions.checkNotNull(config, "ImageConfigImpl is required");

        if (config.getImageViews() != null && config.getImageViews().length > 0) {//取消在执行的任务并且释放资源
            for (ImageView imageView : config.getImageViews()) {
                GlideApp.get(ctx).getRequestManagerRetriever().get(ctx).clear(imageView);
            }
        }
    }
}
