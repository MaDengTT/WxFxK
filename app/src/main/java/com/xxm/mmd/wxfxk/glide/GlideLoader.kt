package com.xxm.mmd.wxfxk.glide

import android.widget.ImageView
import com.xxm.mmd.common.image.GlideApp
import com.xxm.mmd.wxfxk.R
import java.util.*

/**
 * Created by MaDeng on 2018/4/19.
 */
object GlideLoader {

    private val AVATAR_PLACEHOLDER_IV: Int
        get() {
            val IV = R.drawable.abc_ic_menu_copy_mtrl_am_alpha
            return IV
        }

    private val AVATAR_ERROR_IV: Int
        get() {
            val IV = R.drawable.abc_ab_share_pack_mtrl_alpha
            return IV
        }

    fun loadAvatar(imageView: ImageView, objects: Objects) {
        GlideApp.with(imageView.context)
                .asBitmap()
                .placeholder(AVATAR_PLACEHOLDER_IV)
                .load(objects)
                .error(AVATAR_ERROR_IV)
                .into(imageView)
    }


    private val PLACEHOLDER_IV: Int
        get() {
            val IV = R.drawable.abc_action_bar_item_background_material
            return IV
        }
    private val ERROR_IV: Int
        get() {
            val IV = R.drawable.abc_btn_borderless_material
            return IV
        }

    fun loadNormal(imageView: ImageView, url: String) {
        if (url.endsWith(".gif")) {
            GlideApp.with(imageView.context)
                    .asGif()
                    .load(url)
                    .placeholder(PLACEHOLDER_IV)
                    .error(ERROR_IV)
                    .into(imageView)
        }else{
            GlideApp.with(imageView.context)
                    .asBitmap()
                    .load(url)
                    .placeholder(PLACEHOLDER_IV)
                    .error(ERROR_IV)
                    .into(imageView)
        }
    }
}